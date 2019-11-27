package system_design.project.hall_planning_service.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
/**
 * Describes a class that represents a movie hall (NL: zaal).
 * @author robin
 *
 */
public class MovieHall{
	
	@Id
	private long hall_id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "movieHall")
	private List<Seat> seats;
	
	@Column(name = "projectorTypes", nullable = false)
    @ElementCollection(targetClass=ProjectorType.class)
	@Enumerated(EnumType.STRING)
	private List<ProjectorType> projector_types;
	
		
	//------------ separation declarations and methods ------------------------

	/**
	 * Returns the amount of total seats
	 * @return the amount of available seats
	 */
	public int get_seats_count() {
		return seats.size();
	}
	
	public long getHall_id() {
		return hall_id;
	}

	public void setHall_id(long hall_id) {
		this.hall_id = hall_id;
	}
	
	public List<ProjectorType> getProjector_types() {
		return projector_types;
	}

	public void setProjector_types(List<ProjectorType> projector_types) {
		this.projector_types = projector_types;
	}
	
	
}
