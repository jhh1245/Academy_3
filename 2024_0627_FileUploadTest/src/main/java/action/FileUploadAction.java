package action;



import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class FileUploadAction
 */

@WebServlet("/upload.do") // 여기로 들어오면 
public class FileUploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// /upload.do?title=제목&photo=a.jpg
		
		//String saveDir = "d:\\dev\\upload"; //upload위치
		String webPath = "/upload/"; // 웹(URL)경로
		
		//현재 웹어플리케이션의 전역관리객체(상대경로->절대경로)
		ServletContext application = request.getServletContext();
		
		//                웹(상대)경로->절대경로 구하기
		String saveDir = application.getRealPath(webPath);
		System.out.println(saveDir);
		
		int maxSize = 1024 * 1024 * 100;  // 1KB * 1KB = 1MB -> 1MB * 100 => 100MB 
		// 1개 파일 기준 
		
		// FileUpload객체 => MultipartRequest 클래스가 파일과, 파라미터 ("제목")등 다 관리함 
		MultipartRequest mr = new MultipartRequest(
				request, 			// request를 MultipartRequest에 위임한다. 파라미터 처리 열할을 위임한다. 
				saveDir,			// 저장위치 
				maxSize,			// 최대업로드 크기 
				"utf-8",			// 수신인코딩 
				new DefaultFileRenamePolicy() // 파일 이름이 동일할 경우 다른 이름으로 변경
				);

		
		
		//업로드 파일명 
		String filename = "no_file";
		
		// mr에 의해서 업로드된 파일정보를 관리한다. File f가 파일명, 경로도 다 알고 있다.   
		File f = mr.getFile("photo");
		
		if(f != null) { // 업로드 파일 존재하면 
			filename = f.getName();
			// f.getAbsolutePath();
		}
		
		String title = mr.getParameter("title"); //request.getParmeter()가 아니고 mr로 받는다. 위임했으니까 
		//System.out.println(title);
		
		
		//request binding 
		request.setAttribute("title", title);
		request.setAttribute("filename", filename);
		
		
		// Dispatcher 형식으로 호출 
		String forward_page = "result1.jsp";
		RequestDispatcher disp = request.getRequestDispatcher(forward_page);
		disp.forward(request, response); // forwarding 
	}

}
