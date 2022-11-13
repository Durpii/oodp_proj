package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;
import cinema.ShowTime;

/**
 * This class represents the "backend" to write to files
 * @author Lim Zi Hao
 * @version 1.0
 * @since 2022-11-11
 *
 */

public class AdminController {
	
	/**
	 * The delimiter used to split strings
	 */
	static final String SEPARATOR = "@@@";
	
	/**
	 * The date time format used to convert date objects to and from String
	 */
	static final SimpleDateFormat DATETIMEFORMAT = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
	
	/**
	 * Creates an AdminController object to be used by AdminUi class
	 */
	public AdminController() {
		
	}
	
	/**
	 * Creates a new movie object with the following parameters
	 * The movie object is then passed into the movies.txt file
	 * @param id
	 * @param title
	 * @param typeOfMovie
	 * @param synopsis
	 * @param director
	 * @param casts
	 * @param overallRating
	 * @param showStatus
	 * @param ageRating
	 */
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
				showStatus, ageRating);
		
		
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
	
	/**
	 * Removes a movie in movies.txt with the indicated movie name parameter
	 * @param movieName
	 */
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
	
	/**
	 * Updates movie in movies.txt indicated by oldTitle parameter and movie object from AdminUi class
	 * @param movie
	 * @param oldTitle
	 */
	public void updateMovie(Movie movie, String oldTitle) {
		// check if movie details are correct
		System.out.println("\nNew Movie details: ");
		System.out.println("Title: " + movie.getTitle());
		System.out.println("Type of Movie: " + movie.getTypeOfMovie());
		System.out.println("Synopsis: " + movie.getSynopsis());
		System.out.println("Director: " + movie.getDirector());
		System.out.println("Casts: " + Arrays.toString(movie.getCasts()));
		System.out.println("Show Status: " + movie.getShowStatus());
		System.out.println("Age Rating: " + movie.getAgeRating());
		
		//write movie object to file
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
				if (dataTitle.equals(oldTitle)) {
					System.out.println("\nMovie updated!");
					writer.write("$ID:" + movie.getId() + "@@@" + movie.getTitle() + "@@@" + movie.getTypeOfMovie() +
							"@@@" + movie.getSynopsis() + "@@@" + movie.getDirector() + "@@@" + String.join("|", movie.getCasts()) +
							"@@@" + movie.getOverallRating() + "@@@" + movie.getShowStatus() + "@@@" + movie.getAgeRating() + "\n");
					continue;
				}
				
				writer.write(currentLine + System.getProperty("line.separator"));
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
			System.out.println("Error editing movie");
			e.printStackTrace();	
		}
	}

	/**
	 * Creates a new showtime in showTimes.txt for indicated movieId, cinemaId
	 * @param movieId
	 * @param cinemaId
	 * @param showTime
	 */
	public void createCinemaShowtime(int movieId, int cinemaId, String showTime) {
		
		FileWriter fileWriter = null;
		Scanner sc = null;
		
		try {
			
			File showTimesFile = new File("showTimes.txt");
			fileWriter = new FileWriter("showTimes.txt", true);
			
			sc = new Scanner(new FileReader(showTimesFile));		
			
			while(sc.hasNextLine()) {
				String[] data = sc.nextLine().split(SEPARATOR);
				
				int dataMovieId = Integer.valueOf(data[0]);
				int dataCinemaId = Integer.valueOf(data[1]);
				String dataShowTime = data[2];
				
				//check if movie with same title exists
				if (dataShowTime.equals(showTime)) {
					System.out.println("Movie with showtime " + showTime + " already exists, unable to add");
					return;
				}
			}
			
			System.out.println("Adding showtime to showTimes file...");
			fileWriter.write(movieId + "@@@" + cinemaId + "@@@" + showTime + "\n");
					
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error adding datetime, please try again");
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
	
	/**
	 * Removes indicated showTime in showTimes.txt with corresponding movieId and cinemaId
	 * @param movieId
	 * @param cinemaId
	 * @param showTime
	 */
	public void removeCinemaShowtime(int movieId, int cinemaId, String showTime) {
        //code to search showTime by id and remove from showTimes.txt
                File inputFile = new File("showTimes.txt");
                File tempFile = new File("showTimesTemp.txt");
                
                BufferedReader reader = null;
                BufferedWriter writer = null;
                
                try {
                    reader = new BufferedReader(new FileReader(inputFile));
                    writer = new BufferedWriter(new FileWriter(tempFile));
                    
                    String currentLine;
                    int showTimeRemoved = 0;
                    
                    while ((currentLine = reader.readLine()) != null) {
                        //trim newline
                        //String trimmedLine = currentLine.trim();
                        
                        String[] data = currentLine.split(SEPARATOR);
                
                        int dataMovieId = Integer.valueOf(data[0]);
                        int dataCinemaId = Integer.valueOf(data[1]);
                        String dataShowTime = data[2];
                        
                        //check if line has showtime has corresponding movie and cinema id
                        if (dataMovieId == movieId && dataCinemaId == cinemaId && dataShowTime.equals(showTime)) {
                            System.out.println("Successfully removed showTime");
                            showTimeRemoved++;
                            continue;
                        }
                        
                        writer.write(currentLine + System.getProperty("line.separator"));
                    }
                    
                    if (showTimeRemoved == 0) {
                        System.out.println("showTime not found");
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
                    System.out.println("Error removing showTime");
                    e.printStackTrace();    
                }
                
    }
		
	/**
	 * Updates cinema showtime with indicated movieId and cinemaId, incomplete
	 * @param movieId
	 * @param cinemaId
	 */
	public void updateCinemaShowtime(int movieId, int cinemaId) {
		//incomplete function
	}
	
	/**
	 * Returns array of showtimes from showTimes.txt with indicated movieId and cinemaId to be used in adminUi.
	 * @param movieId
	 * @param cinemaId
	 * @return array of showtimes, null if showtimes do not exist for movieId and cinemaId
	 */
	public String[] returnShowTimes(int movieId, int cinemaId) {
		//code to search showTime by id and remove from showTimes.txt
        File inputFile = new File("showTimes.txt");
        int size = 0;
              
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
            String currentLine;
            
            while ((currentLine = reader.readLine()) != null) {
                
                String[] data = currentLine.split(SEPARATOR);
        
                int dataMovieId = Integer.valueOf(data[0]);
                int dataCinemaId = Integer.valueOf(data[1]);
                String dataShowTime = data[2];
                
                //check if line has showtime exists for corresponding movie and cinema id
                if (dataMovieId == movieId && dataCinemaId == cinemaId) {
                    size++;
                }
                
            }
            
            if (size == 0) {
                reader.close();
                return null;
            }
            
            reader.close();
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error removing showTime");
            e.printStackTrace();    
        }
        
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            
            String currentLine;
        
	        String[] showTimeArray = new String[size];
	    	
			//read through file again to store values into array for specific movieId and cinemaId
	    	int index = 0;
	    	
			while ((currentLine = reader.readLine()) != null) {    
				
	            String[] data = currentLine.split(SEPARATOR);
	    
	            int dataMovieId = Integer.valueOf(data[0]);
	            int dataCinemaId = Integer.valueOf(data[1]);
	            String dataShowTime = data[2];
	            
	            //check if line has showtime exists for corresponding movie and cinema id
	            //then stores it in showTimeArray
	            if (dataMovieId == movieId && dataCinemaId == cinemaId) {
	                showTimeArray[index] = dataShowTime;
	                index++;
	            }
	            
	        }
	        	
	        reader.close();
	        
	        return showTimeArray;
        } catch(FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        }
        catch (IOException e) {
            System.out.println("Error removing showTime");
            e.printStackTrace();    
        }
        
		return null;
	}
	
	/**
	 * Function returns a movie object if corresponding title exists in movies.txt
	 * @param title
	 * @return movie object with specified title
	 */
	
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
							dataSynopsis, dataDirector, dataCasts,
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

	/**
	 * function to update system settings, incomplete
	 */
	public void updateSystemSettings() {
		
	}
	
	
}