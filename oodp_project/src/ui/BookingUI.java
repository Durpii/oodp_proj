package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import cinema.Cinema;
import cinema.Cineplex;
import cinema.Movie;
import cinema.ShowStatus;
import cinema.ShowTime;
import cinema.Ticket;
import controllers.BookingController;
import controllers.CinemaController;
import controllers.CineplexController;
import controllers.PaymentController;
import controllers.ReviewController;
import users.MovieGoer;

/**
Booking UI for searching, displaying and booking Movies. 
@author Liu Woon Kit
@version 1.0
@since 2022-11-11
*/

public class BookingUI {
	/**
	 * Initialized Booking Controller
	 */
	BookingController bc = new BookingController();
	
	/**
	 * Initialized Scanner Object
	 */
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Divider for formatting output
	 */
	String divider = "_".repeat(40).concat("\n\n");
	
	/** 
	* Class constructor.
	*/
	public BookingUI() {
		
	}
	
	 /**
	 * Prompts user for search query.
	 * Display a list of matching movies 
	 * Allows user to select a movie.
	 * Selected movie displays movie details
	 */
	public void searchMovie() {
		ArrayList<Movie> result = new ArrayList<Movie>();
		String searchQuery = "";
		while(result.isEmpty()) {
			System.out.print("Enter Search Query:");
			searchQuery = sc.nextLine().trim();
			result.addAll(bc.search(searchQuery));
		}

		int choice = 0;
		while(true) {
			System.out.print(divider);
			System.out.printf("Search Result for \"%s\":\n", searchQuery);
			for(int i = 0; i < result.size(); i++) {
				System.out.printf("\t%d) %s\n",i+1,result.get(i).getTitle());
			}
			System.out.print("Choose an option or enter q to return: ");
			
			if(sc.hasNextInt()) {
				if((choice = sc.nextInt()) <= result.size()) {
					displayMovieDetails(result.get(choice-1));
				}
			} else if(sc.next().equals("q")) {
				break;
			}
		};
	}

	 /**
	 * Displays the movie given the movie object.
	 * Displays information such as Synopsis, Genre, Age Restriction, Overall Rating, Shows Status, Cast, Director.
	 * Displays take-able actions through displayMovieOptions(). 
	 * @param Movie The selected Movie.
	 */
	public void displayMovieDetails(Movie m) {
		System.out.printf(
				divider
				+ "%s\n"
				+ "\tSYNOPSIS: %s\n"
				+ "\tGENRE: %s\n"
				+ "\tAGE RESTRICTION: %s\n"
				+ "\tOVERALL RATING: %s/5.0\n"
				+ "\tSHOWS STATUS: %s\n"
				+ "\tCAST: %s\n"
				+ "\tDIRECTOR: %s\n",
				
				m.getTitle(),
				m.getSynopsis(),
				m.getTypeOfMovie(),
				m.getAgeRating(),
				m.getOverallRating() == -1 ? "NA" : m.getOverallRating(),
				m.getShowStatus().toString().replace("_", " "),
				String.join(", ", m.getCasts()),
				m.getDirector()
				);
		displayMovieOptions(m);
	}
	
	 /**
	 * Displays what the user can do with the selected Movie
	 * User can View Seat Availability, Book Movie, Add/View Review
	 * @param Movie The selected Movie.
	 */
	public void displayMovieOptions(Movie m) {
		if(m.getShowStatus() == ShowStatus.END_OF_SHOWING) {
			return;
		}
		int choice = 0;
		while(true) {
			System.out.print("\n"
					+ "What would you like to do?\n"
					+ "\t1) View Seat Availability\n"
					+ "\t2) Book Movie\n"
					+ "\t3) Add Review\n"
					+ "\t4) View Reviews\n"
					+ "Choose an option or enter q to return: ");
			if(sc.hasNextInt()) {
				choice = sc.nextInt();
				if(choice < 1 && choice > 4) {
					continue;
				}
				
				ReviewUI reviewUI = new ReviewUI(m);
				if(choice == 3 || choice == 4) {
					if(choice == 3) {
						reviewUI.addReview();
					} else {
						reviewUI.displayReview();
					}
					break;
				}
				
				if(m.getShowStatus() == ShowStatus.END_OF_SHOWING || m.getShowStatus() == ShowStatus.COMING_SOON) {
					System.out.println("\nSorry! Not available");
					break;
				}
				int cinemaId = promptCinemaSelection(m.getId());
				ShowTime showTime = promptShowTimeSelection(cinemaId, m.getId());
				if(cinemaId == -1 || showTime == null) {
					break;
				}
				switch(choice) {
					case 1:
						viewSeatAvailability(cinemaId, m.getId(), showTime);
						continue;
					case 2:
						viewSeatAvailability(cinemaId, m.getId(), showTime);
						while(true) {
							System.out.print("\nSelect a seat number: ");
							if(sc.hasNext()) {
								String input = sc.next();
								if(input.equals("q")) {
									break;
								} else {
									String[][] test = bc.getSeatAvailability(m.getId(), cinemaId, showTime.getDate());
									String alphabert = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
									String[] seat = input.split("[A-Za-z]");
									try {
										if(test[alphabert.indexOf(seat[0])][Integer.valueOf(seat[1])-1] == "_") {
											displayBookingForm(cinemaId, m, showTime, input);
										} else {
											System.out.println("Seat is already taken!");
										}
									} catch(ArrayIndexOutOfBoundsException e) {
										System.out.println("Enter a valid seat!");
									}
								}
							} 
						}
						break;
				}
			}
			else if(sc.next().equals("q")) {
				break;
			}
		}
	}
	
	 /**
	 * This methods prompts for information that must be collected from user prior to booking.
	 * Additional information collected such as their name, phone number, email address, and age.
	 * @param cinemaId The selected cineplex.
	 * @param movie The selected movie.
	 * @param showTime The selected show time.
	 * @param seatNum The selected seat number.
	 */
	public void displayBookingForm(int cinemaId, Movie movie, ShowTime showTime, String seatNum) {
		int phoneNum = 0;
		String email = null, name = null;
		
		while(true) {
			System.out.print("Enter your age: ");
			if(sc.hasNextInt()) {
				int inputAge = sc.nextInt();
				int ageRestriction;
				switch(movie.getAgeRating()) {
					case NC16:
						ageRestriction = 16;
						break;
					case M18:
						ageRestriction = 18;
						break;
					case R21:
						ageRestriction = 21;
						break;
					default:
						ageRestriction = 0;
				}
				if(inputAge < ageRestriction) {
					System.out.println("Age restricted! Returning...");
					return;
				} else {
					break;
				}
			}
		}
		
		// Consume \n
		sc.nextLine();
		System.out.print("Enter your name: ");
		name = sc.nextLine();
	
		System.out.print("Enter your phone number: ");
		while(true) {
			phoneNum = sc.nextInt();
			if(Integer.toString(phoneNum).length() != 8) {
				System.out.print("Enter valid phone number: ");
				continue;
			} else {
				break;
			}
		}
		
		sc.nextLine();
		System.out.print("Enter your email address:");
		email = sc.nextLine();
		
		Ticket ticket = new Ticket(cinemaId, movie.getId(), name, phoneNum, email, 0, seatNum,
				showTime.getDate());
		double returnedPrice = 0.0;
		
		PaymentUI pUI = new PaymentUI();
		if((returnedPrice = pUI.displayPaymentForm(ticket)) != -1) {
			bc.book(ticket);
			return;
		}
	}

	 /**
	 * Displays prompt for the selection of the Cineplex the user wishes to go.
	 * @param movieId The selected Movie.
	 * @return the id of the cineplex
	 */
	public int promptCinemaSelection(int movieId) {
		ArrayList<Cineplex> cineplex = new ArrayList<Cineplex>();
		ArrayList<Integer> cinemaIds = bc.getCinemaIdsWithMovie(movieId);
		CineplexController cineplexController = new CineplexController();
		for(int c : cinemaIds) {
			cineplex.add(cineplexController.getCineplexById(c));
		}
		// Display cinemas
		int choice = 0;
		while(true) {
			System.out.println("\nSelect Cinema:");
			int i = 1;
			for(Cineplex c : cineplex) {
				System.out.printf("\t%d) %s\n" , i++, c.getCineplexName());
			}
			System.out.print("Choose an option or enter q to return: ");
			if(sc.hasNextInt()) {
				if((choice = sc.nextInt()) <= cineplex.size()) {
					return cineplex.get(choice-1).getCineplexID();
				}
			} else if(sc.next().equals("q")) {
				break;
			}
		}
		return -1;
	}
	
	 /**
	 * Displays prompt for the selection of the time slot the user wishes to go.
	 * The name should include both first and
	 * last name.
	 * @param cinemaId the selected cineplex.
	 * @param movieId The selected Movie.
	 * @return the selected time slot
	 */
	public ShowTime promptShowTimeSelection(int cinemaId, int movidId) {
		Map<Instant, List<ShowTime>> showTimes = bc.getShowTimes(cinemaId, movidId).stream().collect(Collectors.groupingBy(e -> e.getDate().toInstant().truncatedTo(ChronoUnit.DAYS)));
		int choice = 0;
		
		// Select Date
		while(true) {
			System.out.print(divider);
			System.out.println("Available Dates:");
			int s = 1;
			ArrayList<List<ShowTime>> stList = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Iterator<Entry<Instant, List<ShowTime>>> iterator = showTimes.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByKey())).iterator();
			while(iterator.hasNext()) {
				Entry<Instant, List<ShowTime>> e = iterator.next();
				System.out.printf("\t%d) %s\n", s++, sdf.format(Date.from(e.getKey())));
				stList.add(e.getValue());
			}
			System.out.print("Choose an option or enter q to return: ");
			if(sc.hasNextInt()) {
				if((choice = sc.nextInt()) <= showTimes.size()) {
					List<ShowTime> timeSlots = stList.get(choice-1);
					while(true) {
						System.out.print(divider);
						System.out.println("Available Time Slots:");
						int t = 1;
						sdf = new SimpleDateFormat("hh:mm");
						for(ShowTime st : timeSlots) {
							System.out.printf("\t%d) %s\n", t++, sdf.format(st.getDate()));
						}
						System.out.print("Choose an option or enter q to return: ");
						if(sc.hasNextInt()) {
							if((choice = sc.nextInt()) <= timeSlots.size()) {
								return timeSlots.get(choice-1);
							}
						} else if(sc.next().equals("q")) {
							break;
						}
					}
				}
			} else if(sc.next().equals("q")) {
				break;
			}
		}
		return null;
	}
	
	 /**
	 * This displays the booking history of the customers.
	 * Prompts user for email or phone number.
	 */
	public void viewBookingHistory() {
		System.out.print(divider);
		System.out.println("Enter email address or phone number: ");
		if(sc.hasNext()) {
			String query = sc.next();
			ArrayList<Ticket> tickets = bc.getBookingHistory(query);
			tickets.sort(Comparator.comparing(Ticket::getDateTime).reversed());
			
			for(Ticket t : tickets) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
				System.out.printf(divider
						+ "TID: " + t.gettId()
						+ "\nMovie: " + bc.getMovieById(t.getMovieId()).getTitle()
						+ "\nTime Slot: " + sdf.format(t.getDateTime())
						+ "\nSeat Number: " + t.getSeatNum()
						+ "\nPrice: %.2f\n", t.getPrice());
			}
		}
	}
	
	 /**
	 * Retrieve populated seats and displays the status of the seating arrangement.
	 * "_" denotes that the seat is available, while "X" represents a taken seat.
	 * @param cimeaID ID of the selected Cineplex.
	 * @param movieID ID of the selected Movie.
	 * @param showTime The selected time slot.
	 */
	public void viewSeatAvailability(int cimeaID, int movieID, ShowTime showTime) {
		String alphabert = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[][] seats = bc.getSeatAvailability(cimeaID, movieID, showTime.getDate());
		
		System.out.print(divider);
		System.out.println("Legends:");
		System.out.println("\tAvailable Seats: _");
		System.out.println("\tSold: X");
		
		System.out.println("Seats:");
		System.out.println(" ".repeat((int) ((seats[0].length+1)*1.5)) + "Screen");
		
		for(int i = 1; i <= seats[0].length; i++) {
			if((seats[0].length)/2+1 == i) {
				System.out.print("   ");
			}
			System.out.printf(" %2d ", i);
		}
		System.out.println();
		for(int i = 0; i < seats.length; i ++) {
			System.out.print(alphabert.charAt(i) + " ");
			for(int j = 0; j < seats[0].length; j++) {
				if((seats[0].length)/2 == j) {
					System.out.print(" | ");
				}
				System.out.print(seats[i][j].concat(" | "));
			}
			System.out.println();
		}
		
		System.out.println(" ".repeat((int) ((seats[0].length+1)*1.5)) + "Entrance");
		
		
	}
	
	 /**
	 * Displays the top 5 movies, with option of if the results should be sorted by ticket sales or by ratings.
	 * @param byTicketSales The choice of sorting.
	 */
	public void listTopFive(boolean byTicketSales) {
		System.out.print(divider);
		if(byTicketSales) {
			System.out.println("Top 5 Movie by Sales");
			int i = 1;
			ArrayList<Movie> movie = new ArrayList<>();
			Iterator<Entry<Integer, Long>> it = bc.getTopFiveByTicketSales().iterator();
			while(it.hasNext()) {
				Entry<Integer, Long> e = it.next();
				Movie m = bc.getMovieById(e.getKey());
				movie.add(m);
				Long numOfTickets = e.getValue();
				System.out.printf("%d) %s - %d sold\n", i++, m.getTitle(), numOfTickets);
			}
		} else {
			System.out.println("Top 5 Movie by Ratings");
			ArrayList<Movie> top = bc.getTopFivebyRatings();
		}
	}	
}
