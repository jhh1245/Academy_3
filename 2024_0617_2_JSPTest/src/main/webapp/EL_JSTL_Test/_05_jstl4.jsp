<%@page import="action.PersonVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	List<PersonVo> p_list = new ArrayList<PersonVo>();
	p_list.add(new PersonVo("일길동", 31, "서울 관악 신림1동"));
	p_list.add(new PersonVo("이길동", 32, "서울 관악 신림2동"));
	p_list.add(new PersonVo("삼길동", 33, "서울 관악 신림3동"));
	p_list.add(new PersonVo("사길동", 34, "서울 관악 신림4동"));
	p_list.add(new PersonVo("오길동", 35, "서울 관악 신림5동")); 
	// 이 내용을 jstl로 출력하려면 scope 어딘가에는 넣어놓아야 jstl을 쓸 수 있다.
	
	// request binding
	request.setAttribute("p_list", p_list);
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
	<tr>
		<th>순번</th>
		<th>이름</th>
		<th>나이</th>
		<th>주소</th>
	</tr>
	
	<!-- table data -->
	<!-- for(PersonVo p : p_list)와 동일 -->
	<c:forEach var="p" items="${p_list}" varStatus="i"> <!-- requestScope 영역이름 생략. request영역에 바인딩되어있다. -->
		<tr>
			<td>${i.index }</td>
			<td>${p.name }</td>
			<td>${p.age }</td>
			<td>${p.addr }</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>