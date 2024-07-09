package moviebooking.bean.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import moviebooking.common.crypt.BCrypt;
import moviebooking.common.crypt.SHA256;
import moviebooking.common.dbcon.DBUtil;

public class MemberDAO {

	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	private MemberDAO() {
	}

	// 회원가입 처리
	public int insertMember(MemberVO mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();
			String shaPass = sha.getSha256(mvo.getPasswd().getBytes());
			String bcPass = BCrypt.hashpw(shaPass, BCrypt.gensalt());

			pstmt = conn.prepareStatement("insert into membertbl values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)");
			pstmt.setString(1, mvo.getId());
			pstmt.setString(2, bcPass);
			pstmt.setString(3, mvo.getName());
			pstmt.setString(4, mvo.getNickName());
			pstmt.setString(5, mvo.getEmail());
			pstmt.setString(6, mvo.getSignUpPath());
			pstmt.setString(7, mvo.getTel());
			pstmt.setString(8, mvo.getPhone());
			pstmt.setString(9, mvo.getBirth());
			pstmt.setString(10, mvo.getAddress());
			pstmt.setString(11, mvo.getAddress2());
			pstmt.setString(12, mvo.getAddress3());
			pstmt.setString(13, mvo.getAddress4());
			pstmt.setString(14, mvo.getEtcinfo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return result;
	}

	// 회원가입시 아이디 중복 확인
	public int confirmId(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select id from membertbl where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())// 아이디 존재
				x = 1; // 같은 아이디 있음
			else
				x = -1;// 같은 아이디 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}
	
	// 회원가입시 이메일 중복확인
	public int confirmEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select email from membertbl where email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next())// 이메일 존재
				x = 1; // 같은 이메일 있음
			else
				x = -1;// 같은 이메일 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 로그인폼 처리 인증 메소드
	public int userCheck(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = -1;

		// 1. SHA256 객체를 가져온다.
		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();

			String orgPass = passwd;
			// 2. 암호화된 패스워드를 가져와서 비교하는 방법
			String shaPass = sha.getSha256(orgPass.getBytes());

			pstmt = conn.prepareStatement("select passwd from membertbl where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 해당 아이디가 있으면 수행
				String dbpasswd = rs.getString("passwd");
				if (BCrypt.checkpw(shaPass, dbpasswd))
					x = 1; // 인증성공
				else
					x = 0; // 비밀번호 틀림
			} else// 해당 아이디 없으면 수행
				x = -1;// 아이디 없음
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return x;
	}

	// 주어진 id 에 해당하는 회원정보를 얻어내는 메소드
	public MemberVO getMember(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;

		try {
			conn = DBUtil.getConnection();

			pstmt = conn.prepareStatement("select * from memberTbl where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				member = new MemberVO();// 데이터저장빈 객체생성
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setNickName(rs.getString("nickName"));
				member.setEmail(rs.getString("email"));
				member.setTel(rs.getString("tel"));
				member.setPhone(rs.getString("phone"));
				member.setBirth(rs.getString("birth"));
				member.setAddress(rs.getString("address"));
				member.setAddress2(rs.getString("address2"));
				member.setAddress3(rs.getString("address3"));
				member.setAddress4(rs.getString("address4"));
				member.setRegDate(rs.getTimestamp("regDate"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return member;// 데이터 저장빈 객체 member 리턴
	}

	// 주어진 id, passwd 에 해당하는 회원정보를 얻어내는 메소드
	public MemberVO getMember(String id, String passwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;

		SHA256 sha = SHA256.getInsatnce();
		try {
			conn = DBUtil.getConnection();

			String shaPass = sha.getSha256(passwd.getBytes());

			pstmt = conn.prepareStatement("select * from memberTbl where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {// 해당 아이디에 대한 레코드가 존재
				String dbpasswd = rs.getString("passwd");
				if (BCrypt.checkpw(shaPass, dbpasswd)) { // 사용자가 입력한 비밀번호와 db 저장된 비밀번호가 같은지체크
					member = new MemberVO();// 데이터저장빈 객체생성
					member.setId(rs.getString("id"));
					member.setName(rs.getString("name"));
					member.setNickName(rs.getString("nickName"));
					member.setEmail(rs.getString("email"));
					member.setTel(rs.getString("tel"));
					member.setPhone(rs.getString("phone"));
					member.setBirth(rs.getString("birth"));
					member.setAddress(rs.getString("address"));
					member.setAddress2(rs.getString("address2"));
					member.setAddress3(rs.getString("address3"));
					member.setAddress4(rs.getString("address4"));
					member.setRegDate(rs.getTimestamp("regDate"));
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return member;// 데이터 저장빈 객체 member 리턴
	}

	// 회원정보 수정을 처리하는 매소드
	public int updateMember(MemberVO mvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int x = -1;

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(
					"update memberTbl set nickname=?, email=?, tel=?, phone=?, birth=?, address=?, address2=?, address3=?, address4=? where id=?");
			pstmt.setString(1, mvo.getNickName());
			pstmt.setString(2, mvo.getEmail());
			pstmt.setString(3, mvo.getTel());
			pstmt.setString(4, mvo.getPhone());
			pstmt.setString(5, mvo.getBirth());
			pstmt.setString(6, mvo.getAddress());
			pstmt.setString(7, mvo.getAddress2());
			pstmt.setString(8, mvo.getAddress3());
			pstmt.setString(9, mvo.getAddress4());
			pstmt.setString(10, mvo.getId());
			x = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return x;
	}

	// 유저리스트 가져오는 메소드 << 어드민전용
	public List<MemberVO> getUserList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> userList = null;
		String sql = "select * from memberTbl";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			userList = new ArrayList<MemberVO>();
			while (rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setPhone(rs.getString("phone"));
				member.setBirth(rs.getString("birth"));
				member.setRegDate(rs.getTimestamp("regDate"));
				userList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return userList;
	}

	// 유저삭제 메소드 << admin만 가능
	public int deleteUser(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = -1;
		String sql = "Delete From memberTbl where id = ?";

		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return result;
	}

}
