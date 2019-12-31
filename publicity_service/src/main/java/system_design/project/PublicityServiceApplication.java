package system_design.project;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import system_design.project.publicity_service.domain.AMovie;
import system_design.project.publicity_service.domain.Ad_Movie;
import system_design.project.publicity_service.domain.Advertisement;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.domain.Trailer;
import system_design.project.publicity_service.persistence.TrailerRepository;
import system_design.project.publicity_service.persistence.Ad_MovieRepository;
import system_design.project.publicity_service.persistence.AdvertisementRepository;

@SpringBootApplication
public class PublicityServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(PublicityServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PublicityServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner testAd_MovieRepository(Ad_MovieRepository repository) {
		return (args) -> {
			logger.info("Adding 2 Ad_Movies:");
			Ad_Movie ad_Movie1 = new Ad_Movie(1, Duration.between(LocalTime.MIN, LocalTime.parse("00:30:00")),
					Category.Children, "Ad_MovieChildren1", null, LocalDate.now());
			Ad_Movie ad_Movie2 = new Ad_Movie(2, Duration.between(LocalTime.MIN, LocalTime.parse("00:20:00")),
					Category.Action, "Ad_MovieAction1", null, LocalDate.now());
			Ad_Movie ad_Movie3 = new Ad_Movie(3, Duration.between(LocalTime.MIN, LocalTime.parse("00:20:00")),
					Category.Plus18, "Ad_MoviePlus18_1", null, LocalDate.now());
			
			logger.info(ad_Movie1.toString());
			logger.info(ad_Movie2.toString());
			logger.info(ad_Movie3.toString());
			
			repository.save(ad_Movie1);
			repository.save(ad_Movie2);
			repository.save(ad_Movie3);
			logger.info("saved");
		};
	}
	
	@Bean
	CommandLineRunner getActionAd_Movie(Ad_MovieRepository repository) {
		return (args) -> {
			logger.info("Checking database population. Printing all Ad_Movies...");
			repository.findAll().forEach((ad_Movie) -> logger.info(ad_Movie.toString()));

			logger.info("Checking database population. Printing count...");
			logger.info(repository.count() + " items");
			
			logger.info("Checking database population. Printing all Action Ad_Movies made in this week...");
			repository.findAd_MovieByCategoryAndDate(Category.Action, LocalDate.now().minusDays(7))
														.forEach((ad_Movie) -> logger.info(ad_Movie.toString()));
			
			logger.info("Checking database population. Printing all Children Ad_Movies");
			repository.findAd_MovieByCategoryIsChildren().forEach((ad_Movie) -> logger.info(ad_Movie.toString()));
		};
	}

	@Bean
	CommandLineRunner testAdvertisementRepository(AdvertisementRepository repository) {
		return (args) -> {
			logger.info("Adding an advertisement:");
			Advertisement advertisement1 = new Advertisement(4, Duration.between(LocalTime.MIN, LocalTime.parse("00:03:00")),
					Category.All, "Belgiëbankreclame", "belgiëbank nv");
			
			logger.info(advertisement1.toString());
			
			repository.save(advertisement1);
			logger.info("saved");
		};
	}
	
	@Bean
	CommandLineRunner getAdvertisement(AdvertisementRepository repository) {
		return (args) -> {
			logger.info("get all Plus18 Advertisements:");
			repository.findAdvertisementByCategory(Category.Plus18).forEach((aMovie) -> logger.info(aMovie.toString()));
		};
	}

	@Bean
	CommandLineRunner testTrailerRepository(TrailerRepository repository) {
		return (args) -> {
			logger.info("Adding a trailer:");
			Trailer trailer1 = new Trailer(5, Duration.between(LocalTime.MIN, LocalTime.parse("00:05:00")),
					Category.Plus18, "TrailerBoy");
			logger.info(trailer1.toString());
			
			repository.save(trailer1);
			logger.info("saved");
		};
	}
	
	@Bean
	CommandLineRunner getTrailer(TrailerRepository repository) {
		return (args) -> {
			logger.info("get all Plus18 Trailers:");
			repository.findTrailerByCategory(Category.Plus18).forEach((aMovie) -> logger.info(aMovie.toString()));
		};
	}
	
	@Bean
	CommandLineRunner testPlaylistUpdate(Ad_MovieRepository aRepository, TrailerRepository tRepository, AdvertisementRepository adRepository) {
		return (args) -> {
			logger.info("Adding playlist to Ad_Movie");
			List<AMovie> playlist = new ArrayList<AMovie>();
			List<Advertisement> een = adRepository.findAdvertisementByName("Belgiëbankreclame");
			List<Trailer> twee = tRepository.findTrailerByName("TrailerBoy");
			playlist.add(een.get(0));
			playlist.add(twee.get(0));
			logger.info("get movie");
			Ad_Movie a = aRepository.getOne((long)1);
			a.setPlaylist(playlist);
			logger.info(a.getDuration().toString());//8 minuten
			
			List<Ad_Movie> ad_Movies = aRepository.findAd_MovieByCategoryAndDate(Category.Children, LocalDate.now().minusDays(7));//nog steeds 30 minuten
			ad_Movies.forEach((adMovie) -> logger.info(adMovie.toString()));
		};
	}
	
	@Bean
	CommandLineRunner testGetUpdate(Ad_MovieRepository aRepository, TrailerRepository tRepository, AdvertisementRepository adRepository) {
		return (args) -> {
			List<Ad_Movie> ad_Movies = aRepository.findAd_MovieByCategoryAndDate(Category.Children, LocalDate.now().minusDays(7));//nog steeds 30 minuten
			ad_Movies.forEach((adMovie) -> logger.info(adMovie.toString()));
		};
	}
}
