package system_design.project.staff_service.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system_design.project.staff_service.domain.TimeSlot;

import java.util.UUID;

@Repository
public interface TimeSlotRepository extends CrudRepository<TimeSlot, UUID> {

}
