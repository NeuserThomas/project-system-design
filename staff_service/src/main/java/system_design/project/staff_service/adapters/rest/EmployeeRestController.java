package system_design.project.staff_service.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import system_design.project.staff_service.persistence.EmployeeRepository;

@RestController("employee")
public class EmployeeRestController {

    private EmployeeRepository employeeRepository;
    //private TimeSlotRepository timeSlotRepository;

    @Autowired
    private EmployeeRestController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
        //this.timeSlotRepository = timeSlotRepository;
    }




}
