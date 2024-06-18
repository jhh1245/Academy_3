<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
     /*
         EL(Expresion Language): 표현언어
         1.JSP에 값을 표현하는 언어
         2.형식: ${  변수/수식/연산자  }
         3.변수: scope내에 저장된 변수만 사용가능하다
                 scope(pageScope < requestScope < sessionScope < applicationScope )
                 parameter변수(param) : main.do?menu=company
                                        ${ param.menu }  
     */
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<hr>
   EL 산술연산자
<hr>
<!-- HTML주석 : JSP->서블릿변환시 그대로 넘어간다 -->
<%-- JSP주석  : JSP->서블릿변환시 안넘어간다
     EL표현형식 : ${ 값 }  
--%>
\${ 3 + 2 }  => ${ 3 + 2 } <br>
\${ 3 - 2 }  => ${ 3 - 2 } <br>
\${ 3 * 2 }  => ${ 3 * 2 } <br>
\${ 3 / 2 }  => ${ 3 / 2 } <%-- or ${ 3 div 2 } --%><br> 
\${ 3 % 2 }  => ${ 3 % 2 } <%-- or ${ 3 mod 2 } --%> <br>

<hr>
  EL 관계연산자
<hr>
\${ 3 >  2 } => ${ 3 >  2 } or ${ 3 gt 2 } <br>  <!-- gt : greater than  -->
\${ 3 >= 2 } => ${ 3 >= 2 } or ${ 3 ge 2 } <br>  <!-- ge : greater equal -->

\${ 3 <  2 } => ${ 3 <  2 } or ${ 3 lt 2 } <br>  <!-- lt : less than  -->
\${ 3 <= 2 } => ${ 3 <= 2 } or ${ 3 le 2 } <br>  <!-- le : less equal -->

\${ 3 == 2 } => ${ 3 == 2 } or ${ 3 eq 2 } <br>  <!-- eq : equal -->
\${ 3 != 2 } => ${ 3 != 2 } <%-- or ${ 3 ne 2 } --%> <br>  <!-- ne : not equal -->

<hr>
  EL 3항연산자 : (조건) ? 참값 : 거짓값 
<hr>
<!--  /01_el1.jsp
      /01_el1.jsp?msg= 

-->
메시지 : ${ (empty param.msg) ? 'No Message' : param.msg } <br>

<hr>
  EL 논리연산자
<hr>
\${ true && true  } => ${ true && true  } or ${ true and true } <br>
\${ true || false } => ${ true || false } or ${ true or false } <br>
\${ not true } => ${ not true } <br>



</body>
</html>