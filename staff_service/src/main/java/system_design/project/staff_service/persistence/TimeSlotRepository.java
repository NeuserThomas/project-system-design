package system_design.project.staff_service.persistence;

import com.datastax.driver.core.LocalDate;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system_design.project.staff_service.domain.TimeSlot;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, UUID> {

    @Query(allowFiltering = true)
    List<TimeSlot> getTimeSlotByCinemaId(Long cinemaId);

    @Query(allowFiltering = true)
    List<TimeSlot> getTimeSlotsByDay(LocalDate day);

    @Query(allowFiltering = true)
    List<TimeSlot> getTimeSlotsByDayAndCinemaId(LocalDate day, Long cinemaId);

    @Query(allowFiltering = true)
    List<TimeSlot> getTimeSlotsByCinemaIdAndEmployeeId(Long cinemaId, UUID employeeId);

    @Query(allowFiltering = true)
    List<TimeSlot> getTimeSlotsByDayAndCinemaIdAndEmployeeId(LocalDate day, Long cinemaId, UUID employeeId);

    @Query(allowFiltering = true)
    List<TimeSlot> getTimeSlotsByDayAndTimeslotAndCinemaIdAndEmployeeId(LocalDate day, LocalTime timeslot, Long cinemaId, UUID employeeId);


}
