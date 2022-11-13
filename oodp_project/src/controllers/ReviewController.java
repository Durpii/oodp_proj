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
/**
 * ReviewController to act as the controller for communication between ReviewUI and Review. 
 * @author	Qwek Jin Kee
 * @version	1.0
 * @since	2022-11-11
 */
public class ReviewController {
	
	/**
	 * The variable that will be used to separate values in the text file.
	 */
	public final String SEPARATOR = "@@@";

	/**
	 * Creates a new ReviewController object.
	 */
	public ReviewController() {
		//empty constructor
	}
	
	/**
	 * Adds a review to the movie.
	 * @param movie The movie that the review will be added to.
	 * @param reviewerName The name of the reviewer.
	 * @param review The content of the review.
	 * @param reviewRating The rating of the review.
	 */
	public void addReview(Movie movie, String reviewerName, String review, int reviewRating) {
		
		Review mreview = new Review(movie, reviewerName, review, reviewRating);
		
		FileWriter fileWriter = null;
		
		try {
			File reviewFile = new File("reviews.txt");
			fileWriter = new FileWriter("reviews.txt", true);
			
			if (reviewFile.createNewFile()) {
				System.out.println("File created: " + reviewFile.getName());
			}
			
			System.out.println("Adding your review...");
			fileWriter.write(mreview.getReviewerName() + "@@@" + movie.getId() + "@@@" + mreview.getReviewRating() 
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
	
	/**
	 * Displays the reviews of the specified movie.
	 * @param movie The movie specified to display the reviews.
	 */
	public void displayReview(Movie movie) {
		
		System.out.println("Review for " + movie.getTitle());
		
		File inputFile = new File("reviews.txt");
		
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			
			String currentLine;
			
			while ((currentLine = reader.readLine()) != null) {
				//trim newline
				String trimmedLine = currentLine.trim();
				
				String[] data = trimmedLine.split(SEPARATOR);
				String dataName = data[0];
				int dataMovieId = Integer.valueOf(data[1]);
				int dataRating = Integer.valueOf(data[2]);
				String dataReview = data[3];
				
				if(dataMovieId != movie.getId()) {
					continue;
				}
				else {
					System.out.println("Reviewer:" + dataName + "\n" + "Rating:" + dataRating + "\n" + "Review:" + dataReview + "\n");
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