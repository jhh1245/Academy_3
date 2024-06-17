<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 회원 정보 수정 버튼 -->
<form action="memberUpdate.jsp" method="get">
    <input type="submit" value="회원 정보 수정">
</form>

<!-- 회원 탈퇴 버튼 -->
<form action="memberDelete.jsp" method="post" onsubmit="return confirm('정말로 탈퇴하시겠습니까?');">
    <input type="submit" value="회원 탈퇴">
</form>
</body>
</html>