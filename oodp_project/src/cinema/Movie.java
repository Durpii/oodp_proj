package cinema;

import java.util.ArrayList;

/**
 * Represents a Movie.
 * There can be many movies in a Cinema.
 * @author	Qwek Jin Kee
 * @version	1.0
 * @since	2022-11-11
 */
public class Movie {
	
	/**
	 * ID of the Movie.
	 */
	private int id;
	
	/**
	 * The title of the Movie.
	 * The type of the Movie.
	 * The synopsis of the Movie.
	 * The director of the Movie.
	 */
	private String title, typeOfMovie, synopsis, director;
	
	/**
	 * The casts of the Movie.
	 */
	private String[] casts;
	
	/**
	 * The overall rating of the Movie.
	 */
	private float overallRating = -1;
	
	/**
	 * The show status of the Movie.
	 */
	private ShowStatus showStatus;
	
	/**
	 *	The age rating of the movie. 
	 */
	private AgeRating ageRating;
	
	/**
	 * The Movie type of the Movie.
	 * The Movie can either be a Blockbuster Movie or a 3-D Movie.
	 */
	private MovieType movieType;
	
	/**
	 * The list of reviews for this movie.
	 */
	ArrayList<Review> reviews = new ArrayList<Review>();
	
	/**
	 * Creates a new Movie.
	 */
	public Movie() {
		//empty constructor
	}

	/**
	 * Gets the ID of the Movie.
	 * @return The movie's ID.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Creates a new Movie with the following parameters.
	 * @param id	The ID of the Movie.
	 * @param title	The title of the Movie.
	 * @param typeOfMovie	The genre of the Movie.
	 * @param synopsis	The synopsis of the Movie.
	 * @param director	The director of the Movie.
	 * @param casts		The casts of the Movie.
	 * @param showStatus	The show status of the Movie.
	 * @param ageRating		The age rating of the Movie.
	 */
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

	/**
	 * Gets the Type of the Movie (Regular/Platinum)
	 * @return This movie's type.
	 */
	public MovieType getMovieType() {
		return movieType;
	}

	/**
	 * Change the type of the Movie (Regular/Platinum)
	 * @param movieType	This movie's type.
	 */
	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	/**
	 * Changes the ID of this Movie.
	 * @param id	This Movie's ID.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get the title of this Movie.
	 * @return	This movie's title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Changes the title of this Movie.
	 * @param title	This Movie's title.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the genre of this Movie.
	 * @return	This movie's genre.
	 */
	public String getTypeOfMovie() {
		return typeOfMovie;
	}

	/**
	 * Changes the genre of this Movie.
	 * @param typeOfMovie	This movie's genre.
	 */
	public void setTypeOfMovie(String typeOfMovie) {
		this.typeOfMovie = typeOfMovie;
	}

	/**
	 * Gets the synopsis of this Movie.
	 * @return	This Movie's synopsis.
	 */
	public String getSynopsis() {
		return synopsis;
	}

	/**
	 * Changes the synopsis of this Movie.
	 * @param synopsis	This Movie's synopsis.
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * Gets the director of this Movie.
	 * @return	This Movie's director.
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Changes the director of this Movie.
	 * @param director	This Movie's director.
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Gets the overall rating of this Movie.
	 * @return	This Movie's overall rating.
	 */
	public float getOverallRating() {
		return overallRating;
	}

	/**
	 * Changes the overall rating of this Movie.
	 * @param overallRating	This Movie's overall rating.
	 */
	public void setOverallRating(float overallRating) {
		this.overallRating = overallRating;
	}

	/**
	 * Gets the show status of this Movie.
	 * @return	This Movie's Show Status.
	 */
	public ShowStatus getShowStatus() {
		return showStatus;
	}
	
	/**
	 * Changes the SHow Status of this Movie.
	 * @param showStatus	This Movie's Show Status.
	 */
	public void setShowStatus(ShowStatus showStatus) {
		this.showStatus = showStatus;
	}
	
	/**
	 * Gets the Age Rating of this Movie.
	 * @return	This Movie's Age Rating.
	 */
	public AgeRating getAgeRating() {
		return ageRating;
	}

	/**
	 * Changes the Age Rating of this Movie.
	 * @param ageRating	This Movie's Age Rating.
	 */
	public void setAgeRating(AgeRating ageRating) {
		this.ageRating = ageRating;
	}

	/**
	 * Gets the casts of this Movie.
	 * @return	This Movie's casts.
	 */
	public String[] getCasts() {
		return casts;
	}

	/**
	 * Changes the casts of this Movie.
	 * @param casts	This Movie's casts.
	 */
	public void setCasts(String[] casts) {
		this.casts = casts;
	}
	
	/**
	 * Updates the overall rating of the movie.
	 * Does nothing when there is less than 2 reviews.
	 */
	public void updateOverallRating() {
		if(reviews.isEmpty()) {
			//do nothing
		}
		else {
			int count;
			overallRating = 0;
			for(count = 0; count<reviews.size(); count++) {
				overallRating += reviews.get(count).getReviewRating();
			}
			overallRating /= count;
		}
	}
	
	/**
	 * Adds a new review to this Movie and update the overall rating of this Movie.
	 * @param review	The review of this Movie.
	 */
	public void addNewReview(Review review) {
		reviews.add(review);
		
		if(reviews.size() <= 1) {
			overallRating = -1;
		}
		else {
			updateOverallRating();
		}
	}
}
