package ui;

import java.util.Scanner;

import cinema.CinemaClass;
import cinema.Cineplex;
import controllers.CinemaController;

/**
 * The UI for the Cinema.
 * @author	Qwek Jin Kee
 * @version	1.0
 * @since	2022-11-11
 */
public class CinemaUI {

	/**
	 * The class of the Cinema.
	 * 1 will be Regular.
	 * 2 will be Platinum.
	 */
	private int cineClass = 0;
	
	/**
	 * The Cineplex of this Cinema.
	 */
	private Cineplex cineplex;
	
	/**
	 * Creates a new CinemaController.
	 */
	CinemaController cc = new CinemaController();
	
	/**
	 * Creates a new CinemaUI with the given Cineplex.
	 * @param c	This CinemaUI's Cineplex.
	 */
	public CinemaUI(Cineplex c) {
		this.cineplex = c;
	}
	
	/**
	 * Adds a Cinema to the this Cineplex.
	 */
	public void addCinema() {
		try (Scanner sc = new Scanner(System.in)) {
			do {
				System.out.print("Enter type of cinema (1 for Regular, 2 for Platinum): ");
				cineClass = sc.nextInt();
				if(cineClass != 1 && cineClass != 2) {
					System.out.print("Please select a valid option! (1 or 2)");
				}
				else {
					switch(cineClass) {
					case 1:
						cc.addCinema(cineplex, CinemaClass.REGULAR);
						System.out.println("Cinema added!");
						break;
					case 2:
						cc.addCinema(cineplex, CinemaClass.PLATINUM);
						System.out.println("Cinema added!");
						break;
					}	
				}
			} while(cineClass != 1 && cineClass != 2);
	
		}
	}
}
