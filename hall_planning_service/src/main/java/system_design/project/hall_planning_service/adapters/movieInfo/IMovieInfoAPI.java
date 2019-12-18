package system_design.project.hall_planning_service.adapters.movieInfo;

import system_design.project.hall_planning_service.domain.Movie;

public interface IMovieInfoAPI {
	public Movie FindMovieByName(String movieName);
}
