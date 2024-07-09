package moviebooking.command.movie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.movie.MovieDAO;
import moviebooking.bean.movie.MovieVO;
import moviebooking.command.CommandAction;

public class MovieInfoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int movieCd = Integer.parseInt(request.getParameter("movieCd"));
		MovieDAO dbPro = MovieDAO.getInstance();
		MovieVO movie = dbPro.getMovieInfo(movieCd);
		
		request.setAttribute("type", Integer.valueOf(1));
		request.setAttribute("movie", movie);
		
		
		return "/movie/movieInfo.jsp,영화";
	}
}
