<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ include file="/WEB-INF/views/pds/pdsBanner.jsp" %>
<section class="content container-fluid">
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="card-body">
                <div class="row justify-content-end" style="margin-right: 10px;">
                    <c:if test="${loginUser.memberid == pds.memberid }">
                        <button type="button" class="btn btn-secondary mr-2" onclick="modify_go();">수정</button>
                        <button type="button" class="btn btn-warning" onclick="delete_do();">삭제</button>
                    </c:if>
                </div>
                <div class="row border-bottom py-2">
                    <div class="col-sm-12">
                        <h2 class="font-weight-bold">${pds.pdstitle }</h2>
                    </div>
                </div>
                <div class="row col-sm-12 border-bottom py-2">
                    <div class="col-sm-9">
                        <h3 class="font-weight-bold">${pds.writer }</h3>
                    </div>
                    <div class="col-sm-2  align-self-center">
                    	<label>작성일</label>&nbsp;&nbsp;
                        <span> <fmt:formatDate pattern="yyyy.MM.dd" value="${pds.pdsregdate}"/></span>
                    </div>
                    <div class="col-sm-1  align-self-center">
                    	<label>조회수</label>&nbsp;&nbsp;
                        <span>${pds.pdsviewpoint }</span>
                    </div>
                </div>
                <hr>
	            <div class="row">
	                <div class="col-sm-12">
	                    <label class="form-label">파일</label>
	                </div>
	            </div>
	            <c:forEach items="${pds.pdsfilelist }" var="pdsfile">
	                <div class="col-md-4 col-sm-6 col-xs-12 mb-2" style="cursor:pointer;"
	                     onclick="location.href='<%=request.getContextPath()%>/pds/getFile.do?pdsfileid=${pdsfile.pdsfileid }';">
	                    <div class="card">
	                        <div class="card-body">
	                            <h5 class="card-title">${pdsfile.pdsfilename.split('\\$\\$')[1] }</h5>
	                        </div>
	                    </div>
	                </div>
	            </c:forEach>
	            <hr>
                <div class="row border-bottom py-2">
                    <div class="col-sm-12">
                        <div class="border p-3">${pds.pdscontent }</div>
                    </div>
                </div>
                <br>
                <div class="row">
                        <div class="col-sm-6">
                            <button type="button" class="btn btn-outline-secondary" onclick="goToMainPage()">목록으로</button>
                        </div>
                        </div>
            </div>
        </div>
    </div>
</section>


<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function delete_do(){
        if(confirm("삭제하시겠습니까? ")){
            location.href ="/pds/delete?pdsid=${pds.pdsid}";
        }else{
            return;
        }
    }

    function modify_go(){
        location.href = "/pds/modifyForm?pdsid=${pds.pdsid}";
    }
    function goToMainPage() {
        window.location.href = "/pds/list";
    }
</script>
<%@ include file="/WEB-INF/views/modules/footer.jsp" %>
