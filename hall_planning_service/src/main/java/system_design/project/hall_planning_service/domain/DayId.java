package system_design.project.hall_planning_service.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * This class is using 2 id's, so that we can later expand to multiple cinema's with the same classes.
 * This is why it is a IdClass.
 * @author robin
 *
 */

public class DayId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Describes what day it is.
	 */
	//TODO: generated manually
	private LocalDate date;
	/**
	 * Describes what cinema it is mapped to.
	 */
	private Cinema cinema;
	
	
	
	public DayId(LocalDate date, Cinema cinema) {
		super();
		this.date = date;
		this.cinema = cinema;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
}
