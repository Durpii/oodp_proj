package ui;

import java.util.Scanner;

import cinema.Movie;
import controllers.ReviewController;

/**
 * The UI for the Review.
 * @author	Qwek Jin Kee
 * @version 1.0
 * @since	2022-11-11
 */
public class ReviewUI {
	
	/**
	 * The name of this review's reviewer.
	 * The content of this review.
	 */
	private String reviewerName, review;
	
	/**
	 * The rating of this review.
	 * The rating should be a number between 1 to 5.
	 */
	private int reviewRating;
	
	/**
	 * The movie this review belongs to.
	 */
	private Movie m;
	
	/**
	 * ReviewController for this review.
	 */
	ReviewController rc = new ReviewController();
	
	/**
	 * Creates a review with the given movie.
	 * @param m	The movie that this review belongs to.
	 */
	public ReviewUI(Movie m) {
		this.m = m;
	}
	
	/**
	 * Adds a review to this movie.
	 */
	public void addReview() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("Please enter your name: ");
			reviewerName = sc.nextLine();
			System.out.print("Please enter your review: ");
			review = sc.nextLine();
			System.out.print("Please enter your rating (1 ~ 5): ");
			reviewRating = sc.nextInt();	
		}
		
		rc.addReview(m, reviewerName, review, reviewRating);	
	}
	
	/**
	 * Displays the reviews of this Movie.
	 */
	public void displayReview() {
		rc.displayReview(m);
	}
}
