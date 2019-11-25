package system_design.project.ticket_management_service.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//this class is used for getting an schedule from the MoviePlanner service, it contains all the Movies that came with the GET request
public class MovieSchedule implements Serializable {

    private List<Movie> movies;

    public MovieSchedule(){
        this.movies = new ArrayList<>();
    }

    public MovieSchedule(List<Movie> movies){
        this.movies = movies;
    }

    @Override
    public String toString(){
        String result="";
        for(Movie m: movies){
            result += m.toString();
        }
        return result;
    }

}
