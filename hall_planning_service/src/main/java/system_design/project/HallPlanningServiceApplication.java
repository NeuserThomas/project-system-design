package system_design.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import system_design.project.hall_planning_service.adapters.messaging.Channels;
import system_design.project.hall_planning_service.adapters.movieInfo.IMovieInfoAPI;
import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.Movie;
import system_design.project.hall_planning_service.domain.MovieHall;
import system_design.project.hall_planning_service.domain.PlannedMovies;
import system_design.project.hall_planning_service.domain.Seat;
import system_design.project.hall_planning_service.persistence.CinemaRepository;
import system_design.project.hall_planning_service.persistence.MovieRepository;
import system_design.project.hall_planning_service.persistence.PlannedMoviesRepository;
import system_design.project.hall_planning_service.service.MovieAPIService;

/**
 * @author robin
 *
 */
@SpringBootApplication
@EnableBinding(Channels.class)
public class HallPlanningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HallPlanningServiceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner testRepository(CinemaRepository cRep,PlannedMoviesRepository pRepo, MovieRepository mRepo, MovieAPIService mService) {
		return (args)->{
			final Logger logger = LoggerFactory.getLogger(HallPlanningServiceApplication.class);
			logger.info("Checking if there is cinema info?");
			initCinema(logger,cRep);
			logger.info("Checking if there are movies in de db?");
			List<ObjectId> ids=initMovies(logger,mRepo,mService);
			logger.info("Checking if there are planned movies?");
			initPlannedMovies(logger,cRep,pRepo,ids);
			logger.info("Service initialized.");
		};
	}
	
	void initCinema(Logger logger, CinemaRepository cRep) {
		long cAmount = cRep.count();
		if(cAmount<1) {
			logger.warn("No instances found! Populating database!");
			Cinema c = fillCinema();
			c.setCinemaName("Cinema Init");
			c.setPlannedMovies(new PlannedMovies());
			c.getPlannedMovies().setCinema(c);
			cRep.save(c);
			logger.info("Added new cinema: "+c.getCinemaName());
		} else {
			logger.info(""+cAmount+" amount of instances found, no intervention needed.");
		}
	}
	
	void initPlannedMovies(Logger logger, CinemaRepository cRep,PlannedMoviesRepository pRepo, List<ObjectId> ids) {
		Optional<Cinema> cinema =cRep.findOneByCinemaName("Cinema Init");
		if(cinema.isPresent()) {
			Cinema c = cinema.get();
			PlannedMovies pm = c.getPlannedMovies();
			if(pm==null) {
				logger.warn("No PlannedMovies object. Adding them now. Amount to add: "+ids.size());
				pm=new PlannedMovies();
				pm.setMovieIds(ids);
				c.setPlannedMovies(pm);
				pm.setCinema(c);
				pRepo.save(pm);
				logger.info("Amount of plannedMovies objects: "+pRepo.findAll().size());
				cRep.saveAndFlush(c);
				logger.info("Movies saved!");
			} else {
				if(pm.getMovieIds().isEmpty()) {
					logger.warn("No movies planned. Adding them now. Amount to add: "+ids.size());
					pm.setMovieIds(ids);
					pm.setCinema(c);
					pRepo.save(pm);
					c.setPlannedMovies(pm);
					cRep.saveAndFlush(c);
					logger.info("Movies saved!");
				} else {
					logger.info("There are movies planned, no intervention needed.");
				}
			}
		} else {
			logger.info("The test cinema \"Cinema init\" was not found. Nothing was added.");
		}
	}
	
	List<ObjectId> initMovies(Logger logger,MovieRepository mRepo,IMovieInfoAPI api) {
		List<ObjectId> movieIds = new ArrayList<>();
		if(mRepo.count()<1) {
			logger.warn("No movies are in db!");
			List<String> list = new ArrayList<String>();
			list.add("Star Wars: The Rise of skywalker");
			list.add("Avengers: Endgame");
			list.add("The Matrix");
			list.add("Lego Movie");
			list.add("The expandables");
			for(String t: list){
				Movie m = api.FindMovieByName(t);
				if(m!=null) {
					mRepo.save(m);
					movieIds.add(m.getId());
				}
			}
		} else {
			logger.info("Movies were found in the db, no intervention needed.");
			for(Movie m: mRepo.findAll()) {
				movieIds.add(m.getId());
			}
		}
		return movieIds;
	}
		
	Cinema fillCinema() {
		Cinema c = new Cinema();
		List<MovieHall> halls=new ArrayList<MovieHall>();
		for(int i = 0;i<10;i++) {
			MovieHall hall = new MovieHall();
			hall.setHallNumber(i+1);
			List<Seat> seats = new ArrayList<Seat>();
			for(int j = 0;j<100;j++) {
				Seat seat = new Seat();
				seat.setRow(j/10);
				seat.setColumn((j%10)+1);
				seat.setMovieHall(hall);
				seats.add(seat);
			}
			hall.setSeats(seats);
			hall.setCinema(c);
			halls.add(hall);
		}
		c.setHalls(halls);
		return c;
	}
	
	
}
