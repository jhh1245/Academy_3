<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
  .mycommon{
 
     /* text-align: center; */
     width: 300px;
     margin: auto;
     border: 1px solid gray;
     margin-bottom: 10px;
     padding: 5px;
     
     box-shadow: 1px 1px 1px black;
  }
  
  
  
  #pop_image{
     width: 300px;
     height: 300px;
     border: 2px solid gray;
     outline: 2px solid black;
  }
  
  #pop_content{
     min-height: 80px;
  }
</style>
</head>
<body>
	<!-- 이미지 팝업 -->
	<!-- Modal -->
	<div class="modal fade" id="photoModal" role="dialog">
		<div class="modal-dialog" style="width: 360px;">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">×</button>
					<h4 class="modal-title" id="pop_mem_name">올린이: 홍길동</h4>
				</div>

				<!-- 본문 -->
				<div class="modal-body">
					<div style="text-align: center; height: 310px;">
						<img id="pop_image">
					</div>
					<div class="mycommon" id="pop_title">사진제목</div>
					<div class="mycommon" id="pop_content">사진내용</div>
					<div class="mycommon" id="pop_regdate">올린날짜</div>

					<div id="pop_job" style="text-align: center;">
						<input style="display: none;" class="btn btn-info" type="button"
							id="btn_popup_update" value="수정"> <input
							style="display: none;" class="btn btn-danger" type="button"
							id="btn_popup_delete" value="삭제"> <input
							style="display: none;" class="btn btn-success" type="button"
							id="btn_popup_download" value="다운">
						<button class="btn btn-primary" type="button" data-dismiss="modal">닫기</button>
					</div>


				</div>
				<!--   
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div> -->
			</div>

		</div>
	</div>
</body>
</html>