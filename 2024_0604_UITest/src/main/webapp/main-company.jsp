<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- CSS Link -->
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/main-menu.css">
<link rel="stylesheet" href="css/sub-menu.css">
<link rel="stylesheet" href="css/content.css">

</head>
<body>
	<div id="main-box">
		<div id="header">
			<!-- header.jsp include -->
			<%@ include file="header/header.jsp"%> <!-- jsp 문법 -->
		</div>
		
		<!-- Sub menu -->
		<div id="aside">
			<!-- submenu-company.jsp include -->
			<%@ include file="menu/submenu-company.jsp"%>
		</div>
		
		<!-- 내용 -->
		<div id="content">
			<!-- menu == 'introduce' --> <!--  submenu-company.jsp에서 -> a태그 menu 대로 써야된다. -->
			<c:if test="${ (empty param.menu) or param.menu == 'introduce'}"> <!-- 비어있으면 -->
				<%@include file="content/company/introduce.jsp" %>
			</c:if>
			
			
			<!-- menu == 'history' -->
			<c:if test="${ param.menu == 'history'}">
				<%@include file="content/company/history.jsp" %>
			</c:if>
			
			<!-- menu == 'location' -->
			<c:if test="${ param.menu == 'location'}">
				<%@include file="content/company/location.jsp" %>
			</c:if>

		</div>
		
		
		<div id="footer">
			<!-- footer.jsp include -->
			<%@ include file="footer/footer.jsp"%>
		</div>
	</div>
</body>
</html>