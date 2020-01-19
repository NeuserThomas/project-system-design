package system_design.project.hall_planning_service.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(
    uniqueConstraints=
        @UniqueConstraint(columnNames={"date", "id"})
)
/**
 * The class uses 2 Id's, as we might later have multiple cinemas, that way we can use a different cinema
 * @author robin
 *
 */
public class Day {

	@Id
	@GeneratedValue
	private long dayId;
	/**
	 * Describes what day it is.
	 */
	//TODO: generated manually
	@Column(name = "date", columnDefinition = "DATE")
	private LocalDate date;
	/**
	 * Maps the hall number, to an array of TimeSlots that describe what movies will be played when.
	 * The hall number, is the same number it has in the array of Cinema.
	 */
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL)
	private List<HallDay> planning;
	
	//@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime startTime;
	//@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime stopTime;
	
	@ManyToOne
	/**
	 * Describes what Cinema it is mapped to.
	 */
	@JoinColumn(name="id")
	@JsonIgnoreProperties("halls")
	private Cinema cinema;
	
	//------------ separation declarations and methods ------------------------
	
	public Day() {
		planning = new ArrayList<HallDay>();
		startTime=LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 00));
		stopTime=LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 30));
	}
	
	public List<HallDay> getPlanning() {
		return planning;
	}

	public void setPlanning(List<HallDay> planning) {
		this.planning = planning;
	}

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Long getCinemaId() {
		return cinema.getId();
	}
	
	public long getDayId() {
		return dayId;
	}

	public void setDayId(long dayId) {
		this.dayId = dayId;
	}
	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}
	public void setStartTime(LocalDate date, LocalTime startTime) {
		this.startTime=LocalDateTime.of(date, startTime);
	}
	
	public LocalDateTime getStopTime() {
		return stopTime;
	}
	public void setStopTime(LocalDateTime stopTime) {
		this.stopTime = stopTime;
	}
	public void setStopTime(LocalDate date, LocalTime stopTime) {
		this.stopTime=LocalDateTime.of(date, stopTime);
	}
	
}
