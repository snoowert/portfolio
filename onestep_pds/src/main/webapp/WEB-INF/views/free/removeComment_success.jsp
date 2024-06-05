<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	alert("댓글이 삭제되었습니다.");
	var freeid = "${freeid}";
	location.href="detail?freeid=" + freeid;
</script>