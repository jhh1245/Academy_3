package action;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalcAction
 */
@jakarta.servlet.annotation.WebServlet("/cal.do")
public class CalcAction extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

   
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Client 측에서 들어온 정보 처리(request) : Query 통해서 전달된 정보는 String 
		String su1 = request.getParameter("su1");
		String su2 = request.getParameter("su2"); // 안들어오거나, 오타나면 null
		
		int s1 = Integer.parseInt(su1);
		int s2 = Integer.parseInt(su2);
		
		int result1 = s1 + s2;
		int result2 = s1 - s2;
		int result3 = s1 * s2;
		int result4 = s1 / s2;
		
		String msg = String.format("%d+%d=%d<br>", s1, s2, result1);
		String msg2 = String.format("%d-%d=%d<br>", s1, s2, result2);
		String msg3 = String.format("%d*%d=%d<br>", s1, s2, result3);
		String msg4 = String.format("%d/%d=%d<br>", s1, s2, result4);
		
		// 응답처리
		response.setContentType("text/html; charset=utf-8;");
				
		// 응답처리객체 얻어온다.
		PrintWriter out = response.getWriter();
		
		
		
		out.print("<html>");
		out.print("<head><title></title></head>");
		out.print("<body>");
		out.print("<form action='cal.do' method='post'>");
		out.print("수1 : <input type=\"text\" name=\"su1\"><br>");
		out.print("수2 : <input type=\"text\" name=\"su2\"><br>");  
		out.print("<input type=\"button\" value=\"계산해줘\" onclick=\"send(this.form);\">");
		out.print("</form>");  
		out.print(msg);
		out.print(msg2);
		out.print(msg3);
		out.print(msg4);
		
		out.print("<a href='calc.html'>계산하기</a>");
		
		out.print("</body>");
		out.print("</html>");
		
		
		
	}

}
