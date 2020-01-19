package system_design.project.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
/**
 * Describes a class that represents a movie hall (NL: zaal).
 * @author robin
 *
 */
@Table(
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"id", "hallNumber"})
	)
public class MovieHall{
	
	@Id
	@GeneratedValue
	private long hallId;
	
	@Column(nullable=false)
	private int hallNumber;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id")
	@JsonIgnore
	private Cinema cinema;
		
		
	//------------ separation declarations and methods ------------------------
	
	public long getHallId() {
		return hallId;
	}

	public void setHall_id(long hallId) {
		this.hallId = hallId;
	}
	
	public int getHallNumber() {
		return hallNumber;
	}

	public void setHallNumber(int hall_number) {
		this.hallNumber = hall_number;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}	
}
