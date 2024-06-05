<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ include file="/WEB-INF/views/modules/header.jsp"%>
<div class="card card-primary">
	<div class="card-header">
		<h3 class="card-title">댓글 수정</h3>
	</div>
	<!-- /.card-header -->
	<!-- form start -->
	<form method="post" action="commentmodify" role="form">
		<div class="card-body">
			<div class="form-group">
				<label for="memberid">작성자</label> <input readonly
					type="text" class="form-control" id="writer" name="writer" 
					value="${comment.writer }">
				<input type="hidden" id="memberid" name="memberid" value="${comment.memberid }">
			</div>
			<div class="form-group">
				<label for="commentcontent">내용</label> 
				<textarea id="commentcontent" class="form-control" rows="5"
									name="commentcontent">${comment.commentcontent }</textarea>
			</div>
			<input type="hidden" value="${comment.commentid }" name="commentid">
		</div>
		<!-- /.card-body -->

		<div class="card-footer">
			<button type="button" onclick="modify_go()" class="btn btn-warning">수정</button>
			<button type="button" onclick="CloseWindow()" class="btn btn-primary">취소</button>
		</div>
	</form>
</div>

<%@ include file="/WEB-INF/views/modules/common_js.jsp"%>
<!-- REQUIRED SCRIPTS -->
<script>
	function modify_go(){
		if ($("input[name='commentcontent']").val() == "") {
			alert("내용은 필수입니다.");
			$("input[name='commentcontent']").focus();
			return;
		}
		
		$("form[role='form']").submit();
	}
</script>

<%@ include file="/WEB-INF/views/modules/footer.jsp"%>