package moviebooking.common.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateInfo {
	
	private Timestamp date;
	private String day;
	
	public DateInfo(Timestamp date, String day) {
		super();
		this.date = date;
		this.day = day;
	}
	
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	

}
