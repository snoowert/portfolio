<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ include file="/WEB-INF/views/free/freeBanner.jsp" %>
 <!-- Main content -->
<section class="content container-fluid">
    <div class="row justify-content-center">
        <div class="col-md-9">
            <div class="card card-outline card-info">

                <div class="card-body">
                    <form role="form" method="post" action="modify.do" name="modifyForm" onsubmit="return false;">
                        <input type="hidden" name="freeid" value="${free.freeid }" />
                        <div class="form-group"> 
                            <input type="text" id="freetitle" name='freetitle' class="form-control" value="${free.freetitle }">
                        </div>
                        <div class="form-group">
                            <input type="text" id="writer" readonly name="writer" class="form-control" value="${free.writer }"> 
                            <input type="hidden" id="memberid" readonly	name="memberid" class="form-control"  value="${free.memberid }">
                        </div>
                        <div class="form-group">
                            <div id="content"></div>  
                            <input type="hidden" name="freecontent" id="freecontent"/>    
                        </div>
                    </form>
                </div><!--end card-body  -->
                <div class="card-footer" style="display:none"></div>
                <div class="card-footer text-right"> <!-- 수정된 부분 -->
                    <button type="button" class="btn btn-warning" id="modifyBtn" onclick="modify_submit();">수 정</button>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <button type="button" class="btn btn-default" id="cancelBtn" onclick="history.go(-1);">취 소</button>
                </div>      
            </div><!-- end card -->        
        </div><!-- end col-md-12 -->

    </div><!-- end row -->
</section>

    <!-- /.content -->
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script>
function modify_submit(){
	document.querySelector("#freecontent").value = editor.getHTML();
	document.querySelector("form[role='form']").submit();	
}
const editor = new toastui.Editor({
    el: document.querySelector('#content'), // 에디터를 적용할 요소 (컨테이너)
    height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
    initialEditType: 'wysiwyg',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
    initialValue: '${free.freecontent}',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
    previewStyle: 'vertical',                // 마크다운 프리뷰 스타일 (tab || vertical)
    hooks: {
        async addImageBlobHook(blob, callback) {
            try {
                // FormData에 이미지 추가
                var formData = new FormData();
                formData.append('image', blob);

                // 서버로 이미지 업로드
                const response = await fetch('/free/tui-editor/image-upload', {
                    method: 'POST',
                    body: formData
                });

                // 서버에서 반환된 파일명
                const filename = await response.text();
             	// 이미지 URL 생성
                const imageUrl = '/free/tui-editor/image-print?filename=' + filename;

                // 에디터에 이미지 삽입
                callback(imageUrl, 'image');
            } catch (error) {
                console.error('이미지 업로드 실패:', error);
            }
        }
    }
});
</script>


<%@ include file="/WEB-INF/views/modules/footer.jsp" %>