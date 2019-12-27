package system_design.project.hall_planning_service.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
/**
 * Class that describes how long a movie will take. The class could extend from an event class, if you would like to 
 * have more flexibility. (Paper already submitted, extra feature)
 * @author robin
 *
 */
public class TimeSlot implements Serializable {
	
	/**
	 * Version number?
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long Tid;
	
	//@Column(name = "startTime", columnDefinition = "TIMESTAMP")
	private LocalDateTime startTime;
	
	//@Column(name = "stopTime", columnDefinition = "TIMESTAMP")
	private LocalDateTime stopTime;
	//remove when working with more than event.
	@Column(nullable=false)
	/*
	 * Maps to the string representation of movieId
	 */
	private String movieId;
		
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="hallId",nullable=false)
	private MovieHall hall;
	
	//------------ separation declarations and methods ------------------------
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	public LocalDateTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalDateTime stopTime) {
		this.stopTime = stopTime;
	}

	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String objectId) {
		this.movieId = objectId;
	}
	
	public long getTid() {
		return Tid;
	}
	public void setTid(long tid) {
		Tid = tid;
	}
	public MovieHall getHall() {
		return hall;
	}
	public void setHall(MovieHall hall) {
		this.hall = hall;
	}
}
