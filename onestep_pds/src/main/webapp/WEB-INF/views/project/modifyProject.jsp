<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">

<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title>OneStep</title>
<style>
.dt-pjc-main {
	width: 100%;
	margin-top: 45px;
}

.dt-pjc-main .card {
	box-shadow: 0 12px 18px 2px rgba(34, 0, 51, 0.04), 0 6px 22px 4px
		rgba(7, 48, 114, 0.12), 0 6px 10px -4px rgba(14, 13, 26, 0.12)
		!important;
	border-radius: 16px;
}

.dt-pjc-main .dt-pjc-create {
	height: 450px !important;
}

.dt-pjc-main header {
	text-align: center;
	margin: 30px 0 0 0;
	font-size: 24px;
	font-weight: bold;
}

.dt-pjc-main section {
	width: 800px;
	margin: 10px auto;
}

.dt-pjc-input {
	margin-top: 10px;
}

.dt-pjc-textarea {
	height: 300px;
}

.dt-pjc-btn {
	margin: 20px 0 0 0;
	width: 100px;
}
</style>
</head>
<body>
		<!-- 카드? -->
	<div class="card dt-pjc-main" style="margin-left:auto; margin-right:auto;">
		<div class="dt-pjc-create">
			<header> 프로젝트 정보 수정 </header>
			<hr style="box-shadow: 1px 1px 1px 1px rgba(32, 0, 31, 0.04)"/>
			<div class="container-fluid" style="margin-top:50px;">
				<section>
					<!-- 입력란 -->
					<form role="form" method="post" action="modifyPj.do" name="modifyForm">
						<input type="hidden" name="projectId" value="${project.projectId }">
						<div class="form-group">
							<label for="projectname"> 프로젝트 이름 </label>
							<input type="text" id="projectName" name="projectName" class="form-control" value="${project.projectName }" placeholder="프로젝트 명">
						</div>
						<div class="form-group">
							<label for="projectdesc"> 프로젝트 상세 설명</label>
							<input type="text" id="projectDescription" name="projectDescription" class="form-control" value="${project.projectDescription }" placeholder="프로젝트 상세설명" >
						</div>
					</form>
					<!-- 생성버튼 -->
					<div style="text-align:center;">
						<button type="button" class="btn btn-primary dt-pjc-btn" id="registBtn" onclick="regist_go();">수정</button>
					</div> 
				</section>
			</div>
		</div>
	</div>

</body>

<!-- jQuery -->
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>


<script>
function regist_go(){
	if($("input[name='projectName']").val()==""){
		alert("이름은 필수입니다.");
		$("input[name='projectName']").focus();
		return;
	}
	
	if($("input[name='projectDescription']").val()=="") {
		alert("상세설명은 필수입니다.");
		$("input[name='projectDescription']").focus();
		return;
	}
	
	
	var form = $("form[role='form']");
	form.submit();

}

</script>
</html>