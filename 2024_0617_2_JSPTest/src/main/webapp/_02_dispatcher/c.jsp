<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.println("--c.jsp 다녀감 -> d.jsp로 forward--");
	RequestDispatcher disp = request.getRequestDispatcher("d.jsp");
	disp.forward(request, response);
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