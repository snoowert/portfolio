<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert("파일 등록에 성공했습니다.");
	window.opener.location.href = '/devnote/projectFile?projectId=' + ${projectId};
	window.close();
</script>