<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var projectId = "<%= request.getAttribute("projectId") %>";
	window.location.href=  'main?projectId='+ projectId;
</script>