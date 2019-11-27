package system_design.project.hall_planning_service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import system_design.project.hall_planning_service.domain.MovieHall;

public interface HallRepository extends JpaRepository<MovieHall, Long>  {
	
}
