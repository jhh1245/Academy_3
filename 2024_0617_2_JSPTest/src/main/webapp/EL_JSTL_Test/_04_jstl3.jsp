<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	// 배열
	String [] sido_array={"서울", "경기", "인천", "대전", "강원"};
	request.setAttribute("sido_array", sido_array);  // request에 sido_array를 넣었다.

	// ArrayList
	List<String> fruit_list = new ArrayList<String>();
	fruit_list.add("사과");
	fruit_list.add("참외");
	fruit_list.add("수박");
	fruit_list.add("딸기");
	request.setAttribute("fruit_list", fruit_list);
 	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<hr>
	시도 목록
	<hr>
	<ul>
		<!-- 		items : 배열/컬렉션/Map -->
		<c:forEach var="sido" items="${ requestScope.sido_array }"> <!-- for(String sido: sido_array) -->  
		<!-- EL 표현식을 쓴다. request에 sido_array를 넣었다.  -->
		<!-- el 표현법으로는 requestScope다. -->
			<li>${pageScope.sido}</li> 
			<!-- forEach에서 만든 변수는(items) pageScope에 있다.  -->
		</c:forEach>
	</ul>
	
	<hr>
	과일목록
	<hr>
	<ul>
		<!-- JSTL 내 변수 표현(EL) 공백 띄우지 말 것!! -->
		<c:forEach var="fruit" items="${ fruit_list }" varStatus="i"> <!-- varStatus : index(첨자), count(반복횟수) -->
			<li>${ i.count } 번째 : ${fruit}(${i.index })</li>
		</c:forEach>	
	</ul>
</body>
</html>