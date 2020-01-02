package system_design.project.staff_service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import system_design.project.staff_service.domain.Cinema;
import system_design.project.staff_service.domain.Employee;
import system_design.project.staff_service.domain.TimeSlot;
import system_design.project.staff_service.persistence.CinemaRepository;
import system_design.project.staff_service.persistence.EmployeeRepository;
import system_design.project.staff_service.persistence.TimeSlotRepository;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

@SpringBootApplication
@ConfigurationPropertiesScan
public class StaffServiceApplication {
	private static final Logger logger = LoggerFactory.getLogger(StaffServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StaffServiceApplication.class, args);
	}

	@ConditionalOnProperty(value="populateCinemaRepository.enabled")
	@Bean
	public CommandLineRunner populateCinemaRepository(CinemaRepository repo){
		return (args)->{
			// start clean
			repo.deleteAll();

			logger.info("called: populateCinemaRepository()");

			System.out.println("Adding 2 employees...");
			repo.save(new Cinema("Gent"));
			repo.save(new Cinema("Brussel"));

		};
	}

	@ConditionalOnProperty(value="populateEmployeeRepository.enabled")
	@Bean
	public CommandLineRunner populateEmployeeRepository(EmployeeRepository repo){
		return (args)->{

			// start clean
			repo.deleteAll();

			logger.info("called: populateEmployeeRepository()");
			logger.info("employeerepo.findAll(): ");
			ArrayList<Employee> employees = (ArrayList<Employee>) repo.findAll();
			logger.info("nr of employees: " + employees.size());

			System.out.println("Adding 2 employees...");
			repo.save(new Employee("Geralt","Of Rivia"));
			repo.save(new Employee("Joris", "Moreau"));
			System.out.println("employees are added.");

			logger.info("employeerepo.findAll(): ");
			employees = (ArrayList<Employee>) repo.findAll();
			logger.info("nr of employees: " + employees.size());
		};
	}

	@ConditionalOnProperty(value="populateTimeSlotRepository.enabled")
	@Bean
	public CommandLineRunner experimentSimpleTimeSlot(TimeSlotRepository repo, EmployeeRepository employeeRepository, CinemaRepository cinemaRepository){
		return (args)->{
			logger.info("populateTimeSlotRepository() called!");



			UUID geraltUUID = employeeRepository.findEmployeeByFirstName("Geralt").get(0).getId();
			//employeeRepository.findEmployeeByFirstName("Geralt").get(0).getId();
			UUID evansUUID = employeeRepository.findEmployeeByFirstName("Joris").get(0).getId();

			UUID dummyCinemaId = cinemaRepository.findCinemaByName("Gent").get(0).getId();

			repo.save(new TimeSlot(dummyCinemaId, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(12,00), geraltUUID,0));
			repo.save(new TimeSlot(dummyCinemaId, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(12,15), geraltUUID,0));
			repo.save(new TimeSlot(dummyCinemaId, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(12,30), geraltUUID,0));

			repo.save(new TimeSlot(dummyCinemaId, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(18,00), evansUUID,0));
			repo.save(new TimeSlot(dummyCinemaId, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(18,15), evansUUID,0));
			repo.save(new TimeSlot(dummyCinemaId, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(18,30), evansUUID,0));

//

		};
	}

}
