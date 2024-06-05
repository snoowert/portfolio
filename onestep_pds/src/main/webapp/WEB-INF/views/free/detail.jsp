<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ include file="/WEB-INF/views/free/freeBanner.jsp" %>
<section class="content container-fluid">
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="card-body">
                <div class="row justify-content-end" style="margin-right: 10px;">
                    <c:if test="${loginUser.memberid == free.memberid or loginUser.authority eq 'manager'}">
                        <button type="button" class="btn btn-secondary mr-2" onclick="submit_go('modifyForm.do');">수정</button>
                        <button type="button" class="btn btn-warning" onclick="submit_go('remove.do');">삭제</button>
                    </c:if>
                </div>
                <div class="row border-bottom py-2">
                    <div class="col-sm-12">
                        <h2 class="font-weight-bold">${free.freetitle }</h2>
                    </div>
                </div>
                <div class="row col-sm-12 border-bottom py-2">
                    <div class="col-sm-9">
                        <h3 class="font-weight-bold">${free.writer }</h3>
                    </div>
                    <div class="col-sm-2 align-self-center">
                    	<label>작성일</label>&nbsp;&nbsp;
                        <span> <fmt:formatDate pattern="yyyy.MM.dd" value="${free.freeregdate}"/></span>
                    </div>
                    <div class="col-sm-1 align-self-center">
                    	<label>조회수</label>&nbsp;&nbsp;
                        <span>${free.freeviewpoint }</span>
                    </div>
                </div>
                <div class="row border-bottom py-2">
                    <div class="col-sm-12">
                        <div class="border p-3">${free.freecontent }</div>
                    </div>
                </div>
                <div class="row border-bottom py-2">
                    <h3 class="font-weight-bold">댓글</h3>
                </div>
                <c:forEach var="comment" items="${free.commentlist}" varStatus="status">
				    <div class="row border-bottom py-2" style="background-color: ${status.index % 2 == 0 ? 'aliceblue' : 'white'};">
				        <div class="col-sm-12">
				            <div class="font-weight-bold">${comment.writer}</div>
				            <div class="text-muted"><fmt:formatDate pattern="yyyy.MM.dd hh:mm" value="${comment.commentregdate}"/></div>
				            <c:if test="${loginUser.memberid == comment.memberid }">
				                <button type="button" class="btn btn-secondary mr-2" onclick="window.open('modifycommentForm?commentid=${comment.commentid}', '_blank', 'width=600,height=400')">수정</button>
				                <button type="button" class="btn btn-danger" onclick="deleteComment(${comment.commentid})">삭제</button>
				            </c:if>
				        </div>
				    </div>
				    <div class="row border-bottom py-2" style="background-color: ${status.index % 2 == 0 ? 'aliceblue' : 'white'};">
				        <div class="col-sm-12">
				            <div class="text">${comment.commentcontent}</div>
				        </div>
				    </div>
				</c:forEach>
				<sec:authorize access="isAuthenticated()">
                <form class="comment-form insert-form" action="commentregist" method="post">
                    <input type="hidden" name="freeid" value="${free.freeid}" />
                    <input type="hidden" name="memberid" value="${loginUser.memberid}" />
                    <div class="row border py-2">
                        <input class="font-weight-bold form-control col-sm-12 mb-2" readonly name="username" value="${loginUser.username}" />
                        <textarea class="reply-form-textarea form-control" name="commentcontent" required="required" rows="3" placeholder="내용을 입력하세요."></textarea>
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <button type="button" class="btn btn-outline-secondary" onclick="goToMainPage()">목록으로</button>
                        </div>
                        <div class="col-sm-6 text-right">
                            <button type="submit" class="btn btn-primary">등록</button>
                        </div>
                    </div>
                </form>
                </sec:authorize>
            </div>
        </div>
    </div>
</section>

<form role="form">
    <input type="hidden" name="freeid" value="${free.freeid }" />
</form>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
    var formObj = document.querySelector("form[role='form']");
    
    function submit_go(url) {
        if(url=="remove.do" && !confirm("정말로 삭제하시겠습니까?")){
            return;
        }
        formObj.action=url;
        formObj.submit();
    }

    function deleteComment(commentid) {
        if (confirm("정말로 삭제하시겠습니까?")) {
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "/free/commentremove");
            form.setAttribute("style", "display: none;");

            var commentIdInput = document.createElement("input");
            commentIdInput.setAttribute("type", "hidden");
            commentIdInput.setAttribute("name", "commentid");
            commentIdInput.setAttribute("value", commentid);
            form.appendChild(commentIdInput);

            var freeid = document.createElement("input");
            freeid.setAttribute("type", "hidden");
            freeid.setAttribute("name", "freeid");
            freeid.setAttribute("value", "${free.freeid}");
            form.appendChild(freeid);

            document.body.appendChild(form);
            form.submit();
        }
    }

    function goToMainPage() {
        window.location.href = "/free/main";
    }
</script>

<%@ include file="/WEB-INF/views/modules/footer.jsp" %>
