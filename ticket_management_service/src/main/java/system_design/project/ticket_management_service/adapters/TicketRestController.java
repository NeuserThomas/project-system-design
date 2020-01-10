package system_design.project.ticket_management_service.adapters;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import system_design.project.ticket_management_service.adapters.payment.PaymentAdapter;
import system_design.project.ticket_management_service.domain.Screening;
import system_design.project.ticket_management_service.domain.Ticket;
import system_design.project.ticket_management_service.persistence.ScreeningRepository;
import system_design.project.ticket_management_service.persistence.TicketRepository;
import system_design.project.ticket_management_service.services.PaymentService;

@SuppressWarnings({ "rawtypes" })
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("ticket")
public class TicketRestController {

    private TicketRepository ticketRepo;
    private ScreeningRepository screeningRepo;
    private Logger logger = LoggerFactory.getLogger(Ticket.class);
    private PaymentAdapter paymentAdapter = new PaymentAdapter();
    
    @Autowired
    private PaymentService paymentService;

    @Autowired
    public TicketRestController(TicketRepository ticketRepo, ScreeningRepository screeningRepo){
        this.ticketRepo = ticketRepo;
        this.screeningRepo = screeningRepo;
    }

    @GetMapping
    public Iterable<Ticket> getAllTickets(){
        return ticketRepo.findAll();
    }
    
    @GetMapping(value="/screenings/{date}")
    public Iterable<Screening> getScreeningsByDate(@PathVariable("date") String date){
    	LocalDate ld = LocalDate.parse(date);

    	return screeningRepo.findbyDate(ld);
    }
    
    
    @GetMapping(value="/buyTicket")
    public DeferredResult<ResponseEntity> sellTicket(@RequestParam(value = "screeningId") String screeningId) throws InterruptedException{
    	DeferredResult<ResponseEntity> output = new DeferredResult<>();
    	try {
    		
    		Screening screening = screeningRepo.findById(Long.valueOf(screeningId)).get();
    		
    		if(screening.getSoldTickets() < screening.getAvailableSeats()) {
    			Ticket t = new Ticket(Long.valueOf(screeningId));
    			
    			ForkJoinPool.commonPool().submit(() -> {
        			try {
        				if (paymentService.TryAndSell(t).get()) {
        					screening.sellTicket();
        					ticketRepo.save(t);
        	    			screeningRepo.save(screening);
        					output.setResult(new ResponseEntity<Ticket>(t,HttpStatus.OK));
        				}
        			} catch (Exception e) {
        				e.printStackTrace();
        			}
        			output.setResult(new ResponseEntity<String>(HttpStatus.CONFLICT));
        		});
    		}
    		else {
    			output.setResult(new ResponseEntity<String>("No more tickets available", HttpStatus.BAD_REQUEST));
    		}
    	}
    	catch(NoSuchElementException ne) {
    		output.setResult(new ResponseEntity<String>("Screening with that ID doesnt exist!", HttpStatus.BAD_REQUEST));
    	}
    	return output;
    	
    }

    //this call can be used for validateTicket: check if ticket is in db en is sold, check endTime etc
    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable("id") Long id){
        return ticketRepo.findById(id).orElse(null);
    }
    
    @GetMapping("/movies")
    public Iterable<Screening> getMovies(){
    	return screeningRepo.findAll();
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
