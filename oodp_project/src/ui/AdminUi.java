package ui;

import java.io.File;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;
import controllers.AdminController;

/**
 * This class represents the "UI" for the admin page.
 * @author Lim Zi Hao
 * @version 1.0
 * @since 2022-11-11
 */

public class AdminUi {
	
	/**
	 * Declares an AdminController object to pass functions to the "backend"
	 */
	AdminController ac = new AdminController();
	
	public AdminUi() {
		
	}
	
	/**
	 * Creates a new movie from user input and passes it into the AdminController class to add to file
	 */
	public void uiCreateMovie() {
		Scanner sc = new Scanner(System.in);
		int noOfCasts;
		
		
		System.out.print("Enter title of movie: ");
		String movieTitle = sc.nextLine();
		
		System.out.print("Enter type of movie: ");
		String typeOfMovie = sc.nextLine();
		
		System.out.print("Enter synopsis of movie: ");
		String movieSynopsis = sc.nextLine();
		
		System.out.print("Enter director of movie: ");
		String movieDirector = sc.nextLine();
		
		System.out.print("Enter number of casts (at least 2): ");
		do {
			noOfCasts = sc.nextInt();
		} while (noOfCasts < 2);
		
		String[] movieCasts = new String[noOfCasts];
		
		sc.nextLine();
		System.out.println("Enter cast names: ");
		for (int i = 0; i < noOfCasts; i++) {
			movieCasts[i] = sc.nextLine();
		}	
		
		//Enter movie status
		ShowStatus movieStatus = null;
		int statusChoice = 0;
		System.out.println("Enter movie show status");
		System.out.println("\t1. " + ShowStatus.PREVIEW);
		System.out.println("\t2. " + ShowStatus.COMING_SOON);
		System.out.println("\t3. " + ShowStatus.NOW_SHOWING);
		System.out.println("\t4. " + ShowStatus.END_OF_SHOWING);
		System.out.println("\t5. Exit");
		statusChoice = sc.nextInt();
		
		if (statusChoice == 1) {
			movieStatus = ShowStatus.PREVIEW;
		} else if (statusChoice == 2) {
			movieStatus = ShowStatus.COMING_SOON;
		} else if (statusChoice == 3) {
			movieStatus = ShowStatus.NOW_SHOWING;
		} else if (statusChoice == 4) {
			movieStatus = ShowStatus.END_OF_SHOWING;
		}
		
		//Enter movie rating
		AgeRating movieRating = null;		
		int ratingChoice = 0;
		System.out.println("Enter movie age rating");
		System.out.println("\t1. " + AgeRating.G);
		System.out.println("\t2. " + AgeRating.PG);
		System.out.println("\t3. " + AgeRating.PG13);
		System.out.println("\t4. " + AgeRating.NC16);
		System.out.println("\t5. " + AgeRating.M18);
		System.out.println("\t6. " + AgeRating.R21);
		System.out.println("\t7. Exit");
		statusChoice = sc.nextInt();
		movieRating = AgeRating.values()[statusChoice-1];
		/*if (ratingChoice == 1) {
			movieRating = AgeRating.G;
		} else if (ratingChoice == 2) {
			movieRating = AgeRating.PG;
		} else if (ratingChoice == 3) {
			movieRating = AgeRating.PG13;
		} else if (ratingChoice == 4) {
			movieRating = AgeRating.NC16;
		} else if (ratingChoice == 5) {
			movieRating = AgeRating.M18;
		} else if (ratingChoice == 6) {
			movieRating = AgeRating.R21;
		}*/
		
		
		
		// Fix id
		int id = Long.valueOf(Instant.now().toEpochMilli()).intValue();
		//create movie
		ac.createMovie(id, movieTitle, typeOfMovie, movieSynopsis, movieDirector, movieCasts, 5, movieStatus, movieRating);
		
		sc.nextLine();
		PaymentUI paymentUi = new PaymentUI();
		paymentUi.displayMoviePriceAdjustmentForm(id);
		
		sc.close();
	}
	
	/**
	 * Gets title from user input and removes movie based on title
	 */
	public void uiRemoveMovie() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter name of movie to remove (Case Sensitive): ");
		String movieTitle = sc.nextLine();
		
		sc.close();
		ac.removeMovie(movieTitle);
	}
	
	/**
	 * Get title of movie from user input and chekcs if movie exists
	 * If movie exists, passes it into a movie object and asks user what features they would like to edit
	 * If movie does not exist, return
	 */
	public void uiUpdateMovie() {
		Scanner sc = new Scanner(System.in);
		//Check if movie title exists first
		System.out.println("Enter movie title to edit (Case Sensitive): ");
		String movieTitle = sc.nextLine();	
		
		//create new movie object to parse data into it 
		//if movie exists
		Movie movieToEdit = ac.returnMovieIfExists(movieTitle);
		
		if (movieToEdit == null) {
			sc.close();
			return;
		}
		
		int choice = 0;
			
		
		do {
			System.out.println("Choose property of Movie to edit: ");
			System.out.println("\t1. Title");
			System.out.println("\t2. Type of Movie");
			System.out.println("\t3. Synopsis");
			System.out.println("\t4. Director");
			System.out.println("\t5. Casts");
			System.out.println("\t6. Show Status");
			System.out.println("\t7. Age Rating");
			System.out.println("\t8. Ticket Prices");
			System.out.println("\tType -1 to quit");
			
			choice = sc.nextInt();
			sc.nextLine(); // clear newline
			
			switch(choice) {
				case 1:
					//update title
					System.out.print("Give new title of movie: ");
					String newTitle = sc.nextLine();
					
					movieToEdit.setTitle(newTitle);
					System.out.print("Movie title updated: ");
					break;
				case 2:
					//update type of movie
					System.out.print("Give new movie type: ");
					String newMovieType = sc.nextLine();
					
					movieToEdit.setTypeOfMovie(newMovieType);
					System.out.println("Movie type updated");
					break;
				case 3:
					//update synopsis
					System.out.print("Give new movie synopsis: ");
					String newSynopsis = sc.nextLine();
					
					movieToEdit.setSynopsis(newSynopsis);
					System.out.println("Movie synopsis updated");
					break;
				case 4:
					//update director
					System.out.print("Give new movie director: ");
					String newDirector = sc.nextLine();
					
					movieToEdit.setDirector(newDirector);
					System.out.println("Movie director updated");
					break;
				case 5:
					//update casts
					updateCasts(movieToEdit);					
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
					sc.nextLine();
					
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
					
					movieToEdit.setShowStatus(newStatus);
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
					ratingChoice = sc.nextInt();
					sc.nextLine();
					
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
					
					movieToEdit.setAgeRating(newRating);
					break;
				case 8:
					sc.nextLine();
					PaymentUI paymentUi = new PaymentUI();
					paymentUi.displayMoviePriceAdjustmentForm(movieToEdit.getId());
					break;
				default:
					break;
			}
			
		} while (choice != -1);		
		
		//provide movie object to admincontroller to update movies file
		ac.updateMovie(movieToEdit, movieTitle);
		sc.close();
	}
	
	/**
	 * Creates cinema showtime based on user input
	 */
	public void uiCreateCinemaShowtime() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter movieId");
		int movieId = sc.nextInt();
		
		System.out.print("Enter cinemaId");
		int cinemaId = sc.nextInt();
		
		sc.nextLine(); // clear newline
		
		System.out.print("Enter showtime: (Format: yyyy-MM-dd-hh-mm)");
		String showTime = sc.nextLine();
		
		sc.close();
		
		ac.createCinemaShowtime(movieId, cinemaId, showTime);
	}
	
	/**
	 * Removes cinema showtime based on user input
	 */
	public void uiRemoveCinemaShowtimes() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter movieId: ");
		int movieId = sc.nextInt();
		
		System.out.print("Enter cinemaId: ");
		int cinemaId = sc.nextInt();
		
		sc.nextLine(); //clear newline
		
		String[] showTimes = ac.returnShowTimes(movieId, cinemaId);
		
		if (showTimes == null) {
			System.out.println("Showtimes do not exist for this movie and cinema");
			sc.close();
			return;
		}
		
		System.out.println("Showtimes for movieId: " + movieId + " and cinemaId: " + cinemaId);
		for (int i = 0; i < showTimes.length; i++) {
			System.out.println((i+1) + ") " + showTimes[i]);
		}
		System.out.print("Enter showtime to remove: ");
		
		int showTimeChoice = (sc.nextInt() - 1);
		
		sc.nextLine();
		//System.out.println("showTimeChoice: " + showTimeChoice);
		//System.out.println("showTime: " + showTimes[showTimeChoice]);
		sc.close();
		
		
		ac.removeCinemaShowtime(movieId, cinemaId, showTimes[showTimeChoice]);
	}
	
	/**
	 * Function to update cinema showtimes (incomplete)
	 */
	public void uiUpdateCinemaShowtimes() {
		//incomplete function
	}
	
	/**
	 * Separated from uiUpdateMovie() to update casts
	 * @param movie
	 */
	private void updateCasts(Movie movie) {
		int castChoice = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			String[] currentCasts = movie.getCasts();
			
			System.out.println("Update Cast Members Options");
			System.out.println("\t1. Add new cast members");
			System.out.println("\t2. Edit existing cast members");
			System.out.println("\t3. Remove cast members");
			System.out.println("\t4. Exit");
			System.out.println("Current Cast Members: " + Arrays.toString(currentCasts));
			
			castChoice = sc.nextInt();
			sc.nextLine(); //clear newline
			
			if (castChoice == 1) {
				System.out.print("Enter new cast name: ");
				String castName = sc.nextLine();
				
				//copy existing casts into new array +1 to add new cast
				String[] tempCasts = new String[currentCasts.length + 1];
				
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
				int index = -1;
				
				System.out.print("Enter cast member to replace: ");
				String replacementCastMember = sc.nextLine();
				
				for (int i = 0; i < tempCasts.length; i++) {
					if (replacementCastMember.toLowerCase().equals(tempCasts[i].toLowerCase())) {
						System.out.print("Enter new name for cast member: ");
						String editedCastMember = sc.nextLine();
						
						index = i;
						tempCasts[i] = editedCastMember;
						movie.setCasts(tempCasts);
					}
					
				}
				
				if (index == -1) {
					System.out.println("Cast member not found!");
				}
													
			} else if (castChoice == 3) {
				int index = -1;
				System.out.print("Enter cast member to delete: ");
				String deletedCastMember = sc.nextLine();
				
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
					continue;
				}
				System.out.println("Cast member not found!");
			} else {
				break;
			}
			
		} while (castChoice != 4);
		//sc.close();
	}
	
}
