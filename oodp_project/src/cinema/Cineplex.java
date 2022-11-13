package cinema;

import java.util.ArrayList;

/**
 * Represents a Cineplex.
 * A Cineplex can have many Cinema objects.
 * @author	Qwek Jin Kee
 * @version	1.0
 * @since	2022-11-11
 */
public class Cineplex {

	/**
	 * Name of the Cineplex.
	 */
	private String cineplexName;
	
	/**
	 * ID of the Cineplex.
	 */
	private int cineplexID;
	
	/**
	 * The list of the Cinemas that belongs to this Cineplex.
	 */
	ArrayList<Cinema> cinemas;
	
	/**
	 * Creates a new Cineplex object.
	 */
	public Cineplex() {
		//empty constructor
	}
	
	/**
	 * Creates a new Cineplex object with the Cineplex name and Cineplex ID.
	 * @param cineplexName This Cineplex's name.
	 * @param cineplexID This Cineplex's ID.
	 */
	public Cineplex(String cineplexName, int cineplexID) {
		this.cineplexName = cineplexName;
		this.cineplexID = cineplexID;
		this.cinemas = new ArrayList<Cinema>();
		//initialise with 2 regular cinemas and 1 platinum cinema
		for(int i=0; i<2; i++) {
			Cinema cinema = new Cinema(this, (i+1) , CinemaClass.REGULAR);
			cinemas.add(cinema);
		}
		
		Cinema cinema = new Cinema(this, 2, CinemaClass.PLATINUM);
		cinemas.add(cinema);
		
	}
	
	/**
	 * Gets the name of this Cineplex.
	 * @return this Cineplex's name.
	 */
	public String getCineplexName() {
		return cineplexName;
	}
	
	/**
	 * Changes the name of this Cineplex.
	 * @param cineplexName This Cineplex's name.
	 */
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	/**
	 * Gets the ID of this Cineplex.
	 * @return this Cineplex's ID.
	 */
	public int getCineplexID() {
		return cineplexID;
	}
	
	/**
	 * Changes the ID of this Cineplex.
	 * @param cineplexID This Cineplex's ID.
	 */
	public void setCineplexID(int cineplexID) {
		this.cineplexID = cineplexID;
	}
	
	/**
	 * Gets the ID of Cinema in this Cineplex by ID.
	 * @param id The ID of the Cinema.
	 * @return The Cinema object that matches the ID.
	 */
	public Cinema getCinemaById(int id) {
		return cinemas.get(id);
	}
	
	/**
	 * Adds a Cinema to this Cineplex. 
	 * @param c The Cinema object.
	 */
	public void addCinema(Cinema c) {
		cinemas.add(c);
	}
	
	/**
	 * Gets the size of the Cinemas in this Cineplex.
	 * @return The number of Cinemas in this Cineplex.
	 */
	public int getCinemaSize() {
		return cinemas.size();
	}
}
