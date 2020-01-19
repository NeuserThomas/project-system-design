package system_design.project.adapters.messaging.channels;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface DomoticChannels {
    // lights
    static final String LIGHTS_DIMMED = "lights_dimmed";
    static final String LIGHTS_ACTIVATED = "lights_activated";
    
    @Input(LIGHTS_ACTIVATED)
    SubscribableChannel lightsActivated();
    @Input(LIGHTS_DIMMED)
    SubscribableChannel lightsDimmed();
    
}
