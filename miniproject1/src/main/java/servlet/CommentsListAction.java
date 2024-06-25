package servlet;

import java.io.IOException;
import java.util.List;

import dao.PostDao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.CommentVo;

/**
 * Servlet implementation class PostAction
 */

@WebServlet("/comments/list.do")
public class CommentsListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	        int p_idx = Integer.parseInt(request.getParameter("p_idx"));
	        
	        System.out.println(p_idx);
	        
	        List<CommentVo> c_list = PostDao.getInstance().selectCommentByPidx(p_idx);
			
			request.setAttribute("c_list", c_list);

			//Dispatcher형식으로 호출
			String forward_page = "comments_list.jsp";
			RequestDispatcher disp = request.getRequestDispatcher(forward_page);
			disp.forward(request, response);
	                    
	      
	    }

}
