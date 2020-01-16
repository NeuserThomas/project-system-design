package system_design.project.publicity_service.adapters.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import system_design.project.publicity_service.domain.AdMovie;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.persistence.AdMovieRepository;
import system_design.project.publicity_service.service.PublicityService;

@RestController
@RequestMapping("publicity")
public class PublicityRestController {
	private final AdMovieRepository adMovieRepository;
	private final PublicityService publicityService;
	@Autowired
	public PublicityRestController(AdMovieRepository adMovieRepository, PublicityService publicityService) {
		this.adMovieRepository = adMovieRepository;
		this.publicityService = publicityService;
	}
	
	@GetMapping("/{category}")
	public @ResponseBody ResponseEntity<AdMovie> getAdMovieByCategoryForToday(@PathVariable("category") String category) {
		//there is no need for the user to send the LocalDate (now) in Rest, because the controller knows that LocalDate too.
		List<AdMovie> adMovies = this.adMovieRepository.findAdMovieByCategoryAndDate(Category.valueOf(category),LocalDate.now());
		AdMovie res = adMovies.get(0);
		for(AdMovie adMovie : adMovies) {//get most recent of selected adMovies
			if(adMovie.getCommissioningDate().compareTo(res.getCommissioningDate()) > 1) {
				//adMovie is more recent than res
				res = adMovie;
			}
		}
		return new ResponseEntity<AdMovie>(res, HttpStatus.OK);
	}
	
	@GetMapping("/maxDuration")
	public @ResponseBody ResponseEntity<Integer> getMaxDurationInSeconds() {
		//this seems a bit strange, but it's also strange to get a final value from an object in a database instead of from the class itself.
		//maybe it was a better option to create an event to say that for the next adMovies the maximal duration is changed.
		return new ResponseEntity<Integer>(AdMovie.getMaximalDuration()*60, HttpStatus.OK);
	}
	
	@PostMapping("/maxDuration/{minutes}")
	public @ResponseBody ResponseEntity<Integer> setMaximalDuration(@PathVariable("minutes") int minutes) {
		//change the business logic of how long the adMovie can be
		AdMovie.setMaximalDuration(minutes);
		publicityService.setMinimumCommissioningDate();
		return new ResponseEntity<Integer>(minutes, HttpStatus.OK);
	}
	
	@PostMapping("/delay/{minutes}")
	public @ResponseBody ResponseEntity<Integer> setDelay(@PathVariable("minutes") int minutes) {
		AdMovie.setDelay(minutes);
		return new ResponseEntity<Integer>(minutes, HttpStatus.OK);
	}
}
