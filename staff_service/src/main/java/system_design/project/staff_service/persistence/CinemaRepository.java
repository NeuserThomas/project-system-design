package system_design.project.staff_service.persistence;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system_design.project.staff_service.domain.Cinema;

import java.util.List;

@Repository
public interface CinemaRepository extends CrudRepository<Cinema, Long> {

    @Query(allowFiltering = true)
    List<Cinema> findCinemaById(String id);

    @Query(allowFiltering = true)
    List<Cinema> findCinemaByName(String name);
}
