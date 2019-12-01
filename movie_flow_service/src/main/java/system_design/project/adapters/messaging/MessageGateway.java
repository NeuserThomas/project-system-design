package system_design.project.adapters.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import system_design.project.adapters.messaging.channels.MovieChannels;

@MessagingGateway
public interface MessageGateway {
	
	
	@Gateway(requestChannel = MovieChannels.START_MOVIE)
	public void startMovie(int hallID);
	@Gateway(requestChannel = MovieChannels.END_MOVIE)
	public void endMovie(int hallID);

	@Gateway(requestChannel = MovieChannels.START_MOVIE_BREAK)
	public void startMovieBreak(int hallID, int duration);
	@Gateway(requestChannel = MovieChannels.END_MOVIE_BREAK)
	public void endMovieBreak(int hallID);
	
	
}
