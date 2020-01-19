package system_design.project.persistence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import system_design.project.domain.TimeSlot;

/**
 * @author robin
 *
 */
public interface TimeSlotRepository extends JpaRepository<TimeSlot,Long> {
	
	@Query("Select t from TimeSlot t where t.hall.cinema.id=:cinemaId and t.movieId=:movieId and YEAR(t.startTime)=YEAR(:date) and MONTH(t.startTime)=MONTH(:date) and DAY(t.startTime)=DAY(:date)")
	public List<TimeSlot> findByCinemaIdAndMovieAndDate(long cinemaId,String movieId,LocalDate date);

	//@Query("Select t from TimeSlot t where YEAR(t.startTime)=YEAR(:date) and MONTH(t.startTime)=MONTH(:date) and DAY(t.startTime)=DAY(:date)")
	@Query("Select t from TimeSlot t where DATE(t.startTime)=DATE(:date)")
	public List<TimeSlot> findbyDate(LocalDate date);

	//@Query("Select t from TimeSlot t where YEAR(t.startTime)=YEAR(:date) and MONTH(t.startTime)=MONTH(:date) and DAY(t.startTime)=DAY(:date)")
	@Query("Select t from TimeSlot t where DATE(t.startTime)>=DATE(:date)")
	public List<TimeSlot> findafterDate(LocalDate date);
	
	@Query("Select t from TimeSlot t where t.hall.cinema.id=:cinemaId and YEAR(t.startTime)=YEAR(:date) and MONTH(t.startTime)=MONTH(:date) and DAY(t.startTime)=DAY(:date)")
	public List<TimeSlot> findForCinemaAndDate(long cinemaId, LocalDate date);
	
	@Query("Select t from TimeSlot t where t.hall.cinema.cinemaName=:cinemaName and YEAR(t.startTime)=YEAR(:date) and MONTH(t.startTime)=MONTH(:date) and DAY(t.startTime)=DAY(:date)")
	public List<TimeSlot> findForCinemaAndDate(String cinemaName, LocalDate date);
}
