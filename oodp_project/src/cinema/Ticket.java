package cinema;

import java.util.Date;

public class Ticket {
	private int userId;
	private int cinemId;
	private int movieId;
	private int seatNum;
	private Date dateTime;
	public Ticket(int userId, int cinemId, int movieId, int seatNum, Date dateTime) {
		this.userId = userId;
		this.cinemId = cinemId;
		this.movieId = movieId;
		this.seatNum = seatNum;
		this.dateTime = dateTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCinemId() {
		return cinemId;
	}
	public void setCinemId(int cinemId) {
		this.cinemId = cinemId;
	}
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
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
}