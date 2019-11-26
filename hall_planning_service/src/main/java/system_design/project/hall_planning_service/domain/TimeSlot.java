package system_design.project.hall_planning_service.domain;

import java.time.LocalTime;

import javax.persistence.Embeddable;

@Embeddable
/**
 * Class that describes how long a movie will take.
 * @author robin
 *
 */
public class TimeSlot {
	
	private LocalTime startTime,stopTime;
	/**
	 * First array contains the breaks. Second dimension start and stop time. 
	 */
	private LocalTime[][] breaks;
	//Todo mapping
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
	public LocalTime[][] getBreaks() {
		return breaks;
	}
	public void setBreaks(LocalTime[][] breaks) {
		this.breaks = breaks;
	}
	public long getMovieId() {
		return movieId;
	}
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}
	
}
