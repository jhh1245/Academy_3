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
		
		<!-- Submenu -->
		<div id="aside">
			<!-- submenu-company.jsp include -->
			<%@ include file="menu/submenu-product.jsp"%>
		</div>
		
		
		<div id="content">
		<!-- 상품소개 -->
			<!-- menu == 'top100' -->
			<c:if test="${  (empty param.menu) or param.menu == 'top100'}">
				<%@include file="content/product/top100.jsp" %>
			</c:if>
			
			<!-- menu == 'category' -->
			<c:if test="${ param.menu == 'category'}">
				<%@include file="content/product/category.jsp" %>
			</c:if>
		</div>
		
		
		<div id="footer">
			<!-- footer.jsp include -->
			<%@ include file="footer/footer.jsp"%>
		</div>
	</div>
</body>
</html>