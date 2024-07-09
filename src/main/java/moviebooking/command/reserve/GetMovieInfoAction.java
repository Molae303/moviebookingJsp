package moviebooking.command.reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import moviebooking.bean.movie.MovieDAO;
import moviebooking.bean.movie.MovieVO;
import moviebooking.command.CommandAction;

public class GetMovieInfoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int movieCd = Integer.parseInt(request.getParameter("movieCd"));
		
		System.out.println(movieCd);
		MovieDAO mDbpro = MovieDAO.getInstance();
		MovieVO movie = mDbpro.getMovieInfo(movieCd);
		
		JSONObject result = new JSONObject();
		result.put("movieNm", movie.getMovieNm());
		result.put("audits", movie.getAudits());
		result.put("image", movie.getImage());
		
		return result.toString();
	}

}
