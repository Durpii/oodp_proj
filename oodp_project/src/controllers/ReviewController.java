package controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import cinema.Movie;
import cinema.Review;
import users.User;

public class ReviewController {
	
	private Movie movie;
	private String reviewerName, review;
	private int reviewRating, uid;
	public final String SEPARATOR = "@@@";

	public ReviewController(Movie movie, User user){
		this.movie = movie;
		this.uid = user.getUserId();
	}
	
	public void addReview() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Please enter your name: ");
			reviewerName = sc.next();
			System.out.print("Please enter your review: ");
			review = sc.next();
			System.out.print("Please enter your rating (1 ~ 5): ");
			reviewRating = sc.nextInt();
		}
		
		Review mreview = new Review(movie, reviewerName, review, reviewRating);
		
		FileWriter fileWriter = null;
		
		try {
			File reviewFile = new File("reviews.txt");
			fileWriter = new FileWriter("reviews.txt", true);
			
			if (reviewFile.createNewFile()) {
				System.out.println("File created: " + reviewFile.getName());
			}
			
			System.out.println("Adding your review...");
			fileWriter.write("$ID:" + uid + "@@@" + movie.getId() + "@@@" + mreview.getReviewRating() 
			+ "@@@" + mreview.getReview() + "\n");
			
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Error adding review, please try again");
			e.printStackTrace();
		}
		finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				
	}
	
	public void displayReview() {
		File inputFile = new File("reviews.txt");
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			
			String currentLine;
			
			while ((currentLine = reader.readLine()) != null) {
				//trim newline
				String trimmedLine = currentLine.trim();
				
				String[] data = trimmedLine.split(SEPARATOR);
				int dataUid = Integer.valueOf(data[0].split(":")[1]);
				int dataMovieId = Integer.valueOf(data[1]);
				int dataRating = Integer.valueOf(data[2]);
				String dataReview = data[3];
				
				if(dataMovieId != movie.getId()) {
					continue;
				}
				else {
					System.out.println("User ID:" + dataUid + "\n" + "Rating:" + dataRating + "\n" + "Review:" + dataReview);
				}
				
			}
			
			reader.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();	
		}

	}

}
