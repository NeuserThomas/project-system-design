package system_design.project.hall_planning_service.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cinema {
	@Id
	@GeneratedValue
	private long id=0;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cinema",cascade = CascadeType.ALL)
	private List<MovieHall> halls;
	
	private String cinemaName;
	
	//TODO: Add address etc
	
	//-----------------------------------------------
	
	public long getId() {
		return id;
	}

	public void setId(long cinemaId) {
		this.id = cinemaId;
	}

	public List<MovieHall> getHalls() {
		return halls;
	}

	public void setHalls(List<MovieHall> halls) {
		this.halls = halls;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
}
