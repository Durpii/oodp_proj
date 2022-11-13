package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import cinema.Movie;
import cinema.Ticket;

public class PaymentController {
	private final String SEPARATOR = "@@@";
	
	public void adjustMoviePrices(int movieId, double weekday, double weekend, double holiday) {
		File file = new File("moviePrices.txt");
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(file));
			StringBuffer contents = new StringBuffer();
		
			while (sc.hasNextLine()){
				String line = sc.nextLine();
				String[] data = line.split(SEPARATOR);
				if(Integer.valueOf(data[0]) == movieId) {
					continue;
				} else {
					contents.append(line);
				}
				contents.append("\n");
			}
			
			String newLine = String.join(SEPARATOR,
					Integer.valueOf(movieId).toString(),
					Double.valueOf(holiday).toString(),
					Double.valueOf(weekend).toString(),
					Double.valueOf(holiday).toString());
		
			contents.append(newLine);	
			FileOutputStream fileOut = new FileOutputStream(file);
	        fileOut.write(contents.toString().getBytes());
	        fileOut.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public double[] getMoviePrices(int movieId) {
		double[] prices = new double[3];
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("moviePrices.txt"));
			while (sc.hasNextLine()){
				String[] data = sc.nextLine().split(SEPARATOR);
				if(Integer.valueOf(data[0]) == movieId) {
					prices[0] = Double.valueOf(data[1]);
					prices[1] = Double.valueOf(data[2]);
					prices[2] = Double.valueOf(data[3]);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return prices;
	}
	
	public double calculateFee(Ticket ticket) {
		double[] prices = getMoviePrices(ticket.getMovieId());
		if(ticket.isHoliday(ticket.getDateTime())) {
			return prices[2];
		} else if(ticket.isWeekend(ticket.getDateTime())) {
			return prices[1];
		} else {
			return prices[0];
		}
	}
}
