package moviebooking.bean.movie;

import java.sql.Timestamp;

public class ReservationVO {
	
	private int id;
	private int screeningId;
	private String memberId;
	private Timestamp reservationTm;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getScreeningId() {
		return screeningId;
	}
	public void setScreeningId(int screeningId) {
		this.screeningId = screeningId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Timestamp getReservationTm() {
		return reservationTm;
	}
	public void setReservationTm(Timestamp reservationTm) {
		this.reservationTm = reservationTm;
	}
}
