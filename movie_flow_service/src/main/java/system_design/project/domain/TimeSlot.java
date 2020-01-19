package system_design.project.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Class that describes how long a movie will take. The class could extend from an event class, if you would like to 
 * have more flexibility. (Paper already submitted, extra feature)
 * @author robin
 *
 */
@Entity
public class TimeSlot implements Serializable {
	
	/**
	 * Version number?
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tId;

	//@Column(name = "startTime", columnDefinition = "TIMESTAMP") 
	private LocalDateTime startTime=LocalDateTime.now();
	
	//@Column(name = "stopTime", columnDefinition = "TIMESTAMP") //Creates error?
	private LocalDateTime stopTime=LocalDateTime.now();
	//remove when working with more than event.
	@Column(nullable=false)
	/*
	 * Maps to the string representation of movieId
	 */
	private String movieId;
	
	@Transient
	@JsonSerialize
	private String movieTitle;
	
	@ManyToOne
	@JsonIgnoreProperties(value = { "cinema","seats" })
	@JoinColumn(name="hallId",nullable=false)
	private MovieHall hall;
	
	//------------ separation declarations and methods ------------------------
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	
	public long gettId() {
		return tId;
	}
	public void settId(long tId) {
		this.tId = tId;
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
	

	public MovieHall getHall() {
		return hall;
	}
	public void setHall(MovieHall hall) {
		this.hall = hall;
	}
	
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
}
