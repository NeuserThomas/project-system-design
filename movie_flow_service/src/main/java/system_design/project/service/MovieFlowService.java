package system_design.project.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import system_design.project.adapters.messaging.channels.PlanningChannels;

import java.net.URL;


@Service("MovieFlowService")
@EnableScheduling
public class MovieFlowService {
	final Logger logger = LoggerFactory.getLogger(MovieFlowService.class);
	
	public MovieFlowService() {
		logger.info("MovieFlowService started");
	}
		
	
	@Scheduled(cron = "0 * * * * *")
	public void pullMoviePlanning() {
		final int PLANNING_PORT = 2223;
		
		// starting from yesterday
		final LocalDate startDate = LocalDate.now().minusDays(1);
		// construct url
		String planningUrl = String.format(
				"http://localhost:%d/%s/%s",
				PLANNING_PORT,
				"planning",
				startDate.format(DateTimeFormatter.ISO_DATE)
				);
		
		logger.info("pullMoviePlanning()... @ " + LocalDate.now());
		RestTemplate restTemplate  = new RestTemplate();
		logger.info("planningUrl: "+planningUrl);
		ResponseEntity<String> response = restTemplate.getForEntity(planningUrl, String.class);
		logger.info("pullMoviePlanning() --> pull OK");
		//TODO: parse movie planning
	}
		
	
	@StreamListener(PlanningChannels.PLANNING_UPDATED)
	public void updatePlanning() {
		logger.info("MovieflowService received PLANNING_UPDATED event!");
	}
		
}
