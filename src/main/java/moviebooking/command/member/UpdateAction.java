package moviebooking.command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.command.CommandAction;

public class UpdateAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("type", Integer.valueOf(1));
		return "/member/update.jsp,마이페이지";
	}

}
