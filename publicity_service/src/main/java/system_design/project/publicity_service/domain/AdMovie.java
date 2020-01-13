package system_design.project.publicity_service.domain;

import java.time.LocalDate;
import java.text.MessageFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * 
 * @author Jasper Derbaix
 * 
 * An AdMovie is the combination of trailers, advertisements of the cinema and other companies and so on,
 * that is played before the movie is played itself.
 *
 */
@Entity
@DiscriminatorValue("P")
public class AdMovie extends AMovie {
	//minimal amount of minutes the duration of an adMovie has to be (if possible)
	public static final int minimalMinutes = 25;
	public static final int maximalMinutes = minimalMinutes + 5;
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AMovie> playlist;
	
	//day from which the film may be used
	private LocalDate commissioningDate;
	
	public AdMovie() {
		super(null, null, null);
		this.playlist = new ArrayList<AMovie>();
	}

	public AdMovie(Duration duration, Category category, String name, List<AMovie> playlist, LocalDate commissioningDate) {
		super(duration, category, name);
		// TODO Auto-generated constructor stub
		this.playlist = playlist;
		this.commissioningDate = commissioningDate;
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

	public LocalDate getCommissioningDate() {
		return commissioningDate;
	}

	public void setCommissioningDate(LocalDate commissioningDate) {
		this.commissioningDate = commissioningDate;
	}
	
	@Override
	public String toString() {
		return MessageFormat.format("{0}\t CommissioningDate: {1}" ,
				super.toString(), this.commissioningDate);
	}
}
