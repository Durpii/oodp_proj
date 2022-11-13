package controllers;

import java.util.ArrayList;

import cinema.Cineplex;

/**
 * CineplexController to act as the controller for communication between classes.
 * @author	Qwek Jin Kee
 * @version 1.0
 * @since	2022-11-11
 */
public class CineplexController {

	/**
	 * The variable that will be used to separate values in the text file
	 */
	public final String SEPARATOR = "@@@";
	
	/**
	 * The list of all the Cineplex.
	 */
	ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
	
	/**
	 * Creates a CineplexController and creates 3 Cineplex.
	 */
	public CineplexController() {
		Cineplex cineplex = new Cineplex("JEM", 1);
		Cineplex cineplex2 = new Cineplex("AMK Hub", 2);
		Cineplex cineplex3 = new Cineplex("Parkway Parade", 3);
		
		cineplexes.add(cineplex);
		cineplexes.add(cineplex2);
		cineplexes.add(cineplex3);
	}
	
	/**
	 * Gets the Cineplex with given ID.
	 * @param id	ID of the Cineplex.
	 * @return	The Cineplex with the given ID.
	 */
	public Cineplex getCineplexById(int id) {
		return cineplexes.get((id-1));
	}
	
}
