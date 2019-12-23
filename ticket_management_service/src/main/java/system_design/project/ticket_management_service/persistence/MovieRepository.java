package system_design.project.ticket_management_service.persistence;

import org.springframework.data.repository.CrudRepository;

import system_design.project.ticket_management_service.domain.Movie;

public interface MovieRepository extends CrudRepository<Movie, Long>{

}
