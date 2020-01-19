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
import system_design.project.publicity_service.domain.AdMovie;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.persistence.AdMovieRepository;
import system_design.project.publicity_service.persistence.AdvertisementRepository;
import system_design.project.publicity_service.persistence.TrailerRepository;

@Service("publicityService")
@EnableScheduling
public class PublicityService {
	
	@Autowired
	public AdMovieRepository adMovieRepo;
	@Autowired
	public AdvertisementRepository adRepo;
	@Autowired
	public TrailerRepository trailerRepo;
	
	private static Logger logger = LoggerFactory.getLogger(PublicityService.class);
	
	private LocalDate yetToPlan = LocalDate.MIN;//changes by event from hallPlanning
	
	//is set to yetToPlan when the variable maximumDuration of AdMovie is changed
	//adMovies that are created with the new maximumDuration, can only be played and planned after this day
	//the days before were already planned before the change of maximumDuration
	private LocalDate minimumCommissioningDate = LocalDate.MIN;
	
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
		while(duration.toMinutes() < AdMovie.getMinimalDuration() && !choice.isEmpty()) {
			//choose new AMovie and removes it from the choice
			AMovie a = getNewAMovie(choice);
			
			//check if duration is not going to exceed the maximum value
			//toMinutes is no rounded value, but is the minutes part
			if(duration.toSeconds() + a.getDuration().toSeconds() < AdMovie.getMaximalDuration()*60) {
				playlist.add(a);
				duration = duration.plus(a.getDuration());
			}
		}
		
		//
		//if there was no recent change (minComDate before now), the adMovie can be played from the moment it exists,
		//if there was a recent change, the adMovie can only be played from the day
		//that was not yet planned (by hallPlanning) at the time of the change.
		// if a is more recent than b, a.compareTo(b) is greater than 1 
		LocalDate commissioningDate = LocalDate.now().compareTo(minimumCommissioningDate) > 1 ? LocalDate.now() : minimumCommissioningDate;
		AdMovie newAdMovie = new AdMovie(duration, category, "AdMovie"+category.name()+LocalDate.now().toString(), playlist, commissioningDate);
		logger.info("Save " + newAdMovie.toString());
		adMovieRepo.save(newAdMovie);
	}
	
	private AMovie getNewAMovie(List<AMovie> choice) {
		Random r = new Random();
		return choice.remove(r.nextInt(choice.size()));
	}
	
	public void setYetToPlan(LocalDate yetToPlan) {
		//HallPlanning planned a day
		this.yetToPlan = yetToPlan;
	}

	public void setMinimumCommissioningDate() {
		//maximumDuration (AdMovie) changed
		this.generateAllCategories();
		minimumCommissioningDate = yetToPlan;
	}
}
