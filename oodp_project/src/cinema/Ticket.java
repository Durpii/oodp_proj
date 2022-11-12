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
					holiday.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(sc.nextLine()));
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