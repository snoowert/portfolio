<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료 수정</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ include file="/WEB-INF/views/pds/pdsBanner.jsp" %>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
</head>
<body>
<section class="container">
	<dev class="container-fluid col-sm-10">
		<form class="mb-3" enctype="multipart/form-data" role="form" method="post" action="modify.do" name="modifyForm">
			<label class="form-label">제목</label>
			<input id="pdstitle" name="pdstitle" type="text" class="form-control" placeholder="자료명" value="${pds.pdstitle }">
			<input type="hidden" name="pdscontent" id="pdscontent">
			<input type="hidden" name="pdsid" id="pdsid" value="${pds.pdsid }">
			<br>
			<!-- 
			<div class="row">
			&nbsp;&nbsp;<label class="form-label">파일</label>&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-default" value="업로드">
			</div>		
			<ul>
				<div class="row">
					<li>11</li>&nbsp;
					<a href="">x</a>
				</div>
			</ul>
			<br>
			<label class="form-label">자료 설명</label>
			<textarea  id="content" class="form-control" rows="5" placeholder="내용을 입력해주세요."></textarea>
			<br>-->
			<div id="content">
			</div>
			<div class="form-group">								
				<div class="card card-outline card-success">
					<div class="card-header">
						<h3 style="display:inline;line-height:40px;">첨부파일 : </h3>
						&nbsp;&nbsp;
						<button class="btn btn-primary"	onclick="addFile_go()" type="button" id="addFileBtn">Add File</button>
					</div>									
					<div class="card-footer fileInput">
						<ul class="mailbox-attachments d-flex align-items-stretch clearfix">
							<!-- 첨부파일 썸네일 -->		
							
							<c:forEach items="${pds.pdsfilelist }" var="pdsfile" >
							<li class="attach-item thumb${pdsfile.pdsfileid}" file-name="${pdsfile.pdsfilename.split('\\$\\$')[1] }" target-ano="${pdsfile.pdsfileid }" >																			
								<div class="mailbox-attachment-info ">
									<a class="mailbox-attachment-name" name="attachedFile" href="<%=request.getContextPath()%>/getFile.do?pdsfileid=${pdsfile.pdsfileid} " >													
										<i class="fas fa-paperclip"></i>
										${pdsfile.pdsfilename.split('\\$\\$')[1] }&nbsp;&nbsp;
										<button type="button" 
											onclick="removeFile_go('thumb${pdsfile.pdsfileid}');return false;"
											style="border:0;outline:0;" class="badge bg-red">X</button>																									
									</a>													
								</div>
							</li>	
							</c:forEach>									
						</ul>
						<br/>														
					</div>
				</div>
			</div>
			<br>
			<input type="button" class="btn btn-primary" value="수정" onclick="modify_submit();">&nbsp;&nbsp;&nbsp;
			<input type="button" class="btn btn-default" value="취소"  onclick="history.go(-1);">
		</form>
	</dev>
</section>
</body>
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
const editor = new toastui.Editor({
    el: document.querySelector('#content'), // 에디터를 적용할 요소 (컨테이너)
    height: '500px',                        // 에디터 영역의 높이 값 (OOOpx || auto)
    initialEditType: 'wysiwyg',            // 최초로 보여줄 에디터 타입 (markdown || wysiwyg)
    initialValue: '${pds.pdscontent}',     // 내용의 초기 값으로, 반드시 마크다운 문자열 형태여야 함
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

var dataNum=0;
function addFile_go(){
	//alert("add file btn");
	var attachedFile=$('a[name="attachedFile"]').length; //기존파일
	var inputFile=$('input[name="uploadFile"]').length;	 //추가된 파일
	var attachCount=attachedFile+inputFile; //기존파일 + 추가된파일 개수
	
	if(attachCount >=5){
		alert("파일추가는 5개까지만 가능합니다.");
		return;
	}
	
	var div=$('<div>').addClass("inputRow").attr("data-no",dataNum);		
	var input=$('<input>').attr({"type":"file","name":"uploadFile","onchange":"fileChange_go("+dataNum+");"}).css("display","inline");
	div.append(input).append("<button onclick='remove_go("+dataNum+");' style='border:0;outline:0;' class='badge bg-red' type='button'>X</button>");		
	$('.fileInput').append(div);
	dataNum++;		
	
}

function removeFile_go(className){
	//alert(className);
	let li = $('li.'+className);
	
	if(!confirm(li.attr("file-name")+"을 정말 삭제하시겠습니까?")){
		return;
	}    
	
	li.remove();
	
	var input=$('<input>').attr({"type":"hidden",
		 "name":"deleteFile",
		 "value":li.attr("target-ano")
		}); 
	$('form[role="form"]').prepend(input);
}
function fileChange_go(dataNum){
    //alert("file change");
    let input = $('div[data-no="'+dataNum+'"] input[type="file"]')[0];
    let file = input.files[0];
	
}
function remove_go(dataNum){
	let div = $('div[data-no="'+dataNum+'"]');
	div.remove();
}

function modify_submit(){
	//alert("submit btn");
	let form = $('form[name="modifyForm"]');
	
	//유효성 체크
	if($("input[name='pdstitle']").val()==""){
		alert(input.name+"은 필수입니다.");
		$("input[name='pdstitle']").focus();
		return;
	}
	
	//파일 첨부확인
	let files = $('input[name="uploadFile"]');
	for(var file of files){
		console.log(file.name+" : "+file.value);
		if(file.value==""){
			alert("파일을 선택하세요.");
			file.focus();
			return;
		}
	}	
	document.querySelector("#pdscontent").value = editor.getHTML();
	form.submit();

}
</script>
</html>