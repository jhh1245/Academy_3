package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LogoutAction
 */
@WebServlet("/logout.do")
public class LogoutAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 세션을 가져오기, 세션이 없으면 null을 반환
        HttpSession session = request.getSession(false);

        if (session != null) {
            // 세션을 무효화
            session.invalidate();
        }
        
        // 쿠키 종료
        Cookie cookie = new Cookie("user", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        // 로그아웃 후 로그인 페이지 또는 홈 페이지로 리다이렉트
        response.sendRedirect("JSP/main/mainpage2(login_before).jsp");
	}

}
