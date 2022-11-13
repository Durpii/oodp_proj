package cinema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
Ticket Class, for storing various information about the booking.
@author Liu Woon Kit
@version 1.0
@since 2022-11-11
*/

public class Ticket {
	private String tId;
	private int cinemaId;
	private int movieId;
	private String name;
	private int phoneNum;
	private String email;
	private double price;
	private String seatNum;
	private Date dateTime;
	
	/** 
	* Class constructor.
	*/
	public Ticket() {
		
	}
	
	/**
	* Class constructor specifying the transaction(ticket) ID, cinema ID, movie ID, user's name, phone number, email address and the seat number.
	*/
	public Ticket(String tId, int cinemaId, int movieId, String name, int phoneNum, String email, double price, String seatNum,
			Date dateTime) {
		this.tId = tId;
		this.cinemaId = cinemaId;
		this.movieId = movieId;
		this.name = name;
		this.phoneNum = phoneNum;
		this.email = email;
		this.price = price;
		this.seatNum = seatNum;
		this.dateTime = dateTime;
	}
	
	/**
	* Class constructor specifying the cinema ID, movie ID, user's name, phone number, email address and the seat number.
	*/
	public Ticket(int cinemaId, int movieId, String name, int phoneNum, String email, double price, String seatNum,
			Date dateTime) {
		this.cinemaId = cinemaId;
		this.movieId = movieId;
		this.phoneNum = phoneNum;
		this.name = name;
		this.email = email;
		this.price = price;
		this.seatNum = seatNum;
		this.dateTime = dateTime;
	}
	
	/**
	 * Gets the transaction ID of the ticket.
	 * @return this Ticket's transaction ID.
	 */
	public String gettId() {
		return tId;
	}

	/**
	* Sets the transaction ID.
	* @param tId the transaction ID of the ticket
	*/
	public void settId(String tId) {
		this.tId = tId;
	}

	/**
	 * Gets the cinema ID.
	 * @return this Ticket's cinema ID.
	 */
	public int getCinemaId() {
		return cinemaId;
	}

	/**
	* Sets the cinema ID.
	* @param cinemaId the cinema ID
	*/
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	/**
	 * Gets the Movie ID of this Ticket.
	 * @return this Ticket's Movie ID.
	 */
	public int getMovieId() {
		return movieId;
	}

	/**
	* Sets the Movie ID.
	*
	* @param movieId the ID of Movie
	*/
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	/**
	 * Gets phone number of this Ticket.
	 * @return this Ticket's phone number.
	 */
	public int getPhoneNum() {
		return phoneNum;
	}

	/**
	* Sets phone number of this Ticket.
	* @param phoneNum the phone number of this Ticket
	*/
	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	/**
	 * Gets the email address of the Ticket.
	 * @return this Ticket's email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	* Sets the email address of the Ticket.
	* @param email the email address of the Ticket
	*/
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the Ticket price.
	 * @return this price price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the Ticket price.
	 * @param price the price of the Ticket.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the seat number of the Ticket.
	 * @return this Ticket's seat number.
	 */
	public String getSeatNum() {
		return seatNum;
	}

	/**
	 * Sets the seat number of this Ticket.
	 * @param seatNum the Ticket's seat number.
	 */
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	/**
	 * Gets the time slot of this Ticket.
	 * @return this Ticket's time slot.
	 */
	public Date getDateTime() {
		return dateTime;
	}

	/**
	 * Sets the time slot of this Ticket.
	 * @param dateTime the Ticket's time slot.
	 */
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * Gets the name of the person whom this ticket belongs to.
	 * @return this Tickets's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the ticket owner.
	 * @param the name of the person this ticket belongs to
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Checks if the ticket's date falls on a holiday
	 * @param date The date of booking
	 * @return true if it's a holiday, else false.
	 */
	public boolean isHoliday(Date dateTime) {
		String SEPARATOR = "@@@";
		Scanner sc = null;
		File file = new File("holidays.txt");
		if(file.exists()) {
			try {
				sc = new Scanner(new FileInputStream(file));
				sc.useDelimiter(SEPARATOR);
				Calendar target = Calendar.getInstance();
				target.setTime(dateTime);
				while (sc.hasNextLine()){
					Calendar holiday = Calendar.getInstance();
					holiday.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine()));
					if(target.get(Calendar.YEAR) == holiday.get(Calendar.YEAR) && target.get(Calendar.DAY_OF_YEAR) == holiday.get(Calendar.DAY_OF_YEAR)) {
						return true;
					}
				}
			} catch (FileNotFoundException | ParseException e) {
				e.printStackTrace();
			} finally {
				sc.close();
			}
		}
		return false;
	}
	
	/**
	 * Checks if the ticket's date falls on a weekend
	 * @param date The date of booking
	 * @return true if it's a weekend, else false.
	 */
	public boolean isWeekend(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(calendar.DAY_OF_WEEK>6) {
			return true;
		}
		return false;
	}
}