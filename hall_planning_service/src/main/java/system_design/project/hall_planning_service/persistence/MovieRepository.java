package system_design.project.hall_planning_service.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import system_design.project.hall_planning_service.domain.Movie;


public interface MovieRepository extends MongoRepository<Movie, Long>{
	
}
