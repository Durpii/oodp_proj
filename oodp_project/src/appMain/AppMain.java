package appMain;

import cinema.AgeRating;
import cinema.ShowStatus;
import controllers.AdminController;
import controllers.LoginController;

public class AppMain {

	public static void main(String[] args) {
		//App launching point
		//LoginController lc = new LoginController();
		//lc.login("Jenna", "admin2");
		
		AdminController ac = new AdminController();
		ac.createMovie(1, "Shrek", "Comedy", "Shrek is love, Shrek is life", "Andrew Adamson / Vicky Jensen", 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		ac.createMovie(2, "Up", "Comedy", "House on Mountain", "Pete Docter", 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		ac.createMovie(3, "Shrek 2", "Comedy", "Shrek two", "John Smith", 5, ShowStatus.COMING_SOON, AgeRating.PG13);
		ac.createMovie(4, "Shrek 2", "Duplicate test", "Dupe", "John Smith", 5, ShowStatus.END_OF_SHOWING, AgeRating.PG13);
		//ac.removeMovie(1);
	}

}
