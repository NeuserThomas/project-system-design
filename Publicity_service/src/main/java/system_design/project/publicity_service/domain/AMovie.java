package main.java.system_design.project.publicity_service.domain;

import java.time.LocalTime;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class AMovie {
    @Id
    @GeneratedValue
    private long id;
    private String category;
    private LocalTime duration;

    public AMovie(){
    }
}
