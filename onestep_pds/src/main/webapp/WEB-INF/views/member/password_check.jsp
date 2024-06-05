<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 확인</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
<section class="container">
<div class="col-sm-10">
	<div>
		<h3>현재 비밀번호 입력</h3>
	</div>
	<br>
	<div>
		<form name="checkForm" action="/member/PWCheck" method="POST">
			<input type="password" id="password" name="password" placeholder="현재 비밀번호 입력" class="form-control">
			<input type="hidden" id="memberid" name="memberid" value="${memberid }">
		</form>
	</div>
	<br>
	<div class="row">
		<input type="button" class="btn btn-primary col-sm-5 mx-auto" onclick="onChecking();" value="확인">
		<button class="btn btn-default col-sm-5 mx-auto" onclick="history.go(-1)">취소</button>
	</div>
</div>
</section>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	var error = "${error}";
	if(error){
		alert(error);
	}
	function onChecking(){
		form = $("form[name='checkForm']");
		
		if(!$("input[name='password']").val()){
			alert("현재 비밀번호를 입력해주세요.");
			return;
		}
		
		form.submit();
	}
</script>
</html>