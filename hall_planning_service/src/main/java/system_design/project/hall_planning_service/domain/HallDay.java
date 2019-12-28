/**
 * 
 */
package system_design.project.hall_planning_service.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author robin
 *
 */
@Entity
public class HallDay {

	@Id
	@GeneratedValue
	private long hallDayId;
	
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL)
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
	
	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(List<TimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}
	
	public long getHallDayId() {
		return hallDayId;
	}

	public void setHallDayId(long hallDayId) {
		this.hallDayId = hallDayId;
	}
}
