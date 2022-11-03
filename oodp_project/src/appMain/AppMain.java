package appMain;

import controllers.LoginController;

public class AppMain {

	public static void main(String[] args) {
		//App launching point
		LoginController lc = new LoginController();
		lc.login("Jenna", "admin2");
	}

}
