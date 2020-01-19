package parking_service.adapters;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import parking_service.domain.Parking;
import parking_service.domain.ParkingTicket;
import parking_service.persistence.ParkingRepository;
import parking_service.persistence.ParkingTicketRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("parking")
public class ParkingRestController {

	private ParkingRepository parkingRepo;
	private ParkingTicketRepository parkingTicketRepo;

	@Autowired
	private Environment env;

	final Logger logger = LoggerFactory.getLogger(ParkingRestController.class);

	@Autowired
	public ParkingRestController(ParkingRepository parkingRepo, ParkingTicketRepository parkingTicketRepo) {
		this.parkingRepo = parkingRepo;
		this.parkingTicketRepo = parkingTicketRepo;
	}

	@GetMapping
	public Iterable<Parking> getParkings() {
		return parkingRepo.findAll();
	}

	@GetMapping(value = "/numberOfFreeSpots")
	public int getNumberOfFreeSpots() {
		return parkingRepo.findAll().iterator().next().getNumberOfFreeSpots();
	}

	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public Iterable<ParkingTicket> getParkingTickets() {
		return parkingTicketRepo.findAll();
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/exitParking/{parkingTicketId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity exitParking(@PathVariable("parkingTicketId") long parkingTicketId) {

		try {
			ParkingTicket pt = parkingTicketRepo.findById(parkingTicketId).get();
			Parking p = parkingRepo.findAll().iterator().next();

			if (pt.isValidated()) {
				parkingTicketRepo.delete(pt);
				p.freeSpot();
				parkingRepo.save(p);
				return new ResponseEntity<ParkingTicket>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<String>("Parkingticket not validated", HttpStatus.BAD_REQUEST);
			}
		} catch (NoSuchElementException ne) {
			return new ResponseEntity<String>("Parkingticket doesnt exist", HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/validateTicket/{parkingTicketId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity validateParkingTicket(@PathVariable("parkingTicketId") long parkingTicketId,
			@RequestParam(value = "ticketId") long ticketId) {
		try {
			ParkingTicket pt = parkingTicketRepo.findById(parkingTicketId).get();
			RestTemplate rt = new RestTemplate();
			RequestEntity<ParkingTicket> re = new RequestEntity<ParkingTicket>(HttpMethod.PUT,
					URI.create("http://" + env.getProperty("host.name") + ":" + env.getProperty("host.port")
							+ "/ticket/ticket/validateParkingTicket/" + ticketId));

			if (pt.isValidated() == false) {

				try {
					ResponseEntity<ParkingTicket> resp = rt.exchange(re, ParkingTicket.class);

					if (resp.getStatusCode() == HttpStatus.OK) {
						pt.setValidated(true);
						pt.setValidationTime(LocalDateTime.now());
						parkingTicketRepo.save(pt);
						return new ResponseEntity<ParkingTicket>(pt, HttpStatus.OK);
					} else {
						return resp;
					}
				} catch (HttpServerErrorException e) {
					return new ResponseEntity<String>("Ticket has already been used to validate or doesn't exist",
							HttpStatus.BAD_REQUEST);
				}
			} else {
				return new ResponseEntity<String>("Parkingticket already validated", HttpStatus.BAD_REQUEST);
			}

		} catch (NoSuchElementException ne) {
			return new ResponseEntity<String>("Parkingticket doesnt exist", HttpStatus.BAD_REQUEST);
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getTicket", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity getParkingTicket() {

		Parking p = parkingRepo.findAll().iterator().next();

		if (p.getNumberOfFreeSpots() > 0) {

			ParkingTicket pt = new ParkingTicket();
			p.takeSpot();
			parkingRepo.save(p);
			parkingTicketRepo.save(pt);
			logger.info("new ticket added");

			return new ResponseEntity<ParkingTicket>(pt, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Parking is fully occupied", HttpStatus.BAD_REQUEST);
		}
	}

}
