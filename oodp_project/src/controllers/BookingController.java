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
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;
import cinema.ShowTime;
import cinema.Ticket;

public class BookingController {
	public BookingController() {}
	
	private final String SEPARATOR = "@@@";
		
	public ArrayList<Movie> search(String query) {
		Scanner sc = null;
		ArrayList<Movie> movies = new ArrayList<Movie>();
		File file = new File("movies.txt");
		if(file.exists()) {
			try {
				sc = new Scanner(new FileInputStream(file));
				
				while (sc.hasNextLine()){
					Movie m = parseMovie(sc.nextLine());
					if(m.getTitle().toLowerCase().contains(query.toLowerCase())) {
						movies.add(m);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return null;
			} finally {
				sc.close();
			}
		}
		return movies;
	}
	
	public boolean book(Ticket ticket) {
		//Ticket ticket = new Ticket(userId, cinemId, movieId, seatNum, dateTime);
		
		File file = new File("tickets.txt");
		PrintWriter out = null;
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			out = new PrintWriter(new FileWriter(file));
2			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDhhmm");
			String tId = String.format("%3d", ticket.getCinemaId()).replace(' ', '0') + sdf.format(new Date());
			sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			String formattedDate = sdf.format(ticket.getDateTime());
			
			String str = String.join(SEPARATOR,
					tId,
					String.valueOf(ticket.getCinemaId()),
					String.valueOf(ticket.getMovieId()),
					ticket.getName(),
					String.valueOf(ticket.getPhoneNum()),
					ticket.getEmail(),
					String.valueOf(ticket.getPrice()),
					ticket.getSeatNum(),
					formattedDate);

			out.println(str);
			out.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<ShowTime> getShowTimes(int cinemaId, int movieId) {
		ArrayList<ShowTime> showTimes = new ArrayList<ShowTime>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("showTimes.txt"));
			
			while (sc.hasNextLine()){
				String[] data = sc.nextLine().split(SEPARATOR);
				if(movieId == Integer.valueOf(data[0]) && cinemaId == Integer.valueOf(data[1])) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
					ShowTime st = new ShowTime(sdf.parse(data[2]));
					if(st.getDate().after(new Date())) {
						showTimes.add(st);
					}
				}
			}
		} catch (FileNotFoundException | ParseException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return showTimes;
	}
	
	public String[][] getSeatAvailability(int cimeaID, int movieID, Date showTime) {
		String[][] seats = new String[0][0];
		String alphabert = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		Scanner sc = null;
		int rows, cols;
		try {
			// TODO
			int totalSeats = 90;
			rows = 10;
			cols = totalSeats/rows;
			
			// Default to empty seats
			seats = new String[rows][cols];
			for(int i = 0; i < rows; i ++) {
				for(int j = 0; j < cols; j++) {
					seats[i][j] = "_";
				}
			}
			
			sc = new Scanner(new FileInputStream("tickets.txt"));
			while (sc.hasNextLine()){
				Ticket ticket = parseTicket(sc.nextLine());
				String[] seat = ticket.getSeatNum().split("[A-Za-z]");
				int x = alphabert.indexOf(seat[0]);
				int y = Integer.valueOf(seat[1])-1;
				seats[x][y] = "X";
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return seats;
	}
	
	public Stream<Entry<Integer, Long>> getTopFiveByTicketSales() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("tickets.txt"));
			
			while (sc.hasNextLine()){
				tickets.add(parseTicket(sc.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Map<Integer, Long> count = tickets.stream().collect(Collectors.groupingBy(Ticket::getMovieId, Collectors.counting()));
		/*count.entrySet().stream().sorted().limit(5).forEach((e) -> {
			movies.add(getMovieById(e.getValue().intValue()));
		});*/
		return count.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(5);
	}
	
	public ArrayList<Movie> getTopFivebyRatings() {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("movies.txt"));
			
			while (sc.hasNextLine()){
				movies.add(parseMovie(sc.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		movies.sort(Comparator.comparing(Movie::getOverallRating).reversed());
		if(movies.size() < 5) {
			return movies;
		} else {
			return (ArrayList<Movie>) movies.subList(0, 5);
		}
	}
	
	public ArrayList<Ticket> getBookingHistory(String... query) {
		ArrayList<Ticket> history = new ArrayList<Ticket>();
		Scanner sc = null;
		File file = new File("tickets.txt");
		if(file.exists()) {
			try {
				sc = new Scanner(new FileInputStream(file));
				while (sc.hasNextLine()){
					Ticket ticket = parseTicket(sc.nextLine());
					;
					if(Arrays.asList(query).contains(String.valueOf(ticket.getPhoneNum()))
							|| Arrays.asList(query).contains(ticket.getEmail())) {
						history.add(ticket);
					}
				}
			} catch (FileNotFoundException | NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return history;
	}
	
	public Movie getMovieById(int movieId) {
		Scanner sc = null;
		File file = new File("movies.txt");
		if(file.exists()) {
			try {
				sc = new Scanner(new FileInputStream(file));
				
				while (sc.hasNextLine()){
					Movie m = parseMovie(sc.nextLine());
					if(m.getId() == movieId) {
						return m;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				sc.close();
			}
		}
		return null;
	}
	
	public Movie parseMovie(String input) {
		String[] data = input.split(SEPARATOR);
		int id = Integer.valueOf(data[0].split(":")[1]);
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
	
	public Ticket parseTicket(String input) {
		String[] data = input.split(SEPARATOR);
		String tId = data[0];
		int cinemaId = Integer.parseInt(data[1]);
		int movieId = Integer.parseInt(data[2]);
		String name = data[3];
		int phoneNum = Integer.parseInt(data[4]);
		String email = data[5];
		double price = Double.parseDouble(data[6]);
		String seatNum = data[7];
		Date dateTime;
		try {
			dateTime = new SimpleDateFormat("yyyy-MM-dd-HH-mm").parse(data[8]);
		} catch (ParseException e) {
			dateTime = new Date();
		}
		return new Ticket(tId, cinemaId, movieId, name, phoneNum, email, price, seatNum, dateTime);
	}
}
