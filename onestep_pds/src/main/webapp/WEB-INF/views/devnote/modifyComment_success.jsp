<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert("댓글이 수정되었습니다.");
    window.location.href = '/devnote/feed?projectId=' + ${projectId};
	window.opener.location.reload();
</script>