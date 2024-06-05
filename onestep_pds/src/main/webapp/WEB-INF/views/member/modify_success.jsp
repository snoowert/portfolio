<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var memberid = "<%= request.getAttribute("memberid") %>";
	alert("수정되었습니다.");
	location.href="/member/detail?memberid=" + memberid;
</script>