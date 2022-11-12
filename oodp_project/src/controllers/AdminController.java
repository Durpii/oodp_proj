package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
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
		
		/*
		if(casts.length < 2) {
			System.out.println("Number of casts cannot be less than 2");
			return;
		}*/
		
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
			
			//add movie to file
			//prevent duplicate movie entry ids
			sc = new Scanner(new FileReader(moviesFile));		
			
			while(sc.hasNextLine()) {
				String[] data = sc.nextLine().split(SEPARATOR);
				
				int dataId = Integer.valueOf(data[0].split(":")[1]);
				String dataTitle = data[1];
				String dataTypeOfMovie = data[2];
				String dataSynopsis = data[3];
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
				if (dataTitle.equals(m.getTitle())) {
					System.out.println("Movie with name " + m.getTitle() + " already exists, unable to add");
					return;
				}
			}
					
			System.out.println("Adding movie " + title + " to movies file...");
			fileWriter.write("$ID:" + m.getId() + "@@@" + m.getTitle() + "@@@" + m.getTypeOfMovie() +
						"@@@" + m.getSynopsis() + "@@@" + m.getDirector() + "@@@" + String.join("|", m.getCasts()) +
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
	public void removeMovie(String movieName) {
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
			int movieRemoved = 0;
			
			while ((currentLine = reader.readLine()) != null) {
				//trim newline
				//String trimmedLine = currentLine.trim();
				
				String[] data = currentLine.split(SEPARATOR);
		
				int dataId = Integer.valueOf(data[0].split(":")[1]);
				String dataTitle = data[1];
				String dataTypeOfMovie = data[2];
				String dataSynopsis = data[3];
				String dataDirector = data[4];
				String[] dataCasts = data[5].split("\\|");
				float dataOverallRating = Float.valueOf(data[6]);
				ShowStatus dataShowStatus = ShowStatus.valueOf(data[7]);
				AgeRating dataAgeRating = AgeRating.valueOf(data[8]);
				
				//check if line has movie id
				if (dataTitle.equals(movieName)) {
					System.out.println("Successfully removed movie");
					movieRemoved++;
					continue;
				}
				
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			
			if (movieRemoved == 0) {
				System.out.println("Movie not found");
			}
			
			//need to close reader and writer here or inputFile **WILL NOT** be deleted
			reader.close();
			writer.close();
			
			inputFile.delete();
			tempFile.renameTo(inputFile);
			
		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Error removing movie");
			e.printStackTrace();	
		}
		
	}
	
	public Movie returnMovieIfExists(String title) {
		File inputFile = new File("movies.txt");
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			
			String currentLine;
			
			while ((currentLine = reader.readLine()) != null) {
				//trim newline
				String trimmedLine = currentLine.trim();
				
				String[] data = trimmedLine.split(SEPARATOR);
		
				int dataId = Integer.valueOf(data[0].split(":")[1]);
				String dataTitle = data[1];
				String dataTypeOfMovie = data[2];
				String dataSynopsis = data[3];
				String dataDirector = data[4];
				String[] dataCasts = data[5].split("\\|");
				float dataOverallRating = Float.valueOf(data[6]);
				ShowStatus dataShowStatus = ShowStatus.valueOf(data[7]);
				AgeRating dataAgeRating = AgeRating.valueOf(data[8]);
				
				//check if line has movie
				if (dataTitle.equals(title)) {
					Movie m = new Movie(dataId, dataTitle, dataTypeOfMovie, 
							dataSynopsis, dataDirector, dataCasts, dataOverallRating,
							dataShowStatus, dataAgeRating);
					reader.close();
					System.out.println("Movie found!");
					//System.out.println("dataTitle " + dataTitle);
					//System.out.println("movieName " + title);
					return m;
				}
				
			}
				
			reader.close();
			
		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();	
		}
		
		System.out.println("Movie not found!");
		return null;
	}
		
	
	public void updateMovie(Movie movie) {
		// will update movie object to file later
		System.out.println("Movie updated!");
		System.out.println("\nNew Movie details: ");
		System.out.println("Title: " + movie.getTitle());
		System.out.println("Type of Movie: " + movie.getTypeOfMovie());
		System.out.println("Synopsis: " + movie.getSynopsis());
		System.out.println("Director: " + movie.getDirector());
		System.out.println("Casts: " + Arrays.toString(movie.getCasts()));
		System.out.println("Show Status: " + movie.getShowStatus());
		System.out.println("Age Rating: " + movie.getAgeRating());
	}
	

	public void createCinemaShowtime() {
		
	}
	
	public void removeCinemaShowtime() {
		
	}
	
	public void updateCinemaShowtime() {
		
	}
	
	public void updateSystemSettings() {
		
	}
	
	public void ListTop5Rankings() {
		//List by ticket sale or by overall reviewer's rating
	}
	
	
}
