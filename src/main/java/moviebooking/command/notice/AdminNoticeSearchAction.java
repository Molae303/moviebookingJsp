package moviebooking.command.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.notice.NoticeDAO;
import moviebooking.bean.notice.NoticeVO;
import moviebooking.command.CommandAction;

public class AdminNoticeSearchAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String searchCategory = request.getParameter("searchCategory");
		String keyword = request.getParameter("keyword");
		// 리스트에 보여줄 게시글갯수(페이지 라인수)
		int pageSize = 10;
		String pageNum = null;
		// 페이지를 지정하지 않으면 기본으로 1페이지를 보여준다.
		if (request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("")) {
			pageNum = request.getParameter("pageNum");
		}
		if (pageNum == null) {
			pageNum = "1";
		}
		// 현재페이지, 현재페이지 시작라인, 끝라인을 계산(공식)
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;

		int count = 0;
		int number = 0;
		List<NoticeVO> articleList = null;
		NoticeDAO dbPro = NoticeDAO.getInstance();
		count = dbPro.getArticleCount(searchCategory,keyword);// 전체 글수
		if (count > 0) {
			articleList = dbPro.getArticles(startRow, endRow, searchCategory, keyword);//
		}
		number = count - (currentPage - 1) * pageSize;// 번호순으로 보여주는 공식()

		int pageBlock = 0;
		int pageCount = 0;
		int startPage = 0;
		int endPage = 0;
		if (count > 0) {
			pageBlock = 5; // 하단에 보여줘야할 페이지
			// 전체페이지수를 구하는 공식
			int imsi = (count % pageSize == 0) ? 0 : 1;
			pageCount = count / pageSize + imsi;
			startPage = (int) ((currentPage - 1) / pageBlock) * pageBlock + 1;
			endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
		}
		
		request.setAttribute("keyword", keyword);
		request.setAttribute("searchCategory", searchCategory);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("count", Integer.valueOf(count));
		request.setAttribute("articleList", articleList);
		request.setAttribute("number", Integer.valueOf(number));
		request.setAttribute("pageBlock", Integer.valueOf(pageBlock));
		request.setAttribute("pageCount", Integer.valueOf(pageCount));
		request.setAttribute("startPage", Integer.valueOf(startPage));
		request.setAttribute("endPage", Integer.valueOf(endPage));
		request.setAttribute("type", Integer.valueOf(0));
		request.setAttribute("search", Integer.valueOf(1));
		return "/notice/noticeList.jsp,공지사항";

	}

}
