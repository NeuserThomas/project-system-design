package system_design.project.hall_planning_service.adapters.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Channels {

	static final String PLANNING_UPDATED = "planning_updated";
	static final String NEW_MOVIE = "new_movie";

	
	@Output(PLANNING_UPDATED)
	MessageChannel updatePlanning();
	
	@Output(NEW_MOVIE)
	MessageChannel newMovie();
	
}
