package system_design.project.staff_service.domain;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table(value="employees")
public class Employee {

    @PrimaryKey("employee_id") private UUID id;
    @Column("first_name") private String firstName;


    public Employee(String firstName) {
        this.id = UUIDs.timeBased();
        this.firstName = firstName;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
