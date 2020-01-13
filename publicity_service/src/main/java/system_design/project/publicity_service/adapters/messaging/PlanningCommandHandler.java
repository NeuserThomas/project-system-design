package system_design.project.publicity_service.adapters.messaging;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class PlanningCommandHandler {
	
	
	@StreamListener(Channels.PLANNING_UPDATED)
	public void a(String message) {
		//how is the message structured? how can I extract the day?
		
	}
}
