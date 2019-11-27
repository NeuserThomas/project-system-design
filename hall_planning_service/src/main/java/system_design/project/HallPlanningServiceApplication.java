package system_design.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import system_design.project.hall_planning_service.domain.Day;
import system_design.project.hall_planning_service.persistence.HallRepository;
import system_design.project.hall_planning_service.persistence.PlanningRepository;

@SpringBootApplication
public class HallPlanningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HallPlanningServiceApplication.class, args);
	}

	
	@Bean
	CommandLineRunner testRepository(HallRepository h, PlanningRepository p) {
		
		return (args)->{
			final Logger logger = LoggerFactory.getLogger(Day.class);
			
			
			logger.info("We load");
			
			

		};
	}
}
