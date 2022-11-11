package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import users.User;

public class LoginController {
	
	public final String SEPARATOR = "@@@";
	private boolean isLoggedIn = false;
	private User userInfo = null;
	
	public LoginController() {
		
	}
	
	public boolean isLoggedIn() {
		return this.isLoggedIn;
	}
	
	public void adminLogin(String name, String password) {
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
					return;
				}
			}
			
			System.out.println("Incorrect username or password for user (Case Sensitive)");
			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found!");
			e.printStackTrace();
		} finally {
			sc.close();
		}
		
		
	}
	
	public void userLogin(String name, String password) {
		//check a text file if name exists, if exists, continue to check password
		Scanner sc = null;
		try {
			File inputFile = new File("userAccounts.txt");
			
			sc = new Scanner(new FileReader(inputFile));
			
			while(sc.hasNextLine()) {	
				String[] data = sc.nextLine().split(SEPARATOR);
				String dataLoginName = data[0];
				String dataLoginPass = data[1];
				
				if (dataLoginName.equals(name) && dataLoginPass.equals(password)) {
					System.out.println("Logged in as " + name);
					this.isLoggedIn = true;
					return;
				}
			}
			
			System.out.println("Incorrect username or password for user (Case Sensitive)");
			
		} catch (FileNotFoundException e) {
			System.out.println("File is not found!");
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
	
	public void logout() {
		isLoggedIn = false;
		userInfo = null;
	}
}
