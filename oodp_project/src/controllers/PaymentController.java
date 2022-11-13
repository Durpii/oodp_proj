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

/**
This Payment Controller is for manipulating data in "moviePrices.txt" database.
@author Liu Woon Kit
@version 1.0
@since 2022-11-11
*/

public class PaymentController {
	/**
	 * String to be used as delimiter
	 */
	private final String SEPARATOR = "@@@";
	
	/**
	* Edits "moviePrices.txt", in order to store the new prices.
	* If prices already exist for a movie, skip that line and add the new prices at the end.
	* @param movieId	The ID of the Movie.
	* @param weekday	The price of the Movie when it is a weekday.
	* @param weekend	The price of the Movie when it is a weekend.
	* @param holiday	The price of the Movie when it is a holiday.
	*/
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
	
	/**
	* Searches "moviePrices.txt" for the specified movie ID.
	* @param movieId	The ID of the movie.
	* @return			The weekend, holiday and weekday prices in an array
	*/
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
	
	/**
	* Groups the tickets by movie ID.
	* Sorts the tickets by the number of occurrences.
	* @return			The top 5 Movie by sales
	*/
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
