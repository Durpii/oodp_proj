package cinema;

import java.util.ArrayList;

public class Cineplex {

	private String cineplexName;
	private int cineplexID;
	
	ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
	
	public Cineplex() {
		//empty constructor
	}
	
	//construct a Cineplex object with 4 Cinemas (3 Regular, 1 Platinum)
	public Cineplex(String cineplexName, int cineplexID) {
		this.cineplexName = cineplexName;
		this.cineplexID = cineplexID;
		
		//initialise with 3 regular cinemas and 1 platinum cinema
		for(int i=0; i<3; i++) {
			Cinema cinema = new Cinema(this, i, CinemaClass.REGULAR);
			cinemas.add(cinema);
		}
		
		Cinema cinema = new Cinema(this, 3, CinemaClass.PLATINUM);
		cinemas.add(cinema);
		
	}
	
	public String getCineplexName() {
		return cineplexName;
	}
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}
	public int getCineplexID() {
		return cineplexID;
	}
	public void setCineplexID(int cineplexID) {
		this.cineplexID = cineplexID;
	}
	
	public Cinema getCinemaById(int id) {
		return cinemas.get(id);
	}
}
