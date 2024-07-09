package moviebooking.bean.movie;

public class MovieVO {
	private int movieCd;
	private String movieNm;
	private double salesShare;
	private String director;
	private int showTm;
	private String openDt;
	private String genres;
	private String actors;
	private String audits;
	private String companys;
	private String image;
	private String content;
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getMovieCd() {
		return movieCd;
	}
	public void setMovieCd(int movieCd) {
		this.movieCd = movieCd;
	}
	public String getMovieNm() {
		return movieNm;
	}
	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}
	public double getSalesShare() {
		return salesShare;
	}
	public void setSalesShare(double salesShare) {
		this.salesShare = salesShare;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public int getShowTm() {
		return showTm;
	}
	public void setShowTm(int showTm) {
		this.showTm = showTm;
	}
	public String getOpenDt() {
		return openDt;
	}
	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}
	public String getGenres() {
		return genres;
	}
	public void setGenres(String genres) {
		this.genres = genres;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getAudits() {
		return audits;
	}
	public void setAudits(String audits) {
		this.audits = audits;
	}
	public String getCompanys() {
		return companys;
	}
	public void setCompanys(String companys) {
		this.companys = companys;
	}
	@Override
	public String toString() {
		return "MovieVO [movieCd=" + movieCd + ", movieNm=" + movieNm + ", salesShare=" + salesShare + ", director="
				+ director + ", showTm=" + showTm + ", openDt=" + openDt + ", genres=" + genres + ", actors=" + actors
				+ ", audits=" + audits + ", companys=" + companys + "]";
	}
	
	
	
}
