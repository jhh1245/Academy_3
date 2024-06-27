<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	제목 : ${ title }
	<hr>
	<img src="upload/${ filename }" width="200">
	<!--  현재경로가 webapp니까 그 안에 upload 폴더를 의미한다. -->
</body>
</html>