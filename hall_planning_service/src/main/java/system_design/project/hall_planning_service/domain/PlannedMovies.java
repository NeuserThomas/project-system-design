package system_design.project.hall_planning_service.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.bson.types.ObjectId;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PlannedMovies {
	@Id
	@GeneratedValue
	private long pId;
		
	@ElementCollection
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ObjectId> movieIds;
	@LazyCollection(LazyCollectionOption.FALSE)
	@ElementCollection
	//Can't seem to work with ObjectId and map.
	/**
	 * Maps the index of the movieId in movieIds to a double. It describes if a certain movie is wanted more.
	 */
	private Map<Integer,Double> movieWantStatus;
	//private Map<ObjectId,Double> movieWantStatus;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="id")
	private Cinema cinema;
	
	public PlannedMovies(){
		movieIds= new ArrayList<ObjectId>();
		movieWantStatus = new HashMap<Integer,Double>();
	}
	
	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public Map<Integer,Double> getMovieWantStatus() {
		return movieWantStatus;
	}

	public void setMovieWantStatus(Map<Integer,Double> movieWantStatus) {
		this.movieWantStatus = movieWantStatus;
	}
	
	public List<ObjectId> getMovieIds() {
		if(movieIds==null) {
			movieIds=new ArrayList<ObjectId>();
		}
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

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
}
