package system_design.project.hall_planning_service.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import system_design.project.hall_planning_service.adapters.movieInfo.IMovieInfoAPI;

@Service("movieAPIService")
public class MovieAPIService implements IMovieInfoAPI {
	
	@Value("${movie.api.key}")
	private String apiKey="754be433";
	
	final String baseURL;
	
	public MovieAPIService(){
		baseURL="http://www.omdbapi.com/?apikey="+apiKey+"&plot=full";
	}
	
	@Override
	public String FindMovieByName(String movieName) {
		String result="";
		URL url;		
		try {
			url = new URL(baseURL+"&t="+movieName);
			System.out.println(url.toString());
			
			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			if (con.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ con.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((con.getInputStream())));
			StringBuilder ss=new StringBuilder("");
			String output;
			while ((output = br.readLine()) != null) {
				ss.append(output);
				System.out.println(output);
			}
			con.disconnect();
			result=ss.toString();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
