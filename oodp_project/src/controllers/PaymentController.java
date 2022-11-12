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
			StringBuffer sb = new StringBuffer();
		
			boolean overwritten = false;
			while (sc.hasNextLine()){
				String line = sc.nextLine();
				String[] data = line.split(SEPARATOR);
				if(Integer.valueOf(data[0]) == movieId) {
					overwritten = true;
					
				} else {
					sb.append(line);
				}
				sb.append("\n");
			}
			sb.append(movieId);
			sb.append(weekday);
			sb.append(weekend);
			sb.append(holiday);
			
		} catch(IOException e) {
			e.printStackTrace();
		}
			
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer sb = new StringBuffer();
	        String line;
	        
	        while ((line = reader.readLine()) != null) {
	            sb.append(line);
	            sb.append('\n');
	        }
	        reader.close();
	        FileOutputStream fileOut = new FileOutputStream(file);
	        fileOut.write(sb.toString().getBytes());
	        fileOut.close();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		PrintWriter out = null;
		
		try {
			
			out = new PrintWriter(new FileWriter(file));
			StringBuilder str = new StringBuilder(SEPARATOR);
			str.append(movieId);
			str.append(weekday);
			str.append(weekend);
			str.append(holiday);
			out.println(str);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
	
	public boolean processPayment(double fee, int creditCardNumber) {
		return true;
	}
}
