package cinema;

public class Review {

	private String reviewerName, review;
	private int reviewRating = 0;
	private Movie movie;
	
	public Review() {
		//empty constructor
	}
	
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

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(int reviewRating) {
		this.reviewRating = reviewRating;
	}
	
}
