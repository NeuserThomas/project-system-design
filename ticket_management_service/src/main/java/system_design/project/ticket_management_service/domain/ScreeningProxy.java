package system_design.project.ticket_management_service.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreeningProxy {
	
	private LocalDateTime startTime;
	private LocalDateTime stopTime;
	private String movieId;
	private String movieTitle;
	private Hall hall;
	private int tid;
	
	public ScreeningProxy() {};
	
	public ScreeningProxy(LocalDateTime startTime, LocalDateTime stopTime, Hall hall) {
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.hall = hall;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}	
	
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

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	@Override
	public String toString() {
		return "Screening: " + tid + "---" + startTime + " - " + stopTime + " movie: " + movieId + "HALL: " + hall.toString();
	}
}
