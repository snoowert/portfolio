<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
<section class="container">
<div class="col-sm-10">
	<div>
		<h3>회 원 정 보 </h3>
	</div>
	<br>
	<div class="row">
		<label style="font-size:large; " class="col-sm-3">회원명</label>
		<p style="font-size:larger;font-weight: bolder;">${member.username }</p>
	</div>
	<div class="row">
		<label style="font-size:large; " class="col-sm-3">가입일자</label>
		<p style="font-size:larger;font-weight: bolder;"><fmt:formatDate pattern="yyyy.MM.dd" value="${member.regdate}"/></p>
	</div>
	<div class="row">
		<label style="font-size:large; " class="col-sm-3">이메일</label>
		<p style="font-size:larger;font-weight: bolder;">${member.email }</p>
	</div>
	<div class="row">
		<label style="font-size:large; " class="col-sm-3">권한</label>
		<p style="font-size:larger;font-weight: bolder;">${member.authority }</p>
	</div>
	<div class="row">
		<c:if test="${loginUser.memberid == member.memberid}">
		<button class="col-sm-5 btn btn-primary mx-auto" onclick="modifyForm();">수정</button>
		<button class="col-sm-5 btn btn-warning mx-auto" onclick="banSelf();">탈퇴</button>
		</c:if>
	</div>
	<br>
	<div class="row">
		<c:if test="${loginUser.authority eq 'manager' }">
		<button class="col-sm-10 btn btn-danger mx-auto" name="Mbutton" id="Mbutton" onclick="ManagerDeleteGo();">
	        <c:if test="${member.isDelete eq 'N'}">탈퇴 처리</c:if>
    		<c:if test="${member.isDelete eq 'Y'}">탈퇴 복구</c:if>
	    </button>
	    </c:if>
	</div>
	<input type="hidden" value="${member.isDelete }" name="memberisD" id="memberisD">
	<form id="modifyForm" action="/member/modifyForm" method="GET">
		<input type="hidden" name="memberid" value="${member.memberid }">
	</form>
</div>
</section>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</body>
<script>
	function ManagerDeleteGo(){
		var isDelete = $('#memberisD').val();
		if(isDelete == 'Y'){
			restoreMember(${member.memberid});
		}
		else{
			banMember(${member.memberid});
		}
	}
	function modifyForm(){
		var form = $("form[id='modifyForm']");
		form.submit();
	}
	// 밴 요청을 보내는 함수
	function banMember(memberid) {
	    $.ajax({
	        url: '/member/ban',
	        type: 'POST',
	        data: { memberid: memberid },
	        success: function(response) {
	        	$('#Mbutton').text('탈퇴 복구');// 성공한 경우의 처리
	        	$('#memberisD').val('Y');
	        },
	        error: function(xhr, status, error) {
	            console.error(error); // 에러 발생 시의 처리
	        }
	    });
	}

	// 복구 요청을 보내는 함수
	function restoreMember(memberid) {
	    $.ajax({
	        url: '/member/restore',
	        type: 'POST',
	        data: { memberid: memberid },
	        success: function(response) {
	        	$('#Mbutton').text('탈퇴 처리'); // 성공한 경우의 처리
	        	$('#memberisD').val('N');
	        },
	        error: function(xhr, status, error) {
	            console.error(error); // 에러 발생 시의 처리
	        }
	    });
	}
	
	function banSelf(){
		if(confirm("탈퇴하시겠습니까? ")){
			location.href ="/member/banSelf?memberid=${member.memberid}";
		}else{
			return;
		}	
	}
</script>
</html>