<%@page import="util.Util"%>
<%@page import="vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="util" class="util.Util"></jsp:useBean>
<%
	if (!Util.isLogIn(request)) {
	   	response.sendRedirect("mainpage2(login_before).jsp");
   	} else {
		MemberVo mv = (MemberVo) session.getAttribute("member");
		int m_type = mv.getM_type();
		if (m_type != 2){
			response.sendRedirect("../main/mainpage(login_after).jsp");
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<!-- BootStrap 3.x -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function del(f){
		
		// 삭제 확인
		if(confirm("정말 삭제하시겠습니까?") == false) return;
		
		// 삭제 요청
		location.href = "member_delete.do?m_idx=" + f.m_idx.value;
		
	}
</script>
<style type="text/css">
   	#box{
       	width: 1200px;
       	margin: auto; /* 중앙정렬(Horizontal) */
       	margin-top: 20px;
   	}
   
   	.mycolor{
      	background: #333333;
   	   	color: white;
   	}
   
   	h3{
   		text-align: center;
   		font-size: 30px;
   		font-weight: bolder;
   	}
   
</style>
</head>
<body>
<h3>회원 정보</h3>
<div id="box">
	<table class="table">
		<tr class="mycolor">
			<th>회원번호</th>
			<th>닉네임</th>
			<th>아이디</th>
			<th>이메일</th>
			<th>소개글</th>
			<th>생성날짜</th>
			<th>수정날짜</th>
			<th>관리번호</th>
			<th>수정</th>
			<th>탈퇴<th>
		</tr>
		<tr>
		<c:forEach var="mv" items="${ list }">
			<tr>
				<form>
					<input type="hidden" name="m_idx" value="${ mv.m_idx }">
					<td>${ mv.m_idx }</td>
					<td>${ mv.m_name }</td>
					<td>${ mv.m_id }</td>
					<td>${ mv.m_email }</td>
					<td>${ mv.m_intro }</td>
					<td>${ mv.m_rdate }</td>
					<td>${ mv.m_mdate }</td>
					<td>${ mv.m_type }</td>
					<td><input class="btn btn-success" 	type="button" 	value="수정" 	onclick="regi(this.form)"></td>
					<td><input class="btn btn-danger" 	type="button" 	value="삭제" 	onclick="del(this.form)"></td>
				</form>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>
