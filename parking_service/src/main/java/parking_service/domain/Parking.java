package parking_service.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Parking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
//	private List<ParkingSpot> parkingSpots;
	private int numberOfFreeSpots;
	
	public Parking() {
		this.numberOfFreeSpots = 200;
	}
	
	public Parking(int numberOfSpots) {
		
//		parkingSpots = new ArrayList<>();
		this.numberOfFreeSpots = numberOfSpots;
		
//		for(int i=0; i<numberOfSpots; i++) {
//			parkingSpots.add(new ParkingSpot());
//		}
	}
	
	public int getNumberOfFreeSpots() {
		return this.numberOfFreeSpots;
	}
	
	//nog een check toevoegen of dit niet onder 0 kan gaan
	public void takeSpot() {
		this.numberOfFreeSpots--;
	}
	
	public void freeSpot() {
		this.numberOfFreeSpots++;
	}

}







