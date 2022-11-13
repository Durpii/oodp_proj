package controllers;

import java.util.ArrayList;

import cinema.Cineplex;
import cinema.Cinema;
import cinema.CinemaClass;

public class CineplexController {

	public final String SEPARATOR = "@@@";
	ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
	
	public CineplexController() {
		Cineplex cineplex = new Cineplex("JEM", 1);
		Cineplex cineplex2 = new Cineplex("AMK Hub", 2);
		Cineplex cineplex3 = new Cineplex("Parkway Parade", 3);
		
		cineplexes.add(cineplex);
		cineplexes.add(cineplex2);
		cineplexes.add(cineplex3);
	}
	
	public Cineplex getCineplexById(int id) {
		return cineplexes.get((id-1));
	}
	
	public void addCinema(Cineplex cineplex, CinemaClass cinemaClass) {
		int i = cineplex.getCinemaSize();
		Cinema cinema = new Cinema(cineplex, i, cinemaClass);
		cineplex.addCinema(cinema);
	}
}
