package system_design.project.staff_service.adapters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import system_design.project.staff_service.persistence.TimeSlotRepository;

@RestController("timeslot")
public class TimeSlotRestController {
        private static final Logger logger = LoggerFactory.getLogger(TimeSlotRestController.class);
    private TimeSlotRepository timeSlotRepository;

    @Autowired
    private TimeSlotRestController(TimeSlotRepository timeSlotRepository){
        this.timeSlotRepository = timeSlotRepository;


        logger.info("TimeSLotRestController constructor OK");
    }


}
