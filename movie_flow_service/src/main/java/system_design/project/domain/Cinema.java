package system_design.project.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Cinema {
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cinema",cascade = CascadeType.ALL)
	private List<MovieHall> halls;
	
	@Column(unique=true)
	private String cinemaName;
	//-----------------------------------------------
	
	public Cinema() {
		//plannedMovies=new PlannedMovies();
		halls = new ArrayList<>();
	}
	

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
