package cinema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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
	public Ticket() {
		
	}
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
	
	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public int getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSeatNum() {
		return seatNum;
	}

	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
	
	public boolean isWeekend(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if(calendar.DAY_OF_WEEK>6) {
			return true;
		}
		return false;
	}
}