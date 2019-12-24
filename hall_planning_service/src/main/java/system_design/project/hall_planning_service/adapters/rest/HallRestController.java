package system_design.project.hall_planning_service.adapters.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import system_design.project.hall_planning_service.domain.MovieHall;
import system_design.project.hall_planning_service.persistence.HallRepository;

@RestController
@RequestMapping("hall")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HallRestController {

	@Autowired
	private HallRepository hallRepo;

	final Logger logger = LoggerFactory.getLogger(HallRestController.class);
	
	/*
	@RequestMapping(path="/{cinema}/{date}",produces="application/json",method=RequestMethod.GET)
	public @ResponseBody Day getPlanningForDay(@PathVariable LocalDate date, @PathVariable Cinema cine) {
		return planRepo.findDayByDateAndCinema(date,cine);
	}*/
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<MovieHall>> getAllHalls() {
		logger.info("Call: GetAllHalls");
		return new ResponseEntity<List<MovieHall>>(hallRepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<MovieHall> postHall(@RequestBody MovieHall hall) {
		hallRepo.save(hall);
		logger.info("Call: postHall");
		return new ResponseEntity<MovieHall>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{hallId}")
	public @ResponseBody ResponseEntity<MovieHall> getHallById(@PathVariable long hallId) {
		MovieHall hall = hallRepo.findByHallId(hallId);
		logger.info("Call: getHallById");
		if(hall!=null) {
			return new ResponseEntity<MovieHall>(hall,HttpStatus.OK);
		} else {
			return new ResponseEntity<MovieHall>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/cinema/{cinemaId}")
	public @ResponseBody ResponseEntity<List<MovieHall>> getHallsByCinemaId(@PathVariable long cinemaId) {
		List<MovieHall> halls = hallRepo.findAllByCinemaId(cinemaId);
		logger.info("Call: getHallsByCinemaId");
		if(!halls.isEmpty()) {
			return new ResponseEntity<List<MovieHall>>(halls,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<MovieHall>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/{cinemaId}/{hallNumber}")
	public @ResponseBody ResponseEntity<MovieHall> getHall(@PathVariable long cinemaId,@PathVariable int hallNumber) {
		List<MovieHall> hall = hallRepo.findAllByCinemaIdAndHallNumber(cinemaId,hallNumber);
		logger.info("Call: getHall");
		if(!hall.isEmpty()) {
			return new ResponseEntity<MovieHall>(hall.get(0),HttpStatus.OK);
		} else {
			return new ResponseEntity<MovieHall>(HttpStatus.NOT_FOUND);
		}
	}
	

	
}
