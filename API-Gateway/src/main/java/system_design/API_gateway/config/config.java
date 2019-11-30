package system_design.API_gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "connection")
public class config {
	/*
	 * Sets address for the connection to the container
	 */
	private String hall_container;
	

	public String getHall_container() {
		return hall_container;
	}

	public void setHall_container(String hall_container) {
		this.hall_container = hall_container;
	}
}
