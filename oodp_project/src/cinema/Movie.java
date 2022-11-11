package cinema;

import java.util.ArrayList;

public class Movie {
	
	private int id, count;
	private String title, typeOfMovie, synopsis, director;
	private String[] casts;
	private float overallRating = 0;
	private ShowStatus showStatus;
	private AgeRating ageRating;
	
	ArrayList<Review> reviews = new ArrayList<Review>();
	
	public Movie() {
		//empty constructor
	}

	public int getId() {
		return id;
	}

	public Movie(int id, String title, String typeOfMovie, String synopsis, String director, String[] casts,
			ShowStatus showStatus, AgeRating ageRating) {
		super();
		this.id = id;
		this.title = title;
		this.typeOfMovie = typeOfMovie;
		this.synopsis = synopsis;
		this.director = director;
		this.casts = casts;
		this.showStatus = showStatus;
		this.ageRating = ageRating;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTypeOfMovie() {
		return typeOfMovie;
	}

	public void setTypeOfMovie(String typeOfMovie) {
		this.typeOfMovie = typeOfMovie;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public float getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(float overallRating) {
		this.overallRating = overallRating;
	}

	public ShowStatus getShowStatus() {
		return showStatus;
	}
	
	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}
	
	public AgeRating getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(AgeRating ageRating) {
		this.ageRating = ageRating;
	}

	public String[] getCasts() {
		return casts;
	}

	public void setCasts(String[] casts) {
		this.casts = casts;
	}
	
	//run this function to update the overall rating whenever new review is added
	public void updateOverallRating() {
		if(reviews.isEmpty()) {
			//do nothing because overall rating is already 0
		}
		else {
			overallRating = 0;
			for(count = 0; count<reviews.size(); count++) {
				overallRating += reviews.get(count).getReviewRating();
			}
			overallRating /= count;
		}
	}
	
	//function to add new review to the review arraylist
	public void addNewReview(Review review) {
		reviews.add(review);
	}
}
