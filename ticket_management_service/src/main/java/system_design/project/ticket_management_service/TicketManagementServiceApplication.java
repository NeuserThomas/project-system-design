package system_design.project.ticket_management_service;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import system_design.project.ticket_management_service.adapters.messaging.Channels;
import system_design.project.ticket_management_service.domain.Screening;
import system_design.project.ticket_management_service.domain.ScreeningProxy;
import system_design.project.ticket_management_service.domain.Ticket;
import system_design.project.ticket_management_service.persistence.ScreeningRepository;
import system_design.project.ticket_management_service.persistence.TicketRepository;


@SpringBootApplication
@EnableBinding(Channels.class)
public class TicketManagementServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(Ticket.class);
	@Autowired
	private ScreeningRepository sp;

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateDatabase(TicketRepository ticketRepo, ScreeningRepository movieRepo){

		return args ->{
//			Ticket t1 = new Ticket(76534);
//			Ticket t2 = new Ticket(76543);
//
//			ticketRepo.save(t1);
//			ticketRepo.save(t2);
			
			Screening m1 = new Screening("F&F", 1, 100);
			Screening m2 = new Screening("F&F2", 2, 150);
			
			movieRepo.save(m1);
			movieRepo.save(m2);
		};

	}

	@Bean
	public CommandLineRunner getAllTickets(TicketRepository ticketRepo, ScreeningRepository movieRepo){
		return args -> {
			ticketRepo.findAll().forEach(ticket -> {logger.info(ticket.toString());});
			logger.info("---------------------------------------------------------------------------------------");
			movieRepo.findAll().forEach(movie -> {logger.info(movie.toString());});
		};
	}
	
	@Bean
	public CommandLineRunner run(){
		RestTemplate restTemplate = new RestTemplate();
		
		return args -> {
			ScreeningProxy[] screeningProxies = restTemplate.getForObject("http://localhost:2223/timeslot/getByDate/2020-01-10", ScreeningProxy[].class);
			for(int i=0; i<screeningProxies.length; i++) {
				logger.info("SCRPROXY ----" + screeningProxies[i].toString());
			}
		};
	}


}
