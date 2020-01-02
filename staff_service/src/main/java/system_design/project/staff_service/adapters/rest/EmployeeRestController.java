package system_design.project.staff_service.adapters.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import system_design.project.staff_service.domain.Employee;
import system_design.project.staff_service.persistence.EmployeeRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("employee")
public class EmployeeRestController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRestController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping("/greeting")
    public String greeting(){
        return "Hello";
    }


    @GetMapping("/")
    public Iterable<Employee> getAllEmployees(){
        return this.employeeRepository.findAll();
    }
    /**
     * debug example: http://localhost:8080/employee/getEmployeeByFirstName/Geralt
     * @param firstName
     * @return
     */
    @GetMapping("/getEmployeeByFirstName/{firstName}")
    public Iterable<Employee> getEmployeeByFirstName(@PathVariable String firstName){
        return this.employeeRepository.findEmployeeByFirstName(firstName);
    }






}
