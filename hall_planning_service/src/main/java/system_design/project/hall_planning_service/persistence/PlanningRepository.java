package system_design.project.hall_planning_service.persistence;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.Day;

@Repository
public interface PlanningRepository extends JpaRepository<Day,Long>{

	public Day findDayByDateAndCinema(LocalDate date, Cinema cine);
	
}
