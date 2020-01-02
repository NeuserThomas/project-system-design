package system_design.project.staff_service.adapters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import system_design.project.staff_service.domain.TimeSlot;
import system_design.project.staff_service.persistence.TimeSlotRepository;

@RestController
@RequestMapping("/timeslot")
public class TimeSlotRestController {
    private static final Logger logger = LoggerFactory.getLogger(TimeSlotRestController.class);
    private TimeSlotRepository repo;

    @Autowired
    private TimeSlotRestController(TimeSlotRepository repo){
        this.repo = repo;
    }

    @GetMapping("/try")
    public Iterable<TimeSlot> getAllSimpleTimeSlots(){
        return this.repo.findAll();
    }


}
