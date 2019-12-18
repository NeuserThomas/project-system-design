package system_design.project.hall_planning_service.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.bson.types.ObjectId;

@Entity
public class PlannedMovies {
	@Id
	private long id;
		
	@ElementCollection
	private List<ObjectId> movieIds;

	@ElementCollection
	private List<Double> movieWantStatus;
	
	public PlannedMovies(){
		movieIds= new ArrayList<ObjectId>();
		movieWantStatus = new ArrayList<Double>();
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<ObjectId> getMovieIds() {
		return movieIds;
	}

	public void setMovieIds(List<ObjectId> movieIds) {
		this.movieIds = movieIds;
	}
	
	public void addPlannedMovie(Movie m) {
		movieIds.add(m.getId());
	}
	
	public void removePlannedMovie( Movie m) {
		movieIds.remove(m.getId());
	}
}
