package system_design.project.ticket_management_service.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Screening {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String movieName;
	private long hallNumber;
	private LocalDateTime startTime;
	private LocalDateTime stopTime;
	private int availableSeats;
	private int soldTickets;

	private Screening() {
	}

	public Screening(String movieName, long hallNumber, int availableSeats) {
		this.movieName = movieName;
		this.hallNumber = hallNumber;
		this.soldTickets = 0;
		this.availableSeats = availableSeats;
		// this.startTime = startTime;
	}
	
	public Screening(ScreeningProxy proxy) {
		this.id = proxy.getTid();
		this.movieName = proxy.getMovieId();
		this.hallNumber = proxy.getHall().getHallNumber();
		this.startTime = proxy.getStartTime();
		this.stopTime = proxy.getStopTime();
		this.availableSeats = proxy.getHall().getSeatCount();
		this.soldTickets = 0;
	}

	public void sellTicket() {
		this.soldTickets++;
	}
	
	public void cancelTicket() {
		this.soldTickets--;
	}

	public String toString() {
		return "" + this.id + ": " + this.movieName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public long getHallNumber() {
		return hallNumber;
	}

	public void setHallNumber(long hallNumber) {
		this.hallNumber = hallNumber;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public int getSoldTickets() {
		return soldTickets;
	}

	public void setSoldTickets(int soldTickets) {
		this.soldTickets = soldTickets;
	}

}
