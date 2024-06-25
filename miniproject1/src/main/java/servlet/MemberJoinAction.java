package servlet;

import java.io.IOException;

import dao.BlogDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.MemberVo;

/**
 * Servlet implementation class MemberJoinAction
 */
@WebServlet("/register.do")
public class MemberJoinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 받은 정보 UTF-8로 인코딩
		request.setCharacterEncoding("UTF-8");
		
		String m_name = request.getParameter("m_name");
		String m_id = request.getParameter("m_id");
		String m_pw = request.getParameter("m_pw");
		String m_email = request.getParameter("m_email");
		String m_intro = request.getParameter("m_intro");
		int m_type = 1;
		
		// DB에서 회원 정보를 등록
        BlogDao dao = BlogDao.getinstance();
        MemberVo mv = new MemberVo(m_name, m_id, m_pw, m_email, m_intro, m_type);
        dao.memberInsert(mv);
		
        response.sendRedirect("login.jsp");
        
	}

}
