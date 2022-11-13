package cinema;

/**
 * Represents a cinema hall.
 * There can be many cinema halls in a Cineplex object.
 * @author	Qwek Jin Kee
 * @version	1.0
 * @since	2022-11-11
 */
public class Cinema {
	
	/**
	 * The Cineplex that the cinema belongs to.
	 */
	private Cineplex cineplex;
	
	/**
	 * The ID of the cinema.
	 */
	private int cinemaID;
	
	/**
	 * The class of the Cinema (Regular/Platinum).
	 */
	private CinemaClass cinemaClass;
	
	/**
	 * Creates a Cinema object.
	 */
	public Cinema() {
		//empty constructor
	}
	
	/**
	 * Creates a Cinema object.
	 * @param cineplex	This Cinema's Cineplex.
	 * @param cinemaID	This Cinema's ID.
	 * @param cinemaClass	The Cinema's Class.
	 */
	public Cinema(Cineplex cineplex, int cinemaID, CinemaClass cinemaClass) {
		super();
		this.cineplex = cineplex;
		this.cinemaID = cinemaID;
		this.cinemaClass = cinemaClass;
	}

	/**
	 * Gets the ID of the Cinema.
	 * @return This Cinema's ID.
	 */
	public int getCinemaID() {
		return cinemaID;
	}

	/**
	 * Sets the ID of the Cinema.
	 * @param cinemaID This Cinema's ID.
	 */
	public void setCinemaID(int cinemaID) {
		this.cinemaID = cinemaID;
	}

	/**
	 * Gets the class of the Cinema.
	 * @return This Cinema's Class.
	 */
	public CinemaClass getCinemaClass() {
		return cinemaClass;
	}

	/**
	 * Set the class of the Cinema.
	 * @param cinemaClass This Cinema's class.
	 */
	public void setCinemaClass(CinemaClass cinemaClass) {
		this.cinemaClass = cinemaClass;
	}

}