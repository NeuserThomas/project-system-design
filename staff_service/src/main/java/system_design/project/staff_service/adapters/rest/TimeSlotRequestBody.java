package system_design.project.staff_service.adapters.rest;

import java.time.LocalTime;
import java.util.UUID;

public class TimeSlotRequestBody {
    //LocalDate day;
    LocalTime time;
    UUID employeeId;
    Long cinemaId;
    int availabilityCode;


    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public Long getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getAvailabilityCode() {
        return availabilityCode;
    }

    public void setAvailabilityCode(int availabilityCode) {
        this.availabilityCode = availabilityCode;
    }
}
