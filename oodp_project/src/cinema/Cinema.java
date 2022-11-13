package cinema;

public class Cinema {

	private Cineplex cineplex;
	private int cinemaID;
	private int noOfRows;
	private int totalSeats;
	private CinemaClass cinemaClass;
	
	public Cinema() {
		//empty constructor
	}
	
	/*initiate cinema with which cineplex it's from, the id of the cinema, the class of the cinema
	if it is imax*/
	public Cinema(Cineplex cineplex, int cinemaID, CinemaClass cinemaClass) {
		super();
		this.cineplex = cineplex;
		this.cinemaID = cinemaID;
		this.cinemaClass = cinemaClass;
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

	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}

	public void setCinemaClass(CinemaClass cinemaClass) {
		this.cinemaClass = cinemaClass;
	}

}