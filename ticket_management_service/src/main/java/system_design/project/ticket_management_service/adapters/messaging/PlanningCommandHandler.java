package system_design.project.ticket_management_service.adapters.messaging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import system_design.project.ticket_management_service.domain.CinemaProxy;
import system_design.project.ticket_management_service.domain.Screening;
import system_design.project.ticket_management_service.domain.ScreeningProxy;
import system_design.project.ticket_management_service.persistence.ScreeningRepository;

@Service
public class PlanningCommandHandler {
	private static Logger logger = LoggerFactory.getLogger(Screening.class);
	private ScreeningRepository sp;

	@Autowired
	public PlanningCommandHandler(ScreeningRepository sp) {
		this.sp = sp;
	}
	
	@StreamListener(Channels.PLANNING_UPDATED)
	public void processPlanningUpdated(String message) {
		
		RestTemplate rt = new RestTemplate();
		CinemaProxy[] cinemaProxies = rt.getForObject("http://localhost:2223/planning/cinema", CinemaProxy[].class);
		
		long cinemaId = cinemaProxies[0].getId();
		
		
		ScreeningProxy[] screeningProxies = rt.getForObject("http://localhost:2223/planning/timeslot/getByCinemaId/"+ String.valueOf(cinemaId) +"/" +message, ScreeningProxy[].class);
		
		for(int i=0; i<screeningProxies.length; i++) {
			Screening screening = new Screening(screeningProxies[i]);
			sp.save(screening);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
