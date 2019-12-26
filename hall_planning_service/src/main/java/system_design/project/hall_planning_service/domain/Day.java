package system_design.project.hall_planning_service.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
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
	
	private LocalTime startTime=LocalTime.of(14, 0);
	private LocalTime stopTIme=LocalTime.of(23, 30);
	
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
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getStopTIme() {
		return stopTIme;
	}
	public void setStopTIme(LocalTime stopTIme) {
		this.stopTIme = stopTIme;
	}
	
	
}
