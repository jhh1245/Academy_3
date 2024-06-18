<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<style type="text/css">

	*{
		margin: 0;
		padding: 0;
	
	}

	/* 이미지파일 설정 start */
	img {
		width: 250px;
		height: 250px;
		float: left;
		margin-left	: 30px; 
	}
	/* 이미지파일 설정 end */

	
	#main-box {
		margin:auto;
		width: 1000px;
		height: 800px;
		background: gray;
	}

	#header {
		width:100%;
		height: 10%;
		background: yellow;
	}

	.write {
		float:right;
		margin: 10px;
	}
	
	.join {
		float: right;
		margin:10px; 
	}

	.logo {
	
		list-style-type: none;
		font-size: 50px;
		color: black;
	}


/* 드롭다운메뉴 start */
	li.mainmenu{
		float:left;
		width: 300px;
		list-style-type: none;
		margin-left: 20px;
		text-align: center;
	}
	
	li.mainmenu ul{
		margin: 0px;
		list-style-type: none;
		padding: 0px;
	}
	
	.mainmenu a{
 		background : white; 
		width: 300px;
		display: block; 
		color: black;
		text-decoration: none;
		padding: 10px;
		border-bottom: 1px solid #ffffff;
	}
	
	ul#dropdownmenu li a:hover {
		background-color: #aaaaaa;
	} 
	/* 드롭다운메뉴 end */

	/* 콘텐츠 start */
	#content {
		width:100%;
		min-height: 400px;
		height: auto; 
		float:left;
	}


	#content ul li {	
		list-style-type: none;
		float:left;
		margin: 10px;
		padding: 10px;
	}
	/* 콘텐츠 end */
</style>
<script type="text/javascript">
$(document).ready(function(){
	
	$("li.mainmenu ul").hide(); //하위메뉴 숨기기
	
	
	$("li.mainmenu").hover(function(){
		// 자신의 자식요소중에 ul요소 화면에 보이기
		//$(child,parents).show(1);
		$("ul",this).show(1);
		//$("ul",this).slideDown(500);
		//$("ul",this).fadeIn(1000);
	}, function(){
		$("ul",this).hide(1);
	});
	
	
});


</script>


</head>
<body>
	<div id="main-box">
		<div id="header">
		<!--header start -->
		<a class="logo" href="https://www.naver.com">로고</a>
		<a class="join"  href="register.jsp" >회원가입</a>
		<a class="join" href="login.jsp">로그인</a>
		<a class="write" href = "#">글쓰기</a> <!--  //글쓰기page 생성해야함 -->
		<a href="adminMemList.jsp">관리자페이지</a>
		<!--header end-->
		
		<!--Menu start  -->
		<ul id="dropdownmenu">
		<li class="mainmenu">
			<a href="#">카테고리</a>
			<ul>
				<li>
    				<a href="https://www.naver.com">태그명</a>
				</li>
				<li>
					<a href="https://www.naver.com">태그명</a>
				</li>			
				<li>
					<a href="https://www.naver.com">태그명</a>
				</li>
			</ul>
		</li>
		<li class="mainmenu">
			<a href="#">카테고리</a>
			<ul>
				<li><a href="https://www.naver.com">태그명</a></li>
				<li><a href="https://www.naver.com">태그명</a></li>
				<li><a href="https://www.naver.com">태그명</a></li>
			</ul>
		</li>
		
		<li class="mainmenu">
			<a href="#">카테고리</a>
			<ul>
				<li>
    				<a href="https://www.naver.com">태그명</a>
				</li>
				<li>
					<a href="https://www.naver.com">태그명</a>
				</li>			
				<li>
					<a href="https://www.naver.com">태그명</a>
				</li>
			</ul>
		</li>
		
		</ul>
	<!--header end-->
	
	</div>
		<div id="content">
			<ul>
					<li>
						<a href="https://www.naver.com">
						<img src="test-image/image1.jpg" />
						</a>
					</li>
					<li>
						<a href="https://www.naver.com">
						<img src="test-image/image2.jpg" />
						</a>
					</li>
					<li>
						<a href="https://www.naver.com">
						<img src="test-image/image3.jpg" />
						</a>
					</li>
					<li>
						<a href="https://www.naver.com">
						<img src="test-image/image4.jpg" />
						</a>
					</li>
					<li>
						<a href="https://www.naver.com">
						<img src="test-image/image5.jpg" />
						</a>
					</li>
					<li>
						<a href="https://www.naver.com">
						<img src="test-image/image6.jpg" />
						</a>
					</li>
		</ul>
		</div>
		
		
		<div id="footer"></div>
	</div>
</body>
</html>