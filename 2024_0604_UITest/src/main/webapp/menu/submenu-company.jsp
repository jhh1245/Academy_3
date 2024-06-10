<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="submenu">
	<!-- submenu는 main-company안에 있다. 
		여기서 a테그 의미는자기 자신을 호출했다. (main-company.jsp) -->
		
		<li><a href="main-company.jsp?menu=introduce">인사말</a></li>
		<li><a href="main-company.jsp?menu=history">연혁</a></li>
		<li><a href="main-company.jsp?menu=location">오시는길</a></li>
	</ul>
</body>
</html>