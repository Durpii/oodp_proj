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
import users.MovieGoer;

public class AppMain {

	public static void main(String[] args) {
		//App launching point
		LoginController lc = new LoginController();
		//lc.adminLogin("Bob", "test2");
		MovieGoer movieGoer = lc.userLogin("User1", "password1");
		
		AdminController ac = new AdminController();
		//ac.createMovie(1, "Shrek", "Comedy", "Shrek is love, Shrek is life", "Andrew Adamson / Vicky Jensen", new String[]{"Mike Myers", "Eddie Murphy", "Cameron Diaz", "John Lithgow"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		//ac.createMovie(2, "Up", "Comedy", "House on Mountain", "Pete Docter", new String[]{"Edward Asner", "Jordan Nagai", "John Ratzenberger"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		//ac.createMovie(3, "Shrek 2", "Comedy", "Shrek two", "John Smith", new String[]{"Mike Myers", "Eddie Murphyey", "Cameron Diaz"}, 5, ShowStatus.COMING_SOON, AgeRating.PG13);
		//ac.createMovie(4, "Shrek 2", "Duplicate test", "Dupe", "John Smith", new String[]{"Mike Myers", "Eddie Murphyey", "Cameron Diaz"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		//ac.removeMovie(3);
		//ac.createCinemaShowtime(1, 1, "2022-11-12-20-00");
		//ac.createCinemaShowtime(1, 1, "2022-11-12-21-00");
		//ac.createCinemaShowtime(1, 1, "2022-11-12-22-00");
		
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
		
		//AdminUi aUi = new AdminUi();
		
		//aUi.uiRemoveMovie();
		//aUi.uiUpdateMovie();
		BookingUI bUI = new BookingUI();
		bUI.viewBookingHistory();
	}

}
