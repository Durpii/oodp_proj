package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import cinema.Movie;
import cinema.Ticket;
import controllers.BookingController;

public class BookingUI {
	BookingController bc = new BookingController();
	Scanner sc = new Scanner(System.in);
	
	public BookingUI() {
		
	}
	
	public void searchMovie() {
		ArrayList<Movie> result = new ArrayList<Movie>();
		while(result.isEmpty()) {
			System.out.print("Enter Search Query: ");
			String searchQuery = sc.next().trim();
			result.addAll(bc.search(searchQuery));
		}
		int i = 1;
		for(Movie m : result) {
			System.out.println(i++ + ". " + m.getTitle());
		}
		
		// TODO
		String choice = null;
		while(!choice.equals("q")) {
			System.out.print("Select a movie or enter q to return: ");
			choice = sc.next().trim();
			if(choice.matches("\\d+") && Integer.valueOf(choice) <= result.size()) {
				viewDetails(result.get(Integer.valueOf(choice)-1));
			}
		}
	}
	
	public void viewDetails(Movie m) {
		System.out.printf(
				"==============================\n"
				+ "%s\n"
				+ "SYNOPSIS: %s\n"
				+ "GENRE: %s\n"
				+ "AGE RESTRICTION: %s\n"
				+ "OVERALL RATING: %s/5.0\n"
				+ "SHOWS STATUS: %s\n"
				+ "CAST: %s\n"
				+ "DIRECTOR: %s\n"
				+ "==============================\n",
				
				m.getTitle(),
				m.getSypnosis(),
				m.getTypeOfMovie(),
				m.getAgeRating(),
				m.getOverallRating(),
				m.getShowStatus().toString().replace("_", " "),
				String.join(", ", m.getCasts()),
				m.getDirector()
				);		
	}
	
	public void viewBookingHistory(int userId) {
		ArrayList<Ticket> tickets = bc.getBookingHistory(userId);
		
		for(Ticket t : tickets) {
			System.out.print("==============================");
			// Fetch Movie name from movie database
			System.out.println("Movie: ");
			
			
			DateFormat formattedDate = new SimpleDateFormat();  
			formattedDate.format(t.getDateTime());
			System.out.println("Date: " + formattedDate);
			
			System.out.println("Seat Number: " + t.getSeatNum());
		}
	}
	
	public void viewSeatAvailability(int cimeaID, int movieID, Date showTime) {
		String alphabert = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String[][] seats = bc.getSeatAvailability(cimeaID, movieID, showTime);
		for(int i = 0; i < seats.length; i ++) {
			System.out.println(alphabert.charAt(i));
			for(int j = 0; j < seats[0].length; j++) {
				System.out.print(seats[i][j]);
			}
			System.out.println();
		}
	}
	
	void listTopFive() {
		ArrayList<Movie> tickets = bc.getTopFive();
	}	
}
