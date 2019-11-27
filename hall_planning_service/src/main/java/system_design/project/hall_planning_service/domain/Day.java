package system_design.project.hall_planning_service.domain;

import java.time.LocalDate;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
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
	private LocalDate date;
	/**
	 * Maps the hall number, to an array of TimeSlots that describe what movies will be played when.
	 * The hall number, is the same number it has in the array of Cinema.
	 */
	private HashMap<Integer,TimeSlot[]> timeSlots;
	
	@ManyToOne
	/**
	 * Describes what Cinema it is mapped to.
	 */
	@JoinColumn(name="id")

	private Cinema cinema;
	
	//------------ separation declarations and methods ------------------------
	Day(Cinema cinema){
		this.cinema=cinema;
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
	public void setCinemaId(Cinema cinema) {
		this.cinema=cinema;
	}

	public HashMap<Integer,TimeSlot[]> getTimeslots() {
		return timeSlots;
	}

	public void setTimeslots(HashMap<Integer,TimeSlot[]> timeslots) {
		this.timeSlots = timeslots;
	}
	
	public long getDayId() {
		return dayId;
	}

	public void setDayId(long dayId) {
		this.dayId = dayId;
	}

	public HashMap<Integer, TimeSlot[]> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(HashMap<Integer, TimeSlot[]> timeSlots) {
		this.timeSlots = timeSlots;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	
}
