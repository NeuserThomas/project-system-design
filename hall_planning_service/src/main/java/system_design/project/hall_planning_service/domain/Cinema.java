package system_design.project.hall_planning_service.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cinema {
	@Id
	private long CinemaId;
	
	@OneToMany
	private MovieHall[] halls;
	
	private String cinemaName;
	

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
}
