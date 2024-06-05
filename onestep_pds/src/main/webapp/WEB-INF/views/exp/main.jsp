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
    body {
            background-image: url('<%=request.getContextPath() %>/resources/img/banner/체험관화면.jpg');
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 0;
        }
</style>
<br>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Video Streaming</title>
</head>
<section class="content col-md-12">
		<div class="row">
		    <div class="col-md-8">
		        <div class="card card-primary">

		            <div class="card-body">
					    <img id="videoElement" style="width:100%; height:865px;"></img>
					</div>

		        </div>
		    </div>
		
		    <div class="col-md-3 align-items-stretch">
			    <div class="card card-primary">
			            <div class="card-header">
			                <h3 class="card-title">자율 주행</h3>
			            </div>
			            <div class="card-body">
			               <div class="row justify-content-center">
		               		<button class="image-button" onclick="startDriving()" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/movement/AutoStart2.png" class="image-button-img">
			                </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="image-button" onclick="stopDriving()" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/movement/AutoStop2.png" class="image-button-img">
			                </button>
						</div> 
			            </div>
			        </div>
		        <div class="card card-primary">
		            <div class="card-header">
		                <h3 class="card-title">LED</h3>
		            </div>
		            <div class="card-body">
		                <div class="row justify-content-center">
		                    <button class="image-button btn-primary" style="background:orange;border:none;color:black" onclick="lightcontrol('sos');">
		                        <img src="<%=request.getContextPath() %>/resources/img/light/sos.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" style="background:orange;border:none;color:black" onclick="lightcontrol('danger');">
		                        <img src="<%=request.getContextPath() %>/resources/img/light/4wayFlasher.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" style="background:orange;border:none;color:black" onclick="lightcontrol('light');;">
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
		                    <button class="image-button btn-primary" onclick="manualControl('left');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/goLeft.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" onclick="manualControl('forward')">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/go.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" onclick="manualControl('right');">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/goRight.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                </div>
		                <br>
		                <div class="row justify-content-center">
		                    <button class="image-button btn-primary" onclick="manualControl('turn_left')">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/turnLeft.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-danger" onclick="manualControl('stop')">
		                        <img src="<%=request.getContextPath() %>/resources/img/sign/stop.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" onclick="manualControl('turn_right')">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/turnRight.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                </div>
		                <br>
		                <div class="row justify-content-center">
		                    <button class="image-button btn-primary" onclick="manualControl('bleft')">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/backLeft.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary " onclick="manualControl('backward')">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/back.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                    <button class="image-button btn-primary" onclick="manualControl('bright')">
		                        <img src="<%=request.getContextPath() %>/resources/img/movement/backRight.png" class="image-button-img">
		                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                </div>
		                <br>
		                
		                </div>
		                </div>
		            </div>
		          </div>
	           <div class="col-md-1">
	            <div class="card card-primary">
	            <div class="card-header">
	                <h3 class="card-title">SPEED</h3>
	            </div>
	            <div class="card-body">
	                <div class="row justify-content-center">
	                
	                    <button class="image-button btn-primary" style="background:transparent;border:none;color:black" onclick="speedcontrol('speed40');">
	                        <img src="<%=request.getContextPath() %>/resources/img/speed/speed40.png" class="image-button-img">
	                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    <button class="image-button btn-primary" style="background:transparent;border:none;color:black" onclick="speedcontrol('speed50');">
	                        <img src="<%=request.getContextPath() %>/resources/img/speed/speed50.png" class="image-button-img">
	                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    <button class="image-button btn-primary" style="background:transparent;border:none;color:black" onclick="speedcontrol('speed60');">
	                        <img src="<%=request.getContextPath() %>/resources/img/speed/speed60.png" class="image-button-img">
	                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    <button class="image-button btn-primary" style="background:transparent;border:none;color:black" onclick="speedcontrol('speed80');">
	                        <img src="<%=request.getContextPath() %>/resources/img/speed/speed80.png" class="image-button-img">
	                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                    <button class="image-button btn-primary" style="background:transparent;border:none;color:black" onclick="speedcontrol('speed100');">
	                        <img src="<%=request.getContextPath() %>/resources/img/speed/speed100.png" class="image-button-img">
	                    </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			                    <button class="image-button signButton" id="rock" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/rock.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button signButton" id="across" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/across.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button signButton" id="speed50"  style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/speed50.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button signButton" id="person"  style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/person.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button signButton" id="stop"  style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/stop.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button signButton" id="slow"  style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/slow.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button signButton" id="car"  style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/car.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button signButton" id="motorcycle" style="width:116px;height:116px">
			                        <img src="<%=request.getContextPath() %>/resources/img/sign/bike.png" class="image-button-img">
			                    </button>
			                </div>
			                <div class="col my-auto">
			                    <button class="image-button signButton" id="keepout"  style="width:116px;height:116px">
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
	document.querySelectorAll('.signButton').forEach(button => {
	    button.style.backgroundColor = 'white';
	});
	function manualControl(direction) {
	    fetch('http://127.0.0.1:5000/onestep/manual-control', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify({ direction: direction })
	    })
	    .then(response => response.json())
	    .then(data => console.log('수동 주행:', data))
	    .catch(error => console.error('수동 주행 오류:', error));
	}
	function lightcontrol(direction) {
	    fetch('http://127.0.0.1:5000/onestep/light', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify({ direction: direction })
	    })
	    .then(response => response.json())
	    .then(data => console.log('불빛:', data))
	    .catch(error => console.error('불빛 오류:', error));
	}
	function speedcontrol(direction) {
	    fetch('http://127.0.0.1:5000/onestep/speed', {
	        method: 'POST',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        body: JSON.stringify({ direction: direction })
	    })
	    .then(response => response.json())
	    .then(data => console.log('스피드:', data))
	    .catch(error => console.error('스피드 오류:', error));
	}
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
	window.onload = function() {
		
		var videoElement = document.getElementById('videoElement');
		var sourceUrl = 'http://127.0.0.1:5000/onestep/video';
		setInterval(fetchLabelData, 200);
		videoElement.src = sourceUrl; // 비디오 요소의 src 속성에 비디오 URL 설정
	};	
	
	// 서버에서 라벨 데이터를 가져오는 함수
	function fetchLabelData() {
		document.querySelectorAll('.signButton').forEach(button => {
		    button.style.backgroundColor = 'white';
		});
	    fetch('http://127.0.0.1:5000/onestep/get_label_data')
	    .then(response => response.json())
	    .then(data => {
	        // 받은 데이터를 이용하여 라벨을 업데이트하는 함수 호출
	        updateLabel(data);
	    })
	    .catch(error => console.error('라벨 데이터를 가져오는 중 오류 발생:', error));
	}
	function updateLabel(labelData) {
	    // 라벨 데이터 파싱
	    var label = labelData.label;

	    // 라벨에 해당하는 버튼 선택
	    var button = document.getElementById(label);

	    // 선택한 버튼의 배경색 변경
	    if (button) {
	        button.style.backgroundColor = 'yellow';
	    }
	}
	function startDriving() {
        fetch('http://127.0.0.1:5000/onestep/start-driving', { method: 'POST' })
            .then(response => response.json())
            .then(data => console.log('주행 시작:', data))
            .catch(error => console.error('주행 시작 오류:', error));
    }

    function stopDriving() {
        fetch('http://127.0.0.1:5000/onestep/stop-driving', { method: 'POST' })
            .then(response => response.json())
            .then(data => console.log('긴급 정지:', data))
            .catch(error => console.error('긴급 정지 오류:', error));
    }
	
</script>
<%@ include file="/WEB-INF/views/modules/footer.jsp" %>
