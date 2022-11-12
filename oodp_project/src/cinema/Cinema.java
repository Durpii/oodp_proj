package cinema;

public class Cinema {

	private Cineplex cineplex;
	private String cinemaName;
	private int cinemaID;
	private int noOfRows;
	private int totalSeats;
	private String cinemaClass;
	private boolean isIMax;
	
	public Cinema() {
		//empty constructor
	}
	
	/*initiate cinema with which cineplex it's from, the id of the cinema, the class of the cinema
	if it is imax*/
	public Cinema(Cineplex cineplex, int cinemeName, int cinemaID, String cinemaClass, boolean isIMax) {
		this.cineplex = cineplex;
		this.cinemaID = cinemaID;
		this.cinemaClass = cinemaClass;
		this.isIMax = isIMax;
	}

	public String getCinemaName() {
		return cinemaName;
	}

	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	public int getCinemaID() {
		return cinemaID;
	}

	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}

	public int getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(int noOfRows) {
		this.noOfRows = noOfRows;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getCinemaClass() {
		return cinemaClass;
	}

	public void setCinemaClass(String cinemaClass) {
		this.cinemaClass = cinemaClass;
	}

	public boolean isIMax() {
		return isIMax;
	}

	public void setIMax(boolean isIMax) {
		this.isIMax = isIMax;
	}

}
