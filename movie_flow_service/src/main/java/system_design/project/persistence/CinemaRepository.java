package system_design.project.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.domain.Cinema;
import system_design.project.domain.MovieHall;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>{

	public List<MovieHall> findAllById(long id);
	
	@Query("Select c from Cinema c where c.cinemaName=?1")
	public Optional<Cinema> findOneByCinemaName(String cinemaName);
	
	@Query("Select c.cinemaName from Cinema c")
	public List<String> findAllCinemaNames();
	
}
