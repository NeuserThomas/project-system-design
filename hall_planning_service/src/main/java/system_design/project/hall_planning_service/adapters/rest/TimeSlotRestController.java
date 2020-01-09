package system_design.project.hall_planning_service.adapters.rest;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import system_design.project.hall_planning_service.domain.Movie;
import system_design.project.hall_planning_service.domain.TimeSlot;
import system_design.project.hall_planning_service.persistence.MovieRepository;
import system_design.project.hall_planning_service.persistence.TimeSlotRepository;

/**
 * @author robin
 *
 */
@RequestMapping("timeslot")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TimeSlotRestController {

	@Autowired
	private TimeSlotRepository timeRepo;
	@Autowired
	private MovieRepository movieRepo;

	final Logger logger = LoggerFactory.getLogger(TimeSlotRestController.class);

	@GetMapping
	public @ResponseBody ResponseEntity<List<TimeSlot>> getTimeSlots() {
		List<TimeSlot> ts = timeRepo.findAll();
		return new ResponseEntity<List<TimeSlot>>(ts, HttpStatus.OK);
	}

	@GetMapping("/getByDate/{date}")
	public @ResponseBody ResponseEntity<List<TimeSlot>> getCinemaById(@PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		logger.info(date.toString());
		List<TimeSlot> list = timeRepo.findbyDate(date);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<TimeSlot>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<TimeSlot>>(HttpStatus.NOT_FOUND);
		}
	}
	 
	@GetMapping("/getByCinemaId/{cinemaId}/{date}")
	public @ResponseBody ResponseEntity<List<TimeSlot>> getByCinemaId(@PathVariable long cinemaId,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		logger.info("Getting timeslots for date: "+ date);
		logger.info("Getting timeslots for date:"+ date.atStartOfDay(ZoneId.systemDefault()));
		logger.info("Getting timeslots for date:"+ date.atStartOfDay(ZoneId.of("Europe/Brussels")));

		List<TimeSlot> list = timeRepo.findForCinemaAndDate(cinemaId,date);
		//TODO cache this
		HashMap<String,String> movies = new HashMap<>();
		/**
		 * Thomas wanted the movie title, but for me it's duplicate data.
		 */
		for(int i = 0;i<list.size();i++) {
			String movId = list.get(i).getMovieId();
			if(!movies.containsKey(movId)) {
				Optional<Movie> m = movieRepo.findById(movId);
				if(m.isPresent()) {
					movies.put(movId, m.get().getTitle());
				} else {
					movies.put(movId, "ERROR: UNKNOWN MOVIE ID");
				}
			}
			list.get(i).setMovieTitle(movies.get(movId));
		}
		if (!list.isEmpty()) {
			return new ResponseEntity<List<TimeSlot>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<TimeSlot>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getByCinemaName/{cinemaName}/{date}")
	public @ResponseBody ResponseEntity<List<TimeSlot>> getByCinemaName(@PathVariable String cinemaName,@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		List<TimeSlot> list = timeRepo.findForCinemaAndDate(cinemaName,date);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<TimeSlot>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<TimeSlot>>(HttpStatus.NOT_FOUND);
		}
	}
}
