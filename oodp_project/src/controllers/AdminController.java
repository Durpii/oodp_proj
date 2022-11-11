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
		int choice = 0;
		
		Scanner sc = new Scanner(System.in);	
		
		do {
			System.out.println("Choose property of Movie to edit: ");
			System.out.println("\t1. Title");
			System.out.println("\t2. Type of Movie");
			System.out.println("\t3. Synopsis");
			System.out.println("\t4. Director");
			System.out.println("\t5. Casts");
			System.out.println("\t6. Show Status");
			System.out.println("\t7. Age Rating");
			System.out.println("\tType -1 to quit");
			
			choice = sc.nextInt();
			
			switch(choice) {
				case 1:
					//update title
					//TODO: Check if duplicate title exists
					System.out.println("Give new title of movie");
					String newTitle = sc.next();
					movie.setTitle(newTitle);
					System.out.println("Movie title updated");
					break;
				case 2:
					//update type of movie
					System.out.println("Give new movie type");
					String newMovieType = sc.next();
					movie.setTypeOfMovie(newMovieType);
					System.out.println("Movie type updated");
					break;
				case 3:
					//update synopsis
					System.out.println("Give new movie synopsis");
					String newSynopsis = sc.next();
					movie.setSynopsis(newSynopsis);
					System.out.println("Movie synopsis updated");
					break;
				case 4:
					//update director
					System.out.println("Give new movie director");
					String newDirector = sc.next();
					movie.setDirector(newDirector);
					System.out.println("Movie director updated");
					break;
				case 5:
					//update casts
					int castChoice = 0;
					do {
						String[] currentCasts = movie.getCasts();
						
						System.out.println("Update Cast Members Options");
						System.out.println("\t1. Add new cast members");
						System.out.println("\t2. Edit existing cast members");
						System.out.println("\t3. Remove cast members");
						System.out.println("Current Cast Members: ");
						Arrays.toString(currentCasts);
						castChoice = sc.nextInt();
						
						if (castChoice == 1) {
							System.out.print("Enter new cast name: ");
							String castName = sc.next();
							
							//copy existing casts into new array +1 to add new cast
							String[] tempCasts = new String[currentCasts.length];
							
							for (int i = 0; i < currentCasts.length; i++) {
								tempCasts[i] = currentCasts[i];
							}
							
							//assign last element of new array to added castname
							tempCasts[currentCasts.length] = castName;
							//assign new casts to movie object
							movie.setCasts(tempCasts);
							//display new casts
							System.out.println("Cast added");
							System.out.println("Current Cast Members: ");
							Arrays.toString(tempCasts);
							
						} else if (castChoice == 2) {
							String[] tempCasts = currentCasts.clone();
							
							System.out.print("Enter cast member to replace: ");
							String replacementCastMember = sc.next();
							
							for (int i = 0; i < tempCasts.length; i++) {
								if (replacementCastMember.toLowerCase().equals(tempCasts[i].toLowerCase())) {
									System.out.println("Enter new name for cast member: ");
									String editedCastMember = sc.next();
									tempCasts[i] = editedCastMember;
									return;
								}
								
							}					
							System.out.println("Cast member not found!");
																
						} else if (castChoice == 3) {
							int index = -1;
							System.out.print("Enter cast member to delete: ");
							String deletedCastMember = sc.next();
							
							for (int i = 0; i < currentCasts.length; i++) {
								if (deletedCastMember.toLowerCase().equals(currentCasts[i].toLowerCase())) {
									index = i;
								}
							}
							
							if (index != -1) {
								String[] newCastsArray = new String[currentCasts.length - 1];

								for (int i = 0, k = 0; i < currentCasts.length; i++) {
									if (i == index) {
										continue;
									}

									newCastsArray[k++] = currentCasts[i];
								}
								//update movie object
								movie.setCasts(newCastsArray);
							}
							System.out.println("Cast member not found!");
						}
						
					} while (castChoice != 4);
					
					break;
				case 6:
					//update show status
					ShowStatus newStatus;
					int statusChoice = 0;
					System.out.println("Give new show status");
					System.out.println("\t1. " + ShowStatus.PREVIEW);
					System.out.println("\t2. " + ShowStatus.COMING_SOON);
					System.out.println("\t3. " + ShowStatus.NOW_SHOWING);
					System.out.println("\t4. " + ShowStatus.END_OF_SHOWING);
					System.out.println("\t5. Exit");
					statusChoice = sc.nextInt();
					
					if (statusChoice == 1) {
						newStatus = ShowStatus.PREVIEW;
					} else if (statusChoice == 2) {
						newStatus = ShowStatus.COMING_SOON;
					} else if (statusChoice == 3) {
						newStatus = ShowStatus.NOW_SHOWING;
					} else if (statusChoice == 4) {
						newStatus = ShowStatus.END_OF_SHOWING;
					} else {
						break;
					}				
					
					movie.setShowStatus(newStatus);
					break;
				case 7:
					//update age rating
					AgeRating newRating;
					int ratingChoice = 0;
					System.out.println("Give new show rating");
					System.out.println("\t1. " + AgeRating.G);
					System.out.println("\t2. " + AgeRating.PG);
					System.out.println("\t3. " + AgeRating.PG13);
					System.out.println("\t4. " + AgeRating.NC16);
					System.out.println("\t5. " + AgeRating.M18);
					System.out.println("\t6. " + AgeRating.R21);
					System.out.println("\t7. Exit");
					statusChoice = sc.nextInt();
					
					if (ratingChoice == 1) {
						newRating = AgeRating.G;
					} else if (ratingChoice == 2) {
						newRating = AgeRating.PG;
					} else if (ratingChoice == 3) {
						newRating = AgeRating.PG13;
					} else if (ratingChoice == 4) {
						newRating = AgeRating.NC16;
					} else if (ratingChoice == 5) {
						newRating = AgeRating.M18;
					} else if (ratingChoice == 6) {
						newRating = AgeRating.R21;
					} else {
						break;
					}
					
					movie.setAgeRating(newRating);
					break;
				default:
					break;
			}
			
		} while (choice != -1);
		
		//TODO: update movie file with movie object
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
