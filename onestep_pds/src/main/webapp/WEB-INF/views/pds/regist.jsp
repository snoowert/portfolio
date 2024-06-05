<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료 등록</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ include file="/WEB-INF/views/pds/pdsBanner.jsp" %>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<body>
<section class="container">
	<dev class="container-fluid col-sm-10">
		<form class="mb-3" name="pdsform" id="pdsform" action="/pds/regist" method="POST" enctype="multipart/form-data">
			<input id="pdstitle" name="pdstitle" type="text" class="form-control" placeholder="자료명">
			<br>
			<input type="hidden" id="pdscontent" name="pdscontent">
			<input type="hidden" id="memberid" name="memberid" value="${loginUser.memberid}">
			
			<div id="content">
			</div>
			<div class="form-group">								
				<div class="card card-outline card-success">
					<div class="card-header">
						<h5 style="display:inline;line-height:40px;">첨부파일 : </h5>
						&nbsp;&nbsp;<button class="btn btn-xs btn-primary"
						onclick="addFile_go();"	type="button" id="addFileBtn">Add File</button>
					</div>									
					<div class="card-footer fileInput">
					</div>
				</div>
			</div>
			<br>
			<input type="button" class="btn btn-primary" value="등록" onclick="savePost()">&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-default" value="취소" onclick="history.go(-1)">
		</form>
	</dev>
</section>
</body>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	var dataNum=0;
	var url = '/pds/regist';
	function addFile_go(){
		//alert("add btn");
		
	
		if($('input[name="uploadFile"]').length >=5){
			alert("파일추가는 5개까지만 가능합니다.");
			return;
		}
		
		var div=$('<div>').addClass("inputRow").attr("data-no",dataNum);	
		var input=$('<input>').attr({"type":"file","name":"uploadFile"}).css("display","inline");
		div.append(input).append("<button onclick='remove_go("+dataNum+");' style='border:0;outline:0;' class='badge bg-red' type='button'>X</button>");	
		$('.fileInput').append(div);
		
		dataNum++;	
	}
	
	function remove_go(index){
		//alert(index);
		$('div[data-no="'+index+'"]').remove();
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
                    const response = await fetch('/pds/tui-editor/image-upload', {
                        method: 'POST',
                        body: formData
                    });

                    // 서버에서 반환된 파일명
                    const filename = await response.text();
                 	// 이미지 URL 생성
                    const imageUrl = '/pds/tui-editor/image-print?filename=' + filename;

                    // 에디터에 이미지 삽입
                    callback(imageUrl, 'image');
                } catch (error) {
                    console.error('이미지 업로드 실패:', error);
                }
            }
        }
    });
    
    async function savePost() {
        // 1. 콘텐츠 입력 유효성 검사
        if (editor.getMarkdown().length < 1) {
            alert('에디터 내용을 입력해 주세요.');
            throw new Error('editor content is required!');
        }
        if($("input[name='pdstitle']").val()==""){ //form.title.value
    		alert("제목은 필수입니다.");
    		$("input[name='pdstitle']").focus();
    		return;
    	}
    	
    	var inputs = $('input[name="uploadFile"]');
    	for(var input of inputs){
    		//console.log(input.name+" : "+input.value);
    		if(input.value==""){
    			alert("파일을 선택하세요.");
    			input.focus();
    			input.click();
    			return;
    		}
    	}	
    	document.querySelector("#pdscontent").value = editor.getHTML();
    	
    	$("form[name='pdsform']").submit();
        
        
    }
    
</script>
</html>