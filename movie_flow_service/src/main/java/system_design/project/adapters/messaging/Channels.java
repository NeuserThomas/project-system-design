package system_design.project.adapters.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    // movie
    static final String MOVIE_STARTED = "movie_started";
    static final String MOVIE_ENDED = "movie_ended";
    // movie breaks
    static final String MOVIE_BREAK_STARTED = "movie_break_started";
    static final String MOVIE_BREAK_ENDED = "movie_break_ended";
    // advertisements
    static final String ADVERTISEMENT_STARTED = "advertisement_started";
    static final String ADVERTISEMENT_ENDED = "advertisement_ended";
    // lights
    static final String LIGHTS_DIMMED = "lights_dimmed";
    static final String LIGHTS_ACTIVATED = "lights_activated";

    
    @Input(MOVIE_STARTED)
    SubscribableChannel movieStarted();
    @Input(MOVIE_ENDED)
    SubscribableChannel movieEnded();
    
    @Input(MOVIE_BREAK_STARTED)
    SubscribableChannel movieBreakStarted();
    @Input(MOVIE_BREAK_ENDED)
    SubscribableChannel movieBreakEnded();
    
    @Input(ADVERTISEMENT_STARTED)
    SubscribableChannel advertisementStarted();
    @Input(ADVERTISEMENT_ENDED)
    SubscribableChannel advertisementEnded();
    
    @Input(LIGHTS_ACTIVATED)
    SubscribableChannel lightsActivated();
    @Input(LIGHTS_DIMMED)
    SubscribableChannel lightsDimmed();
    
    

	
}
