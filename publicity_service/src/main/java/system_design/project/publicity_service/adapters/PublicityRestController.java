package system_design.project.publicity_service.adapters;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import system_design.project.publicity_service.domain.Ad_Movie;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.persistence.Ad_MovieRepository;

@RestController
@RequestMapping("/publicity")
public class PublicityRestController {
	private final Ad_MovieRepository ad_MovieRepository;
	
	@Autowired
	public PublicityRestController(Ad_MovieRepository ad_MovieRepository) {
		this.ad_MovieRepository = ad_MovieRepository;
	}
	
	@GetMapping("/{category}")
	public Ad_Movie getAd_MovieByCategory(@PathVariable("category") String category) {
		List<Ad_Movie> ad_Movies = this.ad_MovieRepository.findAd_MovieByCategory(Category.valueOf(category));
		Ad_Movie res = ad_Movies.get(0);
		for(Ad_Movie ad_Movie : ad_Movies) {
			if(ad_Movie.getCreationDate().compareTo(res.getCreationDate()) > 1) {
				//ad_Movie is more recent than res
				res = ad_Movie;
			}
		}
		return res;
	}
}
