package controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;

public class AdminController {	
	
	public AdminController() {
		
	}
	
	public void createMovie(int id, String title, String typeOfMovie,
			String synopsis, String director, 
			float overallRating, ShowStatus showStatus,  AgeRating ageRating) {
		
		//create new movie object
		Movie m = new Movie(id, title, typeOfMovie, 
				synopsis, director,
				overallRating, showStatus, ageRating);
		
		
		//write movie object to file
		try {
			File moviesFile = new File("movies.txt");
			FileWriter fileWriter = new FileWriter("movies.txt", true);
			
			if (moviesFile.createNewFile()) {
				System.out.println("File created: " + moviesFile.getName());
			}
			//add movie to file
			System.out.println("Adding movie " + title + " to movies file...");
			fileWriter.write(m.getId() + "@@@" + m.getTitle() + "@@@" + m.getTypeOfMovie() +
						"@@@" + m.getSypnosis() + "@@@" + m.getDirector() + 
						"@@@" + m.getOverallRating() + "@@@" + m.getShowStatus() + "@@@" + m.getAgeRating());
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("Error adding movie, please try again");
			e.printStackTrace();
		}
		
	}
	public void removeMovie(int id) {
		//code to search movie by id and remove from movies.txt
	}
}
