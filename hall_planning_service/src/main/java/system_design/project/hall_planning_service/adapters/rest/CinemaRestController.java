package system_design.project.hall_planning_service.adapters.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.persistence.CinemaRepository;

@RequestMapping("cinema")
@RestController
public class CinemaRestController {

	@Autowired
	private CinemaRepository cinemaRepo;
	
	final Logger logger = LoggerFactory.getLogger(CinemaRestController.class);
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Cinema>> getCinemas() {
		List<Cinema> cinemas = cinemaRepo.findAll();
		if(!cinemas.isEmpty()) {
			return new ResponseEntity<List<Cinema>>(cinemas,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Cinema>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{cinemaId}")
	public @ResponseBody ResponseEntity<Cinema> getCinemaById(@PathVariable long cinemaId) {
		Optional<Cinema> cinema = cinemaRepo.findById(cinemaId);
		if(cinema.isPresent()) {
			return new ResponseEntity<Cinema>(cinema.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<Cinema>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<Cinema> postCinema(@RequestBody Cinema cinema) {
		cinemaRepo.save(cinema);
		logger.info("Call: postCinema");
		return new ResponseEntity<Cinema>(HttpStatus.ACCEPTED);
	}
	
}
