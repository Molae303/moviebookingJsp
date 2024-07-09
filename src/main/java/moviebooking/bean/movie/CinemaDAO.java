package moviebooking.bean.movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import moviebooking.common.dbcon.DBUtil;

public class CinemaDAO {
	
	private static CinemaDAO instance = new CinemaDAO();
	
	public static CinemaDAO getInstance() {
		return instance;
	}
	
	private CinemaDAO() {}
	
	//영화관 리스트 가져오는 메소드
	public List<CinemaVO> getCinemaList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CinemaVO> cinemaList = null;
		String sql = "select * from cinema";
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			cinemaList = new ArrayList<CinemaVO>();
			while(rs.next()) {
				CinemaVO cinema = new CinemaVO();
				cinema.setId(rs.getInt("id"));
				cinema.setName(rs.getString("name"));
				cinema.setAddress(rs.getString("address"));
				cinemaList.add(cinema);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return cinemaList;
	}
	
	//데이터베이스에 있는 영화정보를 아이디로 하나만 가져온다. movieInfo.do 메소드
		public CinemaVO getCinemaInfo(int cinemaId) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			CinemaVO cinema = null;
			try {
				conn = DBUtil.getConnection();
				//예매율 높은순으로 가져온다.
				pstmt = conn.prepareStatement("select * from cinema where id = ?");
				pstmt.setInt(1, cinemaId);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					cinema = new CinemaVO();
					cinema.setId(rs.getInt("id"));
					cinema.setName(rs.getString("name"));
					cinema.setAddress(rs.getString("address"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DBUtil.dbReleaseClose(rs, pstmt, conn);
			}
			return cinema;
		}

}
