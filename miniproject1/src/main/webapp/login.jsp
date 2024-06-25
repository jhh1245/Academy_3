<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
    <title>로그인을 해주세요</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <h2>로그인</h2>

            <%
                Object loginObj = request.getAttribute("loginError");
                if ( loginObj != null ) {
                    String errorMessage = ""+loginObj;
            %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <strong>아이디 및 비밀번호가 틀리셨습니다.</strong><br><%=errorMessage %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <% } %>

            <form action="login.do" method="post">
                <div class="mb-3">
                    <input type="text" class="form-control" name="m_id" placeholder="아이디" required />
                </div>
                <div class="mb-3">
                    <input type="password" class="form-control" name="m_pw" placeholder="비밀번호" required />
                </div>
                <div class="mb-3 form-check">
                    <input name="remember" type="checkbox" class="form-check-input" id="exampleCheck1">
                    <label class="form-check-label" for="exampleCheck1">Remember</label>
                </div>
                <input class="btn btn-success" type="submit" value="로그인" />
                <a href="findpwd.jsp" class="btn btn-info btn-md" >비밀번호 찾기</a>
                <a href="joinmember.jsp" class="btn btn-info btn-md" >회원가입</a>
                <a href="JSP/main/mainpage2(login_before).jsp" class="btn btn-warning btn-md" >취소</a>
            </form>

        </div>
        <div class="col-sm-4"></div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>