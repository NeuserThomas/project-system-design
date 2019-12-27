package system_design.project.hall_planning_service.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
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
}
