package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginController {
	
	private boolean isLoggedIn = false;
	
	public LoginController() {
		
	}
	
	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}
	
	public void login(String name, String password) {
		//check a text file if name exists, if exists, continue to check password
		try {
			File nameFile = new File("usernames.txt");
			File passwordFile = new File("passwords.txt");
			
			Scanner nameSc = new Scanner(nameFile);
			Scanner passwordSc = new Scanner(passwordFile);
			int lineNo = 0;
			
			while(nameSc.hasNextLine()) {
				String uname = nameSc.nextLine();
				lineNo++;
				if (!nameSc.hasNextLine()) {
					System.out.println("Username not found, please enter username again!");
					break;
				}
				
				if (name.equals(uname)) {
					System.out.println("Username " + name + " found on line " + lineNo);
					break;
				}
			}
			
			//check if password exists, if it does, login, else throw out error
			int passwordLineNo = 0;
			while(nameSc.hasNextLine()) {
				String pword = passwordSc.nextLine();
				passwordLineNo++;
				if (!passwordSc.hasNextLine()) {
					System.out.println("Password not found, please enter password again!");
					break;
				}
				
				if (password.equals(pword) && lineNo != passwordLineNo) {
					System.out.println("Password " + password + " found on line " + passwordLineNo);
					System.out.println("Password does not correspond with account");
					System.out.println("Incorrect password, please try again");
					break;
				}
				
				if (password.equals(pword) && lineNo == passwordLineNo) {
					System.out.println("Password " + password + " found on line " + passwordLineNo);
					System.out.println("Username on line " + lineNo + " matches password on "
							+ "line " + passwordLineNo);
					System.out.println("Logging in...");
					this.isLoggedIn = true;
					break;
				}
			}
			nameSc.close();
			passwordSc.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found!");
			e.printStackTrace();
		}
		
		
	}
}
