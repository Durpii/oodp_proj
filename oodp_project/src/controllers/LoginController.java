package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
			File inputFile = new File("accounts.txt");
			
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			String currentLine;
			String loginName = name + "@";
			String loginPass = "@" + password;
			
			while((currentLine = reader.readLine()) != null) {
				String trimmedLine = currentLine.trim();
				
				if (trimmedLine.contains(loginName) && trimmedLine.contains(loginPass)) {
					System.out.println("Username " + name + " and " + password + " found");
					System.out.println("Logged in as " + name);
					this.isLoggedIn = true;
					reader.close();
					return;
				}
				
				if (!trimmedLine.contains(loginName) && !trimmedLine.contains(loginPass)) {
					continue;
				} 			

			}
			
			System.out.println("Incorrect username or password for user");
			reader.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
