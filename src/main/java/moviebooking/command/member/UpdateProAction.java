package moviebooking.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.member.MemberDAO;
import moviebooking.bean.member.MemberVO;
import moviebooking.command.CommandAction;

public class UpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		// 수정할 회원 정보
		MemberVO mvo = new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setNickName(request.getParameter("nickName"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setTel(request.getParameter("tel"));
		mvo.setPhone(request.getParameter("phone"));
		mvo.setBirth(request.getParameter("birth"));
		mvo.setAddress(request.getParameter("address"));
		mvo.setAddress2(request.getParameter("address2"));
		mvo.setAddress3(request.getParameter("address3"));
		mvo.setAddress4(request.getParameter("address4"));
		// 수정할 회원 정보를 가지고 수정 처리 후 성공여부 반환
		MemberDAO dbPro = MemberDAO.getInstance();
		int check = dbPro.updateMember(mvo);
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/updatePro.jsp,마이페이지";
	}
}
