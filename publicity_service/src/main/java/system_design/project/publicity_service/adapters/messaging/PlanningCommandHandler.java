package system_design.project.publicity_service.adapters.messaging;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import system_design.project.publicity_service.service.PublicityService;

@Service
public class PlanningCommandHandler {
	private Environment env;
	private PublicityService publicityService;
	
	@Autowired
	public PlanningCommandHandler(PublicityService publicityService) {
		this.publicityService = publicityService;
	}
	
	@StreamListener(Channels.PLANNING_UPDATED)
	public void a(String message) {
		RestTemplate rt = new RestTemplate();
		
		//get a cinema where there is recently planned a movie
		String cinemaUrl = "http://" + env.getProperty("planning.host.name") + ":"
						 + env.getProperty("planning.host.port") + "/planning/cinema";
		CinemaProxy[] cinemaProxies = rt.getForObject(cinemaUrl, CinemaProxy[].class);
		long cinemaId = cinemaProxies[0].getId();
		
		//get a movie that was recently planned, get it's startLocalDateTime
		String screeningUrl = "http://" + env.getProperty("planning.host.name") + ":" + env.getProperty("planning.host.port")
							+ "/planning/timeslot/getByCinemaId/" + String.valueOf(cinemaId) + "/" + message;
		ScreeningProxy[] screeningProxies = rt.getForObject(screeningUrl, ScreeningProxy[].class);
		LocalDateTime plannedDay = screeningProxies[0].getStartTime();
		
		//now the service needs to be notified of the newly plannedDay
		publicityService.setYetToPlan(plannedDay.toLocalDate());
	}
}
