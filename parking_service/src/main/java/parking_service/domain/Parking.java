package parking_service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private int numberOfFreeSpots;

	public Parking() {
	}

	public Parking(int numberOfSpots) {
		this.numberOfFreeSpots = numberOfSpots;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNumberOfFreeSpots(int numberOfFreeSpots) {
		this.numberOfFreeSpots = numberOfFreeSpots;
	}

	public int getNumberOfFreeSpots() {
		return this.numberOfFreeSpots;
	}

	public void takeSpot() {
		this.numberOfFreeSpots--;
	}

	public void freeSpot() {
		this.numberOfFreeSpots++;
	}

}
