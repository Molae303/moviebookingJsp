package moviebooking.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import moviebooking.command.CommandAction;

@WebServlet(urlPatterns = { "/Controller", "*.do" }, initParams = {
		@WebInitParam(name = "propertyConfig", value = "commandMapping.properties") })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> commandMap = new HashMap<String, Object>();

	public Controller() {
	}

	// ActionFactory
	// 서블릿 컨트롤러가 생성될때 미리만들어놓은 commandMapping.properties 파일의 정보를 가지고 모든 XXXAction 클래스
	// 객체를 만들어 commandMap에 저장한다. 한번만 실행됨.
	@Override
	public void init(ServletConfig config) throws ServletException {
		// initParams 에서 propertyConfig 의 값을 읽어옴 >> commandMapping.properties
		// props = "commandMapping.properties";
		String props = config.getInitParameter("propertyConfig");
		// System.out.println("props = "+props); << initParameter 확인
		// 실제 properties 파일이 저장된 폴더
		String realFolder = "/property";
		// 웹어플리케이션 루트 경로
		ServletContext context = config.getServletContext();
		// realFolder 를 웹어플리케이션 시스템상의 절대경로로 변경
		String realPath = context.getRealPath(realFolder) + "\\" + props;
		// System.out.println("context.getRealPath(realFolder) = " +
		// context.getRealPath(realFolder)); << 경로확인

		// 명령어와 처리클래스의 매핑정보를 저장할 Properties 객체 생성
		Properties pr = new Properties();
		FileInputStream f = null;
		try {
			// command.properties 파일의 내용을 읽어옴
			f = new FileInputStream(realPath);
			// command.properties 의 내용을 Properties 객체 pr 에 저장
			pr.load(f);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ex) {
				}
		}
		// Set 객체의 iterator()메소드를 사용해 Iterator 객체를 얻어냄
		Iterator<?> keyIter = pr.keySet().iterator();
		// Iterator 객체에 저장된 명령어와 처리클래스를 commandMap 에 저장
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String className = pr.getProperty(command);
			// System.out.println("command = " + command); << 프로퍼티파일에 들어있는 키값 (콘솔창 확인용)
			// System.out.println("className = " + className); << 프로퍼티파일에 들어있는 벨류값 (콘솔창 확인용)
			try {
				Class<?> commandClass = Class.forName(className);
				// Object commandInstance = commandClass.newInstance();
				Object commandInstance = commandClass.getDeclaredConstructor().newInstance();
				commandMap.put(command, commandInstance);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		requestPro(request, response);
	}

	// 웹브라우저의 요청을 분석하고, 해당 로직의 처리를 할 모델 실행 및
	// 처리 결과를 뷰에 보냄
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String viewTitle = null;
		CommandAction com = null;
		try {
			String command = request.getRequestURI();
			if (command.indexOf(request.getContextPath()) == 0)
				command = command.substring(request.getContextPath().length());

			com = (CommandAction) commandMap.get(command);
			viewTitle = com.requestPro(request, response);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		if (viewTitle == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		if (isJsonResponse(viewTitle)) {
			handleJsonResponse(viewTitle, response);
		} else {
			String[] viewTitleArr = viewTitle.split(",");
			request.setAttribute("cont", viewTitleArr[0]);
			request.setAttribute("titleCont", viewTitleArr[1]);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}

	private boolean isJsonResponse(String viewTitle) {
		return viewTitle.trim().startsWith("{") && viewTitle.trim().endsWith("}");
	}

	private void handleJsonResponse(String jsonResponse, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonResponse);
	}

}
