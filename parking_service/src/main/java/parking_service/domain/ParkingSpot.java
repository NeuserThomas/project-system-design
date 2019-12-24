package parking_service.domain;

public class ParkingSpot {

	private boolean occupied;
	
	public ParkingSpot() {
		this.occupied = false;
	}
	
	public void occupyParkingSpot() {
		this.occupied = true;
	}
	
	public void freeParkingSpot() {
		this.occupied = false;
	}
	
}
