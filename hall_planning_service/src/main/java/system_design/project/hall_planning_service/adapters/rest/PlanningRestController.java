package system_design.project.hall_planning_service.adapters.rest;

import java.time.LocalDate;
import java.util.List;

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

import system_design.project.hall_planning_service.domain.Day;
import system_design.project.hall_planning_service.persistence.PlanningRepository;

@RestController
@RequestMapping("planning")
public class PlanningRestController {
	
	@Autowired
	private PlanningRepository planRepo;
	
	final Logger logger = LoggerFactory.getLogger(PlanningRestController.class);

	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Day>> getCinemas() {
		return new ResponseEntity<List<Day>>(planRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{date}")
	public @ResponseBody ResponseEntity<List<Day>> getCinemasAfterDate(@PathVariable LocalDate date) {
		List<Day> days = planRepo.findDaysAfterDate(date);
		if(!days.isEmpty()) {
			return new ResponseEntity<List<Day>>(days,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Day>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<Day> postDay(@RequestBody Day day) {
		planRepo.save(day);
		logger.info("Call: postDay");
		return new ResponseEntity<Day>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/cinema/{cinemaId}")
	public @ResponseBody ResponseEntity<List<Day>> getDaysForCinema(@PathVariable long cinemaId) {
		List<Day> days = planRepo.findDaysForCinema(cinemaId);
		if(!days.isEmpty()) {
			return new ResponseEntity<List<Day>>(days,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Day>>(HttpStatus.NOT_FOUND);
		}
	}
}
