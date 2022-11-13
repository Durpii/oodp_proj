package appMain;

import java.util.ArrayList;
import java.util.Scanner;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;
import controllers.AdminController;
import controllers.BookingController;
import controllers.LoginController;
import ui.AdminUi;
import ui.BookingUI;
import ui.PaymentUI;
import ui.ReviewUI;
import users.MovieGoer;

/**
This class consolidates and displays the different UI pieces of the application.
@author Liu Woon Kit
@version 1.0
@since 2022-11-11
*/

public class AppMain {
	public static void main(String[] args) {
		LoginController lc = new LoginController();
		AdminUi adminUI = new AdminUi();
		BookingUI bookingUI = new BookingUI();
		//ReviewUI reviewUI = new ReviewUI();
		Scanner sc = new Scanner(System.in);
		boolean isAdmin = false;
		int choice = 0;
		while(true) {
			if(isAdmin) {
				System.out.println("________________________________________________________________________________");
				System.out.println("Admin Options");
				System.out.println("\t1. Create/Update/Remove movie listing");
				System.out.println("\t2. Create/Update/Remove cinema showtimes and the movies to be shown");
				//System.out.println("\t3. Configure system settings");
				System.out.println("\t4. Logout");
				System.out.print("Select Option: ");
				while(true) {
					if(sc.hasNextInt()) {
						choice = sc.nextInt();
						if(choice >= 1 && choice <= 4) {
							switch(choice) {
								case 1:
									System.out.println("Create/Update/Remove movie listing");
									System.out.println("\t1) Create Movie");
									System.out.println("\t2) Update Movie");
									System.out.println("\t3) Remove Movie");
									System.out.print("Select Option: ");
									choice = sc.nextInt();
									if(choice == 1) {
										adminUI.uiCreateMovie();
									} else if(choice == 2) {
										adminUI.uiUpdateMovie();
									} else if(choice == 3) {
										adminUI.uiRemoveMovie();
									}
									break;
								case 2:
									System.out.println("Create/Update/Remove cinema showtimes and the movies to be shown");
									System.out.println("\t1) Create Showtimes");
									System.out.println("\t2) Update Showtimes");
									System.out.println("\t3) Remove Showtimes");
									System.out.print("Select Option: ");
									choice = sc.nextInt();
									if(choice == 1) {
										adminUI.uiCreateCinemaShowtime();
									} else if(choice == 2) {
										adminUI.uiUpdateCinemaShowtimes();
									} else if(choice == 3) {
										adminUI.uiRemoveCinemaShowtimes();
									}
									break;
								case 3:
									break;
								case 4:
									isAdmin = false;
									break;
							}
							break;
						}
					}
				}
			} else {
				System.out.println("________________________________________________________________________________");
				System.out.println("User Options");
				System.out.println("\t1. Search/List movie");
				System.out.println("\t2. View booking history");
				System.out.println("\t3. List the Top 5 ranking by ticket sales OR by overall reviewers' ratings");
				System.out.println("\t4. Login (For Admin only)");
				System.out.print("Select an option: ");
				if(sc.hasNextInt()) {
					choice = sc.nextInt();
					if(choice >= 1 && choice <= 7) {
						switch(choice) {
							case 1:
								bookingUI.searchMovie();
								break;
							case 2:
								bookingUI.viewBookingHistory();
								break;
							case 3:
								System.out.println("Select sorting"
										+ "\n\t1) Ticket sales"
										+ "\n\t2) Overall reviewers’ ratings");
								while(true) {
									System.out.print("Select option: ");
									if(sc.hasNextInt()) {
										choice = sc.nextInt();
										if(choice == 1) {
											bookingUI.listTopFive(true);
											break;
										} else if(choice == 2) {
											bookingUI.listTopFive(false);
											break;
										}
									}
								}
								break;
							case 4:
								// clear \n
								sc.nextLine();
								String username, password;
								System.out.print("Enter username: ");
								username = sc.nextLine();
								System.out.print("Enter password: ");
								password = sc.nextLine();
								
								isAdmin = lc.adminLogin(username, password);
								break;
						}
					}
				}
			}
		}
		//App launching point
		//LoginController lc = new LoginController();
		//lc.adminLogin("Bob", "test2");
		//MovieGoer movieGoer = lc.userLogin("User1", "password1");
		
		//AdminController ac = new AdminController();
		//ac.createMovie(1, "Shrek", "Comedy", "Shrek is love, Shrek is life", "Andrew Adamson / Vicky Jensen", new String[]{"Mike Myers", "Eddie Murphy", "Cameron Diaz", "John Lithgow"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		//ac.createMovie(2, "Up", "Comedy", "House on Mountain", "Pete Docter", new String[]{"Edward Asner", "Jordan Nagai", "John Ratzenberger"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		//ac.createMovie(3, "Shrek 2", "Comedy", "Shrek two", "John Smith", new String[]{"Mike Myers", "Eddie Murphyey", "Cameron Diaz"}, 5, ShowStatus.COMING_SOON, AgeRating.PG13);
		//ac.createMovie(4, "Shrek 2", "Duplicate test", "Dupe", "John Smith", new String[]{"Mike Myers", "Eddie Murphyey", "Cameron Diaz"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		//ac.removeMovie(3);
		//ac.createCinemaShowtime(1, 1, "2022-11-12-20-00");
		//ac.createCinemaShowtime(1, 1, "2022-11-12-21-00");
		//ac.createCinemaShowtime(1, 1, "2022-11-12-22-00");

		//AdminUi aUi = new AdminUi();
		//aUi.uiRemoveMovie();
		//aUi.uiUpdateMovie();
		//aUi.uiRemoveCinemaShowtimes();
		//ac.removeCinemaShowtime(1, 1, "2022-12-12-20-00");
		
		
		//BookingController bc = new BookingController();
		
		
		
		// maybe put into IOController or something
		
		
		/*Scanner sc = new Scanner(System.in);
		
		ArrayList<Movie> result = new ArrayList<Movie>();
		while(result.isEmpty()) {
			System.out.print("Enter Search Query: ");
			String searchQuery = sc.next().trim();
			result.addAll(bc.search(searchQuery));
		}
		
		int i = 1;
		for(Movie m : result) {
			System.out.println(i++ + ". " + m.getTitle());
		}
		
		// TODO
		String choice = null;
		while(choice != "q") {
			System.out.print("Select a movie or enter q to return: ");
			choice = sc.next().trim();
			if(choice.matches("\\d+") && Integer.valueOf(choice) <= result.size()) {
				bc.viewDetails(result.get(Integer.valueOf(choice)-1));
			}
		}
		
		//ac.removeMovie(1);
		 */
		
		//BookingUI bUI = new BookingUI();
		//bUI.viewBookingHistory();
	}

}
