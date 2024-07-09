package moviebooking.bean.admin.enc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import moviebooking.common.crypt.BCrypt;
import moviebooking.common.crypt.SHA256;
import moviebooking.common.dbcon.DBUtil;


public class PassCrypt {
	private static PassCrypt instance = new PassCrypt();
//	private static PassCrypt instance = null;

	public static PassCrypt getInstance() {
//		if(instance == null) {
//			instance = new PassCrypt();
//		} 
		return instance;
	}

	private PassCrypt() {
	}

	public void cryptProcess() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 1. SHA-256 를 사용하는 SHA256 클래스의 객체를 얻어낸다.
		SHA256 sha = SHA256.getInsatnce();

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select Id, Passwd from admin");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String orgPass = rs.getString("passwd");
				// 2. 패스워드를 암호화처리를 진행한다.
				String shaPass = sha.getSha256(orgPass.getBytes());
				String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

				pstmt = conn.prepareStatement("update admin set passwd=? where id=?");
				pstmt.setString(1, bcPass);
				pstmt.setString(2, id);
				pstmt.executeUpdate();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
	}
}
