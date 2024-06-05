<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert("파일 삭제에 성공했습니다.");
    window.location.href = '/devnote/projectFile?projectId=' + ${projectId};
	window.opener.location.reload();
</script>