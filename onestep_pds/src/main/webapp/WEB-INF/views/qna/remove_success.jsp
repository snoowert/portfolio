<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<script>
	alert("삭제 되었습니다.");
	window.location.href='/qna/main';
	window.opener.location.reload();
</script>