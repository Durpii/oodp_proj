package cinema;

import java.util.Date;

public class ShowTime {
	private Date date;
	
	public ShowTime() {
		
	}
	
	public ShowTime(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
