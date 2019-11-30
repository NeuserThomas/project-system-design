package system_design.API_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	@Value(value="${connection.hall_container}")
	private String hall_planning; 
	
	final Logger logger = LoggerFactory.getLogger(ApiGatewayApplication.class);

	
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		String hallConnectionString="http://"+hall_planning+":2223";
		logger.info(hallConnectionString);
		return builder.routes()
		//hall_planning_service
		.route(r ->r.host("*").and().path("/planning/**").uri(hallConnectionString))
		.route(r ->r.host("*").and().path("/hall/**").uri(hallConnectionString))
		.route(r ->r.host("*").and().path("/Cinema/**").uri(hallConnectionString))
		//Ticketing:
		
		//etc
		
		.build();
		
		// add your other routes here.build();}
	}
	
}
