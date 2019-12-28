/**
 * 
 */
package system_design.project.hall_planning_service.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system_design.project.hall_planning_service.domain.TimeSlot;

/**
 * @author robin
 *
 */
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
	
	@Query("Select t from TimeSlot t where t.hall.cinema.id=?1 and t.movieId=?2 and CONVERT(t.startTime,23)=?3")
	public List<TimeSlot> findByCinemaIdAndMovie(long cinemaId,String movidId,LocalDate date);
}
