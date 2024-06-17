<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style type="text/css">


</style>

<script type="text/javascript">
$(document).ready(function(){});

</script>
</head>
<body>
 	<form action="loginProcess2.jsp" method="post">
		<div>
			<span class="item">아이디</span>
			<input type="text" id="m_id" name="m_id" />
			<span id="idValid"></span>
		</div>
		<div>
			<span class="item">암호</span>
			<input type="password" id="m_pw" name="m_pw"/>
			<span id="pwdValid"></span>
		</div>
		<input type="submit" id="regBtn" value="로그인">
		
		<p><a href="register.jsp">회원가입</a></p>
</form>
</body>
</html>