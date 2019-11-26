package system_design.project.hall_planning_service.domain;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
/**
 * Describes a class that represents a movie hall (NL: zaal).
 * @author robin
 *
 */
public class MovieHall{
	
	@Id
	private long hall_id;
	
	private Seat[][] seats;
	
	private ProjectorType projector_types[];//array with possible projectors
	
	
	
	//------------ separation declarations and methods ------------------------

	MovieHall(){
		seats= new Seat[25][15]; //default filled with normal seats
	}

	
	/**
	 * Returns the amount of total seats
	 * @return the amount of available seats
	 */
	public int get_seats_count() {
		int counter=0;
		for(Seat[] row:seats) {
			for(Seat s:row) {
				if(s!=Seat.hall) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	public long getHall_id() {
		return hall_id;
	}

	public void setHall_id(long hall_id) {
		this.hall_id = hall_id;
	}

	public Seat[][] getSeats() {
		return seats;
	}

	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}

	public ProjectorType[] getProjector_types() {
		return projector_types;
	}

	public void setProjector_types(ProjectorType[] projector_types) {
		this.projector_types = projector_types;
	}
	
	
}
