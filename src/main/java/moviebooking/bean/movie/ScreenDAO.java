package moviebooking.bean.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import moviebooking.common.dbcon.DBUtil;

public class ScreenDAO {

	private static ScreenDAO instance = new ScreenDAO();

	public static ScreenDAO getInstance() {
		return instance;
	}

	private ScreenDAO() {

	}

	// 상영관 리스트를 가져오는 메소드
	public List<ScreenVO> getScreenList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ScreenVO> screenList = null;
		String sql = "select * from screen";
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ScreenVO screen = new ScreenVO();
				screen.setId(rs.getInt("id"));
				screen.setCinemaId(rs.getInt("cinemaId"));
				screen.setName(rs.getString("name"));
				screen.setCapacity(rs.getInt("capacity"));
				screenList.add(screen);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return screenList;

	}

}
