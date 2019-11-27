package system_design.project.hall_planning_service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Seat {
	@Id
	@GeneratedValue
	private long seatId;

	@Column(name="hallColumn")
	private int column;
	
	@Column(name="hallRow")
	private int row;
	
	@Enumerated(EnumType.STRING)
	private TypeOfSeat typeOfSeat;

	@ManyToOne
	@JoinColumn(name="hall_id",nullable=false)
	private MovieHall movieHall;
	
	//-------------------------------------------------
	
	/*Seat(){
		typeOfSeat=TypeOfSeat.normal_seat;
		column=1;
		row=1;
	}*/
	
	public long getSeatId() {
		return seatId;
	}

	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}
	
	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public TypeOfSeat getTypeOfSeat() {
		return typeOfSeat;
	}

	public void setTypeOfSeat(TypeOfSeat typeOfSeat) {
		this.typeOfSeat = typeOfSeat;
	}

}
