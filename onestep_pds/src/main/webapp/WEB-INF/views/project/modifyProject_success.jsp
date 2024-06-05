<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var projectId = "<%= request.getAttribute("projectId") %>";
	alert("프로젝트 수정에 성공했습니다." + projectId);
	window.close();
	window.opener.location.href=  'main?projectId='+ projectId;
</script>