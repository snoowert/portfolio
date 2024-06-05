<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@ include file="/WEB-INF/views/modules/subbar.jsp" %>

<section class="content w-75 p-3 mx-auto">
    <div class="card">
        <div class="card-header with-border">
            
        </div>
        <div class="card-body">
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th style="width:10%;">번 호</th>
                        <th style="width:40%;">파일명</th>
                        <th style="width:15%;">등록자</th>
                        <th style="width:10%;">다운수</th>
                        <th style="width:5%"></th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty projectFileList}">
                        <tr>
                            <td colspan="4">해당 내용이 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${projectFileList}" var="projectFile">
                        <tr>
                            <td>${projectFile.fileId }</td>
                            <td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
                            white-space: nowrap; text-overflow: ellipsis;">
                                <a href="getFile.do?fileId=${projectFile.fileId }&from=list">
                                    <span class="col-sm-12 ">${projectFile.fileName.split('\\$\\$')[1] }</span>
                                </a>
                            </td>
                            <td>${projectFile.writer }</td>
                            <td><span class="badge bg-red">${projectFile.fileDownCnt }</span></td>
                            <td><input type="button" class="img-button" onclick="delete_do(${projectFile.fileId});" alt="삭제" value="X"></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="card-footer">
        </div>
    </div>
    <button type="button" class="btn btn-primary btn float-right" id="registBtn" onclick="OpenWindow('projectFile_upload?projectId=${project.projectId}', '업로드', 800, 600);">파일 업로드</button>

</section>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
<script>

function delete_do(fileid){
    if(confirm("삭제하시겠습니까? ")){
        location.href ="/devnote/deleteProjectFile?fileid="+fileid;
    }else{
        return;
    }
}


function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight) {
	winleft = (screen.width - WinWidth) / 2;
	wintop = (screen.height - WinHeight) / 2;
	var win = window.open(UrlStr , WinTitle , "scrollbars=yes,width="+ WinWidth
							+",height="+ WinHeight +", top="+ wintop +", left=" 
							+ winleft +", resizable=yes, status=yes"  );
	win.focus() ; 
}


</script>
<%@ include file="/WEB-INF/views/modules/footer.jsp" %>