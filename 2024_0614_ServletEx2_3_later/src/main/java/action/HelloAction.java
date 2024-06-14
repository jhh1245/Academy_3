package action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class HelloAction
 */
@WebServlet("/hello.do")
public class HelloAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest request,  // 요청 처리 객체
			HttpServletResponse response // 응답 처리 객체 
			) throws ServletException, IOException {
		// System.out.println("--service()--");
		
		// request : client 요청정보 포함, client 정보 포함 -> ip 구할 수 있다
		String ip = request.getRemoteAddr(); // 요청자 IP 
		System.out.printf("[%s]님이 요청하셨습니다.", ip);
		
		// 응답처리 : [192.168.219.164]님 환영합니다. 
		// 동적 HTML 생성 전달 
		
		// 응답할 컨텐츠에 대한 정보를 응답 헤더를 통해서 전달한다. 
		// mime-type : text/html, text/xml, application/json, image/jpg  
		response.setContentType("text/html; charset=utf-8;"); // 브라우저에게 지금 넘어갈건 html이야 알려준다.
		
		
		// 출력 스트림(Output Stream가지고 있다)을 통해서 전송객체를 얻어온다. 
		PrintWriter out = response.getWriter(); // 소캣 포함
		out.print("<html>");
		out.print("<head><title>처음실행서블릿</title></head>");
		out.print("<body>");
		out.printf("<h3>[%s]님 환영합니다.</h3>", ip);
		out.print("</body></html>"); // 상대방 브라우저로 넘어간다. 
	}

}
