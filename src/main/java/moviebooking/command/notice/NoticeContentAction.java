package moviebooking.command.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.notice.NoticeDAO;
import moviebooking.bean.notice.NoticeVO;
import moviebooking.command.CommandAction;

public class NoticeContentAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));

		NoticeDAO dbPro = NoticeDAO.getInstance();
		dbPro.readcountUpdate(num); // 조회수 증가. 유저가 누를때만 증가됨.
		NoticeVO article = dbPro.getArticle(num); // 실제 내용을 가져옴.
		
		request.setAttribute("type", Integer.valueOf(1));
		request.setAttribute("article", article);
		request.setAttribute("num", num);
		request.setAttribute("pageNum", pageNum);
		return "/notice/noticeContent.jsp,공지사항";
	}

}
