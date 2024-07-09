package moviebooking.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.admin.AdminDAO;
import moviebooking.command.CommandAction;

public class AdminLoginProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");// 한글 인코딩

		// 넘어온 요청의 데이터를 얻어냄
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		// DB 와 연동해서 사용자의 인증을 처리
		AdminDAO dbPro = AdminDAO.getInstance();
		int check = dbPro.userCheck(id, passwd);
		// 해당 뷰(응답페이지)로 보낼 내용을 request 속성에 지정
		request.setAttribute("check", check);
		request.setAttribute("id", id);
		return "/admin/adLoginPro.jsp,로그인";
	}

}
