package moviebooking.command.movie;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.bean.movie.CinemaDAO;
import moviebooking.bean.movie.CinemaVO;
import moviebooking.bean.movie.MovieDAO;
import moviebooking.bean.movie.MovieVO;
import moviebooking.command.CommandAction;
import moviebooking.common.util.DateInfo;

public class MovieReservationFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		CinemaDAO cDbPro = CinemaDAO.getInstance();
		MovieDAO mDbPro = MovieDAO.getInstance();
		List<CinemaVO> cinemaList = null;
		List<MovieVO> movieList = null;
		String movieCd = null;
		
		if(request.getParameter("movieCd") != null ) {
			movieCd = request.getParameter("movieCd");
			request.setAttribute("movieCd", movieCd);
		}
		// 요일 리스트 2달담기
		Calendar cal = Calendar.getInstance(); // 현재 날짜로 설정
        List<DateInfo> dateList = new ArrayList<>();

        int totalDays = 70; // 총 60일 동안의 날짜 생성
        for (int i = 0; i < totalDays; i++) {
            Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
            String dayOfWeek = getDayString(cal.get(Calendar.DAY_OF_WEEK));
            dateList.add(new DateInfo(timestamp, dayOfWeek));
            cal.add(Calendar.DAY_OF_MONTH, 1); // 하루씩 추가
        }

		cinemaList = cDbPro.getCinemaList();
		movieList = mDbPro.getMovieList();

		request.setAttribute("cinemaList", cinemaList);
		request.setAttribute("movieList", movieList);
		request.setAttribute("dateList", dateList);
		request.setAttribute("type", Integer.valueOf(1));
		return "/movie/reservationForm.jsp,예매";
	}

	// 요일정보 한글화시키는 메소드
	public String getDayString(int dayOfWeek) {
		switch (dayOfWeek) {
		case Calendar.SUNDAY:
			return "일";
		case Calendar.MONDAY:
			return "월";
		case Calendar.TUESDAY:
			return "화";
		case Calendar.WEDNESDAY:
			return "수";
		case Calendar.THURSDAY:
			return "목";
		case Calendar.FRIDAY:
			return "금";
		case Calendar.SATURDAY:
			return "토";
		default:
			return "";
		}
	}

}
