package moviebooking.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.member.MemberDAO;
import moviebooking.command.CommandAction;

public class ConfirmIdAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");

		// 주어진 id 의 중복여부를 체크해 값을 반환.
		MemberDAO dbPro = MemberDAO.getInstance();
		int check = dbPro.confirmId(id);
		request.setAttribute("check", Integer.valueOf(check));
		return "/member/confirmId.jsp,회원가입";
	}

}
