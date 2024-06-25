<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">
	<title>회원 가입 임시 페이지입니다.</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <h2>회원가입</h2>

            <form action="register.do" method="post">
                <div class="mb-3">
                    닉네임<input type="text" class="form-control" name="m_name" placeholder="닉네임" required />
                </div>
                <div class="mb-3">
                    아이디<input type="text" class="form-control" name="m_id" placeholder="아이디" required />
                </div>
                <div class="mb-3">
                    비밀번호<input type="password" class="form-control" name="m_pw" placeholder="비밀번호" required />
                </div>
                <div class="mb-3">
                    이메일<input type="email" class="form-control" name="m_email" placeholder="이메일" required />
                </div>
                <div class="mb-3">
                    소개글<input type="text" class="form-control" name="m_intro" placeholder="소개글" required />
                </div>
                <input class="btn btn-success" type="submit" value="회원가입" />
            </form>
            <a href="login.jsp" class="btn btn-info btn-md" >취소</a>

        </div>
        <div class="col-sm-4"></div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-U1DAWAznBHeqEIlVSCgzq+c9gqGAJn5c/t99JyeKa9xxaYpSvHU5awsuZVVFIhvj" crossorigin="anonymous"></script>
</body>
</html>