package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringJoiner;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;
import cinema.Ticket;

public class BookingController {
	public BookingController() {}
	
	public final String SEPARATOR = "@@@";
		
	public ArrayList<Movie> search(String query) {
		Scanner sc = null;
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try {
			sc = new Scanner(new FileInputStream("movies.txt"));
			sc.useDelimiter(SEPARATOR);
			while (sc.hasNextLine()){
				String[] data = sc.nextLine().split(SEPARATOR);
				int id = Integer.valueOf(data[0].split(":")[1]);
				String title = data[1];
				String typeOfMovie = data[2];
				String sypnosis = data[3];
				String director = data[4];
				float overallRating = Float.valueOf(data[5]);
				ShowStatus showStatus = ShowStatus.valueOf(data[6]);
				AgeRating ageRating = AgeRating.valueOf(data[7]);
				if(title.toLowerCase().contains(query.toLowerCase())) {
					movies.add(new Movie(id, title, typeOfMovie, sypnosis, director, overallRating, showStatus, ageRating));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			sc.close();
		}
		return movies;
	}
	
	public void viewDetails(Movie movie) {
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
				
				movie.getTitle(),
				movie.getSypnosis(),
				movie.getTypeOfMovie(),
				movie.getAgeRating(),
				movie.getOverallRating(),
				movie.getShowStatus().toString().replace("_", " "),
				"cast?",
				movie.getDirector()
				);		
	}
	
	public void book(int movieId, int seatNum, Date dateTime) {
		String name, email;
		int phoneNum;
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter your name: ");
		name = sc.next();
		System.out.print("Enter email address: ");
		email = sc.next();
		System.out.println("Enter phone number: ");
		phoneNum = sc.nextInt();
		
		//TODO
		boolean isHoliday = false;
		
		Ticket ticket = new Ticket(movieId, name, email, phoneNum, seatNum, dateTime, isHoliday);
		
		File file = new File("tickets.txt");
		PrintWriter out = null;
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			out = new PrintWriter(new FileWriter(file));
			StringJoiner data = new StringJoiner(SEPARATOR);
			data.add(Integer.toString(movieId));
			data.add(name);
			data.add(email);
			data.add(Integer.toString(phoneNum));
			data.add(Integer.toString(seatNum));
			
			DateFormat formattedDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
			data.add(formattedDate.format(dateTime));
			
			data.add(Boolean.toString(isHoliday));
			out.println(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	public void checkSeatAvailability(int cimeaID, int movieID, Date showTime) {
		Scanner sc = null;
		int rows, cols;
		try {
			// Fetch cinema seats and number of rows
			sc = new Scanner(new FileInputStream("cinema.txt"));
			
			// TODO
			int totalSeats = 100;
			rows = 10;
			cols = totalSeats/rows;
			
			// Default to empty seats
			String[][] seats = new String[rows][cols];
			for(int i = 0; i < rows; i ++) {
				for(int j = 0; j < cols; j++) {
					seats[i][j] = "_";
				}
			}
			
			// Scan
			sc = new Scanner(new FileInputStream("tickets.txt"));
			sc.useDelimiter(SEPARATOR);
			while (sc.hasNextLine()){
				String[] data = sc.nextLine().split(SEPARATOR);
	            int seatNumber = Integer.parseInt(data[5]);
				int x = seatNumber/rows;
				int y = seatNumber%rows;
				seats[x][y] = "X";
			}
			
			String alphabert = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			for(int i = 0; i < rows; i ++) {
				System.out.println(alphabert.charAt(i));
				for(int j = 0; j < cols; j++) {
					System.out.print(seats[i][j]);
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
	
	void listTopFive() {
		
	}
	
	public void viewBookingHistory() {
		String query;
		Scanner sc = new Scanner(System.in);
		System.out.print("===== View Booking History =====");
		System.out.print("Enter phone number or email address: ");
		query = sc.next();
		try {
			sc = new Scanner(new FileInputStream("tickets.txt"));
			int count;
			while (sc.hasNextLine()){
				String[] data = sc.nextLine().split(SEPARATOR);
				Ticket ticket = new Ticket(
						Integer.valueOf(data[0]),
						data[1],
						data[2],
						Integer.valueOf(data[3]),
						Integer.valueOf(data[4]),
						null,
						Boolean.valueOf(data[6]));
				if(ticket.getEmail() == query || ticket.getPhoneNum() == Integer.valueOf(query)) {
					System.out.print("==============================");
					// Fetch Movie name from movie database
					System.out.println("Movie: ");
					System.out.println("Name: " + ticket.getName());
					System.out.println("Email address: " + ticket.getEmail());
					System.out.println("Phone number: " + ticket.getPhoneNum());
					
					DateFormat formattedDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
					formattedDate.format(ticket.getDateTime());
					System.out.println("Date: " + formattedDate);
					
					System.out.println("Seat Number: " + ticket.getSeatNum());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
