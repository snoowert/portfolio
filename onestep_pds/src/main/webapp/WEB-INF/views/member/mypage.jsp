<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
<section class="container">
	<div class="row mx-auto">
		<div class="bg-secondary col-sm-10" style="height:100%;">
			<h1 style="margin-top:3rem">마이페이지</h1>
			<br><br><br><br><br><br><br><br><br>
		</div>	
		<div class="bg-secondary col-sm-10">
			<c:if test="${loginUser.authority ne 'manager'}">
			<a href="/member/passwordCheckForm?memberid=${loginUser.memberid }"><h1 style="font-weight: bolder;">회원 정보</h1></a>
			</c:if>
			<br><br><br>
			<c:if test="${loginUser.authority eq 'manager' }">
			<a href="/member/list"><h1 style="font-weight: bolder;">회원 목록</h1></a>
			</c:if>
		</div>
	</div>
</section>
</body>
</html>