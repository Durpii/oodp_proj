package cinema;

public class ShowTime {
	private String date;
	private String time;
	
	public ShowTime() {
		
	}
	
	public ShowTime(String date, String time) {
		this.date = date;
		this.time = time;
	}
	
	public void updateShowTime(String newTime) {
		this.time = newTime;
	}
	
	public void updateShowDate(String newDate) {
		this.date = newDate;
	}
}
