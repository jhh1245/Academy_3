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
function comment_del(f){
	
	if(confirm("정말 삭제하시겠습니까?")==false){
		return;
	}
	
	location.href = "comment_delete.do?p_idx="+f.p_idx.value+"&no="+f.no.value;
}
</script>


<body>
    
    // 게시글 내용 하고
    // 좋아요 개수, 스크랩 개수랑 
    // 수정, 삭제버튼도 --> 이것들은 selectOne하면 될 듯  
    
    // 댓글 개수 
    
    // 댓글 먼저 보여주자 
    <div id="box">
		<h1 id="title">♣댓글♣</h1>
		
		<div style="margin-bottom: 30px">
			<input class="btn btn-primary" type="button" value="댓글쓰기"
				onclick="location.href='comments_insert_form.do'">
		</div>
		
		<!-- 댓글이 없을 경우 -->
		<c:if test="${empty requestScope.c_list}">
			<div id="empty_msg">등록된 댓글이 없습니다</div>
		</c:if>
		
		<!-- 댓글이 있을 경우 -->
		<!-- for(Comments vo: c_list)와 동일함 -->
		<c:forEach var="vo" items="${requestScope.c_list }">
			<form class="form-inline">
				<input type="hidden" name="p_idx" value="${vo.p_idx}">
				<!-- 삭제 처리 요청을 할려면 idx가 넘어가야 함 -->
				<%-- <input type="hidden" name="no" value="${vo.no}"> --%> <!-- 댓글에도 vo가 필요한지? -->
				
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>
							<b>${vo.m_idx }</b>님의 댓글:
						</h4>
					</div>
					
					<!-- 같은 속성을 주기 위해 class타입으로 정함 -->
					<div class="panel-body">
						<div class="mycommon c_content">${vo.c_content}</div>
						<!-- 일자에서 초까지 필요없으면 substring 사용 -->
						<div class="mycommon c_rdate">작성일자:
							${fn:substring(vo.c_rdate,0,16)}</div>
						
						<!-- 댓글 좋아요 기능이 추가될 경우 쓰기 -->
						<%-- <div class="mycommon like">
							좋아요 수 : ${vo.getLike()}</div>
						
						
						<button id="like-btn-${vo.p_idx}" onclick="toggleLike(this.form)">${vo.getLike()}</button>
	           			--%>
						
						<input class="btn btn-success" type="button" value="수정"
						onclick="comment_modify_form(this.form);">
						<input class="btn btn-danger" type="button" value="삭제"
						onclick="comment_del(this.form);"> 
					</div>
				</div>
			</form>
		</c:forEach>
		<%-- <form action="CommentServlet" method="post">
	        <input type="hidden" name="p_idx" value="<%= p_idx %>">
	        <input type="hidden" name="m_idx" value="1"> <!-- 로그인한 사용자 ID, 실제로는 세션에서 가져와야 함 -->
	        <textarea name="c_content" required></textarea>
	        <button type="submit">댓글 작성</button>
    	</form> --%>
		
	</div>  
</body>
</html>