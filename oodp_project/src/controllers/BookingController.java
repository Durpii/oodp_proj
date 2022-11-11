package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
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
				String[] casts = data[5].split("\\|");
				float overallRating = Float.valueOf(data[6]);
				ShowStatus showStatus = ShowStatus.valueOf(data[7]);
				AgeRating ageRating = AgeRating.valueOf(data[8]);
				if(title.toLowerCase().contains(query.toLowerCase())) {
					movies.add(new Movie(id, title, typeOfMovie, sypnosis, director, casts, overallRating, showStatus, ageRating));
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
	
	
	
	public void book(int userId, int cinemId, int movieId, int seatNum, Date dateTime) {
		Ticket ticket = new Ticket(userId, cinemId, movieId, seatNum, dateTime);
		
		File file = new File("tickets.txt");
		PrintWriter out = null;
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			out = new PrintWriter(new FileWriter(file));
			StringBuilder str = new StringBuilder(SEPARATOR);
			str.append(ticket.getUserId());
			str.append(ticket.getCinemId());
			str.append(ticket.getMovieId());
			str.append(ticket.getSeatNum());
			str.append(ticket.getDateTime());
			out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	
	public String[][] getSeatAvailability(int cimeaID, int movieID, Date showTime) {
		String[][] seats = null;
		
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
			seats = new String[rows][cols];
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
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return seats;
	}
	
	public ArrayList<Movie> getTopFive() {
		return null;
	}
	
	public ArrayList<Ticket> getBookingHistory(int userId) {
		ArrayList<Ticket> history = new ArrayList<Ticket>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("tickets.txt"));
			int count;
			while (sc.hasNextLine()){
				String[] data = sc.nextLine().split(SEPARATOR);
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy;HH:mm:ss");
				Ticket ticket = new Ticket(
						Integer.parseInt(data[0]),
						Integer.parseInt(data[0]),
						Integer.parseInt(data[0]),
						Integer.parseInt(data[0]),
						sdf.parse(data[0]));
				if(ticket.getUserId() == userId) {
					history.add(ticket);
				}
			}
		} catch (FileNotFoundException | NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return history;
	}
	
	public Movie getMovieById(int movieId) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("movies.txt"));
			sc.useDelimiter(SEPARATOR);
			while (sc.hasNextLine()){
				String[] data = sc.nextLine().split(SEPARATOR);
				int id = Integer.valueOf(data[0].split(":")[1]);
				if(id == movieId) {
					String title = data[1];
					String typeOfMovie = data[2];
					String sypnosis = data[3];
					String director = data[4];
					String[] casts = data[5].split("\\|");
					float overallRating = Float.valueOf(data[6]);
					ShowStatus showStatus = ShowStatus.valueOf(data[7]);
					AgeRating ageRating = AgeRating.valueOf(data[8]);
					return new Movie(id, title, typeOfMovie, sypnosis, director, casts, overallRating, showStatus, ageRating);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return null;
	}
}
