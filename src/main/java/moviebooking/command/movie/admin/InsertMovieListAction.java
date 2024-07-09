package moviebooking.command.movie.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.movie.MovieVO;
import moviebooking.command.CommandAction;

public class InsertMovieListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println(request.getAttribute("boxofficeList"));
//		for(MovieVO data : movieList) {
//			System.out.println(data.toString()); 
//		}
		return null;
	}

	
}
