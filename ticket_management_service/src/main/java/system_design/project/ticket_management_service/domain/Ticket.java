package system_design.project.ticket_management_service.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double price;
    //movie is a string atm, maybe change it to an Entity?
    private long screeningId;
    
    @Column(columnDefinition = "boolean default false")
    private boolean validatedParking;

    //default constructor used for JPA (is necessary)
    public Ticket(){
        this.price = 10.0;
        this.screeningId = 0;
        this.validatedParking = false;
    }

    //ticket is made, not yet sold
    public Ticket(long screeningId){
        this.price = 10.0;
        this.screeningId = screeningId;
        this.validatedParking = false;
    }

    //ticket gets sold
//    public void sellTicket(){
//        this.status = TicketStatus.SOLD;
//    }

    //add discount, discount value 0 < discount < 1
    public void addActionDiscount(double discount){
        this.price *= discount;
    }

    @Override
    public String toString(){
        return "Ticket " +  id + ": Screening: " + screeningId + ", price: " + price;
    }

    public long getScreeningId() {
		return screeningId;
	}

	public void setScreeningId(long screeningId) {
		this.screeningId = screeningId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	//added getters/ setters for price and movie name, can eventually be usable in the future
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    
    public boolean getParkingValidated() {
    	return this.validatedParking;
    }
    
    public void setParkingValidated(boolean validated) {
        this.validatedParking = validated;
    }
}
