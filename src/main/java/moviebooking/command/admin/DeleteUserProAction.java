package moviebooking.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.member.MemberDAO;
import moviebooking.command.CommandAction;

public class DeleteUserProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		String[] selectUser = request.getParameterValues("selectUser");
		MemberDAO dbPro = MemberDAO.getInstance();
		for(String id : selectUser) {
			dbPro.deleteUser(id);
		}
		request.setAttribute("type", Integer.valueOf(0));
		return "/admin/deleteUserPro.jsp,관리자페이지";
	}

}
