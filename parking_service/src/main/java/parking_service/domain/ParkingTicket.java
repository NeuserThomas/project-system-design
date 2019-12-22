package parking_service.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParkingTicket {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	private LocalDateTime enterTime;
	private LocalDateTime validationTime;
	
	@Column(columnDefinition = "boolean default false")
	private boolean validated;
	
	public ParkingTicket() {
		this.enterTime = LocalDateTime.now();
		this.validationTime = null;
		this.validated = false;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(LocalDateTime enterTime) {
		this.enterTime = enterTime;
	}

	public LocalDateTime getValidationTime() {
		return validationTime;
	}

	public void setValidationTime(LocalDateTime validationTime) {
		this.validationTime = validationTime;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	
}
