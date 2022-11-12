package cinema;

import java.util.Date;

public class ShowTime {
	private Date date;
	
	public ShowTime() {
		
	}
	
	public ShowTime(Date date) {
		this.date = date;
	}
	
	public void updateShowTime(Date date) {
		this.date = date;
	}
	
	public void updateShowDate(Date newDate) {
		this.date = newDate;
	}
}
