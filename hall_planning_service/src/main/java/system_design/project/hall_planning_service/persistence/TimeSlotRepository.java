/**
 * 
 */
package system_design.project.hall_planning_service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import system_design.project.hall_planning_service.domain.TimeSlot;

/**
 * @author robin
 *
 */
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
	
	/* Doesn't work yet
	@Query("Select t from TimeSlot t where t.hall.cinema.id=?1 and t.movieId=?2 and t.startTime=?3")
	public List<TimeSlot> findByCinemaIdAndMovie(long cinemaId,String movidId,LocalDate date);
	*/
}
