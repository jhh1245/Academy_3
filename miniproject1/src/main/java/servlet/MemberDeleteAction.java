package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.MemberVo;

import java.io.IOException;

import dao.BlogDao;

/**
 * Servlet implementation class MemberDeleteAction
 */
@WebServlet("/JSP/mypage/member_delete.do")
public class MemberDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		MemberVo mv = (MemberVo) session.getAttribute("member");
		int m_idx = mv.getM_idx();
		
		BlogDao.getinstance().memberDelete(m_idx);
		
		response.sendRedirect("../main/mainpage2(login_before).jsp");
	}

}