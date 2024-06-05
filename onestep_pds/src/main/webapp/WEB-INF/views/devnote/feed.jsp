<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/base.jsp"%>

<%@ include file="/WEB-INF/views/modules/header.jsp"%>

<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

<style>
    .form-control[readonly] {
        color: transparent;
        opacity: 1;
    }
</style>

<%@ include file="/WEB-INF/views/modules/subbar.jsp"%>

<div class="container" style="margin-top: 20px;">
    <!-- 피드목록, 검색창, 글쓰기 버튼 -->
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-2">
                </div>
                <div class="col-md-6">
                </div>
                <sec:authorize access="isAuthenticated()">
                <div class="col-md-4 d-flex justify-content-end">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">글쓰기</button>
                </div>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>






<!-- 글쓰기 모달 -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl">
        <div class="modal-content" style="padding: 30px;">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">피드 게시글 작성</h1>	
            </div>

            <form role="form" method="post" action="feedRegist" name="feedRegistForm">
                <input type="hidden" id="noteContent" name="noteContent">
                <input type="hidden" id="memberId" name="memberId" value="${loginUser.memberid}">
                <input type="hidden" id="projectId" name="projectId" value="${project.projectId}">

                <div class="form-group" style="margin: 15px;">
                    <input type="text" id="noteTitle" name="noteTitle" class="form-control" placeholder="피드 제목">
                </div>

                <div class="modal-body">
                    <div class="form-group" id="content">
                        <!-- 에디터 입력창 -->
                    </div>
                </div>
            </form>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="button" class="btn btn-primary" id="registBtn" onclick="regist_go();">등록</button>
            </div>
        </div>
    </div>
</div>

<div class="container" style="margin-top: 20px;">
    <div class="row justify-content-center">
        <div class="col-md-10"> <!-- 이 부분이 col-md-9로 고정됩니다 -->
            <!-- 피드 목록 출력 부분 -->
            <div class="row"> <!-- 각 피드 목록을 동일한 크기의 열로 만듭니다 -->
                <c:forEach var="developnote" items="${noteList}">
                    <div class="col-md-12 mb-3"> <!-- 각 피드 목록을 동일한 크기의 열로 만듭니다 -->
                        <div class="card">
                            <div class="card-body">
							    <div class="mb-3">${developnote.writer }</div>
							    <c:if test="${loginUser.memberid == developnote.memberId || loginUser.authority eq 'manager' }">
								    <div class="row justify-content-end mb-3">
			                             <div class="col-md-auto">
			                                 <button type="button" class="btn btn-secondary me-1" onclick="delete_go(${developnote.noteId});">삭제</button>
			                                 <button type="button" class="btn btn-dark" onclick="modify_go(${developnote.noteId});">수정</button>
			                             </div>
			                         </div>
							    </c:if>
							    <div class="card-title">
							        <h5>${developnote.noteTitle}</h5>
							        <hr />
							    </div>
							    <div class="card-text">
							        <p>${developnote.noteContent}</p>
							        <hr />
							    </div>
							    <sec:authorize access="isAuthenticated()">
							    <div class="mb-3">
							        <img src="<%=request.getContextPath() %>/resources/img/comment.png" alt="댓글" style="float: left;width:40px">
							        <form action="/devnote/registComment" method="POST">
							        <input type="hidden" name="memberid" value="${loginUser.memberid }">
							        <input type="hidden" name="projectid" value="${developnote.projectId}">
							        <input type="hidden" name="noteid" value="${developnote.noteId }">
							        <input class="form-control" type="text" name="dncomcontent" style="display: inline-block; width: 85%; margin-left: 10px;">
							        <button type="submit" class="btn btn-secondary">등록</button>
							        </form>
							    </div>
							    </sec:authorize>
							    <c:forEach items="${developnote.commentList }" var="noteComment">
							    <div class="card mb-3" style="padding: 30px;">
							    	<div class="row">
							        <div class="mb-3 col-md-4">${noteComment.writer }</div>
							        <c:if test="${loginUser.memberid == noteComment.memberid || loginUser.authority eq 'manager'  }">
							        <div class="col-md-8">
							            <button type="button" class="btn btn-secondary float-end" onclick="location.href='/devnote/deleteComment?dncomid=${noteComment.dncomid }&projectId=${project.projectId}'">삭제</button>
							            <button type="button" class="btn btn-dark float-end me-1" style="margin-left: 10px;" onclick="showEditForm(${noteComment.dncomid})">수정</button>
							        </div>
							        </c:if>
							        </div>
							        <hr />
							        <div name="detailComment${noteComment.dncomid }">${noteComment.dncomcontent }</div>
							        <div class="mb-3" name="modifyCommentForm${noteComment.dncomid }" style="display:none;">
								        <form action="/devnote/modifyComment" method="POST">
									        <input type="hidden" name="memberid" value="${noteComment.memberid }">
									        <input type="hidden" name="projectid" value="${noteComment.projectid}">
									        <input type="hidden" name="noteid" value="${noteComment.noteid }">
									        <input type="hidden" name="dncomid" value="${noteComment.dncomid }">
									        <input class="form-control" type="text" name="dncomcontent" value="${noteComment.dncomcontent}" style="display: inline-block; width: 80%; margin-left: 10px;">
									        <button type="submit" class="btn btn-secondary">등록</button>
									        <button type="button" class="btn btn-secondary" onclick="closeEditForm(${noteComment.dncomid})">취소</button>
								        </form>
								    </div>
							    </div>
							    </c:forEach>
							</div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/views/modules/footer.jsp"%>

<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script>

    function regist_go() {
        if ($("input[name='projectName']").val() == "") {
            alert("이름은 필수입니다.");
            $("input[name='projectName']").focus();
            return;
        }

        if ($("input[name='projectDescription']").val() == "") {
            alert("상세설명은 필수입니다.");
            $("input[name='projectDescription']").focus();
            return;
        }


        var form = $("form[role='form']");
        document.querySelector("#noteContent").value = editor.getHTML();

        form.action = "feedRegistForm";
        form.method = "post";

        form.submit();
    }

    const editor = new toastui.Editor({
        el: document.querySelector('#content'), // 에디터를 적용할 요소 (컨테이너)
        height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
        initialEditType: 'wysiwyg',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
        placeholder: '내용을 입력해 주세요.',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
        previewStyle: 'vertical',                // 마크다운 프리뷰 스타일 (tab || vertical)
        hooks: {
            async addImageBlobHook(blob, callback) {
                try {
                    // FormData에 이미지 추가
                    var formData = new FormData();
                    formData.append('image', blob);

                    // 서버로 이미지 업로드
                    const response = await fetch('/devnote/tui-editor/image-upload', {
                        method: 'POST',
                        body: formData
                    });

                    // 서버에서 반환된 파일명
                    const filename = await response.text();
                 	// 이미지 URL 생성
                    const imageUrl = '/devnote/tui-editor/image-print?filename=' + filename;

                    // 에디터에 이미지 삽입
                    callback(imageUrl, 'image');
                } catch (error) {
                    console.error('이미지 업로드 실패:', error);
                }
            }
        }
    });
    
    function delete_go(noteid){
    	location.href="/devnote/deleteFeed?noteId="+noteid+"&projectId=${project.projectId}";
    }
    function modify_go(noteid){
    	location.href="/devnote/modifyFeedForm?noteId="+noteid;
    }
    
    function showEditForm(commentId) {
        // 기존 내용을 감춤
        document.getElementsByName('detailComment'+commentId)[0].style.display = 'none';
        // 수정 폼을 표시
        document.getElementsByName('modifyCommentForm'+commentId)[0].style.display = 'block';
    }
    function closeEditForm(commentId){
    	// 기존 내용을 감춤
        document.getElementsByName('detailComment'+commentId)[0].style.display = 'block';
        // 수정 폼을 표시
        document.getElementsByName('modifyCommentForm'+commentId)[0].style.display = 'none';
    }
</script>
