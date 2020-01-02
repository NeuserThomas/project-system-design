package system_design.project.publicity_service.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import system_design.project.publicity_service.domain.AMovie;
import system_design.project.publicity_service.domain.Ad_Movie;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.persistence.Ad_MovieRepository;
import system_design.project.publicity_service.persistence.AdvertisementRepository;
import system_design.project.publicity_service.persistence.TrailerRepository;

@Service("publicityService")
@EnableScheduling
public class PublicityService {
	//minimal amount of minutes the duration of an ad_movie has to be 
	static final int minimalMinutes = 25;
	
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
		List<AMovie> playlist = new ArrayList<AMovie>();
		List<AMovie> choice = new ArrayList<AMovie>();
		choice.addAll(adRepo.findAdvertisementByCategory(category));
		choice.addAll(trailerRepo.findTrailerByCategory(category));
		choice.addAll(adRepo.findAdvertisementByCategory(Category.All));
		choice.addAll(trailerRepo.findTrailerByCategory(Category.All));
		
		Duration duration = Duration.ZERO;
		while(duration.toMinutes() < minimalMinutes && !choice.isEmpty()) {
			AMovie a = getNewAMovie(choice);
			playlist.add(a);
			duration = duration.plus(a.getDuration());
		}
		LocalDate now = LocalDate.now();
		Ad_Movie ad_Movie = new Ad_Movie(1, duration, category, "Ad_Movie"+category.name()+now.toString(), playlist, now);
		logger.info("Save " + ad_Movie.toString());
		ad_MovieRepo.save(ad_Movie);
	}
	
	private AMovie getNewAMovie(List<AMovie> choice) {
		Random r = new Random();
		return choice.remove(r.nextInt(choice.size()));
	}
}
