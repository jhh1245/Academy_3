<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
     width: 500px;
     margin: auto;
     margin-top: 200px;
  }
  
  input[type='button']{
     width: 100px;
     /* background: pink; */
  }

</style>

</head>
<body>
<form>  
   <div id="box">
		<div class="panel panel-primary">
			<div class="panel-heading"><h3>로그인</h3></div>
			<div class="panel-body">
			    <table class="table">
			        <tr>
			            <th>아이디</th>
			            <td><input class="form-control"  name="mem_id"></td>
			        </tr>
			        
			        <tr>
			            <th>비밀번호</th>
			            <td><input class="form-control" type="password" name="mem_pwd"></td>
			        </tr>
			        
			        <tr>
			            <td colspan="2" align="center">
			               <input class="btn btn-success"  type="button"  value="메인화면"
			                      onclick="location.href='list.do'" >
			               <input class="btn btn-primary"  type="button"  value="로그인">
			            </td>
			        </tr>
			    </table>
			</div>
		</div>
	</div>
</form>	
</body>
</html>