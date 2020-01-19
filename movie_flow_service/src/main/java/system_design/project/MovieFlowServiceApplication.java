package system_design.project;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import system_design.project.adapters.messaging.channels.PlanningChannels;
import system_design.project.domain.TimeSlot;
import system_design.project.persistence.TimeSlotRepository;
import system_design.project.service.MovieFlowService;



@SpringBootApplication
@EnableBinding(PlanningChannels.class)
public class MovieFlowServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieFlowServiceApplication.class, args);
	}
	
	/**
	 * Bean used to re add timers on crash
	 * @param tRepo
	 * @param mService
	 * @return
	 */
	@Bean
	CommandLineRunner sayHello(TimeSlotRepository tRepo, MovieFlowService mService) {
		
		return (args)->{
			final Logger logger = LoggerFactory.getLogger(MovieFlowServiceApplication.class);
			List<TimeSlot> dates = tRepo.findafterDate(LocalDate.now());
			TimeSlot[] slots = new TimeSlot[dates.size()];
			slots = dates.toArray(slots);
			mService.addEvents(slots);
			logger.info("Added messages! amount: "+slots.length);
		};
	}
	
	

}
