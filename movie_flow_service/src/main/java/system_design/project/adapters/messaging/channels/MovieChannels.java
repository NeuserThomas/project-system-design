package system_design.project.adapters.messaging.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.binding.MessageChannelAndSourceConfigurer;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MovieChannels {
	// movie
	// output
	static final String START_MOVIE = "start_movie";
    static final String END_MOVIE = "end_movie";
    // input
    static final String MOVIE_STARTED = "movie_started";
    static final String MOVIE_ENDED = "movie_ended";

    // movie breaks
    // output
    static final String START_MOVIE_BREAK = "start_movie_break";
    static final String END_MOVIE_BREAK = "end_movie_break";
    // input
    static final String MOVIE_BREAK_STARTED = "movie_break_started";
    static final String MOVIE_BREAK_ENDED = "movie_break_ended";

    // advertisements
    // TODO: input
    
    // output
    static final String ADVERTISEMENT_STARTED = "advertisement_started";
    static final String ADVERTISEMENT_ENDED = "advertisement_ended";

    
    
    // MOVIE BINDINGS

    @Output(START_MOVIE)
    MessageChannel startMovie();
    @Output(END_MOVIE)
    MessageChannel endMovie();
    
    @Input(MOVIE_STARTED)
    SubscribableChannel movieStarted();
    @Input(MOVIE_ENDED)
    SubscribableChannel movieEnded();
    
    // MOVIE BREAK BINDINGS
    @Output(START_MOVIE_BREAK)
    MessageChannel startMovieBreak();
    @Output(END_MOVIE_BREAK)
    MessageChannel endMovieBreak();
    
    @Input(MOVIE_BREAK_STARTED)
    SubscribableChannel movieBreakStarted();
    @Input(MOVIE_BREAK_ENDED)
    SubscribableChannel movieBreakEnded();
    
    // ADVERTISEMENT BINDINGS
    
    //TODO: @Output(...)
    //TODO: MessageChannel startAdvertisement();
    //TODO: @Output(...)
    //TODO: MessageChannel endAdvertisement();
    
    @Input(ADVERTISEMENT_STARTED)
    SubscribableChannel advertisementStarted();
    @Input(ADVERTISEMENT_ENDED)
    SubscribableChannel advertisementEnded();
}
