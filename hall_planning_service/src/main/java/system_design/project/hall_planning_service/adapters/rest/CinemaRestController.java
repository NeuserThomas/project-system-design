package system_design.project.hall_planning_service.adapters.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.Movie;
import system_design.project.hall_planning_service.domain.MovieHall;
import system_design.project.hall_planning_service.domain.PlannedMovies;
import system_design.project.hall_planning_service.domain.Seat;
import system_design.project.hall_planning_service.persistence.CinemaRepository;
import system_design.project.hall_planning_service.persistence.MovieRepository;

@RequestMapping("cinema")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CinemaRestController {

	@Autowired
	private CinemaRepository cinemaRepo;
	
	@Autowired
	private MovieRepository movieRepo;
	
	final Logger logger = LoggerFactory.getLogger(CinemaRestController.class);
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Cinema>> getCinemas() {
		List<Cinema> cinemas = cinemaRepo.findAll();
		return new ResponseEntity<List<Cinema>>(cinemas,HttpStatus.OK);
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
	
	@GetMapping(path="/dummydata")
	public ResponseEntity<Cinema> postDummyData() {
		if(cinemaRepo.count()<1) {
			Cinema c = new Cinema();
			c.setCinemaName("Cinema TIWI");
			List<MovieHall> halls=new ArrayList<MovieHall>();
			for(int i = 0;i<10;i++) {
				MovieHall hall = new MovieHall();
				hall.setHall_number(i+1);
				List<Seat> seats = new ArrayList<Seat>();
				for(int j = 0;j<100;j++) {
					Seat seat = new Seat();
					seat.setRow(j/10);
					seat.setColumn((j%10)+1);
					seat.setMovieHall(hall);
					seats.add(seat);
				}
				hall.setSeats(seats);
				hall.setCinema(c);
				halls.add(hall);
			}
			c.setHalls(halls);
			cinemaRepo.save(c);
			logger.info("Dummy cinema loaded");
			return new ResponseEntity<Cinema>(c,HttpStatus.OK);
		} else {
			return new ResponseEntity<Cinema>(HttpStatus.NOT_MODIFIED);
		}
		
	}
	
	@GetMapping("/plannedMovies/{cinemaId}")
	public @ResponseBody ResponseEntity<List<Movie>> getPlannedMovies(@PathVariable long cinemaId) {
		Optional<Cinema> cinema = cinemaRepo.findById(cinemaId);
		if(cinema.isPresent()) {
			if(cinema.get().getPlannedMovies()!=null) {
				List<ObjectId> ids= cinema.get().getPlannedMovies().getMovieIds();
				List<Movie> movies = movieRepo.findMoviesWithId(ids);
				return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
			} else {
				PlannedMovies pm = new PlannedMovies();
				pm.setCinema(cinema.get());
				cinema.get().setPlannedMovies(pm);
				List<Movie> movies = new ArrayList<Movie>();
				return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
			}
		} else {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "Cinema with id "+cinemaId+" not found!");
		}
	}
	
	@PostMapping(path = "/plannedMovies/{cinemaId}",consumes="application/json")
	public @ResponseBody ResponseEntity<List<Movie>> planMovie(@PathVariable long cinemaId, @RequestBody Movie m) {
		Optional<Cinema> cinema = cinemaRepo.findById(cinemaId);
		if(cinema.isPresent()) {
			if(cinema.get().getPlannedMovies()==null) {
				cinema.get().setPlannedMovies(new PlannedMovies());
			}
			cinema.get().getPlannedMovies().addPlannedMovie(m);

			return new ResponseEntity<List<Movie>>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<List<Movie>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/unplanMovie/{cinemaId}",consumes="application/json")
	public @ResponseBody ResponseEntity<List<Movie>> unPlanMovie(@PathVariable long cinemaId, @RequestBody Movie m) {
		Optional<Cinema> cinema = cinemaRepo.findById(cinemaId);
		if(cinema.isPresent()) {
			if(cinema.get().getPlannedMovies()==null) {
				cinema.get().setPlannedMovies(new PlannedMovies());
			} else {
				cinema.get().getPlannedMovies().removePlannedMovie(m);
			}
			return new ResponseEntity<List<Movie>>(HttpStatus.OK);
		} else {
			throw new ResponseStatusException(
			           HttpStatus.NOT_FOUND, "The movie was never planned!");		
			}
	}
	
	
	
}
