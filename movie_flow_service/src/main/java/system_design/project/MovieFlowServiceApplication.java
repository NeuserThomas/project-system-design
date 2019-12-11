package system_design.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import system_design.project.adapters.messaging.channels.PlanningChannels;



@SpringBootApplication
@EnableBinding(PlanningChannels.class)
public class MovieFlowServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieFlowServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner sayHello() {
		
		return (args)->{
			final Logger logger = LoggerFactory.getLogger(MovieFlowServiceApplication.class);
			System.out.println("sayHello....");
			logger.info("logger says hello...");
			RestTemplate restTemplate  = new RestTemplate();
			String planningUrl = "http://localhost:2223/planning";
			//ResponseEntity<String> response = restTemplate.getForEntity(planningUrl, String.class);
			
			//logger.info("response: " + response);
			
		};
	}
	
	

}
