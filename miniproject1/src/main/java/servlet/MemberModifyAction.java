package servlet;

import java.io.IOException;

import dao.BlogDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vo.MemberVo;

/**
 * Servlet implementation class DeptListAction
 */
@WebServlet("/JSP/mypage/member_modify.do")
public class MemberModifyAction extends HttpServlet {
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
		
        // 수정된 정보 가져오기
		String m_name = request.getParameter("m_name");
		String m_email = request.getParameter("m_email");
		String m_intro = request.getParameter("m_intro");
		
		// DB 등록
		BlogDao dao = BlogDao.getinstance();
        MemberVo mv = new MemberVo(m_idx, m_name, m_email, m_intro);
        dao.memberUpdate(mv);
		
		// 수정된 정보를 다시 세션에 저장
        session.setAttribute("m_name", m_name);
        session.setAttribute("m_email", m_email);
        session.setAttribute("m_intro", m_intro);

        response.sendRedirect("../main/mainpage(login_after).jsp");
	}

}