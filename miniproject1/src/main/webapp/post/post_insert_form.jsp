<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--  Bootstrap  3.x  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<style type="text/css">
#box {
	width: 600px;
	margin: auto;
	margin-top: 100px;
}

textarea {
	resize: none;
}

th {
	width: 20%;
	vertical-align: middle !important;
}
</style>

<script type="text/javascript">
	function send(f) {

		//입력값을 검증해야 한다 , 공백제거 trim()
		let p_title = f.p_title.value.trim();
		let p_content = f.p_content.value.trim();
		let p_type = f.p_type.value.trim();
		let p_cate = f.p_cate.value.trim();
		alert(p_content);
		
		if (p_title == '') {
			alert("제목를 입력하세요");
			f.p_title.value = ""; //지우기
			f.p_title.focus();
			return;
		}

		if (p_content == '') {
			alert("내용를 입력하세요");
			f.p_content.value = ""; //지우기
			f.p_content.focus();
			return;
		}

		if(p_cate==""){
			alert("내용를 입력하세요");
			f.p_cate.value = ""; //지우기
			f.p_cate.focus();
			return;
		}
		if (p_type == '') {
			alert("구분을 선택하시오");
			f.p_type.value = ""; //지우기
			f.p_type.focus();
			return;
		}

		//		f.method = "POST";
		f.action = "insert.do"; //전송대상(VisitInsertAction)
		f.submit(); //전송
	}
</script>


</head>
<body>

	<form>
		<div id="box">
			<!-- Bootstrap panel -->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>게시글쓰기</h4>
				</div>
				<div class="panel-body">
					<table class="table">
						<input type="hidden" name="m_idx" value="${m_idx}">
						<tr>
							<th>제목</th>
							<!-- 필수입력사항 required="required" -->
							<td><input class="form-control" name="p_title" ></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input class="form-control" name="m_name" placeholder="${m_name}" disabled></td>
							
						</tr>
						<tr>
							<th>내용</th>
							<td><textarea class="form-control" rows="6" name="p_content"></textarea>
							</td>
						</tr>
						<tr>
							<th>카테고리</th>
							<td><input class="form-control" name="p_cate" ></td>
						</tr>
						<tr>
							<th>일반/공지</th>
							<td><input class="form-control" type="text" name="p_type"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
							<input
								class="btn btn-success" type="button" value="목록보기"
								onclick="location.href='list.do'"> <input
								class="btn btn-primary" type="button" value="글올리기"
								onclick="send(this.form);"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>

</body>
</html>