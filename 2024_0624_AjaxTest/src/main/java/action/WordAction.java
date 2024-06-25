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

@WebServlet("/word.do") // 단어의 뜻을 알려주는 서블릿 
public class WordAction extends HttpServlet {
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
		String word = request.getParameter("word").toUpperCase(); // 대문자로 바꿨다.
		
		String kor_word = "";
		switch(word) {
			case "ONE": kor_word = "하나"; break;
			case "TWO": kor_word = "둘"; break;
			case "THREE": kor_word = "셋"; break;
			case "FOUR": kor_word = "넷"; break;
			case "FIVE": kor_word = "다섯"; break;
			default : kor_word = "모름";
		}
		
		// 결과전송 
		response.setContentType("text/html; charset=utf-8;"); //text이고 html로 응답한다. 그래서 test_ajax2.html에서 dataType로 받는다. 
		PrintWriter out = response.getWriter();
		
		out.printf("[%s]는 [%s]라는 뜻입니다.", word, kor_word);
	}

}

