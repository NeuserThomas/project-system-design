package system_design.project.hall_planning_service.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import system_design.project.hall_planning_service.domain.MovieHall;

public interface HallRepository extends JpaRepository<MovieHall, Long>  {
	
}
