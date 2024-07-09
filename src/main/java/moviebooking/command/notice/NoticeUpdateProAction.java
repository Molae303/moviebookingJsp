package moviebooking.command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.notice.NoticeDAO;
import moviebooking.bean.notice.NoticeVO;
import moviebooking.command.CommandAction;

public class NoticeUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		//수정할 공지사항정보
		NoticeDAO dbPro = NoticeDAO.getInstance();
		NoticeVO article = new NoticeVO();
		article.setNum(Integer.parseInt(request.getParameter("num")));
		article.setSubject(request.getParameter("subject"));
		article.setCategory(request.getParameter("category"));
		article.setContent(request.getParameter("content"));
		int check = dbPro.updateArticle(article);
		request.setAttribute("check", check);
		return "/notice/noticeUpdatePro.jsp,공지사항";
	}

}
