package system_design.project.staff_service.domain;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value="employee")
public class Employee {

    @Id
    @PrimaryKey("employee_id") private String id;
    @Column("first_name") private String firstName;


    public Employee(String firstName) {

        this.id = UUIDs.timeBased().toString();
        this.firstName = firstName;

    }

    public String getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = id;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
