package system_design.project.staff_service.persistence;


import org.springframework.data.repository.CrudRepository;
import system_design.project.staff_service.domain.TimeSlot;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {

}
