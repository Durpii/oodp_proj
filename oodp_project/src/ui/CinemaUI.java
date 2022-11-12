package ui;

import java.util.Scanner;

import cinema.CinemaClass;
import cinema.Cineplex;
import controllers.CinemaController;

public class CinemaUI {

	private int cineClass = 0;
	private Cineplex cineplex;
	CinemaController cc = new CinemaController();
	
	public CinemaUI(Cineplex c) {
		this.cineplex = c;
	}
	
	public void addCinema() {
		try (Scanner sc = new Scanner(System.in)) {
			do {
				System.out.print("Enter type of cinema (1 for Regular, 2 for Platinum): ");
				cineClass = sc.nextInt();
				if(cineClass != 1 || cineClass != 2) {
					System.out.print("Please select a valid option! (1 or 2)");
				}
				else {
					switch(cineClass) {
					case 1:
						cc.addCinema(cineplex, CinemaClass.REGULAR);
						break;
					case 2:
						cc.addCinema(cineplex, CinemaClass.PLATINUM);
						break;
					}	
				}
			} while(cineClass != 1 || cineClass != 2);
	
		}
	}
}
