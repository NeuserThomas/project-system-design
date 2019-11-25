package system_design.project.ticket_management_service.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Movie implements Serializable {

    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    //maybe duration is more interesting then using both start and endTime? we'll see later on
    private double duration;
    private int hallNumber;

    public Movie(String name, LocalDateTime startTime, LocalDateTime endTime, int hallNumber){
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hallNumber = hallNumber;
    }

}
