package system_design.project.ticket_management_service.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import system_design.project.ticket_management_service.domain.Ticket;


public interface TicketRepository extends CrudRepository<Ticket, Long> {
	
	@Query("select t from Ticket t where t.paid=0")
	public List<Ticket> findAllUnpaidTickets();

}
