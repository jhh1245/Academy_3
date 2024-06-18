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
   	   EL 표현값 Scope 영역 중에 있어야 표현가능 
   */
   
   
    // 날짜 출력
   	Date now = new Date();
	pageContext.setAttribute("now", now); // key : "now", value : 위에 설정한 Date
	
	int money = 1234500000;
	request.setAttribute("money", money);
	
	//  01234567890123456789012 <- index    
	String str_date = "2024-06-18 16:07:50.001";
	pageContext.setAttribute("str_date", str_date);
	
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
오늘 날짜 : <%= now %> <br>
오늘 날짜 : <fmt:formatDate value="${ pageScope.now }"/> <br>
오늘 날짜 : <fmt:formatDate  pattern="YYYY년MM월dd일"  value="${ pageScope.now }"/> <br>

오늘 날짜(String) : ${fn:substring(str_date,0,10) }<br>
오늘 날짜(String) : ${fn:length(str_date) }<br>

<fmt:setLocale value="ko_kr"/>
내가소유한 현금(원화) : <fmt:formatNumber type="currency" value="${ money }"/> <br>

<fmt:setLocale value="ja_jp"/>
내가소유한 현금(엔화) : <fmt:formatNumber type="currency" value="${ money }"/> <br>

<fmt:setLocale value="en_us"/>
내가소유한 현금(달러) : <fmt:formatNumber type="currency" value="${ money }"/>


</body>
</html>