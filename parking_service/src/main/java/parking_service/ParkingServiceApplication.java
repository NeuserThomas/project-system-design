package parking_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import parking_service.domain.Parking;
import parking_service.persistence.ParkingRepository;

@SpringBootApplication
public class ParkingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingServiceApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner populateDatabase(ParkingRepository parkingRepo){

		return args ->{
			
			if(parkingRepo.count() == 0) {
				Parking p = new Parking(20);
				parkingRepo.save(p);
			}
		};

	}

}
