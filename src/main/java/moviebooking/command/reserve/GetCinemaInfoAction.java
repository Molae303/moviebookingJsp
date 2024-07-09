package moviebooking.command.reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import moviebooking.bean.movie.CinemaDAO;
import moviebooking.bean.movie.CinemaVO;
import moviebooking.command.CommandAction;

public class GetCinemaInfoAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));

		System.out.println(cinemaId);
		CinemaDAO cDbpro = CinemaDAO.getInstance();
		CinemaVO cinema = cDbpro.getCinemaInfo(cinemaId);

		JSONObject result = new JSONObject();
		result.put("cinemaId", cinema.getId());
		result.put("cinemaNm", cinema.getName());
		result.put("address", cinema.getAddress());

		System.out.println(result);
		return result.toString();
	}

}
