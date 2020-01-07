package system_design.project.hall_planning_service.adapters.rest;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import system_design.project.hall_planning_service.domain.Day;
import system_design.project.hall_planning_service.domain.Movie;
import system_design.project.hall_planning_service.domain.TimeSlot;
import system_design.project.hall_planning_service.persistence.DayRepository;
import system_design.project.hall_planning_service.service.PlanningService;

@RestController
@RequestMapping("planning")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PlanningRestController {
	
	@Autowired
	private DayRepository planRepo;
	@Autowired
	private PlanningService planService;
	
	final Logger logger = LoggerFactory.getLogger(PlanningRestController.class);

	@GetMapping
	public @ResponseBody ResponseEntity<List<Day>> getPlanning() {
		return new ResponseEntity<List<Day>>(planRepo.findAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{date}")
	public @ResponseBody ResponseEntity<List<Day>> getCinemasAfterDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
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
	
	@GetMapping(path="/dummydata")
	public ResponseEntity<Day> dummyDay() {
		LocalDate date = LocalDate.now();
		planService.planDay(date);
		planRepo.findDaysAfterDate(date.minusDays(1));
		logger.info("Call: dummyDay");
		return new ResponseEntity<Day>(HttpStatus.OK);
	}
	
	@GetMapping(path="/planDays")
	public ResponseEntity<Day> planDays() {
		LocalDate date = LocalDate.now().plusDays(7);
		planService.planDays(LocalDate.now(), LocalDate.now().plusDays(7));
		logger.info("Call: planDays");
		return new ResponseEntity<Day>(HttpStatus.OK);
	}
	
	@GetMapping("/cinema/{cinemaId}")
	public @ResponseBody ResponseEntity<List<Day>> getDaysForCinema(@PathVariable long cinemaId) {
		List<Day> days = planRepo.findDaysForCinema(cinemaId,LocalDate.now());
		if(!days.isEmpty()) {
			return new ResponseEntity<List<Day>>(days,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Day>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/plannedMovies/{cinemaId}/{date}")
	public @ResponseBody ResponseEntity<List<Movie>> getMoviesForCinema(@PathVariable long cinemaId,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		logger.info("getMoviesForCinema call");
		List<Movie> movies = planService.findPlannedMoviesForCinema(cinemaId,date);
		if(!movies.isEmpty()) {
			return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
		}
	}
	/*
	@GetMapping("/plannedMovies/{cinemaId}/{date}/{movieId}")
	public @ResponseBody ResponseEntity<List<TimeSlot>> getTimeslotForMovies(@PathVariable long cinemaId,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,@PathVariable String movieId) {
		List<TimeSlot> movies = planService.findPlannedMoviesForCinema(cinemaId, date, movieId);
		if(!movies.isEmpty()) {
			return new ResponseEntity<List<TimeSlot>>(movies,HttpStatus.OK);
		} else {
			return new ResponseEntity<List<TimeSlot>>(HttpStatus.NOT_FOUND);
		}
	}*/
	
	@GetMapping("/days/clear")
	/*
	 * Testing purposes. TODO: remove on production
	 */
	public @ResponseBody ResponseEntity<String> clear() {
		planRepo.deleteAll();
		return new ResponseEntity<String>("Done",HttpStatus.OK);
	}
}
