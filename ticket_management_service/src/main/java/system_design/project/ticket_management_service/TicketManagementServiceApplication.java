package system_design.project.ticket_management_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import java.util.TimeZone;
import javax.annotation.PostConstruct;

import system_design.project.ticket_management_service.adapters.messaging.Channels;
import system_design.project.ticket_management_service.domain.Ticket;
import system_design.project.ticket_management_service.persistence.ScreeningRepository;
import system_design.project.ticket_management_service.persistence.TicketRepository;

@SpringBootApplication
@EnableBinding(Channels.class)
public class TicketManagementServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(Ticket.class);
	@Autowired
	private ScreeningRepository screeningRepository;

	@Autowired
	private TicketRepository ticketRepository;

	public static void main(String[] args) {
		SpringApplication.run(TicketManagementServiceApplication.class, args);
	}

	@PostConstruct
	public void init() {
		// Setting Spring Boot SetTimeZone
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Bean
	public CommandLineRunner removeUnpaidTickets(TicketRepository ticketRepo) {
		return args -> {
			ticketRepo.findAllUnpaidTickets().forEach(ticket -> {
				logger.info(ticket.toString());
				ticketRepo.delete(ticket);
			});
		};
	}
}
