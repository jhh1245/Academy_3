package action.member;

import java.io.IOException;
import java.util.List;

import dao.MemberDao;
import db.vo.MemberVo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberListAction
 */

@WebServlet("/member/list.do") // 여기로 들어오면 
public class MemberListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 회원목록 가져오기
		List<MemberVo> list = MemberDao.getInstance().selectList();
		request.setAttribute("list", list); // 리퀘스트 바인딩 
		
		// Dispatcher 형식으로 호출 
		String forward_page = "member_list.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		// clent가 주소를 가지고 호출하면 service()가 호출 (비지니스로직 = dao에게 데이터 가져와 시키는 등의 작업)
		disp.forward(request, response); // dept_list를 부른다. 

		// 이 서블릿과 dept_list.jsp 서블릿이 공유되는 공간 request이다. 
	}

}

