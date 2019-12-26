/**
 * 
 */
package system_design.project.hall_planning_service.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author robin
 *
 */
@Entity
public class HallDay {

	@Id
	@GeneratedValue
	private long id;
	
	@ElementCollection
	private List<TimeSlot> timeSlots;

	public HallDay() {
		timeSlots= new ArrayList<TimeSlot>();
	}
	
	/**
	 * @param timeSlot
	 */
	public void addTimeSlot(TimeSlot timeSlot) {
		if(timeSlots==null) {
			timeSlots= new ArrayList<TimeSlot>();
		}
		timeSlots.add(timeSlot);
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}

	
	
}
