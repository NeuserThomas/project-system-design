package system_design.project.staff_service.persistence;


import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system_design.project.staff_service.domain.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(allowFiltering = true)
    List<Employee> findEmployeeByFirstName(String firstName);
}
