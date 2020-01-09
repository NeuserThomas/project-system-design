package system_design.project.ticket_management_service.persistence;

import org.springframework.data.repository.CrudRepository;

import system_design.project.ticket_management_service.domain.Screening;

public interface ScreeningRepository extends CrudRepository<Screening, Long>{

}
