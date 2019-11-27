package system_design.project.hall_planning_service.service;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.Day;
import system_design.project.hall_planning_service.persistence.CinemaRepository;
import system_design.project.hall_planning_service.persistence.PlanningRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service("planningService")
@EnableScheduling
public class PlanningService {
	
	@Autowired
	public PlanningRepository planRepo;
	@Autowired
	public CinemaRepository cinemaRepo;
	@Autowired
	private KafkaTemplate<String, String> simpleProducer;

	
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
			if(days.isEmpty()) {
				//TODO
				updated=true;
				throw new NotYetImplementedException("Need to have movies to calculate planning.");
			} //else nothing has to be done, since it's planned already
			if(updated) {
				publish("Updated schedule!");
			}
		}
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
		simpleProducer.send("PlanningEvent:", message);
	}
	
}
