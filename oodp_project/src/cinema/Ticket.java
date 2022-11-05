package cinema;

import java.util.Date;

public class Ticket {
	private int movieId;
	private String name;
	private String email;
	private int phoneNum;
	private int seatNum;
	private Date dateTime;
	private boolean isHoliday;
	public Ticket(int movieId, String name, String email, int phoneNum, int seatNum, Date dateTime, boolean isHoliday) {
		this.movieId = movieId;
		this.name = name;
		this.email = email;
		this.phoneNum = phoneNum;
		this.seatNum = seatNum;
		this.dateTime = dateTime;
		this.isHoliday = isHoliday;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(int phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public boolean isHoliday() {
		return isHoliday;
	}
	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}
}