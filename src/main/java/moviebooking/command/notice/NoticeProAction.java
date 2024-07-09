package moviebooking.command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.notice.NoticeDAO;
import moviebooking.bean.notice.NoticeVO;
import moviebooking.command.CommandAction;

public class NoticeProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		NoticeVO nvo = new NoticeVO();
		nvo.setWriter("관리자");
		nvo.setSubject(request.getParameter("subject"));
		nvo.setCategory(request.getParameter("category"));
		nvo.setContent(request.getParameter("content"));
		
		NoticeDAO dbPro = NoticeDAO.getInstance();
		dbPro.insertArticle(nvo);
		request.setAttribute("type", Integer.valueOf(0));
		return "/notice/noticePro.jsp,공지사항";
	}

}
