package moviebooking.bean.movie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import moviebooking.common.dbcon.DBUtil;

public class MovieDAO {

	private static MovieDAO instance = new MovieDAO();

	public static MovieDAO getInstance() {
		return instance;
	}

	private MovieDAO() {
	}

	// 박스오피스API 메소드
	public ArrayList<MovieVO> loadBoxofficeList() {
		// 현재날짜의 전날짜를 String 으로 만들어주는 메소드
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		cal.add(cal.DATE, -1);

		String apiKey = "apiKey"; // 발급받은 API 키 관리자가 직접 입력하게 바꿀 예정.
		String targetDt = sdf.format(cal.getTime());
		String apiUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json"
				+ "?key=" + apiKey + "&targetDt=" + targetDt;
		MovieVO movie = null;
		ArrayList<MovieVO> boxofficeList = null;
		try {
			// URL 객체 생성
			URL url = new URL(apiUrl);
			// HTTP 연결 생성
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// 요청 방식 설정 (GET)
			connection.setRequestMethod("GET");

			// 응답 코드 확인
			int responseCode = connection.getResponseCode();
			System.out.println("Response Code: " + responseCode);

			// 응답이 정상적인 경우에만 처리
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;
				boxofficeList = new ArrayList<MovieVO>();

				// 응답을 한 줄씩 읽어옴
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// JSON 파싱
				JSONObject jsonResponse = new JSONObject(response.toString());
				JSONObject boxOfficeResult = jsonResponse.getJSONObject("boxOfficeResult");
				JSONArray dailyBoxOfficeList = boxOfficeResult.getJSONArray("dailyBoxOfficeList");
				for (int i = 0; i < dailyBoxOfficeList.length(); i++) {
					JSONObject dailyBoxOffice = dailyBoxOfficeList.getJSONObject(i);
					int movieCd = Integer.parseInt(dailyBoxOffice.getString("movieCd"));
					movie = loadMovieInfo(movieCd, apiKey);
					movie.setSalesShare(Double.parseDouble(dailyBoxOffice.getString("salesShare")));
					boxofficeList.add(movie);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boxofficeList;
	}

	// 영화상세정보 API 메소드
	public MovieVO loadMovieInfo(int movieCd, String apiKey) {
		String apiUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json" + "?key="
				+ apiKey + "&movieCd=" + movieCd;
		MovieVO movie = null;
		try {
			// URL 객체 생성
			URL url = new URL(apiUrl);
			// HTTP 연결 생성
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			// 요청 방식 설정 (GET)
			connection.setRequestMethod("GET");

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;
				movie = new MovieVO();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// JSON 파싱
				JSONObject jsonResponse = new JSONObject(response.toString());
				JSONObject movieInfoResult = jsonResponse.getJSONObject("movieInfoResult");
				JSONObject movieInfo = movieInfoResult.getJSONObject("movieInfo");

				movie.setMovieCd(Integer.parseInt(movieInfo.getString("movieCd")));
				movie.setMovieNm(movieInfo.getString("movieNm"));
				movie.setShowTm(Integer.parseInt(movieInfo.getString("showTm")));
				movie.setOpenDt(movieInfo.getString("openDt"));
				movie.setDirector(movieInfo.getJSONArray("directors").getJSONObject(0).getString("peopleNm"));

				// 장르가 여러가지일 경우 사이에 ","을 붙힘
				String genreNm = "";
				JSONArray genres = movieInfo.getJSONArray("genres");
				for (int i = 0; i < genres.length(); i++) {
					JSONObject genre = genres.getJSONObject(i);
					if (i >= 1) {
						genreNm += ",";
					}
					genreNm += genre.getString("genreNm");
				}
				movie.setGenres(genreNm);

				// 배우정보가 2개 이상일경우 사이에 ","을 붙힘
				StringBuilder actorsNm = new StringBuilder();
				JSONArray actors = movieInfo.getJSONArray("actors");
				for (int i = 0; i < actors.length(); i++) {
					if (i > 4) {
						break;
					}
					JSONObject actor = actors.getJSONObject(i);
					if (i >= 1) {
						actorsNm.append(",");
					}
					actorsNm.append(actor.getString("peopleNm"));
				}
				movie.setActors(actorsNm.toString());
				movie.setCompanys(movieInfo.getJSONArray("companys").getJSONObject(0).getString("companyNm"));
				movie.setAudits(genreNm);
				movie.setAudits(movieInfo.getJSONArray("audits").getJSONObject(0).getString("watchGradeNm"));
			} else {
				System.out.println("GET request not worked");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie;
	}

	// 영화 API에서 얻어온 영화리스트를 DB테이블에 insert
	public int insertMovieList(ArrayList<MovieVO> boxofficeList) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			for (MovieVO movie : boxofficeList) {
				// API에서 받아온 영화리스트를 넣을때 이미 DB에 존재할경우 예매율만 업데이트 시켜준다.
				if (confirmMovie(movie.getMovieCd())) {
					pstmt = conn.prepareStatement("update movie set salesShare = ? where movieCd = ?");
					pstmt.setDouble(1, movie.getSalesShare());
					pstmt.setInt(2, movie.getMovieCd());
					pstmt.executeUpdate();
					continue;
				}
				pstmt = conn.prepareStatement(
						"insert into movie(movieCd,movieNm,salesShare,director,showTm,openDt,genres,actors,audits,companys) values(?,?,?,?,?,?,?,?,?,?)");
				pstmt.setInt(1, movie.getMovieCd());
				pstmt.setString(2, movie.getMovieNm());
				pstmt.setDouble(3, movie.getSalesShare());
				pstmt.setString(4, movie.getDirector());
				pstmt.setInt(5, movie.getShowTm());
				pstmt.setString(6, movie.getOpenDt());
				pstmt.setString(7, movie.getGenres());
				pstmt.setString(8, movie.getActors());
				pstmt.setString(9, movie.getAudits());
				pstmt.setString(10, movie.getCompanys());
				result = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return result;
	}

	// API에서 얻어온 영화리스트를 insert 할때 이미 존재하는지 확인함.
	public boolean confirmMovie(int movieCd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean result = false;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement("select movieCd from movie where movieCd = ?");
			pstmt.setInt(1, movieCd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return result;
	}

	// 데이터베이스에 있는 영화정보를 리스트로 가져온다.
	public ArrayList<MovieVO> getMovieList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MovieVO> movieList = null;
		try {
			conn = DBUtil.getConnection();
			// 예매율 높은순으로 가져온다.
			pstmt = conn.prepareStatement("select * from movie order by salesShare desc");
			rs = pstmt.executeQuery();
			movieList = new ArrayList<MovieVO>();
			while (rs.next()) {
				MovieVO movie = new MovieVO();
				movie.setMovieCd(rs.getInt("movieCd"));
				movie.setMovieNm(rs.getString("movieNm"));
				movie.setSalesShare(rs.getDouble("salesShare"));
				movie.setOpenDt(rs.getString("openDt"));
				movie.setDirector(rs.getString("director"));
				movie.setGenres(rs.getString("genres"));
				movie.setAudits(rs.getString("audits"));
				movie.setCompanys(rs.getString("companys"));
				movieList.add(movie);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return movieList;
	}

	// 메인화면에 보여줄 top10 영화 리스트
	public ArrayList<MovieVO> getMovieList(int movieCount) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<MovieVO> movieList = null;
		try {
			conn = DBUtil.getConnection();
			// 예매율 높은순으로 가져온다.
			pstmt = conn.prepareStatement(
					"select * from (\r\n" + "select rownum rnum, movieCd,movieNm,salesShare,image from (\r\n"
							+ "select * from movie order by salesShare desc)) where rnum  <= ?");
			pstmt.setInt(1, movieCount);
			rs = pstmt.executeQuery();
			movieList = new ArrayList<MovieVO>();
			while (rs.next()) {
				MovieVO movie = new MovieVO();
				movie.setMovieCd(rs.getInt("movieCd"));
				String movieNm = rs.getString("movieNm");
				movie.setMovieNm(movieNm.length() > 10 ? movieNm.substring(0, 10) + "..." : movieNm);
				movie.setSalesShare(rs.getDouble("salesShare"));
				movie.setImage(rs.getString("image"));
				movieList.add(movie);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return movieList;
	}

	// 데이터베이스에 있는 영화정보를 아이디로 하나만 가져온다. movieInfo.do 메소드
	public MovieVO getMovieInfo(int movieCd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieVO movie = null;
		try {
			conn = DBUtil.getConnection();
			// 예매율 높은순으로 가져온다.
			pstmt = conn.prepareStatement("select * from movie where movieCd = ?");
			pstmt.setInt(1, movieCd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				movie = new MovieVO();
				movie.setMovieCd(rs.getInt("movieCd"));
				movie.setMovieNm(rs.getString("movieNm"));
				movie.setSalesShare(rs.getDouble("salesShare"));
				movie.setDirector(rs.getString("director"));
				movie.setShowTm(rs.getInt("showTm"));
				movie.setOpenDt(rs.getString("openDt"));
				movie.setGenres(rs.getString("genres"));
				movie.setActors(rs.getString("actors"));
				movie.setAudits(rs.getString("audits"));
				movie.setCompanys(rs.getString("companys"));
				movie.setImage(rs.getString("image"));
				movie.setContent(rs.getString("content"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(rs, pstmt, conn);
		}
		return movie;
	}

	// 영화 이미지, 줄거리 정보업데이트
	public int updateMovie(int movieCd, String content, String image) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DBUtil.getConnection();
			// 예매율 높은순으로 가져온다.
			pstmt = conn.prepareStatement("update movie set content=?, image=? where movieCd = ?");
			pstmt.setString(1, content);
			pstmt.setString(2, image);
			pstmt.setInt(3, movieCd);
			result = pstmt.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.dbReleaseClose(pstmt, conn);
		}
		return result;
	}
}
