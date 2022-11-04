package cinema;

public class Movie {
	
	enum showStatus{ COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING; }
	enum ageRating{ G, PG, PG13, NC16, M18, R21; }
	
	private int id;
	private String title, typeOfMovie, sypnosis, director;
	private float overallRating = 0;
	private showStatus showStatus;
	private ageRating ageRating;
	
	public Movie() {
		//empty constructor
	}

	public int getId() {
		return id;
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

	public String getSypnosis() {
		return sypnosis;
	}

	public void setSypnosis(String sypnosis) {
		this.sypnosis = sypnosis;
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

	public showStatus getShowStatus() {
		return showStatus;
	}
	
	public void setShowStatus(showStatus showStatus) {
		this.showStatus = showStatus;
	}
	
	public ageRating getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(ageRating ageRating) {
		this.ageRating = ageRating;
	}
	
}
