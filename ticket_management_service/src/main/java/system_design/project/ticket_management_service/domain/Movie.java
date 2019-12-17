package system_design.project.ticket_management_service.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
    private String name;
    private long hallId;
    private LocalDateTime startTime;
  
    private Movie() {}
    
    public Movie(String name, long hallId){
        this.name = name;
        this.hallId = hallId;
        //this.startTime = startTime;
    }
    
    public String toString() {
    	return "" + this.id + ": " + this.name;
    }


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public long getHallId() {
		return hallId;
	}


	public void setHallId(long hallId) {
		this.hallId = hallId;
	}


	public LocalDateTime getStartTime() {
		return startTime;
	}


	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

}
