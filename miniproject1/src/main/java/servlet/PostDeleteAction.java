package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import dao.PostDao;

/**
 * Servlet implementation class PostDeleteAction
 */

@WebServlet("/post/delete.do")
public class PostDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int p_idx = Integer.parseInt(request.getParameter("p_idx"));
		String no = request.getParameter("no");
		
		int res = PostDao.getInstance().delete(p_idx);
		
		response.sendRedirect("list.do#p_"+no);
		

	}

}
