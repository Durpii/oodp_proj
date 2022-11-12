package cinema;

import java.util.ArrayList;

public class Cineplex {

	private String cineplexName;
	private int cineplexID;
	
	ArrayList<Cinema> cinemas;
	
	public Cineplex() {
		//empty constructor
	}
	
	//construct a Cineplex object with 3 Cinemas (2 Regular, 1 Platinum)
	public Cineplex(String cineplexName) {
		this.cineplexName = cineplexName;
		this.cinemas = new ArrayList<Cinema>();
		//initialise with 2 regular cinemas and 1 platinum cinema
		for(int i=0; i<2; i++) {
			Cinema cinema = new Cinema(this, (i+1) , CinemaClass.REGULAR);
			cinemas.add(cinema);
		}
		
		Cinema cinema = new Cinema(this, 2, CinemaClass.PLATINUM);
		cinemas.add(cinema);
		
	}
	
	public String getCineplexName() {
		return cineplexName;
	}
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;
	}

	public Cinema getCinemaById(int id) {
		return cinemas.get(id);
	}
	
	public void addCinema(Cinema c) {
		cinemas.add(c);
	}
	
	public int getCinemaSize() {
		return cinemas.size();
	}
}
