package system_design.project.publicity_service.domain;

import java.time.LocalDate;
import java.time.Duration;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

/**
 * 
 * @author Jasper Derbaix
 * 
 * An Ad_Movie is the combination of trailers, advertisements of the cinema and other companies and so on,
 * that is played before the movie is played itself.
 *
 */
@Entity
@DiscriminatorValue("P")
public class Ad_Movie extends AMovie {
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AMovie> playlist;
	private LocalDate creationDate;
	
	public Ad_Movie() {
		super(0, null, null, null);
	}

	public Ad_Movie(long id, Duration duration, Category category, String name, List<AMovie> playlist, LocalDate creationDate) {
		super(id, duration, category, name);
		// TODO Auto-generated constructor stub
		this.playlist = playlist;
		this.creationDate = creationDate;
	}

	public List<AMovie> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<AMovie> playlist) {
		this.playlist = playlist;
		
		//recalculate duration
		Duration newDuration = Duration.ZERO;
		if(playlist != null) {
			for(AMovie aMovie : playlist) {
				newDuration = newDuration.plus(aMovie.getDuration());
			}
		}
		this.setDuration(newDuration);
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

}
