<%@page import="action.PersonVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	PersonVo p = new PersonVo("홍길동", 30, "서울 관악 남부순환로");
	
	pageContext.setAttribute("p", p);
	
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
		p's info
	<hr>
	<!-- 객체 내의 속성 표현 방법 : .(dot) -->
	이름 : ${ pageScope.p.name }<br> <!-- p.getName() call -->
	나이 : ${ p.age } <!-- 스코프 생략 가능 -->
	<!-- square bracket [] -->
	주소: ${ p['addr'] }<br>
</body>
</html>