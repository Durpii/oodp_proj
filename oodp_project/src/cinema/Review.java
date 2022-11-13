package cinema;

/**
 * Represents a review.
 * A movie can consist of multiple reviews.
 * @author	Qwek Jin Kee
 * @version	1.0
 * @since	2022-11-11
 */
public class Review {

	/**
	 * The name of the reviewer.
	 * The content of this review.
	 */
	private String reviewerName, review;
	
	/**
	 * The rating of this review.
	 * The rating should be a number from 1 to 5.
	 */
	private int reviewRating = 0;
	
	/**
	 * The movie that this review belongs to.
	 */
	private Movie movie;
	
	/**
	 * Creates a review.
	 */
	public Review() {
		//empty constructor
	}
	
	/**
	 * Creates a review with the following parameters.
	 * @param movie	Movie that this review belongs to.
	 * @param reviewerName	The name of the reviewer for this review.
	 * @param review	The content of the review.
	 * @param reviewRating	The rating of this review.
	 */
	public Review(Movie movie, String reviewerName, String review, int reviewRating) {
		if(reviewRating > 5) {
			this.reviewRating = 5;
		}
		else if(reviewRating < 1) {
			this.reviewRating = 1;
		}
		else {
			this.reviewRating = reviewRating;
		}
		
		this.movie = movie;
		this.reviewerName = reviewerName;
		this.review = review;
		
		movie.addNewReview(this);
	}

	/**
	 * Gets the name of the reviewer for this review.
	 * @return	The name of the reviewer for this review.
	 */
	public String getReviewerName() {
		return reviewerName;
	}

	/**
	 * Changes the name of the reviewer for this review.
	 * @param reviewerName	The name of the reviewer for this review.
	 */
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	/**
	 * Gets the content of this review.
	 * @return	This review's content.
	 */
	public String getReview() {
		return review;
	}

	/**
	 * Changes the content of this review.
	 * @param review	This review's content.
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * Gets the rating of this review.
	 * @return	This review's rating.
	 * 			The rating should be a number between 1 to 5.
	 */
	public int getReviewRating() {
		return reviewRating;
	}

	/**
	 * Changes the rating of this review.
	 * @param reviewRating	This review's rating.
	 * 						The rating should be a number between 1 to 5.
	 */
	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}
	
}
