<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ include file="/WEB-INF/views/qna/qnaBanner.jsp" %>
<section class="content container-fluid">
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="card-body">
                <div class="row justify-content-end" style="margin-right: 10px;">
                
	                     <button type="button" class="btn btn-outline-secondary" onclick="goToMainPage()">목록으로</button>&nbsp;&nbsp;&nbsp;
	                 
                    <c:if test="${loginUser.memberid == qna.memberid or loginUser.authority eq 'manager'}">
                        <button type="button" class="btn btn-secondary mr-2" onclick="submit_go('modifyForm.do');">수정</button>
                        <button type="button" class="btn btn-warning" onclick="submit_go('remove.do');">삭제</button>
                    </c:if>
                </div>
                <div class="row border-bottom py-2">
                    <div class="col-sm-12">
                        <h2 class="font-weight-bold">${qna.qnatitle }</h2>
                    </div>
                </div>
                <div class="row col-sm-12 border-bottom py-2">
                    <div class="col-sm-9">
                        <h3 class="font-weight-bold">${qna.writer }</h3>
                    </div>
                    <div class="col-sm-2 align-self-center ">
                    	<label>수정일</label>&nbsp;&nbsp;
                        <span> <fmt:formatDate pattern="yyyy.MM.dd" value="${qna.qnaupdatedate}"/></span>
                    </div>
                    <div class="col-sm-1 align-self-center">
                    	<label>조회수</label>&nbsp;&nbsp;
                        <span>${qna.qnaviewpoint }</span>
                    </div>
                </div>
                <div class="row border-bottom py-2">
                    <div class="col-sm-12">
                        <div class="border p-3">${qna.qnacontent }</div>
                    </div>
                </div>
                <br>
                <div class="form-group col-sm-12" style="border-radious:10%;">
					<c:forEach var="answer" items="${qna.answerlist}">
					    <div class="card mb-3">
					        <div class="card-header">
					            <div class="row">
					                <div class="col-md-6 align-self-center">
					                    <span class="answriter">${answer.writer}</span>
					                </div>
					                <c:if test="${loginUser.memberid == answer.memberid or loginUser.authority eq 'manager'}">
					                <div class="col-md-6 text-right">
					                    <button type="button" class="btn btn-primary mr-2" onclick="modify_go(${answer.answerid});">수정하기</button>
					                    <button type="button" class="btn btn-danger" onclick="remove_go(${answer.answerid});">삭제</button>
					                </div>
					                </c:if>
					            </div>
					        </div>
					        <div class="card-body">
					            <p class="card-text answer-content">${answer.answercontent}</p>
					            <p class="card-text text-muted">작성일: <fmt:formatDate value="${answer.answerupdatedate}" pattern="yyyy-MM-dd"/></p>
					        </div>
					    </div>
					</c:forEach>
				</div>
				<hr>
				<sec:authorize access="hasAnyAuthority('developer', 'manager')">
	                <div class="card-header">
		                
						<div class="card-tools">
							<button type="button" class="btn btn-primary" onclick="location='regist_answerForm?qnaid=${qna.qnaid}'">답변 작성</button>
						</div>
					</div>
                </sec:authorize>
            </div>
        </div>
    </div>
</section>

<form role="form">
	<input type="hidden" name="qnaid" value="${qna.qnaid }" />
	<input type="hidden" name="answerid" />
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
var formObj = document.querySelector("form[role='form']");
function submit_go(url){
	if(url=="remove.do" && !confirm("정말로 삭제하시겠습니까?")){
		return;
	}
	formObj.action=url;
	formObj.submit();
}

function modify_go(url){
	$('input[name="answerid"]').val(url);
	formObj.action='modify_answerForm';
	formObj.submit();
}

function remove_go(id){
	$('input[name="answerid"]').val(id);
	formObj.action="/qna/remove_answer";
	formObj.method="POST";
	formObj.submit();
}
function goToMainPage() {
    window.location.href = "/qna/main";
}
</script>
<%@ include file="/WEB-INF/views/modules/footer.jsp" %>
