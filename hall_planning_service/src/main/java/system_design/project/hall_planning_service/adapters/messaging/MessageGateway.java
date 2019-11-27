package system_design.project.hall_planning_service.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import system_design.project.hall_planning_service.domain.Day;

@MessagingGateway
public interface MessageGateway {

	@Gateway(requestChannel = Channels.PLANNING_UPDATED)
	public void updateDay(String message);
}
