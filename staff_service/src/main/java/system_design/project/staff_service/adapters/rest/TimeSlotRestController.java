package system_design.project.staff_service.adapters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system_design.project.staff_service.domain.TimeSlot;
import system_design.project.staff_service.persistence.TimeSlotRepository;

import java.time.LocalDate;

@RestController
@RequestMapping("/timeslot")
public class TimeSlotRestController {
    private static final Logger logger = LoggerFactory.getLogger(TimeSlotRestController.class);
    private TimeSlotRepository repo;

    @Autowired
    private TimeSlotRestController(TimeSlotRepository repo){
        this.repo = repo;
    }

    /**
     * Helper method for casting java.time.LocalDate to com.datastax.driver.core.LocalDate
     * @param x (java.time.LocalDate)
     * @return (com.datastax.driver.core.LocalDate)
     */
    private com.datastax.driver.core.LocalDate toDataStaxLocalData(LocalDate x){
        return com.datastax.driver.core.LocalDate.fromYearMonthDay(x.getYear(),x.getMonthValue(), x.getDayOfMonth());
    }

    @GetMapping("/")
    public @ResponseBody  ResponseEntity<Iterable<TimeSlot>> getAllTimeSlots(){
        Iterable<TimeSlot> timeSlots = this.repo.findAll();
        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }

    @GetMapping("/cinema/{cinemaId}")
    public @ResponseBody ResponseEntity<Iterable<TimeSlot>> getTimeSlotsByCinema(@PathVariable(required = true) Long cinemaId){
        Iterable<TimeSlot> timeSlots = this.repo.getTimeSlotByCinemaId(cinemaId);
        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }
    @GetMapping("/{day}")
    public  @ResponseBody ResponseEntity<Iterable<TimeSlot>> getTimeSlotsForDate(@PathVariable(required = true)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate day){
        Iterable<TimeSlot> timeSlots = this.repo.getTimeSlotsByDay(toDataStaxLocalData(day));
        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }
    @GetMapping("/today/{cinemaId}")
    public @ResponseBody
    ResponseEntity<Iterable<TimeSlot>> getTimeSlotsForToday(@PathVariable(required = false) Long cinemaId){
        Iterable<TimeSlot> timeSlots = this.repo.getTimeSlotsByDayAndCinemaId(toDataStaxLocalData(LocalDate.now()), cinemaId);
        return new ResponseEntity<>(timeSlots, HttpStatus.OK);
    }

}
