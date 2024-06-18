<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<!-- JSTL사용설정 -->
<!-- core library -->
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>

<!-- fmt library -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!-- fn library -->
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %> 
    
    
<%
   /*   
       JSTL(JSP Standard Tag Library) 
       : 명령을 tag형태로 만든 library
       
       1.core :  if/forEach/Choose/set/....
       2.fmt  :  날짜/숫자(통화)
       3.fn   :  String관련(substring/length/indexOf/replace)
   
       JSTL을 사용하려면.... 6/5일자 수업진행자료 참조
       
       JSTL내에서 사용되는 변수는 반드시 EL표현식으로 표현하게 되어있다
   
   */
   
   
   //날짜 출력
   Date now = new Date();


%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>