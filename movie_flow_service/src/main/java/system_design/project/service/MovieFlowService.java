package system_design.project.service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import system_design.project.domain.TimeSlot;
import system_design.project.persistence.TimeSlotRepository;


@Service("MovieFlowService")
@EnableScheduling
public class MovieFlowService {
	final Logger logger = LoggerFactory.getLogger(MovieFlowService.class);
	
	private Timer timer=new Timer();
	
	@Autowired
	private TimeSlotRepository timeRepo;
	
	public MovieFlowService() {
		logger.info("MovieFlowService started");
	}
		
	
	public void addEvents(TimeSlot[] timeslots) {
		for(TimeSlot t: timeslots) {
			Date d = java.sql.Timestamp.valueOf(t.getStartTime());
			timer.schedule(new EventLogger("Starting movie: "+t.getMovieId()+" in hall: "+t.getHall().getHallId()), d);
			d = java.sql.Timestamp.valueOf(t.getStopTime());
			timer.schedule(new EventLogger("Stopping movie: "+t.getMovieId()+" in hall: "+t.getHall().getHallId()), d);
			timeRepo.save(t);
		}
	}
	
	class EventLogger extends TimerTask {
		String message = "";
		EventLogger(String message){
			this.message= message;
		}
		public void run() {
			logger.info(message);
			timer.cancel(); // Terminate the timer thread
		}
	}
		
	
	
		
}
