package moviebooking.command.member;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.movie.MovieDAO;
import moviebooking.bean.movie.MovieVO;
import moviebooking.bean.notice.NoticeDAO;
import moviebooking.bean.notice.NoticeVO;
import moviebooking.command.CommandAction;

public class MemberMainAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int endRow = 4; // 메인화면에 보여줄 공지사항게시글수
		int movieCount = 10; //메인화면에 보여줄 top10 영화갯수
		List<MovieVO> movieList = null;
		List<NoticeVO> articleList = null;
		NoticeDAO dbPro = NoticeDAO.getInstance();
		MovieDAO mdbPro = MovieDAO.getInstance();
		articleList = dbPro.getArticles(1, endRow);
		movieList = mdbPro.getMovieList(movieCount);
		
		request.setAttribute("movieList", movieList);
		request.setAttribute("articleList", articleList);
		request.setAttribute("type", Integer.valueOf(1));
		return "/main.jsp,메인페이지";
	}

}
