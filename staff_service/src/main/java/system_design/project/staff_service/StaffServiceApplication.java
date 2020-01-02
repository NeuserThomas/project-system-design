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

import java.time.LocalDate;
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
			repo.save(new Cinema((long)0,"Gent"));
			repo.save(new Cinema((long) 1,"Brussel"));

		};
	}

	@ConditionalOnProperty(value="populateEmployeeRepository.enabled")
	@Bean
	public CommandLineRunner populateEmployeeRepository(EmployeeRepository repo){
		return (args)->{

			// start clean
			repo.deleteAll();


			System.out.println("Adding employees...");
			repo.save(new Employee("Geralt","Of Rivia"));
			repo.save(new Employee("Joris", "Moreau"));
			repo.save(new Employee("Jan", "Cnops"));



			ArrayList<Employee> employees = (ArrayList<Employee>) repo.findAll();
			logger.info("nr of employees: " + employees.size());
		};
	}

	@ConditionalOnProperty(value="populateTimeSlotRepository.enabled")
	@Bean
	public CommandLineRunner experimentSimpleTimeSlot(TimeSlotRepository repo, EmployeeRepository employeeRepository, CinemaRepository cinemaRepository){
		return (args)->{
			logger.info("populateTimeSlotRepository() called!");



			UUID gID = employeeRepository.findEmployeeByFirstName("Geralt").get(0).getId();
			UUID jID = employeeRepository.findEmployeeByFirstName("Joris").get(0).getId();
			UUID cnopsID= employeeRepository.findEmployeeByFirstName("Jan").get(0).getId();

			Long idCinemaGent = cinemaRepository.findCinemaByName("Gent").get(0).getId();
			Long idCinemaBrussel = cinemaRepository.findCinemaByName("Brussel").get(0).getId();

			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(12,00), gID,0));
			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(12,15), gID,0));
			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(12,30), gID,0));

			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(18,00), jID,0));
			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(18,15), jID,0));
			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,1), LocalTime.of(18,30), jID,0));



			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1, LocalDate.now().getDayOfMonth()), LocalTime.of(12,00), gID,0));
			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,LocalDate.now().getDayOfMonth()), LocalTime.of(12,15), gID,0));
			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,LocalDate.now().getDayOfMonth()), LocalTime.of(12,30), gID,0));

			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,LocalDate.now().getDayOfMonth()), LocalTime.of(18,00), jID,0));
			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1, LocalDate.now().getDayOfMonth()), LocalTime.of(18,15), jID,0));
			repo.save(new TimeSlot(idCinemaGent, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,LocalDate.now().getDayOfMonth()), LocalTime.of(18,30), jID,0));

			repo.save(new TimeSlot(idCinemaBrussel, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1, LocalDate.now().getDayOfMonth()), LocalTime.of(12,00), cnopsID,0));
			repo.save(new TimeSlot(idCinemaBrussel, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,LocalDate.now().getDayOfMonth()), LocalTime.of(12,15), cnopsID,0));
			repo.save(new TimeSlot(idCinemaBrussel, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,LocalDate.now().getDayOfMonth()), LocalTime.of(12,30), cnopsID,0));
			repo.save(new TimeSlot(idCinemaBrussel, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1, LocalDate.now().getDayOfMonth()), LocalTime.of(12,45), cnopsID,0));
			repo.save(new TimeSlot(idCinemaBrussel, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1, LocalDate.now().getDayOfMonth()), LocalTime.of(13,00), cnopsID,0));
			repo.save(new TimeSlot(idCinemaBrussel, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,LocalDate.now().getDayOfMonth()), LocalTime.of(13,15), cnopsID,0));
			repo.save(new TimeSlot(idCinemaBrussel, com.datastax.driver.core.LocalDate.fromYearMonthDay(2020,1,LocalDate.now().getDayOfMonth()), LocalTime.of(13,30), cnopsID,0));

		};
	}

}
