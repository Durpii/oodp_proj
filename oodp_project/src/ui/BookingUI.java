package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import cinema.Movie;
import cinema.ShowStatus;
import cinema.ShowTime;
import cinema.Ticket;
import controllers.BookingController;
import controllers.PaymentController;
import users.MovieGoer;

public class BookingUI {
	BookingController bc = new BookingController();
	MovieGoer movieGoer;
	Scanner sc = new Scanner(System.in);
	String divider = "_".repeat(40).concat("\n\n");
	
	public BookingUI(MovieGoer movieGoer) {
		this.movieGoer = movieGoer;
	}
	
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
					displayMovieUserOptions(result.get(choice-1));
				}
			} else if(sc.next().equals("q")) {
				break;
			}
		};
	}
	
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
				m.getOverallRating(),
				m.getShowStatus().toString().replace("_", " "),
				String.join(", ", m.getCasts()),
				m.getDirector()
				);
	}
	
	public void displayMovieUserOptions(Movie m) {
		if(m.getShowStatus() == ShowStatus.END_OF_SHOWING) {
			return;
		}
		int choice = 0;
		while(true) {
			System.out.print("\n"
					+ "What would you like to do?\n"
					+ "\t1) View Seat Availability\n"
					+ "\t2) Book Movie\n"
					+ "Choose an option or enter q to return: ");
			if(sc.hasNextInt()) {
				choice = sc.nextInt();
				
				if(m.getShowStatus() == ShowStatus.END_OF_SHOWING || m.getShowStatus() == ShowStatus.COMING_SOON) {
					System.out.println("\nSorry! Not available at this moment");
					break;
				}
				int cinemaId = 0;//promptCinemaSelection();
				Date showTime = new Date();//promptShowTimeSelection(cinemaId, m.getId());
				switch(choice) {
					case 1:
						viewSeatAvailability(cinemaId, m.getId(), showTime);
						continue;
					case 2:
						PaymentController pc = new PaymentController();
						pc.calculateFee(null);
						//PaymentUI pUI = new Payment pUI();
						//pc.calculateFee(false)
						//bc.book(movieGoer.getUserId(), cinemaId, , seatNum, dateTime);
						continue;
				}
			}
			else if(sc.next().equals("q")) {
				break;
			}
		}
	}
	
	// return cinema ID
	// TODO Add movie id to parameter
	public int promptCinemaSelection() {
		// TODO Fetch cinemas
		ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
		
		// Display cinemas
		int choice = 0;
		while(true) {
			System.out.println("\nSelect Cinema:");
			int i = 1;
			for(Cinema c : cinemas) {
				System.out.printf("\t%d) %s" , i++, c.getCinemaName());
			}
			System.out.print("Choose an option or enter q to return: ");
			if(sc.hasNextInt()) {
				if((choice = sc.nextInt()) <= cinemas.size()) {
					return cinemas.get(choice-1).getCinemaID();
				}
			} else if(sc.next().equals("q")) {
				break;
			}
		}
		return -1;
	}
	
	public Date promptShowTimeSelection(int cinemaId, int movidId) {
		// TODO Fetch show times 4 days in advance
		Map<Date, List<ShowTime>> showTimes = null;// = showTimes.stream().collect(Collectors.groupingBy(null, null));
		int choice = 0;
		
		// Select Date
		while(true) {
			System.out.print("Available Dates:");
			int s = 1;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for(Date st : showTimes.keySet()) {
				System.out.printf("\ti) %s", sdf.format(st));
			}
			System.out.print("Choose an option or enter q to return: ");
			if(sc.hasNextInt()) {
				if((choice = sc.nextInt()) <= showTimes.size()) {
					Date[] timeSlots = showTimes.keySet().toArray(new Date[showTimes.size()]);
					while(true) {
						System.out.print("Available Time Slots:");
						int t = 1;
						sdf = new SimpleDateFormat("hh:mm");
						for(Date ts : timeSlots) {
							System.out.printf("\ti) %s", sdf.format(ts));
						}
						System.out.print("Choose an option or enter q to return: ");
						if(sc.hasNextInt()) {
							if((choice = sc.nextInt()) <= timeSlots.length) {
								return timeSlots[choice-1];
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
		return new Date();
	}
	
	public void viewBookingHistory() {
		ArrayList<Ticket> tickets = bc.getBookingHistory(movieGoer.getUserId());
		
		tickets.sort(Comparator.comparing(Ticket::getDateTime).reversed());
		
		for(Ticket t : tickets) {
			
			Movie m = bc.getMovieById(t.getMovieId());
			
			// Fetch Movie name from movie database
			System.out.println("Movie: " + m.getTitle());			
			
			DateFormat formattedDate = new SimpleDateFormat();  
			formattedDate.format(t.getDateTime());
			System.out.println("Date: " + formattedDate);
			
			System.out.println("Seat Number: " + t.getSeatNum());
		}
	}
	
	public void viewSeatAvailability(int cimeaID, int movieID, Date showTime) {
		String alphabert = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[][] seats = bc.getSeatAvailability(cimeaID, movieID, showTime);
		
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
				System.out.printf("%d) %s - %d sold", i++, m.getTitle(), numOfTickets);
			}
		} else {
			System.out.println("Top 5 Movie by Ratings");
			ArrayList<Movie> top = bc.getTopFivebyRatings();
		}
	}	
}
