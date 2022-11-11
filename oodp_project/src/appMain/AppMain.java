package appMain;

import java.util.ArrayList;
import java.util.Scanner;

import cinema.AgeRating;
import cinema.Movie;
import cinema.ShowStatus;
import controllers.AdminController;
import controllers.BookingController;
import controllers.LoginController;
import ui.BookingUI;
import users.MovieGoer;

public class AppMain {

	public static void main(String[] args) {
		//App launching point
		LoginController lc = new LoginController();
		//lc.adminLogin("Bob", "test2");
		
		AdminController ac = new AdminController();
		ac.createMovie(1, "Shrek", "Comedy", "Shrek is love, Shrek is life", "Andrew Adamson / Vicky Jensen", new String[]{"Mike Myers", "Eddie Murphy", "Cameron Diaz", "John Lithgow"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		ac.createMovie(2, "Up", "Comedy", "House on Mountain", "Pete Docter", new String[]{"Edward Asner", "Jordan Nagai", "John Ratzenberger"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		ac.createMovie(3, "Shrek 2", "Comedy", "Shrek two", "John Smith", new String[]{"Mike Myers", "Eddie Murphyey", "Cameron Diaz"}, 5, ShowStatus.COMING_SOON, AgeRating.PG13);
		ac.createMovie(4, "Shrek 2", "Duplicate test", "Dupe", "John Smith", new String[]{"Mike Myers", "Eddie Murphyey", "Cameron Diaz"}, 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		//ac.removeMovie(3);
		
		MovieGoer movieGoer = lc.userLogin("User1", "password1");
		BookingUI bUI = new BookingUI(movieGoer);
		bUI.viewBookingHistory();
		
		//ac.removeMovie(1);
	}

}
