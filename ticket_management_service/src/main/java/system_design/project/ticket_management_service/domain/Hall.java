package system_design.project.ticket_management_service.domain;

public class Hall {
	private int hallId;
	private int hallNumber;
	private int[] projectorTypes;
	private int seatCount;
	
	public Hall() {}
	
	public Hall(int hallNumber, int[] projectorTypes, int seatCount) {
		this.hallNumber = hallNumber;
		this.projectorTypes = projectorTypes;
		this.seatCount = seatCount;
	}

	public int getHallId() {
		return hallId;
	}

	public void setHallId(int hallId) {
		this.hallId = hallId;
	}

	public int getHallNumber() {
		return hallNumber;
	}

	public void setHallNumber(int hallNumber) {
		this.hallNumber = hallNumber;
	}

	public int[] getProjectorTypes() {
		return projectorTypes;
	}

	public void setProjectorTypes(int[] projectorTypes) {
		this.projectorTypes = projectorTypes;
	}

	public int getSeatCount() {
		return seatCount;
	}

	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	
	public String toString() {
		return "Hall: " + hallId + " seats: " + seatCount;
	}

}
