package action;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HiAction
 */
@WebServlet("/hi.do")
public class HiAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response
			) throws ServletException, IOException {
		// http://localhost:8080/2024_0614_ServletEx2_3_later/hi.do?nation=kor&display=20
		                                                          //파라미터 이름이 nation, display이다. 
		
		// Client 측에서 들어온 정보 처리(request) : Query 통해서 전달된 정보는 String 
		String nation = request.getParameter("nation");
		String display = request.getParameter("display"); // 안들어오거나, 오타나면 null
		
		if(nation == null) {nation = "kor";	}
		
		String nation_name = "";
		String greeting = "";
		
		switch(nation) {
		case "kor" : nation_name = "한국"; greeting="안녕하세요"; break;
		case "eng" : nation_name = "미국"; greeting="Hi~~"; break;
		case "fra" : nation_name = "프랑스"; greeting="봉쥬르"; break;
		default : nation_name="어떤나라?"; greeting="인사말을 모름";		
		}
		
		String msg = String.format("<h3>[%s]어 인사말은 [%s]입니다.</h3>", nation_name, greeting);
		
		// 응답처리
		response.setContentType("text/html; charset=utf-8;");
		
		// 응답처리객체 얻어온다.
		PrintWriter out = response.getWriter();
		
		out.print("<html>");
		out.print("<head><title></title></head>");
		out.print("<body>");
		out.print(msg);
		
		out.print("<a href='hi.html'>다시하기</a>");
		
		out.print("</body>");
		out.print("</html>");
		
	}

}
