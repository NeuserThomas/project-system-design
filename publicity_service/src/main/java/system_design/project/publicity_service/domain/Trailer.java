package system_design.project.publicity_service.domain;

import java.time.Duration;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 
 * @author Jasper Derbaix
 * 
 * A Trailer is a short movie that is made to advertise a movie.
 *
 */
@Entity
@DiscriminatorValue("T")
public class Trailer extends AMovie {
	private long id_Movie; //id of the corresponding movie

	public Trailer() {
		super(null, null, null);
	}
	
	public Trailer(Duration duration, Category category, String name, long id_Movie) {
		super(duration, category, name);
		// TODO Auto-generated constructor stub
		this.id_Movie = id_Movie;
	}

	public long getId_Movie() {
		return id_Movie;
	}

	public void setId_Movie(long id_Movie) {
		this.id_Movie = id_Movie;
	}

}
