/**
 * 
 */
package system_design.project.hall_planning_service.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.TimeSlot;

/**
 * @author robin
 *
 */
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
	
	@Query("Select t from TimeSlot t where t.hall.cinema.id=?1 and t.movieId=?2 and CONVERT(t.startTime,23)=?3")
	public List<TimeSlot> findByCinemaIdAndMovieAndDate(long cinemaId,String movidId,LocalDate date);

	@Query("Select t from TimeSlot t where CONVERT(t.startTime,23)=?1")
	public List<TimeSlot> findbyDate(LocalDate date);

	@Query("Select t from TimeSlot t where t.hall.cinema.id=?1 and CONVERT(t.startTime,23)=?2")
	public List<TimeSlot> findForCinemaAndDate(long cinemaId, LocalDate date);
	
	@Query("Select t from TimeSlot t where t.hall.cinema.name=?1 and CONVERT(t.startTime,23)=?2")
	public List<TimeSlot> findForCinemaAndDate(String cinemaName, LocalDate date);
}
