package action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.DeptDao;
import db.vo.DeptVo;
import db.vo.SawonVo;

/**
 * Servlet implementation class SawonListAction
 */
@WebServlet("/sawon/salist.do") // 브라우저 주소로 사용자가 여기로 들어왔다.
public class SawonListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		// 부서 데이터를 가져온다. 
		List<SawonVo> list = DeptDao.getInstance().selectSawonList(); 
		
		// request binding 
		request.setAttribute("list", list);
		// *** list라는 이름으로 위의 ArrayList를 저장한다. 
		
		// Dispatcher 형식으로 호출 
		String forward_page = "sawon_list.jsp"; //이 페이지에게 응답해줘 하고 넘겼다.
		
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		// clent가 주소를 가지고 호출하면 service()가 호출 (비지니스로직 = dao에게 데이터 가져와 시키는 등의 작업)
		
		disp.forward(request, response); // dept_list를 부른다.
		// *** 여기서 list가 request에 담겨서 dept_list.jsp에 넘긴다. 
		// 이 서블릿과 dept_list.jsp 서블릿이 공유되는 공간 request이다. 
	}

}
