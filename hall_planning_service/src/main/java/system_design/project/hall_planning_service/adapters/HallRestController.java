package system_design.project.hall_planning_service.adapters;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.Day;
import system_design.project.hall_planning_service.persistence.PlanningRepository;

@RestController
@RequestMapping("planning")
public class HallRestController {

	@Autowired
	private PlanningRepository planRepo;
	
	@RequestMapping(path="/{cinema}/{date}",produces="application/json",method=RequestMethod.GET)
	public @ResponseBody Day getPlanningForDay(@PathVariable LocalDate date, @PathVariable Cinema cine) {
		return planRepo.findDayByDateAndCinema(date,cine);
	}
	
	@GetMapping("/cinema")
	public @ResponseBody List<Cinema> getCinemas() {
		//TODO: create
		
		return null;
	}
	
}
