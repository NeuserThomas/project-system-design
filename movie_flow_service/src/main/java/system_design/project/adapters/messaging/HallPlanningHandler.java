package system_design.project.adapters.messaging;


import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import system_design.project.adapters.messaging.channels.PlanningChannels;
import system_design.project.domain.TimeSlot;
import system_design.project.service.MovieFlowService;

/**
 * HallPlanningHandler instructs the MovieFlowService according to the events being received
 * on the channels.
 * @author gertjandm rgoussey
 *
 */
@Service
public class HallPlanningHandler {

	@Autowired
	private MovieFlowService movieFlowService;
	
	private Logger logger = LoggerFactory.getLogger(HallPlanningHandler.class);
	@Autowired
	private Environment env;
		
	/**
	 * Triggered when receiving a message on the PLANNING_UPDATED channel. Since we have nothing to send to, it just logs
	 */
	@StreamListener(PlanningChannels.PLANNING_UPDATED)
	public void updatePlanning(String message) {
		logger.info("received PLANNING_UPDATED event!: "+message);
		LocalDate date = LocalDate.parse(message);
		try {
			RestTemplate rt = new RestTemplate();
			TimeSlot[] timeslots = rt.getForObject(env.getProperty("planning.url")+message, TimeSlot[].class);
			logger.info("Received timeslots!");
			movieFlowService.addEvents(timeslots);
		}catch (Exception e) {
			logger.error(""+e);
		}
		
	}
	
	
	
}
