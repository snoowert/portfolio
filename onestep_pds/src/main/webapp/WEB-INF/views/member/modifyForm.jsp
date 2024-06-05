<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
<section class="container">
	<dev class="container-fluid col-sm-10">
		<form class="mb-3" name="memberform" id="memberform" action="/member/modify" method="POST">
			<label class="form-label">사용자명</label>
			<input id="username" name="username" type="text" class="form-control" placeholder="사용자명" value="${member.username }">
			<br>
			<input type="hidden" id="memberid" name="memberid" value="${member.memberid }">
			
			<label class="form-label">이메일</label>
			<input id="email" name="email" type="text" class="form-control" placeholder="이메일" value="${member.email }" readonly>
			<br>	
			<label class="form-label">변경할 비밀번호</label>
			<input id="password" name="password" type="password" class="form-control" placeholder="8자 이상 입력해주세요.">
			<br>
			<label class="form-label">변경할 비밀번호 확인</label>
			<input id="password" name="re-password" type="password" class="form-control" placeholder="비밀번호 재입력">
			<br>
			
			<br>
			<input type="button" class="btn btn-primary" value="수정" onclick="modify_go()">&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-default" value="취소" onclick="history.go(-1)">
		</form>
	</dev>
</section>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	var url = '/pds/regist';
	function modify_go(){
		var form = $('form[name="memberform"]');
		
		if(!$('input[name="username"]').val()){
		    alert("사용자명은 필수입니다.");
		    $('input[name="username"]').focus();
		    return;
		}
		if($('input[name="username"]').val().length > 50){
			alert("사용자명은 50자 이내로 작성해주세요.");
			return;
		}
		if(!$('input[name="password"]').val()){
			$('input[name="password"]').val("${member.password}");
		}
		else{
			if($('input[name="password"]').val().length<8){
				alert("비밀번호는 8자이상 입력해주세요.");
				return;
			}
			if(!PasswordCheck()){
				alert("비밀번호가 일치하지않습니다.\n다시 확인해주세요.");
				return;
			}
		}
		form.submit();
	}
	
	function PasswordCheck(){
		var pw = $('input[name="password"]').val();
		var cpw = $('input[name="re-password"]').val();
		
		if(pw == cpw){
			return true;
		}else{
			return false;
		}
	}
</script>
</html>