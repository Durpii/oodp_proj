package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import cinema.Cineplex;
import cinema.Cinema;
import cinema.CinemaClass;

public class CinemaController {

	public final String SEPARATOR = "@@@";
	
	public Cinema getCinemaById(int id) {
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream("cinema.txt"));
			sc.useDelimiter(SEPARATOR);
			while (sc.hasNextLine()){
				String[] data = sc.nextLine().split(SEPARATOR);
				int cid = Integer.valueOf(data[1]);
				if(cid == id) {
					String cineplex = data[0];
					CinemaClass cinemaClass = CinemaClass.valueOf(data[2]);
					return new Cinema();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return null;
	}
	
	public void addCinema(Cineplex cineplex, CinemaClass cinemaClass) {
		int i = cineplex.getCinemaSize();
		Cinema cinema = new Cinema(cineplex, i, cinemaClass);
		cineplex.addCinema(cinema);
	}
}
