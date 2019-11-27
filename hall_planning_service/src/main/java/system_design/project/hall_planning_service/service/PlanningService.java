package system_design.project.hall_planning_service.service;

import java.time.LocalDate;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system_design.project.hall_planning_service.persistence.PlanningRepository;

@Service("planningService")
public class PlanningService {
	
	@Autowired
	public PlanningRepository planRepo;
	
	public void planDay(LocalDate date) {
		throw new NotYetImplementedException();
	}
	
	public void planDays(LocalDate start,LocalDate stop) {
		throw new NotYetImplementedException();
	}
	
}
