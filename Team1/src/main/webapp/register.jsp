<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	div {padding: 5px;}
	.item {
			float:left;
			width:100px;
		}
	.warning {
		color:red;
		font-weight: bold;
	}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script type="text/javascript">
</script>
<body>
    <h1>회원 가입</h1>
    
    <!-- 회원 가입 폼 -->
    <form action="registerProcess.jsp" method="post">
	 	<div>
			<span class="item">아이디</span>
			<input type="text" id="m_id" name="m_id" required/>
			<span id="idValid"></span>
		</div>
		<div>
			<span class="item">이름</span>
			<input type="text" id="m_name"  name="m_name" required/>
			<span id="nameValid"></span>
		</div>
		<div>
			<span class="item">이메일</span>
			<input type="email" id="m_email" name="m_email" required/>
			<span id="nameValid"></span>
		</div>
		<div>
			<span class="item">암호</span>
			<input type="password" id="m_pw" name="m_pw" required/>
			<span id="m_pwValid"></span>
		</div>
	
		<input type="submit" id="regBtn" value="회원 가입">	
    </form>
</body>
</html>
