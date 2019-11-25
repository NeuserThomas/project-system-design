package system_design.project.ticket_management_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import system_design.project.ticket_management_service.domain.MovieSchedule;
import system_design.project.ticket_management_service.domain.Ticket;
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
	public CommandLineRunner populateDatabase(TicketRepository repo){

		return (args)->{
			Ticket t1 = new Ticket(7.0, "Fast and the Furious 9");
			Ticket t2 = new Ticket(7.5, "The conjuring");

			repo.save(t1);
			repo.save(t2);
		};

	}

	@Bean
	public CommandLineRunner getAllTickets(TicketRepository repo){
		return (args) -> {
			repo.findAll().forEach(ticket -> {logger.info(ticket.toString());});
		};
	}

	@Bean
	public CommandLineRunner testRest(){
		return (args) -> {
			logger.info(getMovieSchedule().toString());
		};
	}

}
