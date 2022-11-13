package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import users.MovieGoer;
import users.User;

public class LoginController {
	
	public final String SEPARATOR = "@@@";
	private boolean isLoggedIn = false;
	
	public LoginController() {
		
	}
	
	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}
	
	public boolean adminLogin(String name, String password) {
		//check a text file if name exists, if exists, continue to check password
		Scanner sc = null;
		try {
			File inputFile = new File("adminAccounts.txt");
			
			sc = new Scanner(new FileReader(inputFile));
			
			while(sc.hasNextLine()) {	
				String[] data = sc.nextLine().split(SEPARATOR);
				String dataLoginName = data[0];
				String dataLoginPass = data[1];
				
				if (dataLoginName.equals(name) && dataLoginPass.equals(password)) {
					System.out.println("Logged in as " + name);
					this.isLoggedIn = true;					
					return true;
				}
			}
			
			System.out.println("Incorrect username or password for user (Case Sensitive)");
			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found!");
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return false;
	}
	
	public MovieGoer userLogin(String name, String password) {
		//check a text file if name exists, if exists, continue to check password
		Scanner sc = null;
		try {
			File inputFile = new File("userAccounts.txt");
			
			sc = new Scanner(new FileReader(inputFile));
			
			while(sc.hasNextLine()) {	
				String[] data = sc.nextLine().split(SEPARATOR);
				int dataId = Integer.valueOf(data[0]);
				int dataAge = Integer.valueOf(data[1]);
				String dataName = data[2];
				int dataPhoneNum = Integer.valueOf(data[3]);
				String dataEmail =  data[4];
				String dataPassword = data[5];
				
				if (dataName.equals(name) && dataPassword.equals(password)) {
					System.out.println("Logged in as " + name);
					this.isLoggedIn = true;
					MovieGoer MovieGoer = new MovieGoer(dataId, dataAge, dataName, dataPhoneNum, dataEmail);
					return MovieGoer;
				}
			}
			
			System.out.println("Incorrect username or password for user (Case Sensitive)");
			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found!");
			e.printStackTrace();
		} finally {
			sc.close();
		}
		return null;
			
	}
}