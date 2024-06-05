<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
<section class="container">
	<dev class="container-fluid col-sm-10">
		<form class="mb-3" name="memberform" id="memberform" action="/member/regist" method="POST">
			<label class="form-label">사용자명</label>
			<input id="username" name="username" type="text" class="form-control" placeholder="사용자명">
			<br>
			<input type="hidden" id="authority" name="authority" value="${authority }">
			
			<label class="form-label">이메일</label>
			<input id="email" name="email" type="text" class="form-control" placeholder="이메일">
			<br>	
			<label class="form-label">비밀번호</label>
			<input id="password" name="password" type="password" class="form-control" placeholder="8자 이상 입력해주세요.">
			<br>
			<label class="form-label">비밀번호 확인</label>
			<input id="re-password" name="re-password" type="password" class="form-control" placeholder="비밀번호 재입력">
			<br>
			<c:if test="${authority eq 'developer' }">
				<label class="form-label">주력 언어</label>&nbsp;&nbsp;&nbsp;
				<div class="input-group">
			        <select class="form-select" name="devlan" id="devlan">
			            <option value="none">없음</option>
			            <option value="java">자바</option>
			            <option value="python">파이썬</option>
			        </select>
			    </div>
			</c:if>
			<c:if test="${authority eq 'normal' }">
				<input name="devlan" type="hidden">
			</c:if>
			<br>
			<input type="button" class="btn btn-primary" value="등록" onclick="regist_go()">&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-default" value="취소" onclick="history.go(-1)">
		</form>
	</dev>
	<input type="hidden" id="userAnswer">
	<input type="hidden" id="quizAnswer">
</section>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	var url = '/pds/regist';
	var form = $('form[name="memberform"]');
	
	function regist_go(){
		var checkE = false;
		if(!$('input[name="username"]').val()){
		    alert("사용자명은 필수입니다.");
		    $('input[name="username"]').focus();
		    return;
		}
		if($('input[name="username"]').val().length > 50){
			alert("사용자명은 50자 이내로 작성해주세요.");
			return;
		}
		if(!$('input[name="email"]').val()){
			   alert("이메일은 필수입니다.");
			   return;
			}
		if(!EmailCheck($('input[name="email"]').val())){
			alert("이메일 형식으로 작성해주세요.");
			return;
		}
		if(!$('input[name="password"]').val()){
			   alert("패스워드는 필수입니다.");
			   return;
		    }
		if($('input[name="password"]').val().length<8){
			alert("비밀번호는 8자이상 입력해주세요.");
			return;
		}
		if(!PasswordCheck()){
			alert("비밀번호가 일치하지않습니다.\n다시 확인해주세요.");
			return;
		}
		
		var authority = $('#authority').val();
		
		if (authority === 'developer' && $('select[name="devlan"]').val() === 'none') {
		    alert("개발 주력 언어를 선택해주세요.");
		    return;
		}
	
		$.ajax({
		    url: '/member/checkEmail',
		    type: 'POST',
		    data: { email: $('input[name="email"]').val() },
		    success: function(data) {
		        var checkE = false; // checkE 변수를 success 콜백 함수 내부로 이동
		        if (data === 'duplicate') {
		            // 중복된 이메일이 있을 경우
		            alert('중복된 이메일입니다.');
		            checkE = true;
		        }
		        if (checkE) {
		            return; // 중복된 경우 함수 종료
		        }
		        // 중복되지 않은 경우 추가 로직 수행
		        if (authority === 'normal') {
			        // authority가 'normal'인 경우, 퀴즈를 받지 않고 바로 회원가입을 처리
			        RealSubmit_go();
			    } else {
			    	alert("개발자 확인용 퀴즈를 맞히시면 가입이 승인됩니다.");
					$.ajax({
				        url: '/member/quiz', // 퀴즈 데이터를 받아오는 컨트롤러 주소
				        type: 'GET',
				        dataType: 'json',
				        data: { devlan: $('#devlan').val() },
				        success: function(data) {
				            // 받아온 퀴즈 데이터를 처리하여 팝업 창에 퀴즈를 표시
				            var quizQuestion = data.question; 
				            var quizAnswer = data.answer; 
				            document.getElementById('quizAnswer').value = quizAnswer;
				            // 퀴즈를 포함한 HTML을 생성
				            var popupContent = quizQuestion;
				            popupContent += '<input type="text" id="quizAnswerInput" class="form-control" placeholder="정답을 입력하세요">';
				            popupContent += '<button type="button" class="btn btn-primary" onclick="on_Check();">확인</button>';
				            popupContent += '<script>\n\
				            					function on_Check(){\n\
				            					var userAnswer = document.getElementById(\'quizAnswerInput\').value;\n\
				            					opener.document.getElementById("userAnswer").value = userAnswer;\n\
				            					window.close();}\n\
				            				<\/script>';
				            // 팝업 창 열기
				            var popupWindow = window.open('', '_blank', 'width=400,height=300');
				            popupWindow.document.write(popupContent);
				            
				            popupWindow.addEventListener('beforeunload', function() {
				            	checkAnswer();
				              });
				        },
				        error: function() {
				            alert('퀴즈 데이터를 불러오는 데 실패했습니다.');
				        }
				    });
			    }
		    },
		    error: function() {
		        alert('이메일 중복 검사 중 오류가 발생했습니다.');
		    }
		});

	    
	}
	function checkAnswer() {
        // 사용자가 입력한 답과 정답을 비교하여 처리하는 함수
        var userAnswer = document.getElementById('userAnswer').value;
        var correctAnswer = document.getElementById('quizAnswer').value;
        if (userAnswer.trim().toLowerCase() === correctAnswer.trim().toLowerCase()) {
            // 정답인 경우, 회원가입 처리
            document.getElementById('memberform').submit();
        } else {
            // 오답인 경우, 메시지 표시 등 추가 처리 가능
            alert('퀴즈의 정답을 맞추어야 회원가입이 가능합니다.');
        }
	}
	function RealSubmit_go(){
		form.submit();
	}
	function EmailCheck(email) {
	    // 이메일 형식을 검증하는 정규 표현식
	    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	    return emailRegex.test(email);
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