package system_design.project.ticket_management_service.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import system_design.project.ticket_management_service.domain.Screening;

public interface ScreeningRepository extends CrudRepository<Screening, Long>{

	@Query("Select t from Screening t where DATE(t.startTime)=DATE(:date)")
	public List<Screening> findbyDate(LocalDate date);
	
}
