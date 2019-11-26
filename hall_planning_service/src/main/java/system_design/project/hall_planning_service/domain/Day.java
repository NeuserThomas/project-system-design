package system_design.project.hall_planning_service.domain;

import java.time.LocalDate;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
/**
 * The class uses 2 Id's, as we might later have multiple cinemas, that way we can use a different cinema
 * @author robin
 *
 */
@IdClass(value = DayId.class)
public class Day {

	@Id
	/**
	 * Describes what day it is.
	 */
	//TODO: generated manually
	private LocalDate date;
	@Id
	/**
	 * Describes what cinema it is mapped to.
	 */
	private Cinema cinema;
	/**
	 * Maps the hall number, to an array of TimeSlots that describe what movies will be played when.
	 * The hall number, is the same number it has in the array of Cinema.
	 */
	private HashMap<Integer,TimeSlot[]> timeSlots;
	
	
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
		return cinema.getCinemaId();
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
	
	
}
