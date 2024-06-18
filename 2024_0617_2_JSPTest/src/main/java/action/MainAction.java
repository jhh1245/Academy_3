package action;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class MainAction
 */
@WebServlet("/main.do")
public class MainAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Command Pattern 
		// Command를 전달해서 처리내용을 다양하게 할 수 있다. 
		
		// main.do
		// main.do?menu=company
		// main.do?menu=product
		// main.do?menu=customer
		
		String menu = request.getParameter("menu");
		if(menu == null) menu = "company";
		
		String forward_page = "";
		switch(menu) {
		case "company" : forward_page = "main-company.jsp"; break;
		case "product" : forward_page = "main-product.jsp"; break;
		case "costomer" : forward_page = "main-costomer.jsp"; break;
		}
		// Dispatcher
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response);
		
	}

}
