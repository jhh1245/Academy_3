<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"  %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--  Bootstrap  3.x  -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
   #box{
      width: 1000px;
      margin: auto;
      margin-top: 50px;
   }
   
   #title{
      text-align: center;
      font-weight: bolod;
      font-size: 32px;
      color: green;
      text-shadow: 1px 1px 1px black;
   }
   
   #empty_msg{
      text-align: center;
      color: red;
      margin-top: 150px;
      font-size: 26px;
   }
   
   th{
      background: #333333 !important;
      color: white;
   }
   
   td{
      vertical-align: middle !important;
   }
   
</style>

</head>
<body>

	<div id="box">
	     <h1 id="title">::::회원목록::::</h1> 
	     <div style="text-align: right;">
	         <input class="btn btn-primary" type="button"  value="로그인" 
	                onclick="location.href='login_form.do'">
	     </div>
	     
	     <div style="margin-top: 50px; margin-bottom: 5px;">
	        <input class="btn btn-primary" type="button" value="회원가입"  
	               onclick="location.href='insert_form.do'">
	     </div>
	     
	     <table class="table">
	       
	        <!-- 테이블 타이틀 -->
	        <tr>
	           <th>회원번호</th>
	           <th>회원명</th>
	           <th>아이디</th>
	           <th>비밀번호</th>
	           <th>우편번호</th>
	           <th>주소</th>
	           <th>아이피</th>
	           <th>가입일자</th>
	           <th>회원등급</th>
	           <th>편집</th>
	        </tr>
	        
	        <!-- Data출력  -->
	        <!-- for(MemberVo vo : list )  -->
	        <c:forEach var="vo"  items="${ list }">
	           <tr>
	              <td>${ vo.mem_idx }</td>
	              <td>${ vo.mem_name }</td>
	              <td>${ vo.mem_id }</td>
	              <td>${ vo.mem_pwd }</td>
	              <td>${ vo.mem_zipcode }</td>
	              <td>${ vo.mem_addr }</td>
	              <td>${ vo.mem_ip }</td>
	              <td>${ fn:substring(vo.mem_regdate,0,10) }</td>
	              <td>${ vo.mem_grade }</td>
	              <td>
	                  <input class="btn  btn-success"  type="button"  value="수정">
	                  <input class="btn  btn-danger"   type="button"  value="삭제">
	              </td>
	           </tr>
	        </c:forEach>
	        
	     </table>
	     
	     <!-- Data가 없는경우  -->
	     <c:if test="${ empty requestScope.list }">
	        <div id="empty_msg">등록된 회원정보가 없습니다</div>
	     </c:if>
	     
	     
	     
	     
	     
	     
	     
	</div>


</body>
</html>






