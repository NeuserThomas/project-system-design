package system_design.project.hall_planning_service.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import system_design.project.hall_planning_service.domain.PlannedMovies;

@Repository
public interface PlannedMoviesRepository extends JpaRepository<PlannedMovies, Long>{

}
