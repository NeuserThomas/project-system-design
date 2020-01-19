package system_design.project.hall_planning_service.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

	@Gateway(requestChannel = Channels.PLANNING_UPDATED)
	public void updateDay(String message);
	
	@Gateway(requestChannel = Channels.NEW_MOVIE)
	public void newMovie(String message);
}
