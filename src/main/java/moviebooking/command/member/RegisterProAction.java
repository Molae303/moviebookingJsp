package moviebooking.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.member.MemberDAO;
import moviebooking.bean.member.MemberVO;
import moviebooking.command.CommandAction;

public class RegisterProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		
		MemberVO mvo = new MemberVO();
		mvo.setId(request.getParameter("id"));
		mvo.setPasswd(request.getParameter("passwd"));
		mvo.setName(request.getParameter("name"));
		mvo.setNickName(request.getParameter("nickName"));
		mvo.setEmail(request.getParameter("email"));
		mvo.setSignUpPath(request.getParameter("signUpPath"));
		mvo.setTel(request.getParameter("tel"));
		mvo.setPhone(request.getParameter("phone"));
		mvo.setBirth(request.getParameter("birth"));
		mvo.setAddress(request.getParameter("address"));
		mvo.setAddress2(request.getParameter("address2"));
		mvo.setAddress3(request.getParameter("address3"));
		mvo.setAddress4(request.getParameter("address4"));
		
		MemberDAO dbPro = MemberDAO.getInstance();
		int check = dbPro.insertMember(mvo);
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/registerPro.jsp,회원가입";
	}

}
