<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>


<style>
    .image-button-img {
        max-width: 100px; 
        max-height: 150px; 
        display: block;
        margin: 0 auto; 
    }
</style>
<br>
<section class="content col-md-12">
		<div class="row">
		    <div class="col-md-7">
		        <div class="card card-primary">
		            <div class="card-header">
		                <h3 class="card-title">Camera</h3>
		            </div>
		            <div class="card-body">
					    <video src="<%=request.getContextPath() %>${exp.expname}" style="width:100%; height:610px" controls autoplay loop id="myVideo"></video>
					</div>

		        </div>
		    </div>
		
		    <div class="col-md-5 align-items-stretch">
		        <div class="card card-primary">
		            <div class="card-header">
		                <h3 class="card-title">LED</h3>
		            </div>
		            <div class="card-body">
		                <div class="row justify-content-center">
		                    <button class="image-button btn-primary" style="background:orange;color:black" onclick="video_on('sos');">
		                        <img src="<%=request.getContextPath() %>/resources/img/light/sos.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" style="background:orange;color:black" onclick="video_on('4WayFlasher');">
		                        <img src="<%=request.getContextPath() %>/resources/img/light/4wayFlasher.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" style="background:orange;color:black" onclick="video_on('fade');">
		                        <img src="<%=request.getContextPath() %>/resources/img/light/fade.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                </div>
		            </div>
		        </div>
		        <div class="card card-primary">
		            <div class="card-header">
		                <h3 class="card-title">차량 조작</h3>
		            </div>
		            <div class="row">
		            <div class="card-body">
		                <div class="row justify-content-center">
		                    <button class="image-button btn-primary" onclick="video_on('goLeft');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/goLeft.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" onclick="video_on('go');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/go.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" onclick="video_on('goRight');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/goRight.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                </div>
		                <br>
		                <div class="row justify-content-center">
		                    <button class="image-button btn-primary" onclick="video_on('turnLeft');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/turnLeft.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-danger" onclick="video_on('all');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/all.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" onclick="video_on('turnRight');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/turnRight.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                </div>
		                <br>
		                <div class="row justify-content-center">
		                    <button class="image-button btn-primary" onclick="video_on('backLeft');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/backLeft.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary " onclick="video_on('back');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/back.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" onclick="video_on('backRight');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/backRight.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                </div>
		                </div>
		            </div>
		            </div>
		        </div>
		    </div>

			<div class="row col-sm-12 mx-auto">
			    <div class="card card-primary mx-auto w-100">
			        <div class="card-header">
			            <h3 class="card-title">신호 패널</h3>&nbsp;&nbsp;
			        </div>
			        <div class="card-body mx-auto w-100">
			            <div class="row justify-content-center">
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('rock');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/rock.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('across');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/across.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('speed50');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/speed50.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('person');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/person.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('stopsign');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/stop.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('slow');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/slow.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('car');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/car.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('bike');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/bike.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button" onclick="video_on('keepout');" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/keepout.png" class="image-button-img">
			                    </button>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>

			
</section>



			

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script>
	function video_on(videoName) {
		$.ajax({
	        url: '/exp/getVideo',
	        type: 'GET',
	        data: { videoName: videoName },
	        success: function(response) {
	            // 서버로부터 받은 응답 데이터를 이용하여 동영상을 변경합니다.
	            var videoSrc = '<%=request.getContextPath() %>' + response;
	            var video = document.getElementById('myVideo');
	            video.src = videoSrc;
	            video.load();
	        },
	        error: function(xhr, status, error) {
	            console.error('Ajax 요청 중 에러가 발생했습니다:', error);
	        }
	    });
	}
</script>
<%@ include file="/WEB-INF/views/modules/footer.jsp" %>