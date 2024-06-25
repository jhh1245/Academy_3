package servlet;

import java.io.IOException;

import dao.PostDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.PostVo;

/**
 * Servlet implementation class PostInsertAction
 */

@WebServlet("/post/insert.do")
public class PostInsertAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 0. 수신인코딩 설정을 해야한다
		request.setCharacterEncoding("utf-8");
		
		// 1. parameter받기 <- 전달이 되는 인자를 parameter라고 부름
		//query 방식이기에 &으로 구분한다
		String p_title = request.getParameter("p_title");
		String p_content = request.getParameter("p_content").replaceAll("\n", "<br>");
		String p_cate = request.getParameter("p_cate");
		int p_type  = Integer.parseInt(request.getParameter("p_type"));
		int m_idx = Integer.parseInt(request.getParameter("m_idx"));
		String m_name = request.getParameter("m_name");
		
		
		// 3. VisitVo를 포장한다
		PostVo vo = new PostVo(p_title,p_content,p_cate,p_type, m_idx, m_name);
		
		// 4. DB insert
		int res = PostDao.getInstance().insert(vo);
		
		// 5. 목록보기로 이동
		response.sendRedirect("list.do");

	}

}

