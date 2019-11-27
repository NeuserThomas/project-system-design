package system_design.project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.MovieHall;
import system_design.project.hall_planning_service.domain.Day;
import system_design.project.hall_planning_service.domain.Seat;
import system_design.project.hall_planning_service.persistence.CinemaRepository;
import system_design.project.hall_planning_service.service.PlanningService;

@SpringBootApplication
public class HallPlanningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HallPlanningServiceApplication.class, args);
	}

	
	@Bean
	CommandLineRunner testRepository(CinemaRepository cRep, PlanningService pServ) {
		
		return (args)->{
			final Logger logger = LoggerFactory.getLogger(Day.class);
			Cinema c = new Cinema();
			c.setCinemaName("Cinema TIWI");
			List<MovieHall> halls=new ArrayList<MovieHall>();
			for(int i = 0;i<10;i++) {
				MovieHall hall = new MovieHall();
				hall.setHall_number(i+1);
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
			cRep.save(c);
			pServ.planDay(LocalDate.now());
			logger.info("Dummy data loaded");
		};
	}
}
