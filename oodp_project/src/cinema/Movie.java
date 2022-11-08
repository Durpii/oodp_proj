package cinema;

public class Movie {
	
	private int id;
	private String title, typeOfMovie, sypnosis, director;
	private float overallRating = 0;
	private ShowStatus showStatus;
	private AgeRating ageRating;
	private String[] casts;
	
	public Movie() {
		//empty constructor
	}

	public int getId() {
		return id;
	}

	public Movie(int id, String title, String typeOfMovie, String sypnosis, String director, float overallRating,
			ShowStatus showStatus, AgeRating ageRating) {
		super();
		this.id = id;
		this.title = title;
		this.typeOfMovie = typeOfMovie;
		this.sypnosis = sypnosis;
		this.director = director;
		this.overallRating = overallRating;
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
	
}
