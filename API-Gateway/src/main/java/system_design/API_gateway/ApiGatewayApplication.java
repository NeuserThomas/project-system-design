package system_design.API_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	
	
	@Value(value = "${connection.hall_container}")
	private String hall_planning;
	@Value(value = "${connection.shop_container}")
	private String shop;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	final Logger logger = LoggerFactory.getLogger(ApiGatewayApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		String hallConnectionString = "" + hall_planning + ":2223";
		String shopConnectionString = "" + shop + ":2230";
		logger.info(hallConnectionString);
		logger.info(shopConnectionString);
		return builder.routes()
				// hall_planning_service
				.route(r -> r.host("*").and().path("/planning**").uri(hallConnectionString))
				.route(r -> r.host("*").and().path("/hall**").uri(hallConnectionString))
				.route(r -> r.host("*").and().path("/cinema**").uri(hallConnectionString))
				.route(r -> r.host("*").and().path("/movie**").uri(hallConnectionString))
				// shop-service:
				.route(r -> r.host("*").and().path("/stock**").uri(shopConnectionString))
				.route(r -> r.host("*").and().path("/shopItem**").uri(shopConnectionString))
				.route(r -> r.host("*").and().path("/transaction**").uri(shopConnectionString))
				// etc

				.build();

		// add your other routes here.build();}
	}

}
