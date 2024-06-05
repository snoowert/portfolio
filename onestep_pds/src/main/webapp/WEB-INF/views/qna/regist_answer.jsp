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
            <div class="card">
                <div class="card-body">
                    <div class="row border-bottom py-2">
                        <div class="col-sm-12">
                            <h2 class="font-weight-bold">${qna.qnatitle}</h2>
                        </div>
                    </div>
                    <div class="row border-bottom py-2">
                        <div class="col-sm-12">
                            <div class="border p-3">${qna.qnacontent}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<section class="content container-fluid">
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="card card-outline card-info">

                <div class="card-body pad">
                    <form role="form" method="post" action="/qna/regist_answer" name="ansregistForm">
                        <input type="hidden" id="answercontent" name="answercontent">
                        <input type="hidden" id="qnaid" name="qnaid" value="${qna.qnaid} ">
                        <div class="form-group">
                            <input type="text" id="writer" title="작성자" readonly
                                name="writer" class="form-control notNull" value="${loginUser.username }">
                            <input type="hidden" id="memberid" name="memberid" value="${loginUser.memberid }">
                        </div>
                        <div class="form-group" id="content">
                            
                        </div>
                    </form>
                </div><!--end card-body  -->
                <div class="card-footer" style="display:none">
                </div><!--end card-footer  -->
                <div class="card-tools" style="margin:10px">
                    <button type="button" class="btn btn-primary float-right" id="registBtn" onclick="regist_go();">등 록</button>
                    <button type="button" class="btn btn-warning float-right mx-2" id="cancelBtn" onclick="history.go(-1);">취 소</button>
                </div>
            </div><!-- end card -->                
        </div><!-- end col-md-12 -->
    </div><!-- end row -->
</section>

<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

<script>
const editor = new toastui.Editor({
    el: document.querySelector('#content'), 	 // 에디터를 적용할 요소 (컨테이너)
    height: '500px',                       	 	 // 에디터 영역의 높이 값 (OOOpx || auto)
    initialEditType: 'wysiwyg',           		 // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
    placeholder: '내용을 입력해주세요.',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
    previewStyle: 'vertical',                	 // 마크다운 프리뷰 스타일 (tab || vertical)
    hooks: {
        async addImageBlobHook(blob, callback) {
            try {
                // FormData에 이미지 추가
                var formData = new FormData();
                formData.append('image', blob);

                // 서버로 이미지 업로드
                const response = await fetch('/qna/tui-editor/image-upload', {
                    method: 'POST',
                    body: formData
                });

                // 서버에서 반환된 파일명
                const filename = await response.text();
             	// 이미지 URL 생성
                const imageUrl = '/qna/tui-editor/image-print?filename=' + filename;

                // 에디터에 이미지 삽입
                callback(imageUrl, 'image');
            } catch (error) {
                console.error('이미지 업로드 실패:', error);
            }
        }
    }
});

function regist_go(){
	var form = document.ansregistForm;
	document.querySelector("#answercontent").value = editor.getHTML();
	var inputNotNull = document.querySelectorAll("input.notNull");
	for(var input of inputNotNull){
		if(!input.value){
			alert(input.getAttribute("title")+"은 필수입니다.");
			input.focus();
			return;
		}
	}
	
	form.submit();
}

</script>

<%@ include file="/WEB-INF/views/modules/footer.jsp"%>