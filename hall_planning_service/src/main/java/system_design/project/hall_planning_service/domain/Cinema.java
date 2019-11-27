package system_design.project.hall_planning_service.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Cinema {
	@Id
	private long CinemaId;
	
	@OneToMany
	@OrderColumn
	private MovieHall[] halls;
	
	private String cinemaName;
	
	//TODO: Add address etc
	
	//-----------------------------------------------
	
	public long getCinemaId() {
		return CinemaId;
	}

	public void setCinemaId(long cinemaId) {
		CinemaId = cinemaId;
	}

	public MovieHall[] getHalls() {
		return halls;
	}

	public void setHalls(MovieHall[] halls) {
		this.halls = halls;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
}
