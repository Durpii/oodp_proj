package controllers;

import java.util.Scanner;

import cinema.Movie;
import cinema.Review;

public class ReviewController {
	
	private Movie movie;
	private String reviewerName, review;
	private int reviewRating;

	public ReviewController(Movie movie){
		this.movie = movie;
	}
	
	public void addReview() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please enter your name: ");
		reviewerName = sc.next();
		System.out.print("Please enter your review: ");
		review = sc.next();
		System.out.print("Please enter your rating (1 ~ 5): ");
		reviewRating = sc.nextInt();
		
		Review mreview = new Review(movie, reviewerName, review, reviewRating);
	}

}
