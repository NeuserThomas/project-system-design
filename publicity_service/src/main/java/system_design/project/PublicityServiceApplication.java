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
import system_design.project.publicity_service.domain.AdMovie;
import system_design.project.publicity_service.domain.Advertisement;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.domain.Trailer;
import system_design.project.publicity_service.persistence.TrailerRepository;
import system_design.project.publicity_service.persistence.AdMovieRepository;
import system_design.project.publicity_service.persistence.AdvertisementRepository;

@SpringBootApplication
public class PublicityServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(PublicityServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PublicityServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner testAdMovieRepository(AdMovieRepository repository) {
		return (args) -> {
			logger.info("Adding 2 AdMovies:");
			AdMovie adMovie1 = new AdMovie(Duration.between(LocalTime.MIN, LocalTime.parse("00:30:00")),
					Category.Children, "AdMovieChildren1", null, LocalDate.now());
			AdMovie adMovie2 = new AdMovie(Duration.between(LocalTime.MIN, LocalTime.parse("00:20:00")),
					Category.Action, "AdMovieAction1", null, LocalDate.now());
			AdMovie adMovie3 = new AdMovie(Duration.between(LocalTime.MIN, LocalTime.parse("00:20:00")),
					Category.Plus18, "AdMoviePlus18_1", null, LocalDate.now());
			
			logger.info(adMovie1.toString());
			logger.info(adMovie2.toString());
			logger.info(adMovie3.toString());
			
			repository.save(adMovie1);
			repository.save(adMovie2);
			repository.save(adMovie3);
			logger.info("saved");
		};
	}
	
	@Bean
	CommandLineRunner getActionAdMovie(AdMovieRepository repository) {
		return (args) -> {
			logger.info("Checking database population. Printing all AdMovies...");
			repository.findAll().forEach((adMovie) -> logger.info(adMovie.toString()));

			logger.info("Checking database population. Printing count...");
			logger.info(repository.count() + " items");
			
			logger.info("Checking database population. Printing all Action AdMovies made in this week...");
			repository.findAdMovieByCategoryAndDate(Category.Action, LocalDate.now().minusDays(7))
														.forEach((adMovie) -> logger.info(adMovie.toString()));
			
			logger.info("Checking database population. Printing all Children AdMovies");
			repository.findAdMovieByCategoryIsChildren().forEach((adMovie) -> logger.info(adMovie.toString()));
		};
	}

	@Bean
	CommandLineRunner testAdvertisementRepository(AdvertisementRepository repository) {
		return (args) -> {
			logger.info("Adding an advertisement:");
			Advertisement advertisement1 = new Advertisement(Duration.between(LocalTime.MIN, LocalTime.parse("00:03:00")),
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
			Trailer trailer1 = new Trailer(Duration.between(LocalTime.MIN, LocalTime.parse("00:05:00")),
					Category.Plus18, "TrailerBoy", 0);//no film_id
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
	CommandLineRunner testPlaylistUpdate(AdMovieRepository aRepository, TrailerRepository tRepository, AdvertisementRepository adRepository) {
		return (args) -> {
			logger.info("Adding playlist to AdMovie");
			List<AMovie> playlist = new ArrayList<AMovie>();
			List<Advertisement> een = adRepository.findAdvertisementByName("Belgiëbankreclame");
			List<Trailer> twee = tRepository.findTrailerByName("TrailerBoy");
			playlist.add(een.get(0));
			playlist.add(twee.get(0));
			logger.info("get movie");
			AdMovie testPlaylist = aRepository.getOne((long)1);
			testPlaylist.setPlaylist(playlist);
			logger.info(testPlaylist.getDuration().toString());//8 minuten
			//aRepository.save(testPlaylist); //gives error detached entity passed to persist, changing the cascadeType of playlist didn't give any result
			
			List<AdMovie> adMovies = aRepository.findAdMovieByCategoryAndDate(Category.Children, LocalDate.now().minusDays(7));//nog steeds 30 minuten
			adMovies.forEach((adMovie) -> logger.info(adMovie.toString()));
		};
	}
	
	@Bean
	CommandLineRunner testGetUpdate(AdMovieRepository aRepository, TrailerRepository tRepository, AdvertisementRepository adRepository) {
		return (args) -> {
			List<AdMovie> adMovies = aRepository.findAdMovieByCategoryAndDate(Category.Children, LocalDate.now().minusDays(7));//nog steeds 30 minuten
			adMovies.forEach((adMovie) -> logger.info(adMovie.toString()));
		};
	}
}
