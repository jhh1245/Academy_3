package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.MemberVo;

/**
 * Servlet implementation class PostInsertFormAction
 */

@WebServlet("/post/insert_form.do")
public class PostInsertFormAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 세션에서 m_idx 가져오기
        HttpSession session = (HttpSession) request.getSession();
        MemberVo member = (MemberVo) session.getAttribute("member");
        int m_idx = member.getM_idx();
        String m_name = member.getM_name();
        
        request.setAttribute("m_idx", m_idx);
        request.setAttribute("m_name", m_name);
        
		//Dispatcher형식으로 호출
		String forward_page = "post_insert_form.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);

	}

}