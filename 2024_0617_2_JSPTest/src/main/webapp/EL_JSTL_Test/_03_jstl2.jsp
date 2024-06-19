<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- JSTL Core -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach var="i" begin="1" end="5" step="1">   <!--  이건 jstl이다.  --> 
	<!--  jsp는 자기만의 공간에 저장한다. pageContext인데 여기는 EL만 쓸 수 있으니까 pageScope라고 해야한다. -->
	
	<!--  EL 표현식을 쓰는 변수는 4개 영역중 1개에 있다.  -->
	<!--   ${ pageScope.i } 안써도 된다. 안쓰면 pageScpoe 부터 찾는다.-->
	<c:if test="${i%2==1 }">
		<font color='red'>${ i } : 안녕</font><br>
	</c:if>
	
	<c:if test="${i%2==0 }">
		<font color='blue'>${ i } : 안녕</font><br>
	</c:if>
</c:forEach>
</body>
</html>