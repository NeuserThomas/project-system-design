package system_design.project.hall_planning_service.persistence;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import system_design.project.hall_planning_service.domain.Movie;


public interface MovieRepository extends MongoRepository<Movie, String>{

	@Query("{_id: { $in: ?0 } })")
	public List<Movie> findMoviesWithId(List<ObjectId> ids);
	
}
