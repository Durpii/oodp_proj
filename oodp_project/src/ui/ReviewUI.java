package ui;

import java.util.Scanner;

import cinema.Movie;
import controllers.ReviewController;

public class ReviewUI {
	
	private String reviewerName, review;
	private int reviewRating;
	private Movie m;
	
	ReviewController rc = new ReviewController();
	
	public ReviewUI(Movie m) {
		this.m = m;
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
		
		rc.addReview(m, reviewerName, review, reviewRating);	
	}
	
	public void displayReview() {
		rc.displayReview(m);
	}
}
