package system_design.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import system_design.project.hall_planning_service.adapters.messaging.Channels;

@SpringBootApplication
@EnableBinding(Channels.class)
public class HallPlanningServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HallPlanningServiceApplication.class, args);
	}

	/*
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
	}*/
}
