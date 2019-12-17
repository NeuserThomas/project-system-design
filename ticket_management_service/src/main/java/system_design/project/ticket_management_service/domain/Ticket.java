package system_design.project.ticket_management_service.domain;

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
    private long movieId;
    private int seat;
    private boolean validatedParking;

    //default constructor used for JPA (is necessary)
    public Ticket(){
        this.price = null;
        this.movieId = 0;
        this.validatedParking = false;
    }

    //ticket is made, not yet sold
    public Ticket(Double price, long movieId){
        this.price = price;
        this.movieId = movieId;
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
        return "Ticket " +  id + ": Movie: " + movieId + ", price: " + price;
    }

    //added getters/ setters for price and movie name, can eventually be usable in the future
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public long getMovie() {
        return movieId;
    }

    public void setMovie(long movieId) {
        this.movieId = movieId;
    }
    
    public boolean getParkingValidated() {
    	return this.validatedParking;
    }
    
    public int getSeat() {
    	return this.seat;
    }

}
