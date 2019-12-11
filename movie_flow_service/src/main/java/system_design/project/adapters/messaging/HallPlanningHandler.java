package system_design.project.adapters.messaging;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import system_design.project.adapters.messaging.channels.PlanningChannels;
import system_design.project.service.MovieFlowService;

/**
 * HallPlanningHandler instructs the MovieFlowService according to the events being received
 * on the channels.
 * @author gertjandm
 *
 */
@Service
public class HallPlanningHandler {
	private static Logger logger = LoggerFactory.getLogger(HallPlanningHandler.class);

	private final MovieFlowService movieFlowService;
	
	@Autowired
	public HallPlanningHandler(MovieFlowService movieFlowService) {
		this.movieFlowService = movieFlowService;
	}
	
	
	/**
	 * Triggered when receiving a message on the PLANNING_UPDATED channel.
	 */
	@StreamListener(PlanningChannels.PLANNING_UPDATED)
	public void updatePlanning() {
		logger.info("received PLANNING_UPDATED event!");
	}
	
}
