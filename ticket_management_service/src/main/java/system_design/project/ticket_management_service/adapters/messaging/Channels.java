package system_design.project.ticket_management_service.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

	static final String PLANNING_UPDATED = "planning_updated";

	@Input(PLANNING_UPDATED)
	SubscribableChannel updatePlanning();

}
