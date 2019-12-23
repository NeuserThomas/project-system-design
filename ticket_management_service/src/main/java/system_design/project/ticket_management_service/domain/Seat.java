package system_design.project.ticket_management_service.domain;

public class Seat {
	
	private long id;
	private int column;
	private int row;
	private boolean occupied;
	
	public Seat() {
		this.column = 0;
		this.row = 0;
		this.occupied = false;	
	}
	
	public Seat(int column, int row) {
		this.column = column;
		this.row = row;
		this.occupied = false;
	}
	
	public void occupySeat() {
		this.occupied = true;
	}
	
	public void freeSeat() {
		this.occupied = false;
	}

}
