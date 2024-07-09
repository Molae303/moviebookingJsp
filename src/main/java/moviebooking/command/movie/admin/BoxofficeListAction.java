package moviebooking.command.movie.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.movie.MovieDAO;
import moviebooking.bean.movie.MovieVO;
import moviebooking.command.CommandAction;

public class BoxofficeListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		MovieDAO dbPro = MovieDAO.getInstance();
		ArrayList<MovieVO> boxofficeList = null;
		boxofficeList = dbPro.getMovieList();
		
		
		
		request.setAttribute("boxofficeList", boxofficeList);
		request.setAttribute("type", Integer.valueOf(0));
		return "/movie/boxofficeList.jsp,관리자페이지";
	}

}
