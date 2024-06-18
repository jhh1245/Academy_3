<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// Scriptlet : Java Code 영역 
	
	// _jspService(request, response) 안에 코딩 (실제 실행되는 파일 (.metadata 폴더 내 깊숙히 있는 파일)) 
	// JSP 내장 객체 : JSP -> Servlet 변환시 내부에 존재하는 객체 
	// request / response 
	// session / application 
	// pageContext 
	// 
	
	
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
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style type="text/css">
	table {
		margin : auto;
		margin-top : 100px;
	}
</style>
</head>
<body>
<div id="box">
	 <table class="table table-hover">
	    <tr>
	       <th class="info">이름</th>
	       <td><%= name %></td>
	    </tr>
	    <tr>
	       <th>아이디</th>
	       <td><% out.print(id);  %></td>
	    </tr>
	    
	    <tr>
	       <th>비밀번호</th>
	       <td><%= pwd %></td>
	    </tr>
	    
	    <tr>
	       <th>성별</th>
	       <td><%= gender %></td>
	    </tr>
	    
	    <tr>
	       <th>혈액형</th>
	       <td><%= blood %></td>
	    </tr>
	    
	    <tr>
	       <th>취미</th>
	       <td><%= hobby_list %></td>
	    </tr>
	    
	    <tr>
	       <th>친구</th>
	       <td><%= friend_list %></td>
	    </tr>
	    
	    <tr>
	       <th>자기소개</th>
	       <td><%= profile %></td>
	    </tr>
	    
	    <tr>
	    	<td colspan="2" align="center">
	    		<button class="btn btn-primary" onclick="location.href='input.html'">다시하기</button>
	    	</td>
	    </tr>
	 </table>
</div>

</body>
</html>