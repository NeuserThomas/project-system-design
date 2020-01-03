package system_design.project.publicity_service.adapters;

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
	public AdMovie getAdMovieByCategory(@PathVariable("category") String category) {
		List<AdMovie> adMovies = this.adMovieRepository.findAdMovieByCategory(Category.valueOf(category));
		AdMovie res = adMovies.get(0);
		for(AdMovie adMovie : adMovies) {
			if(adMovie.getCreationDate().compareTo(res.getCreationDate()) > 1) {
				//adMovie is more recent than res
				res = adMovie;
			}
		}
		return res;
	}
	
	@GetMapping("/maxDuration")
	public int getMaxDurationInSeconds() {
		//this seems a bit strange, but it's also strange to get a final value from an object in a database instead of from the class itself
		return AdMovie.maximalMinutes*60;
	}
}
