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

                <div class="card-body pad">
                    <form role="form" method="post" action="regist.do" name="registForm">
                        <input type="hidden" id="freecontent" name="freecontent">
                        <div class="form-group">
                            <input type="text" id="freetitle" title="제목"
                                name='freetitle' class="form-control notNull" placeholder="제목을 쓰세요">
                        </div>
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
	function regist_go(){
		var form = document.registForm;
		
		var inputNotNull = document.querySelectorAll("input.notNull");
		for(var input of inputNotNull){
			if(!input.value){
				alert(input.getAttribute("title")+"은 필수입니다.");
				input.focus();
				return;
			}
		}
		
		document.querySelector("#freecontent").value = editor.getHTML();
		
		form.action="regist.do";
		form.method="post";
		form.submit();
		
	}	
	 const editor = new toastui.Editor({
	        el: document.querySelector('#content'), // 에디터를 적용할 요소 (컨테이너)
	        height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
	        initialEditType: 'wysiwyg',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
	        initialValue: '',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
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
