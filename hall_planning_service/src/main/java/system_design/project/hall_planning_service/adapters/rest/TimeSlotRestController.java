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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import system_design.project.hall_planning_service.domain.TimeSlot;
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
		List<TimeSlot> list = timeRepo.findForCinemaAndDate(cinemaId,date);
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
