package moviebooking.bean.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import moviebooking.common.dbcon.DBUtil;

public class ScreeningDAO {

	private static ScreeningDAO instance = new ScreeningDAO();

	public static ScreeningDAO getInstance() {
		return instance;
	}

	private ScreeningDAO() {
	}

	// 상영중테이블 리스트를 가져오는 메소드
	public List<ScreeningVO> getScreeningList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreeningVO> screeningList = null;
		String sql = "select * from screening";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ScreeningVO screening = new ScreeningVO();
				screening.setId(rs.getInt("id"));
				screening.setMovieCd(rs.getInt("movieCd"));
				screening.setScreenId(rs.getInt("screenId"));
				screening.setStartTm(rs.getTimestamp("startTm"));
				screening.setEndTm(rs.getTimestamp("endTm"));
				screeningList.add(screening);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return screeningList;
	}

	// 상영중인 영화의 영화관정보를 가져오는 메소드
	public List<CinemaVO> getCinemaList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CinemaVO> cinemaList = null;
		String sql = "select c.id id, c.name name, c.address addres from screening s\r\n"
				+ "    join screen sc on s.screenId = sc.id\r\n" + "join cinema c on sc.cinemaId = c.id";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			cinemaList = new ArrayList<CinemaVO>();
			while (rs.next()) {
				CinemaVO cinema = new CinemaVO();
				cinema.setId(rs.getInt("id"));
				cinema.setName(rs.getString("name"));
				cinema.setAddress(rs.getString("address"));
				cinemaList.add(cinema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return cinemaList;
	}

	// 상영중인 영화 정보 가져오는 메소드
	public List<MovieVO> getMovieList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MovieVO> movieList = null;
		String sql = "select m.moviecd movieCd, m.movieNm movieNm, m.audits audits  from screening s\r\n"
				+ "    join movie m on s.movieCd = m.movieCd";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			movieList = new ArrayList<MovieVO>();
			while (rs.next()) {
				MovieVO movie = new MovieVO();
				movie.setMovieCd(rs.getInt("movieCd"));
				movie.setMovieNm(rs.getString("movieNm"));
				movie.setAudits(rs.getString("audits"));
				movieList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return movieList;
	}
}
