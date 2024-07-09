package moviebooking.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.command.CommandAction;

public class AdminLoginFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/adLoginForm.jsp,로그인";
	}

}
