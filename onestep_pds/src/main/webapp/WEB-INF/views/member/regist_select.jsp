<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
	<section class="container">
	<div class="col-sm-10">
		<div class="row mx-auto">
			<div class="bg-secondary col-sm-10" style="height:100%;">
				<h1 style="margin-top:3rem">회원가입</h1>
			</div>	
		</div>
		<br>
		<div class="row">
			<div class="mx-auto col-sm-5">
				<button style="width:150px;height:150px;" onclick="regist('normal')">일반 사용자</button>
			</div>
			<div class="mx-auto col-sm-5">
				<button style="width:150px;height:150px;" onclick="regist('developer')">개발자</button>
			</div>
		</div>
	</div>
	</section>
</body>
<script>
function regist(authority){
	location.href = "/member/registForm?authority="+authority;
}

</script>
</html>