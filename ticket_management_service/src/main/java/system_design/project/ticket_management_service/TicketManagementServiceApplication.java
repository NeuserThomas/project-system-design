package system_design.project.ticket_management_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import system_design.project.ticket_management_service.domain.Movie;
import system_design.project.ticket_management_service.domain.MovieSchedule;
import system_design.project.ticket_management_service.domain.Ticket;
import system_design.project.ticket_management_service.persistence.MovieRepository;
import system_design.project.ticket_management_service.persistence.TicketRepository;


@SpringBootApplication
public class TicketManagementServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(Ticket.class);

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementServiceApplication.class, args);
	}

	public static MovieSchedule getMovieSchedule(){
		RestTemplate rest = new RestTemplate();
		String baseURL = "http://localhost:portNumber/api";
		return rest.getForObject(baseURL, MovieSchedule.class);
	}

	@Bean
	public CommandLineRunner populateDatabase(TicketRepository ticketRepo, MovieRepository movieRepo){

		return args ->{
			Ticket t1 = new Ticket(7.0,76534);
			Ticket t2 = new Ticket(7.5,76543);

			ticketRepo.save(t1);
			ticketRepo.save(t2);
			
			Movie m1 = new Movie("F&F", 1);
			Movie m2 = new Movie("F&F2", 2);
			
			movieRepo.save(m1);
			movieRepo.save(m2);
		};

	}

	@Bean
	public CommandLineRunner getAllTickets(TicketRepository ticketRepo, MovieRepository movieRepo){
		return args -> {
			ticketRepo.findAll().forEach(ticket -> {logger.info(ticket.toString());});
			logger.info("---------------------------------------------------------------------------------------");
			movieRepo.findAll().forEach(movie -> {logger.info(movie.toString());});
		};
	}

	//@Bean
	//public CommandLineRunner testRest(){
	//	return (args) -> {
	//		logger.info(getMovieSchedule().toString());
	//	};
	//}

}
