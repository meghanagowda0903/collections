package com.movies.service;
import com.movies.controller.MoviesList;
import java.util.*;

public class CrudOperation {
	List<MoviesList> movies = new ArrayList<>();
	
	public void addMovie(MoviesList mov) {
		movies.add(mov);
	}
	
	public List<MoviesList> getAllMovies() {
		return new ArrayList<>(movies);
	}
	public MoviesList getMovieById(int id){
		for(MoviesList m : movies) {
			if(m.getId()== id) {
				return m;
			}
			
			}
		return null;
		}
	public void updateMovie(int id,String movieName,String directorName,double amount) {
		for(MoviesList m:movies) {
			if(m.getId()==id) {
				m.setMovieName(movieName);
				m.setDirectorName(directorName);
				m.setAmount(amount);
				return;
			}
		}
	}
	public void deleteById(int id) {
		for(int i=0;i<movies.size();i++) {
			MoviesList m = movies.get(i);
			if(m.getId()==id) {
				movies.remove(i);
				return;
			}
		}
	}

}
