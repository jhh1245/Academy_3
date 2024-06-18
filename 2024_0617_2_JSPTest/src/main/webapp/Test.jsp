

<!-- JSP 헤더 -->
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>

<%! // <%!는 선언부를 의미함  
	// Java Code를 작성할 수 있는 영역
	// JSP는 HTML이 JAVA를 감싸고 있다. 
	int n = 10;
	Calendar now = Calendar.getInstance();
%>


<% 
	//Scriptlet : _jspService()내에 기록
	n = 100;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>난 Test.jsp야 이건 응답하는 코드야</h3>
	<p>n = <%=n %></p>
</body>
</html>