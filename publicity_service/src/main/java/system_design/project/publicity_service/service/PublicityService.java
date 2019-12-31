package system_design.project.publicity_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import system_design.project.PublicityServiceApplication;
import system_design.project.publicity_service.domain.Ad_Movie;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.persistence.Ad_MovieRepository;
import system_design.project.publicity_service.persistence.AdvertisementRepository;
import system_design.project.publicity_service.persistence.TrailerRepository;

@Service("publicityService")
@EnableScheduling
public class PublicityService {
	@Autowired
	public Ad_MovieRepository ad_MovieRepo;
	@Autowired
	public AdvertisementRepository adRepo;
	@Autowired
	public TrailerRepository trailerRepo;
	
	private static Logger logger = LoggerFactory.getLogger(PublicityService.class);
	
	/**
	 * Will run every week on sunday, at midnight.
	 */
	@Scheduled(cron = "0 0 0 * * SUN")
	public void generateAllCategories() {
		for(Category category : Category.values()) {
			generateByCategory(category);
		}
	}
	
	private void generateByCategory(Category category) {
		Ad_Movie ad_Movie = new Ad_Movie();
	}
}
