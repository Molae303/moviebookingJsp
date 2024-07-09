package moviebooking.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.member.MemberDAO;
import moviebooking.bean.member.MemberVO;
import moviebooking.command.CommandAction;

public class UserListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		List<MemberVO> userList = null;
		MemberDAO dbPro = MemberDAO.getInstance();
		userList = dbPro.getUserList();
		
		request.setAttribute("type", Integer.valueOf(0));
		request.setAttribute("userList", userList);
		return "/admin/userList.jsp,관리자페이지";
	}

}
