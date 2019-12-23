package system_design.project.ticket_management_service.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import system_design.project.ticket_management_service.domain.Movie;
import system_design.project.ticket_management_service.domain.Ticket;
import system_design.project.ticket_management_service.persistence.MovieRepository;
import system_design.project.ticket_management_service.persistence.TicketRepository;

@RestController
@RequestMapping("ticket")
public class TicketRestController {

    private TicketRepository ticketRepo;
    private MovieRepository movieRepo;

    @Autowired
    public TicketRestController(TicketRepository ticketRepo, MovieRepository movieRepo){
        this.ticketRepo = ticketRepo;
        this.movieRepo = movieRepo;
    }

    @GetMapping
    public Iterable<Ticket> getAllTickets(){
        return ticketRepo.findAll();
    }

    //this call can be used for validateTicket: check if ticket is in db en is sold, check endTime etc
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable("id") Long id){
        return ticketRepo.findById(id).orElse(null);
    }
    
    @GetMapping("/movies")
    public Iterable<Movie> getMovies(){
    	return movieRepo.findAll();
    }
    
    @RequestMapping(value="/validateParkingTicket/{ticketId}", method=RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Ticket> validateTicket(@PathVariable("ticketId") long ticketId){
    	Ticket t = ticketRepo.findById(ticketId).get();
    	
    	if(t != null) {
    		if(t.getParkingValidated() == false) {
    			t.setParkingValidated(true);
    			ticketRepo.save(t);
    			return new ResponseEntity<Ticket>(t, HttpStatus.OK);
    		}
    		else {
    			return new ResponseEntity<Ticket>(t, HttpStatus.INTERNAL_SERVER_ERROR);
    		}
    	}
    	else {
    		return new ResponseEntity<Ticket>(t, HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
    }
    

}
