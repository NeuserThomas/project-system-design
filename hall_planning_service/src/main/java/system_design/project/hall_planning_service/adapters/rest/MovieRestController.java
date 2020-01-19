package system_design.project.hall_planning_service.adapters.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.google.gson.Gson;

import system_design.project.hall_planning_service.adapters.movieInfo.IMovieInfoAPI;
import system_design.project.hall_planning_service.domain.Movie;
import system_design.project.hall_planning_service.persistence.MovieRepository;

@RequestMapping("movie")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MovieRestController {

	@Autowired
	@Qualifier("movieAPIService")
	IMovieInfoAPI api;
	
	Gson gson = new Gson();
	
	@Autowired
	private MovieRepository movieRepo;

	final Logger logger = LoggerFactory.getLogger(MovieRestController.class);
	
	@GetMapping
	public @ResponseBody ResponseEntity<List<Movie>> getMovies() {
		List<Movie> movies = movieRepo.findAll();
		return new ResponseEntity<List<Movie>>(movies,HttpStatus.OK);
	}
	
	@GetMapping("/{movieId}")
	public @ResponseBody ResponseEntity<Movie> getMovieById(@PathVariable String movieId) {
		Optional<Movie> movie = movieRepo.findById(movieId);
		if(movie.isPresent()) {
			return new ResponseEntity<Movie>(movie.get(),HttpStatus.OK);
		} else {
			return new ResponseEntity<Movie>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/dummydata")
	public @ResponseBody ResponseEntity<Movie> dummyData() {
		Movie m = api.FindMovieByName("Avenger");
		Movie m2=api.FindMovieByName("Star Wars");
		movieRepo.save(m);
		movieRepo.save(m2);
		return new ResponseEntity<Movie>(HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public ResponseEntity<Movie> postMovie(@RequestBody Movie movie) {
		movieRepo.save(movie);
		logger.info("Call: postMovie");
		return new ResponseEntity<Movie>(HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getMovieByName/{movieName}")
	public @ResponseBody ResponseEntity<Movie> findNewMovieByName(@PathVariable String movieName) {
		Movie m = api.FindMovieByName(movieName);
		if(m==null) {
			return new ResponseEntity<Movie>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Movie>(m,HttpStatus.OK);
		}	
	}	
}
