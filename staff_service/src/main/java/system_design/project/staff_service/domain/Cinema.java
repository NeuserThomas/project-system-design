package system_design.project.staff_service.domain;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table(value="cinema")
public class Cinema {

	@Id
	@PrimaryKey("cinema_id")
	private String id;
	private String name;

	public Cinema(String name){
		this.id = UUIDs.timeBased().toString();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
