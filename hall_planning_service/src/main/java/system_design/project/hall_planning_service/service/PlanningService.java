package system_design.project.hall_planning_service.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import system_design.project.hall_planning_service.adapters.messaging.MessageGateway;
import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.Day;
import system_design.project.hall_planning_service.domain.MovieHall;
import system_design.project.hall_planning_service.domain.TimeSlot;
import system_design.project.hall_planning_service.persistence.CinemaRepository;
import system_design.project.hall_planning_service.persistence.PlanningRepository;


@Service("planningService")
@EnableScheduling
public class PlanningService {
	
	@Autowired
	public PlanningRepository planRepo;
	@Autowired
	public CinemaRepository cinemaRepo;
	//@Autowired
	//private KafkaTemplate<String, String> simpleProducer;
	
	final Logger logger = LoggerFactory.getLogger(PlanningService.class);
	
	private final MessageGateway gateway;

	@Autowired
	public PlanningService(MessageGateway gateway) {
		this.gateway = gateway;
	}
	
	/**
	 * Will run every day, at 8 in the morning.
	 */
	@Scheduled(cron="0 0 8 * * *")
	public void planWeek() {
		LocalDate date = LocalDate.now();
		LocalDate week= date.plusDays(7);
		planDays(date,week);
	}
	
	/**
	 * Will calculate the schedule for every cinema, for a certain date.
	 * @param date
	 */
	public void planDay(LocalDate date) {
		List<Cinema> cinemas = cinemaRepo.findAll();
		boolean updated=false;
		for(Cinema c: cinemas) {
			List<Day> days = planRepo.findDaysOnDateForCinema(date, c.getId());
			if(days==null) {
				days=new ArrayList<Day>();
			}
			if(days.isEmpty()) {
				//TODO: make dynamic
				dummyImplementation(c);
				updated=true;
			} //else nothing has to be done, since it's planned already
			if(updated) {
				publish("Updated schedule!");
			}
		}
	}
	
	private void dummyImplementation(Cinema c) {
		Day day = new Day();
		day.setCinema(c);
		day.setDate(LocalDate.now());
		LocalTime startTime= LocalTime.of(20, 0);
		LocalTime stopTime= LocalTime.of(22, 30);
		HashMap<Integer,ArrayList<TimeSlot>> timeSlots = new HashMap<Integer,ArrayList<TimeSlot>>();
		//Creation of time schedule
		for(MovieHall h:c.getHalls()) {
			TimeSlot t = new TimeSlot();
			t.setStartTime(startTime);
			t.setStopTime(stopTime);
			if(!timeSlots.containsKey(h.getHall_number())) {
				timeSlots.put(h.getHall_number(), new ArrayList<TimeSlot>());
			}
			timeSlots.get(h.getHall_number()).add(t);
			
		}
		day.setTimeSlots(timeSlots);
		planRepo.save(day);
	}
	
	/**
	 * Will remove all days on certain date, for all cinemas
	 * @param date
	 */
	public void clearDay(LocalDate date) {
		List<Cinema> cinemas = cinemaRepo.findAll();
		boolean updated=false;
		for(Cinema c: cinemas) {
			List<Day> days = planRepo.findDaysOnDateForCinema(date, c.getId());
			for(Day d: days) {
				planRepo.delete(d);
				updated=true;
			}
		}
		if(updated) {
			publish("Removed parts from schedule!");
		}
	}
	
	public void planDays(LocalDate start,LocalDate stop) {
		for(LocalDate i = start;i.isBefore(stop);i.plusDays(1)) {
			planDay(i);
		}
	}
	
	private void publish(String message) {
		logger.info("--- Sending kafka message: "+message+" ---");
		gateway.updateDay(message);
		
		//simpleProducer.send("planningMade",message);
	}
}
