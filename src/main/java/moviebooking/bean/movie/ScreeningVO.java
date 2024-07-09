package moviebooking.bean.movie;

import java.sql.Timestamp;

public class ScreeningVO {
	
	private int id;
	private int movieCd;
	private int screenId;
	private Timestamp startTm;
	private Timestamp endTm;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMovieCd() {
		return movieCd;
	}
	public void setMovieCd(int movieCd) {
		this.movieCd = movieCd;
	}
	public int getScreenId() {
		return screenId;
	}
	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}
	public Timestamp getStartTm() {
		return startTm;
	}
	public void setStartTm(Timestamp startTm) {
		this.startTm = startTm;
	}
	public Timestamp getEndTm() {
		return endTm;
	}
	public void setEndTm(Timestamp endTm) {
		this.endTm = endTm;
	}
}
