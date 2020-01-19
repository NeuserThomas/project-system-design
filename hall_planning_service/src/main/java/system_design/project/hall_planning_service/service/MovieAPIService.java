package system_design.project.hall_planning_service.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import system_design.project.hall_planning_service.adapters.movieInfo.IMovieInfoAPI;
import system_design.project.hall_planning_service.domain.Movie;

/**
 * @author robin
 * Service Used to get information about movie.
 */
@Service("movieAPIService")
public class MovieAPIService implements IMovieInfoAPI {
	
	@Value("${movie.api.key}")
	private String apiKey="754be433";
	
	final private Gson gson = new Gson();
	final Logger logger = LoggerFactory.getLogger(MovieAPIService.class);

	final String baseURL;
	
	public MovieAPIService(){
		baseURL="http://www.omdbapi.com/?apikey="+apiKey+"&plot=full";
	}
	
	@Override
	public Movie FindMovieByName(String movieName) {
		String result="";
		URL url;		
		try {
			String urlString =baseURL+"&t="+URLEncoder.encode(movieName, "UTF-8");
			url = new URL(urlString);		
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			if (con.getResponseCode() != 200) {
				logger.error("Failed : HTTP error code for <"+url.toString()+">: "
					+ con.getResponseCode());
			} else {
				BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
				StringBuilder ss=new StringBuilder("");
				String output;
				while ((output = br.readLine()) != null) {
					ss.append(output);
					//System.out.println(output);
				}
				con.disconnect();
				result=ss.toString();
			}
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!result.contains("\"Response\":\"False\"")){
			return gson.fromJson(result, Movie.class);
		}
		logger.error("Movie: <"+movieName+"> was not found!");
		return null;
	}
}
