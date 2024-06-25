<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--  jQuery -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!--  Bootstrap  3.x  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
function del(f){
	
	if(confirm("정말 삭제하시겠습니까?")==false){
		return;
	}
	
	location.href = "delete.do?p_idx="+f.p_idx.value+"&no="+f.no.value;
}

function toggleLike(f) {
	f.action = "like.do"; 
	f.submit(); //전송
}


function toggleScrap(f) {
	f.action = "scrap.do"; 
	f.submit(); //전송
	
	// a.jax 아직 미적용
    /*$.ajax({
        url: 'scrap',
        type: 'POST',
        data: { id: f.p_idx.value },
        success: function(response) {
            $('#scrap-btn-' + f.p_idx.value).text(response.scraps);
        }
    }); */
} 

function comments(f){
	f.action = "comments/list.do";
	f.submit();
}


</script>


</head>
<body>
	<div id="box">
		<h1 id="title">♣게시글♣</h1>

		<div style="margin-bottom: 30px">
			<input class="btn btn-primary" type="button" value="글쓰기"
				onclick="location.href='insert_form.do'">
				<!--  여기에 로그인 세션 확인해서 로그인 된 사용자만 쓸 수 있도록 하기  -->
		</div>

		<!-- 내용이 없을 경우 -->
		<c:if test="${empty requestScope.list}">
			<div id="empty_msg">등록된 게시물이 없습니다</div>
		</c:if>

		<!-- 내용  -->
		<!-- for(VisitVo vo: list)와 동일함 -->
		<c:forEach var="vo" items="${requestScope.list }">
			<form class="form-inline">
				<input type="hidden" name="p_idx" value="${vo.p_idx}">
				<!-- 삭제 처리 요청을 할려면 idx가 넘어가야 함 -->
				<input type="hidden" name="no" value="${vo.no}">
				<input type="hidden" name="likes" value="${vo.getLike()}">
				<input type="hidden" name="scraps" value="${vo.getScrap()}">
				
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>
							<b>${vo.m_name }</b>님의 게시글:
						</h4>
					</div>
					
					<!-- 상세보기로 넘어가는 버튼 -->
					<div><button onclick="comments(this.form)">상세보기(임시로만든버튼)</button></div>
					
					<!-- 같은 속성을 주기 위해 class타입으로 정함 -->
					<div class="panel-body">
						<div class="mycommon p_content">${vo.p_content}</div>
						<!-- 일자에서 초까지 필요없으면 substring 사용 -->
						<div class="mycommon p_rdate">작성일자:
							${fn:substring(vo.p_rdate,0,16)}</div>
						<div class="mycommon p_type">
							일반/공지:${vo.p_type}</div>
						<div class="mycommon like">
							좋아요 수 : ${vo.getLike()}</div>
						<div class="mycommon scrap">
							스크랩 수 : ${vo.getScrap()}</div>
						
						<button id="like-btn-${vo.p_idx}" onclick="toggleLike(this.form)">${vo.getLike()}</button>
            			<button id="scrap-btn-${vo.p_idx}" onclick="toggleScrap(this.form)">${vo.getScrap()}</button>
						
						<input class="btn btn-success" type="button" value="수정"
						onclick="modify_form(this.form);">
						<input class="btn btn-danger" type="button" value="삭제"
						onclick="del(this.form);"> 
						</div>
					</div>
			</form>
		</c:forEach>
	</div>

</body>
</html>