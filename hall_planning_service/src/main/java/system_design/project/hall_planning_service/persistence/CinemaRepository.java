package system_design.project.hall_planning_service.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.MovieHall;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long>{

	public List<MovieHall> findAllById(long id);
	
	public Optional<Cinema> findOneByCinemaName(String cinemaName);
	
}
