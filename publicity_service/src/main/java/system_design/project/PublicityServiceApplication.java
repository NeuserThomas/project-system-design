package system_design.project;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import system_design.project.publicity_service.domain.Ad_Movie;
import system_design.project.publicity_service.domain.Category;
import system_design.project.publicity_service.persistence.Ad_MovieRepository;

@SpringBootApplication
public class PublicityServiceApplication {

	private static Logger logger = LoggerFactory.getLogger(PublicityServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PublicityServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner testRepository(Ad_MovieRepository repository) {
		return (args) -> {
			logger.info("Adding 2 Ad_Movies:");
			Ad_Movie ad_Movie1 = new Ad_Movie(1, LocalTime.parse("00:30:00"), Category.Children, "Ad_MovieChildren1", null, LocalDate.now());
			Ad_Movie ad_Movie2 = new Ad_Movie(2, LocalTime.parse("00:20:00"), Category.Action, "Ad_MovieAction1", null, LocalDate.now());
			
			logger.info(ad_Movie1.toString());
			logger.info(ad_Movie2.toString());
			
			repository.save(ad_Movie1);
			repository.save(ad_Movie2);
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
}
