<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert("피드 수정에 성공했습니다.");
    window.location.href = '/devnote/feed?projectId=' + ${projectId};
	window.opener.location.reload();
</script>