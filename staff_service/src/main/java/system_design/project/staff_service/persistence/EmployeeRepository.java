package system_design.project.staff_service.persistence;


import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system_design.project.staff_service.domain.Employee;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, UUID> {


    @Query(allowFiltering = true)
    List<Employee> findEmployeeByFirstName(String firstName);
}
