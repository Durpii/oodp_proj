package controllers;

import cinema.Cineplex;
import cinema.Cinema;
import cinema.CinemaClass;

public class CinemaController {

	public final String SEPARATOR = "@@@";
	
	public CinemaController() {
		//empty constructor
	}
	
	public void addCinema(Cineplex cineplex, CinemaClass cinemaClass) {
		int i = cineplex.getCinemaSize();
		Cinema cinema = new Cinema(cineplex, i, cinemaClass);
		cineplex.addCinema(cinema);
	}
}