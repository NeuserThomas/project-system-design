package system_design.project.publicity_service.adapters.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import system_design.project.publicity_service.domain.AdMovie;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.persistence.AdMovieRepository;

@RestController
@RequestMapping("/publicity")
public class PublicityRestController {
	private final AdMovieRepository adMovieRepository;
	
	@Autowired
	public PublicityRestController(AdMovieRepository adMovieRepository) {
		this.adMovieRepository = adMovieRepository;
	}
	
	@GetMapping("/{category}")
	public AdMovie getAdMovieByCategoryForToday(@PathVariable("category") String category) {
		//there is no need for the user to send the LocalDate (now) in Rest, because the controller knows that Localtime too.
		List<AdMovie> adMovies = this.adMovieRepository.findAdMovieByCategoryAndDate(Category.valueOf(category),LocalDate.now());
		AdMovie res = adMovies.get(0);
		for(AdMovie adMovie : adMovies) {//get most recent of selected adMovies
			if(adMovie.getCommissioningDate().compareTo(res.getCommissioningDate()) > 1) {
				//adMovie is more recent than res
				res = adMovie;
			}
		}
		return res;
	}
	
	@GetMapping("/maxDuration")
	public int getMaxDurationInSeconds() {
		//this seems a bit strange, but it's also strange to get a final value from an object in a database instead of from the class itself.
		//maybe it was a better option to create an event to say that for the next adMovies the maximal duration is changed.
		return AdMovie.maximalMinutes*60;
	}
}
