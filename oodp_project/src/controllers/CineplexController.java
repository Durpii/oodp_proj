package controllers;

import java.util.ArrayList;

import cinema.Cineplex;

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
	
}
