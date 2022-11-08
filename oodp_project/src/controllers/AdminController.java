package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;

public class AdminController {	
	
	public AdminController() {
		
	}
	
	public void createMovie(int id, String title, String typeOfMovie,
			String synopsis, String director, String[] casts,
			float overallRating, ShowStatus showStatus,  AgeRating ageRating) {
		
		if(casts.length < 2) {
			System.out.println("Number of casts cannot be less than 2");
			return;
		}
		
		//create new movie object
		Movie m = new Movie(id, title, typeOfMovie, 
				synopsis, director, casts,
				overallRating, showStatus, ageRating);
		
		
		//write movie object to file
		try {
			File moviesFile = new File("movies.txt");
			FileWriter fileWriter = new FileWriter("movies.txt", true);
			
			if (moviesFile.createNewFile()) {
				System.out.println("File created: " + moviesFile.getName());
			}
			//add movie to file
			//prevent duplicate movie entry ids
			BufferedReader reader = new BufferedReader(new FileReader(moviesFile));
			String currentLine;
			
			
			while((currentLine = reader.readLine()) != null) {
				String trimmedLine = currentLine.trim();
				
				//check if movie with same ID exists
				String movieId = "$ID:" + m.getId() + "@";
				if (trimmedLine.contains(movieId)) {
					System.out.println("Movie with ID " + m.getId() + " already exists, unable to add");
					reader.close();
					fileWriter.close();
					return;
				}
				
				//check if movie with same title exists
				String movieTitle = "@" + m.getTitle() + "@";
				if (trimmedLine.contains(movieTitle)) {
					System.out.println("Movie with name " + m.getTitle() + " already exists, unable to add");
					reader.close();
					fileWriter.close();
					return;
				}
			}
					
			System.out.println("Adding movie " + title + " to movies file...");
			fileWriter.write("$ID:" + m.getId() + "@@@" + m.getTitle() + "@@@" + m.getTypeOfMovie() +
						"@@@" + m.getSypnosis() + "@@@" + m.getDirector() + "@@@" + String.join("|", m.getCasts()) +
						"@@@" + m.getOverallRating() + "@@@" + m.getShowStatus() + "@@@" + m.getAgeRating() + "\n");
			fileWriter.close();
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error adding movie, please try again");
			e.printStackTrace();
		}
		
	}
	public void removeMovie(int id) {
		//code to search movie by id and remove from movies.txt
		File inputFile = new File("movies.txt");
		File tempFile = new File("moviesTemp.txt");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			
			String lineToRemove = "$ID:" + id + "@";
			String currentLine;
			
			while ((currentLine = reader.readLine()) != null) {
				//trim newline
				String trimmedLine = currentLine.trim();
				if (trimmedLine.contains(lineToRemove)) {
					continue;
				}
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close();
			reader.close();
			
			inputFile.delete();
			tempFile.renameTo(inputFile);
			System.out.println("Successfully removed movie");
		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Error removing movie");
			e.printStackTrace();
		}
		
		
	}
	
	public void updateMovie(Movie movie) {
		//update movie of specific movie object
	}
	
	public void createCinemaShowtime() {
		
	}
	
	public void removeCinemaShowtime() {
		
	}
	
	public void updateCinemaShowtime() {
		
	}
}
