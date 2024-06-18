<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	System.out.println("--a.jsp 들렸다감--");
	// 재 요청에 대한 응답 
	response.sendRedirect("b.jsp"); // 최초에는 a.jsp -> b.jsp로 다시 요청하라고 user에게 전달(바로 b.jsp로 전달하는건 아님) -> user는 b.jsp를 받는다. 
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
