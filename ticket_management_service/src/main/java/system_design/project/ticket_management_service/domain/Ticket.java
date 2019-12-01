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
    private String movie;
    private LocalDateTime startTime;
    //endTime is necessary for validating your parking ticket
    private LocalDateTime endTime;
    private TicketStatus status;

    //default constructor used for JPA (is necessary)
    public Ticket(){
        this.price = null;
        this.movie = null;
    }

    //ticket is made, not yet sold
    public Ticket(Double price, String movie){
        this.status = TicketStatus.AVAILABLE;
        this.price = price;
        this.movie = movie;
    }

    //ticket gets sold
    public void sellTicket(){
        this.status = TicketStatus.SOLD;
    }

    //add discount, discount value 0 < discount < 1
    public void addDiscount(double discount){
        this.price *= discount;
    }

    @Override
    public String toString(){
        return "Ticket " +  id + ": Movie: " + movie + ", price: " + price;
    }

    //added getters/ setters for price and movie name, can eventually be usable in the future
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

}
