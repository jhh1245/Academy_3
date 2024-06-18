package action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class MemberRegisterAction
 */

/* url-pattern 문제발생  : LifeCycleException발생 
   1.서블릿 매핑에서 유효하지 않은 <url-pattern> : /xxx.do
   2.<url-patter> 중복되는 경우
 */

@WebServlet("/member_register.do")
public class MemberRegisterAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(
			HttpServletRequest  request, 
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        // /member_register.do?name=홍길동&id=hong&pwd=a1234&gender=남자&
		//                     hobby=독서&hobby=영화&friend=&friend=&friend=&blood=A&profile=.
		
		//0.수신인코딩 설정(송신측 인코딩과 동일하게 설정)
		request.setCharacterEncoding("utf-8");
		
		//1.parameter 받기
		String name		=	request.getParameter("name");
		String id		=	request.getParameter("id");
		String pwd		=	request.getParameter("pwd");
		String gender	=	request.getParameter("gender");
		String blood	=	request.getParameter("blood");
		String profile	=	request.getParameter("profile");
		
		// parameter이름이 동일한 이름으로 들어온 경우에는 배열로 받는다
		// checkbox : 체크된 목록만 넘어온다(체크항목이 없으면 null)
		String [] hobby_array	=	request.getParameterValues("hobby");
		//                          hobby=독서&hobby=영화&hobby=낚시
		//                              0         1       2
		//String [] hobby_array	=	{ "독서" , "영화" , "낚시"};	
		//취미목록
		String hobby_list="취미없음";
		if(hobby_array != null) {
			
			StringBuffer sb = new StringBuffer();
			for(String hobby : hobby_array) {
				//hobby_list += hobby + " ";  // hobby_list = "독서 " + "영화 " + "낚시 "
				sb.append(hobby);
				sb.append(" ");
			}
			
			hobby_list = sb.toString().trim();
		}
//     ----------------------------------------------------------------------		
		
		// input : 모든값이 다 넘어온다
		String [] friend_array	=	request.getParameterValues("friend");
		//                          friend=&friend=&friend=
		//String [] friend_array = { "개똥이",  "삼식이",  "" };
		String friend_list = "";
		
		StringBuffer sb1 = new StringBuffer(); //  StringBuffer(느리다)  vs StringBuilder(빠르다)
		for(String friend : friend_array) {
			
			sb1.append(friend);
			sb1.append(" ");
		}
				
		friend_list = sb1.toString().trim();
		
		if(friend_list.isEmpty())
			friend_list = "친구없음";
		
		
		//응답처리
		response.setContentType("text/html; charset=utf-8;");
		
		PrintWriter out = response.getWriter();
		
		
		out.print("<html>");
		out.print("<body>");
		out.print("<table border='1'>");
		out.printf("<tr><th>이름</th><td>%s</td></tr>", name);
		out.printf("<tr><th>아이디</th><td>%s</td></tr>", id);
		out.printf("<tr><th>비밀번호</th><td>%s</td></tr>", pwd);
		out.printf("<tr><th>성별</th><td>%s</td></tr>", gender);
		out.printf("<tr><th>혈액형</th><td>%s</td></tr>", blood);
		out.printf("<tr><th>취미</th><td>%s</td></tr>", hobby_list);
		out.printf("<tr><th>친구</th><td>%s</td></tr>", friend_list);
		out.printf("<tr><th>자기소개</th><td>%s</td></tr>", profile);
		out.printf("<tr><td colspan='2'><a href='input.html'>다시하기</a></td></tr>");
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");
		
		
		
		
		
		
		
	}

}












