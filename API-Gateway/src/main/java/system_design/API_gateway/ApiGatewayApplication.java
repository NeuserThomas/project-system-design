package system_design.API_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}
	
	@Bean public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
		//hall_planning_service
		.route(r ->r.host("*").and().path("/planning/**").uri("http://localhost:2223"))
		.route(r ->r.host("*").and().path("/hall/**").uri("http://localhost:2223"))
		.route(r ->r.host("*").and().path("/Cinema/**").uri("http://localhost:2223"))
		//Ticketing:
		
		//etc
		
		.build();
		
		// add your other routes here.build();}
	}
	
}
