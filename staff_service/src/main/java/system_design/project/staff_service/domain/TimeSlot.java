package system_design.project.staff_service.domain;

import com.datastax.driver.core.LocalDate;
import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalTime;
import java.util.UUID;

@Table
public class TimeSlot {
    @Column
    UUID id;

    @PrimaryKeyColumn(value="cinema_id", type = PrimaryKeyType.PARTITIONED, ordinal = 0)
    Long cinemaId;

    @PrimaryKeyColumn(value="day", type = PrimaryKeyType.PARTITIONED, ordinal = 1)
    LocalDate day;

    @PrimaryKeyColumn(value = "time", type=PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    LocalTime timeslot;

    @Column("employee_id")
    UUID employeeId;

    @Column("availibility_code")
    int availabilityCode;

    public TimeSlot(Long cinemaId, LocalDate day, LocalTime timeslot, UUID employeeId, int availabilityCode){
        this.id = UUIDs.timeBased();
        this.cinemaId = cinemaId;
        this.day = day;
        this.timeslot = timeslot;
        this.employeeId = employeeId;
        this.availabilityCode = availabilityCode;
    }

    public TimeSlot() {
    }


    public Long getCinemaId() {
        return cinemaId;
    }

    public LocalDate getDay() {
        return day;
    }

    public LocalTime getTimeslot() {
        return timeslot;
    }

    public UUID getEmployeeId() {
        return employeeId;
    }

    public int getAvailabilityCode() {
        return availabilityCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCinemaId(Long cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public void setTimeslot(LocalTime timeslot) {
        this.timeslot = timeslot;
    }

    public void setEmployeeId(UUID employeeId) {
        this.employeeId = employeeId;
    }

    public void setAvailabilityCode(int availabilityCode) {
        this.availabilityCode = availabilityCode;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof TimeSlot)) return false;
        final TimeSlot other = (TimeSlot) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$cinemaId = this.getCinemaId();
        final Object other$cinemaId = other.getCinemaId();
        if (this$cinemaId == null ? other$cinemaId != null : !this$cinemaId.equals(other$cinemaId)) return false;
        final Object this$day = this.getDay();
        final Object other$day = other.getDay();
        if (this$day == null ? other$day != null : !this$day.equals(other$day)) return false;
        final Object this$timeslot = this.getTimeslot();
        final Object other$timeslot = other.getTimeslot();
        if (this$timeslot == null ? other$timeslot != null : !this$timeslot.equals(other$timeslot)) return false;
        final Object this$employeeId = this.getEmployeeId();
        final Object other$employeeId = other.getEmployeeId();
        if (this$employeeId == null ? other$employeeId != null : !this$employeeId.equals(other$employeeId))
            return false;
        if (this.getAvailabilityCode() != other.getAvailabilityCode()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof TimeSlot;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $cinemaId = this.getCinemaId();
        result = result * PRIME + ($cinemaId == null ? 43 : $cinemaId.hashCode());
        final Object $day = this.getDay();
        result = result * PRIME + ($day == null ? 43 : $day.hashCode());
        final Object $timeslot = this.getTimeslot();
        result = result * PRIME + ($timeslot == null ? 43 : $timeslot.hashCode());
        final Object $employeeId = this.getEmployeeId();
        result = result * PRIME + ($employeeId == null ? 43 : $employeeId.hashCode());
        result = result * PRIME + this.getAvailabilityCode();
        return result;
    }

    public String toString() {
        return "TimeSlot(id=" + this.getId() + ", cinemaId=" + this.getCinemaId() + ", day=" + this.getDay() + ", timeslot=" + this.getTimeslot() + ", employeeId=" + this.getEmployeeId() + ", availabilityCode=" + this.getAvailabilityCode() + ")";
    }
}
