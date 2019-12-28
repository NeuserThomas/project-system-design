package system_design.project.staff_service.domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;


@Table(value ="timeslots")
public class TimeSlot {


    // TODO: create cinema entity
    @PrimaryKeyColumn(name="cinema_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String cinemaId;

    @PrimaryKeyColumn(name="employee_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String employeeId;


    @PrimaryKeyColumn(name="day", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    private LocalDate day;

    @PrimaryKeyColumn(value = "time", ordinal = 3, type=PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private LocalTime timeslot;

    @Column("availability_code")
    private int availabilityCode;


    public TimeSlot(String cinemaId, UUID employeeId, LocalDate day, LocalTime timeslot, int availabilityCode) {
        this.cinemaId = cinemaId;
        this.employeeId = employeeId.toString();
        this.day = day;
        this.timeslot = timeslot;
        this.availabilityCode = availabilityCode;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public LocalTime getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(LocalTime timeslot) {
        this.timeslot = timeslot;
    }

    public int getAvailabilityCode() {
        return availabilityCode;
    }

    public void setAvailabilityCode(int availabilityCode) {
        this.availabilityCode = availabilityCode;
    }
}
