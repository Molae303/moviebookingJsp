package moviebooking.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import moviebooking.bean.member.MemberDAO;
import moviebooking.command.CommandAction;

public class UpdateConfirmAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		// 회원 정보를 수정하기 위한 정보를 얻어냄
		MemberDAO dbPro = MemberDAO.getInstance();
		int check = dbPro.userCheck(id, passwd);
		
		request.setAttribute("check", Integer.valueOf(check));
		request.setAttribute("type", Integer.valueOf(1));
		
		return "/member/updateConfirmPro.jsp,마이페이지";
	}

}
