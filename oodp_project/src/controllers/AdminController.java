package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;

public class AdminController {
	
	public final String SEPARATOR = "@@@";
	
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
		FileWriter fileWriter = null;
		Scanner sc = null;
		
		try {
			File moviesFile = new File("movies.txt");
			fileWriter = new FileWriter("movies.txt", true);
			
			if (moviesFile.createNewFile()) {
				System.out.println("File created: " + moviesFile.getName());
			}
			//add movie to file
			//prevent duplicate movie entry ids
			sc = new Scanner(new FileReader(moviesFile));		
			
			while(sc.hasNextLine()) {
				String[] data = sc.nextLine().split(SEPARATOR);
				
				int dataId = Integer.valueOf(data[0].split(":")[1]);
				String dataTitle = data[1];
				String dataTypeOfMovie = data[2];
				String dataSypnosis = data[3];
				String dataDirector = data[4];
				String[] dataCasts = data[5].split("\\|");
				float dataOverallRating = Float.valueOf(data[6]);
				ShowStatus dataShowStatus = ShowStatus.valueOf(data[7]);
				AgeRating dataAgeRating = AgeRating.valueOf(data[8]);
				
				//check if movie with same ID exists
				if (dataId == m.getId()) {
					System.out.println("Movie with ID " + m.getId() + " already exists, unable to add");
					return;
				}
				
				//check if movie with same title exists
				if (dataTitle.contains(m.getTitle())) {
					System.out.println("Movie with name " + m.getTitle() + " already exists, unable to add");
					return;
				}
			}
					
			System.out.println("Adding movie " + title + " to movies file...");
			fileWriter.write("$ID:" + m.getId() + "@@@" + m.getTitle() + "@@@" + m.getTypeOfMovie() +
						"@@@" + m.getSypnosis() + "@@@" + m.getDirector() + "@@@" + String.join("|", m.getCasts()) +
						"@@@" + m.getOverallRating() + "@@@" + m.getShowStatus() + "@@@" + m.getAgeRating() + "\n");
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error adding movie, please try again");
			e.printStackTrace();
		} finally {
			sc.close();
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	public void removeMovie(int id) {
		//code to search movie by id and remove from movies.txt
		File inputFile = new File("movies.txt");
		File tempFile = new File("moviesTemp.txt");
		
		BufferedReader reader = null;
		BufferedWriter writer = null;
		
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			writer = new BufferedWriter(new FileWriter(tempFile));
			
			//String lineToRemove = "$ID:" + id + "@";
			String currentLine;
			
			while ((currentLine = reader.readLine()) != null) {
				//trim newline
				String trimmedLine = currentLine.trim();
				
				String[] data = trimmedLine.split(SEPARATOR);
		
				int dataId = Integer.valueOf(data[0].split(":")[1]);
				String dataTitle = data[1];
				String dataTypeOfMovie = data[2];
				String dataSypnosis = data[3];
				String dataDirector = data[4];
				String[] dataCasts = data[5].split("\\|");
				float dataOverallRating = Float.valueOf(data[6]);
				ShowStatus dataShowStatus = ShowStatus.valueOf(data[7]);
				AgeRating dataAgeRating = AgeRating.valueOf(data[8]);
				
				//check if line has movie id
				if (dataId == id) {
					continue;
				}
				
				writer.write(trimmedLine + System.getProperty("line.separator"));
			}
			
			//need to close reader and writer here or inputFile **WILL NOT** be deleted
			reader.close();
			writer.close();
			
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
