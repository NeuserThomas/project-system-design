package system_design.project.hall_planning_service.domain;

import java.io.Serializable;
import java.time.LocalTime;

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
	@Column(name = "startTime", columnDefinition = "TIME")
	private LocalTime startTime;
	
	@Column(name = "stopTime", columnDefinition = "TIME")
	private LocalTime stopTime;
	//remove when working with more than event.
	@Column(nullable=false)
	private long movieId;
		
	//------------ separation declarations and methods ------------------------
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalTime stopTime) {
		this.stopTime = stopTime;
	}

	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
}
