package action.member;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberLoginAction
 */

@WebServlet("/member/logout.do")
public class MemberLogoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 세션에서 user 삭제 
		request.getSession().removeAttribute("user"); // 이렇게 간추릴 수도 있다.
		
		//메인페이지로 이동
		response.sendRedirect("list.do");

	}

}