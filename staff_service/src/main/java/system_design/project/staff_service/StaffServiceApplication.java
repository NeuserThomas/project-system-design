package system_design.project.staff_service;

import javafx.util.Pair;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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
			repo.save(new Employee("Geralt"));
			repo.save(new Employee("Evans"));
			System.out.println("employees are added.");

			logger.info("employeerepo.findAll(): ");
			employees = (ArrayList<Employee>) repo.findAll();
			logger.info("nr of employees: " + employees.size());
		};
	}


	@ConditionalOnProperty(value="populateTimeSlotRepositoryBasic.enabled")
	@Bean
	public CommandLineRunner populateTimeSlotRepositoryBasic(TimeSlotRepository repo, EmployeeRepository employeeRepository, CinemaRepository cinemaRepository) {
		return (args) -> {

			logger.info("populateTimeSlotRepositoryBasic()");
			repo.deleteAll();

//			repo.save(new TimeSlot());
//			repo.save(new TimeSlot());
//			repo.save(new TimeSlot());

			Cinema cinemaGent = cinemaRepository.findCinemaByName("Gent").get(0);
			logger.info("found cinema !" + cinemaGent);

			Employee employeeGeralt =employeeRepository.findEmployeeByFirstName("Geralt").get(0);

			repo.save(
					new TimeSlot(
							cinemaGent.getId(), employeeGeralt.getId(), LocalDate.now(), LocalTime.now(), 0
					)
			);


			//repo.save(new TimeSlot("hla",LocalDateTime.now().toLocalDate(),LocalDateTime.now().toLocalTime(),0));

		};
	}

	@ConditionalOnProperty(value="populateTimeSlotRepository.enabled")
	@Bean()
	public CommandLineRunner populateTimeSlotRepository(TimeSlotRepository repo, EmployeeRepository employeeRepository, CinemaRepository cinemaRepository){
		return (args)->{
			logger.info("populateTimeSlotRepository()");
			logger.info("LocalDateTime.now().toLocalDate(): " + LocalDateTime.now().toLocalDate());
			repo.deleteAll();

			Cinema cinemaGent = cinemaRepository.findCinemaByName("Gent").get(0);


			ArrayList<Employee> employees = (ArrayList<Employee>) employeeRepository.findAll();
			ArrayList<Pair<Integer, Integer>> dummySlots = new ArrayList<>();
			dummySlots.add(new Pair<>(18,0));
			dummySlots.add(new Pair<>(18,15));
			dummySlots.add(new Pair<>(18,30));
			dummySlots.add(new Pair<>(18,45));

			dummySlots.add(new Pair<>(19, 0));
			dummySlots.add(new Pair<>(19,15));
			dummySlots.add(new Pair<>(19,30));
			dummySlots.add(new Pair<>(19,45));

			for(Employee e : employees){

				for (Pair<Integer,Integer> s : dummySlots){

					repo.save(
							new TimeSlot(
									cinemaGent.getId(), e.getId(), LocalDate.now(), LocalTime.of(s.getKey(), s.getValue()), 0
							)
					);
//					repo.save(
//							new TimeSlot(
//									//"GENT",
//									e.getId(),
//									LocalDate.of(2020, 1,1),
//									,
//									0
//							)
//					);
				}


			}


		};
	}



}
