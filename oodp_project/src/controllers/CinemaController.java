package controllers;

import cinema.Cineplex;
import cinema.Cinema;
import cinema.CinemaClass;

/**
 * CinemaController to act as the controller for communication between CinemaUI class and Cinema class.
 * @author	Qwek Jin Kee
 * @version 1.0
 * @since	2022-11-11
 */
public class CinemaController {

	/**
	 * The variable that will be used to separate values in the text file
	 */
	public final String SEPARATOR = "@@@";
	
	/**
	 * Creates a CinemaController.
	 */
	public CinemaController() {
		//empty constructor
	}
	
	/**
	 * Adds a cinema hall to this Cineplex.
	 * @param cineplex	This Cineplex.
	 * @param cinemaClass	This cinema's Class.
	 */
	public void addCinema(Cineplex cineplex, CinemaClass cinemaClass) {
		int i = cineplex.getCinemaSize();
		Cinema cinema = new Cinema(cineplex, i, cinemaClass);
		cineplex.addCinema(cinema);
	}
}