package system_design.project.adapters.messaging.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface PlanningChannels {
	
	static final String PLANNING_UPDATED = "planning_updated";

	@Input(PLANNING_UPDATED)
	SubscribableChannel planningUpdated(); 
}
