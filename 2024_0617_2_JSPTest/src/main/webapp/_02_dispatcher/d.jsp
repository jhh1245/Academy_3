<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<hr>Java+HTML<hr>
<%  // 자바코드
	for(int i = 0; i < 5; i++){
%>
	<!-- html 코드 -->
	내가 d.jsp야<br>
<%
	}
%>

<hr>Tag 언어(Tag Library)<hr>
<c:forEach begin="1" end="5">
	나 d.jsp야<br>
</c:forEach>
</body>
</html>