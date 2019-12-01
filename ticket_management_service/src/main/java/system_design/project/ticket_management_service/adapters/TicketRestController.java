package system_design.project.ticket_management_service.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system_design.project.ticket_management_service.domain.Ticket;
import system_design.project.ticket_management_service.persistence.TicketRepository;

@RestController
@RequestMapping("ticket")
public class TicketRestController {

    private TicketRepository repo;

    @Autowired
    public TicketRestController(TicketRepository repo){
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Ticket> getAllTickets(){
        return repo.findAll();
    }

    //this call can be used for validateTicket: check if ticket is in db en is sold, check endTime etc
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable("id") Long id){
        return repo.findById(id).orElse(null);
    }

}
