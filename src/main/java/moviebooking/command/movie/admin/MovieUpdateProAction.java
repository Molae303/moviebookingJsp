package moviebooking.command.movie.admin;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import moviebooking.bean.movie.MovieDAO;
import moviebooking.command.CommandAction;

public class MovieUpdateProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String filename = "";
		String realFolder = "";// 웹 어플리케이션상의 절대 경로 저장
		String saveFolder = "/images/movie/poster"; // 파일 업로드 폴더 지정
		String encType = "utf-8"; // 인코딩타입
		int maxSize = 10 * 1024 * 1024; // 최대 업로될 파일크기 10Mb
		MultipartRequest imageUp = null;
		// 웹 어플리케이션상의 절대 경로를 구함
		ServletContext context = request.getSession().getServletContext();
		realFolder = context.getRealPath(saveFolder);

		try {
			imageUp = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			Enumeration<?> files = imageUp.getFileNames();

			while (files.hasMoreElements()) {
				String name = (String) files.nextElement();
				filename = imageUp.getFilesystemName(name);
				System.out.println("realFolder = " + realFolder + ", filename = " + filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		int movieCd = Integer.parseInt(imageUp.getParameter("movieCd"));
		String content = imageUp.getParameter("content");
		String image = saveFolder + "/" + filename;

		MovieDAO dbPro = MovieDAO.getInstance();
		dbPro.updateMovie(movieCd, content, image);

		request.setAttribute("movieCd", movieCd);
		return "/movie/movieUpdatePro.jsp,영화";
	}

}
