package moviebooking.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moviebooking.bean.member.MemberDAO;
import moviebooking.bean.member.MemberVO;
import moviebooking.command.CommandAction;

public class UpdateFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		// 회원 정보를 수정하기 위한 정보를 얻어냄
		MemberDAO dbPro = MemberDAO.getInstance();
		MemberVO mvo = dbPro.getMember(id);
		request.setAttribute("mvo", mvo);
		request.setAttribute("id", id);
		request.setAttribute("type", Integer.valueOf(1));
		return "/member/updateForm.jsp,마이페이지";
	}

}
