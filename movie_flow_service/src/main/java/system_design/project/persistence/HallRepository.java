package system_design.project.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.domain.MovieHall;

@Repository
public interface HallRepository extends JpaRepository<MovieHall, Long>  {
	
	public List<MovieHall> findAllByCinemaId(long cinemaId);
	
	public MovieHall findByHallId(long hallId);
	
	@Query("select m from MovieHall m where m.cinema.id=?1 and m.hallNumber=?2")
	public List<MovieHall> findAllByCinemaIdAndHallNumber(long cinemaId,int hallNumber);
	
}
