package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import dao.PostDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PostAction
 */

@WebServlet("/post/scrap.do")
public class PostScrapAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	        int p_idx = Integer.parseInt(request.getParameter("p_idx"));
	        //int m_idx = Integer.parseInt(request.getParameter("m_idx")); // 회원 ID 넣기
	        int m_idx = 1;

	        PostDao.getInstance().isPostLikeOrScrap(m_idx, p_idx, 2);
	        
            // 업데이트된 좋아요 수 반환
            int cnt = PostDao.getInstance().getPostScrapCount(p_idx);
           
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"scraps\":" + cnt + "}");
            out.flush();
            
	      
	    }

}
