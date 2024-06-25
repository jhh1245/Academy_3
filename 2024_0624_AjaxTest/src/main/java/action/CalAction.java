package action;


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordAction
 */

@WebServlet("/calc.do") // 단어의 뜻을 알려주는 서블릿 
public class CalAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// /word.do?word=one
		// 0. 수신인코딩 설정 
		request.setCharacterEncoding("utf-8");
		
		// 1. parameter 받기
		int su1 = Integer.parseInt(request.getParameter("su1"));
		int su2 = Integer.parseInt(request.getParameter("su2"));
		
		int plus = su1 + su2;
		int minus = su1 - su2;
		int multiply = su1 * su2;
		int divide = su1 / su2;
		
		
		
		// 결과전송 
		response.setContentType("application/json; charset=utf-8;"); //text이고 html로 응답한다. 그래서 test_ajax2.html에서 dataType로 받는다. 
		PrintWriter out = response.getWriter();
		
		// JSON Data 생성 전송 : {"result":true} 반드시 {"xxx":xxx} 형식으로 
		String json = String.format("{\"plus\":%d,\"minus\":%d, \"multiply\":%d, \"divide\":%d}", 
				plus, minus, multiply, divide);
		out.print(json);
	}

}

