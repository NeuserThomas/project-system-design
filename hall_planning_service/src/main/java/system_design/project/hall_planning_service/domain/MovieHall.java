package system_design.project.hall_planning_service.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
/**
 * Describes a class that represents a movie hall (NL: zaal).
 * @author robin
 *
 */
public class MovieHall{
	
	@Id
	@GeneratedValue
	private long hallId;
	
	private int hallNumber;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id")
	@JsonIgnore
	private Cinema cinema;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "movieHall",cascade = CascadeType.ALL)
	private List<Seat> seats;

	@Column(name = "projectorTypes", nullable = false)
    @ElementCollection(targetClass=ProjectorType.class)
	@Enumerated(EnumType.STRING)
	private List<ProjectorType> projectorTypes;
	
		
	//------------ separation declarations and methods ------------------------

	/**
	 * Returns the amount of total seats
	 * @return the amount of available seats
	 */
	public int get_seats_count() {
		return seats.size();
	}
	
	public long getHallId() {
		return hallId;
	}

	public void setHall_id(long hallId) {
		this.hallId = hallId;
	}
	
	public List<ProjectorType> getProjectorTypes() {
		return projectorTypes;
	}

	public void setProjectorTypes(List<ProjectorType> projector_types) {
		this.projectorTypes = projector_types;
	}

	public int getHall_number() {
		return hallNumber;
	}

	public void setHall_number(int hall_number) {
		this.hallNumber = hall_number;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
}
