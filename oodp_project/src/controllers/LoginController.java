package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import users.MovieGoer;
import users.User;

/**
 * This class represents the controller that is used to login a user into the system
 * @author Lim Zi Hao
 * @version 1.0
 * @since 2022-11-11
 *
 */
public class LoginController {
	
	/**
	 * The delimiter used to split strings
	 */
	public final String SEPARATOR = "@@@";
	/**
	 * Checks if the user is logged in or not
	 */
	private boolean isLoggedIn = false;
	
	/**
	 * Default constructor to initalise object
	 */
	public LoginController() {
		
	}
	
	/**
	 * Checks if a user is logged in
	 * @return logged in status of user
	 */
	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}
	
	/**
	 * Function used to login admins into the system
	 * @param name Name of admin
	 * @param password Password of admin
	 * @return true if admin successfully logs in successfully, false otherwise
	 */
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
	
	/**
	 * Function used to login users into the system
	 * @param name Name of movieGoer
	 * @param password Password of movieGoer
	 * @return movieGoer object if logged in successfully, null otherwise
	 */
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