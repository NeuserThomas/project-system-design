package system_design.project.hall_planning_service.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import system_design.project.hall_planning_service.adapters.messaging.MessageGateway;
import system_design.project.hall_planning_service.domain.Cinema;
import system_design.project.hall_planning_service.domain.Day;
import system_design.project.hall_planning_service.domain.HallDay;
import system_design.project.hall_planning_service.domain.Movie;
import system_design.project.hall_planning_service.domain.PlannedMovies;
import system_design.project.hall_planning_service.domain.TimeSlot;
import system_design.project.hall_planning_service.persistence.CinemaRepository;
import system_design.project.hall_planning_service.persistence.DayRepository;
import system_design.project.hall_planning_service.persistence.MovieRepository;

/**
 * @author robin
 *
 */
@Service("planningService")
@EnableScheduling
public class PlanningService {

	@Autowired
	public DayRepository planRepo;
	@Autowired
	public CinemaRepository cinemaRepo;
	@Autowired
	public MovieRepository movieRepo;
	
	@Autowired
	private Environment env;

	final Logger logger = LoggerFactory.getLogger(PlanningService.class);

	private final MessageGateway gateway;

	@Autowired
	public PlanningService(MessageGateway gateway) {
		this.gateway = gateway;
	}

	/**
	 * Will run every day, at 7 in the morning. (UTC)
	 */
	@Scheduled(cron = "0 0 6 * * *")
	public void planWeek() {
		LocalDate date = LocalDate.now();
		LocalDate week = date.plusDays(7);
		planDays(date, week);
	}

	/**
	 * Will calculate the schedule for every cinema, for to a certain date.
	 * 
	 * @param date
	 */
	public void planDay(LocalDate date) {
		List<Cinema> cinemas = cinemaRepo.findAll();
		boolean updated = false;
		for(Cinema cinema : cinemas){
			//check if a day is already planned
			Optional<Day> day = planRepo.findDaysOnDateForCinema(date, cinema.getId());
			if(!day.isPresent()) {
				logger.info("--- Planning movie for date: "+date+" ---");
				PlannedMovies pm = cinema.getPlannedMovies();
				List<Movie> movies = movieRepo.findMoviesWithId(pm.getMongoMovieIds());
				Map<Integer, Double> wantStatus = pm.getMovieWantStatus();
				List<Double> wStatus = new ArrayList<>();
				for (int i = 0; i < movies.size(); i++) {
					if (!wantStatus.containsKey(i)) {
						wStatus.add(1.0);
					} else {
						wStatus.add(wantStatus.get(i));
					}
				}
				planCinemaForDay(cinema,movies,wStatus,date);
				updated = true;
			}
		}
		if (updated) {
			publish(date.toString());
		}
	}

	
	
	/**
	 * Version 0.1 Rudementary planning algorithm. Next version should be PERT.
	 * @param c
	 * @param movies
	 * @param wStatus
	 * @param date
	 * @return
	 */
	private void planCinemaForDay(Cinema c,List<Movie> movies,List<Double> wStatus, LocalDate date) {
		String publicityUrl = env.getProperty("publicity.url");
		RestTemplate restTemplate = new RestTemplate();
		int adTime=15;
		/**
		 * Get the duration of the ad time, so we have enough time to clean up.
		 */
		try {
			String result = restTemplate.getForObject(publicityUrl, String.class);
			adTime= Integer.parseInt(result);
			logger.info("Publicity answer: "+result);
		}catch (Exception e) {
			logger.error("Publicity unreachable: "+e);
		}
		
		Day day = new Day();
		day.setCinema(c);
		day.setDate(date);
		day.setStartTime(date, day.getStartTime().toLocalTime());
		day.setStopTime(date, day.getStopTime().toLocalTime());

		List<HallDay> planning = day.getPlanning();
		//prevent nulls
		if(planning==null) {
			planning = new ArrayList<HallDay>();
		}
		if(movies.size()!=0) {
			/**
			 * For each movie, count how many tickets are sold.
			 */
			List<Long> amountPlanned = new ArrayList<Long>();
			for(int j = 0;j<wStatus.size();j++) {
				amountPlanned.add(0L);
			}
			Long totalAmount=0L;
			for(int i = 0;i<c.getHalls().size();i++) {			
				LocalDateTime lastTime=LocalDateTime.of(date, day.getStartTime().toLocalTime());
				//logger.info("Lasttime: "+lastTime);
				HallDay hallDay= new HallDay();
				long amountOfSeats=c.getHalls().get(i).getSeatCount();
				
				while(lastTime.compareTo(day.getStopTime())<=0) {
					int chosenMovie = chooseMovie(wStatus,amountPlanned,totalAmount);
					TimeSlot timeSlot = new TimeSlot();
					timeSlot.setHall(c.getHalls().get(i));
					timeSlot.setMovieId(movies.get(chosenMovie).getId());
					timeSlot.setStartTime(lastTime);
					//logger.info("timeslot start: "+timeSlot.getStartTime());
					String temp = movies.get(chosenMovie).getRuntime().replaceAll("\\D", "");
					if(temp.equals("")) {
						temp="120";
						logger.error("Movie has no time! "+movies.get(chosenMovie).getTitle());
						Movie m = movies.get(chosenMovie);
						m.setRuntime("120");
						movieRepo.save(m);
					}
					int minutes = Integer.parseInt(temp);
					timeSlot.setStopTime(timeSlot.getStartTime().plusMinutes(minutes));
					lastTime = timeSlot.getStopTime().plusMinutes(30+adTime); //give employees time to clean
					// Round to 15 minutes
					int unroundedMinutes = lastTime.getMinute();
					int mod = unroundedMinutes % 15;
					lastTime=lastTime.plusMinutes(15-mod);
					hallDay.addTimeSlot(timeSlot);
					amountPlanned.set(chosenMovie,amountPlanned.get(chosenMovie)+amountOfSeats);
				}
				planning.add(hallDay);
			}
			day.setPlanning(planning);
			planRepo.save(day);
		} else {
			logger.warn("No movies planned!");
		}
		
	}
	/*
	 * Algorithm that chooses what the next movie will be. Right now, it is random by chance. If no movie has a preference number, it will assign them all with the same chance.
	 */
	private int chooseMovie(List<Double> wStatus,List<Long> amountPlanned, Long totalAmount) {
		assert(wStatus.size()==amountPlanned.size());
		Random rand = new Random();
		List<Integer> results = new ArrayList<Integer>();
		results.add((int) (wStatus.get(0)*1000));
		for(int i = 1;i<wStatus.size();i++) {
			//chance *1000 (to cast to int)
			//amountPlanned.get(i);
			double thisThreshHold = wStatus.get(i)*1000;
			//thisThreshHold*=(0.25+1-(double)(amountPlanned.get(i))/totalAmount); //average chances out to plan movie.
			results.add((int)(thisThreshHold+results.get(i-1)));
		}
		int chance = rand.nextInt(results.get(results.size()-1));
		int j = 0;
		while(chance>results.get(j) && j<results.size()) {
			j++;
		}
		return j;		
	}
	
	private Properties getProperty(String pr) {
		try (InputStream input = new FileInputStream(pr)) {
	            Properties prop = new Properties();
	            prop.load(input);
	            return prop;
	        } catch (IOException io) {
	            io.printStackTrace();
	            return new Properties();
	        }
	}
	
	/**
	 * Will remove all days on certain date, for all cinemas
	 * 
	 * @param date
	 */
	public void clearDay(LocalDate date) {
		List<Cinema> cinemas = cinemaRepo.findAll();
		boolean updated = false;
		for (Cinema c : cinemas) {
			Optional<Day> day = planRepo.findDaysOnDateForCinema(date, c.getId());
			if(day.isPresent()) {
				planRepo.delete(day.get());
				updated = true;
			}
		}
		if (updated) {
			publish("Removed parts from schedule!");
		}
	}

	public void planDays(LocalDate start, LocalDate stop) {
		for (LocalDate i = start; i.isBefore(stop); i=i.plusDays(1)) {
			planDay(i);
		}
	}

	private void publish(String message) {
		logger.info("--- Sending kafka message: " + message + " ---");
		gateway.updateDay(message);
		// simpleProducer.send("planningMade",message);
	}

	/**
	 * @param cinemaId
	 * @param date
	 * @return
	 */
	public List<Movie> findPlannedMoviesForCinema(long cinemaId, LocalDate date) {
		List<Movie> movies = new ArrayList<Movie>();
		List<Day> days = planRepo.findDaysForCinema(cinemaId, date);
		Set<ObjectId> movieIds=new TreeSet<ObjectId>();
		for(Day d :days) {
			for(HallDay hd: d.getPlanning()) {
				for(TimeSlot ts: hd.getTimeSlots()) {
					movieIds.add(new ObjectId(ts.getMovieId()));
				}
			}
		}
		List<ObjectId> ids = new ArrayList<ObjectId>(movieIds);
		logger.info("Hierzo: aantal films: "+ids.size());
		movies=movieRepo.findMoviesWithId(ids);
		return movies;
	}
	/*
	public List<TimeSlot> findPlannedMoviesForCinema(long cinemaId, LocalDate date, String movieId) {
		return timeSlotRepo.findByCinemaIdAndMovie(cinemaId, movieId,date);
	}*/
}
