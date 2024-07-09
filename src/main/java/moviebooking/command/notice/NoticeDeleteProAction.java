package moviebooking.command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.notice.NoticeDAO;
import moviebooking.command.CommandAction;

public class NoticeDeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		NoticeDAO dbPro = NoticeDAO.getInstance();
		dbPro.deleteArticle(num);
		request.setAttribute("type", Integer.valueOf(0));
		return "/notice/noticePro.jsp,공지사항";
	}

}
