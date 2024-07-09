package moviebooking.bean.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import moviebooking.common.dbcon.DBUtil;

public class NoticeDAO {

	private static NoticeDAO instance = new NoticeDAO();

	public static NoticeDAO getInstance() {
		return instance;
	}

	private NoticeDAO() {
	}

	// 입력 (새로운글 입력)
	public boolean insertArticle(NoticeVO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		int num = article.getNum();
		int number = 0;
		try {
			conn = DBUtil.getConnection();
			// 글 저장
			String sql = "insert into noticebbs values(noticebbs_seq.nextval,?,?,?,0,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getCategory());
			pstmt.setString(4, article.getContent());
			int count = pstmt.executeUpdate();
			if (count > 0)
				flag = true;
		} catch (Exception ex) {
			System.out.println("Exception" + ex);
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return flag;
	}

	// 전체글을 갯수 가져오기
	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from noticebbs");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 전체글가져오기
	public List<NoticeVO> getArticles(int start, int end) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NoticeVO> articleList = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "Select * from (\r\n"
					+ "select rownum rnum, num, subject, writer, category, regdate, readcount, content from (\r\n"
					+ "select * from noticebbs order by regdate desc)) where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			articleList = new ArrayList<NoticeVO>(end - start + 1); // ArrayList에 담을 갯수 지정.
			while (rs.next()) {
				NoticeVO article = new NoticeVO();
				article.setNum(rs.getInt("num"));
				article.setSubject(rs.getString("subject"));
				article.setWriter(rs.getString("writer"));
				article.setCategory(rs.getString("category"));
				article.setRegDate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setContent(rs.getString("content"));
				articleList.add(article);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return articleList;
	}
	
	// 검색한 글의 갯수 가져오기
		public int getArticleCount(String searchCategory, String keyword) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int x = 0;
			try {
				conn = DBUtil.getConnection();
				pstmt = conn.prepareStatement("select count(*) from noticebbs where "+searchCategory+" like ?");
				pstmt.setString(1, "%"+keyword+"%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					x = rs.getInt(1);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DBUtil.dbReleaseClose(rs, pstmt, conn);
			}
			return x;
		}
	
	// 검색한 전체글가져오기 
		public List<NoticeVO> getArticles(int start, int end, String searchCategory, String keyword) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<NoticeVO> articleList = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "Select * from (\r\n"
						+ "select rownum rnum, num, subject, writer, category, regdate, readcount, content from (\r\n"
						+ "select * from noticebbs where "+searchCategory+" like ? order by regdate desc)) where rnum >= ? and rnum <= ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,"%"+keyword+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
				articleList = new ArrayList<NoticeVO>(end - start + 1); // ArrayList에 담을 갯수 지정.
				while (rs.next()) {
					NoticeVO article = new NoticeVO();
					article.setNum(rs.getInt("num"));
					article.setSubject(rs.getString("subject"));
					article.setWriter(rs.getString("writer"));
					article.setCategory(rs.getString("category"));
					article.setRegDate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setContent(rs.getString("content"));
					articleList.add(article);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				DBUtil.dbReleaseClose(rs, pstmt, conn);
			}
			return articleList;
		}

	// 조회수 증가시키는 메소드
	public void readcountUpdate(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("update noticebbs set readcount=readcount+1 where num =?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
	}

	// 하나의 글 가져오기
	public NoticeVO getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		NoticeVO article = null;
		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select * from noticebbs where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				article = new NoticeVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setSubject(rs.getString("subject"));
				article.setCategory(rs.getString("category"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRegDate(rs.getTimestamp("regDate"));
				article.setContent(rs.getString("content"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return article;
	}

	// 글 수정 진행.
	public int updateArticle(NoticeVO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int result = -1;// 데이터베이스 오류
		try {
			conn = DBUtil.getConnection();
			sql = "update noticebbs set subject=?, category=?, content=? where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getSubject());
			pstmt.setString(2, article.getCategory());
			pstmt.setString(3, article.getContent());
			pstmt.setInt(4, article.getNum());
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return result;
	}

	// 글 삭제하기
	public int deleteArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("delete from noticebbs where num = ?");
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return result;
	}

}
