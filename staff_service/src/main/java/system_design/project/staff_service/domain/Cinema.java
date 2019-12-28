package system_design.project.staff_service.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

//@Getter @Setter @Data
@Table(value="cinemas")
public class Cinema {

	@PrimaryKey("cinema_id")
	private long id;
	private String cinemaName;



}
