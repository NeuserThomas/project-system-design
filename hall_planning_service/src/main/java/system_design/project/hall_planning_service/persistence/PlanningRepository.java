package system_design.project.hall_planning_service.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.Day;

@Repository
public interface PlanningRepository extends JpaRepository<Day,Long>{

	public Day findDayByDateAndCinema(LocalDate date, Cinema cine);
	
	@Query("Select d from Day d where d.date>?1 and d.cinema.id=?2")
	public List<Day> findDaysAfterDateForCinema(LocalDate date, long cinemaId);
	
	@Query("Select d from Day d where d.date LIKE ?1 and d.cinema.id=?2")
	public List<Day> findDaysOnDateForCinema(LocalDate date, long cinemaId);
	
	
	@Query("Select d from Day d where d.cinema.id=?1")
	public List<Day> findDaysForCinema(long cinemaId);
	
	@Query("Select d from Day d where d.date>?1")
	public List<Day> findDaysAfterDate(LocalDate date);
}
