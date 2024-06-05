<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.pms.dto.CalendarVO" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.css">




<style>
    /* 추가적인 스타일링이 필요한 경우 여기에 추가 */
</style>
<%@ include file="/WEB-INF/views/modules/subbar.jsp" %>
<div class="container-fluid d-flex justify-content-center">
    <div id="calendar" style="width:60%; float:left;"></div>
</div>

   

    




<%@ include file="/WEB-INF/views/modules/footer.jsp" %>
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.18.1/moment.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.10.1/locales-all.js"></script>

<script src="<%=request.getContextPath() %>/resources/bootstrap/plugins/jquery/jquery.min.js"></script>
	
<script>
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        headerToolbar: {
            start: 'prev today',
            center: 'title',
            end: 'next'
        },
        titleFormat: function(date) {
            return date.date.year + '년 ' + (parseInt(date.date.month) + 1) + '월';
        },
        selectable: true,
        droppable: true,
        editable: true,
        nowIndicator: true,
        select: function(arg) {
            // 캘린더에서 선택한 날짜 정보를 변수에 저장
            var startDate = arg.startStr;
            var endDate = arg.endStr;

            // 프롬프트 창을 통해 일정 제목을 입력 받음
            var title = prompt('일정 제목을 입력하세요.');
			if(title == null){
				return;
			}
            // 회원번호와 프로젝트번호
            var memberId = ${loginUser.memberid}+0; // 예시
            var projectId = ${projectId}; // 예시

            // 입력받은 정보를 서버로 전송하여 DB에 저장 요청
            saveEventToDB(title, startDate, endDate, memberId, projectId);
        },
         events : [
                <c:forEach var="event" items="${calendar}">
                    {
                    	title: '${event.title}',
                        start: '${event.startDate}',
                        end: '${event.endDate}',
                        id: '${event.id}',
                    },
                </c:forEach>
            ], 
         eventClick: function (info){
             if(confirm("'" + info.event.title + "' 일정을 삭제하시겠습니까 ?")){
                 // 확인 클릭 시
                 info.event.remove();

                 // 삭제된 이벤트 정보를 서버로 전송하여 DB에서도 삭제
                 var eventid = info.event.id;
                 $.ajax({
                     url: "/devnote/deleteCalendar",
                     method: "POST",
                     data: {
                    	'id' : eventid, 
                     },
                     success: function(response) {
                         // 성공적으로 삭제되었음을 사용자에게 알릴 수 있음
                         alert("삭제되었습니다.");
                         window.location.reload();
                     },
                     error: function(xhr, status, error) {
                         // 오류가 발생했을 때 사용자에게 알릴 수 있음
                         alert("삭제에 실패하였습니다.");
                     }
                 });
             }
         }
    });
    calendar.render();

    // 서버로 일정을 저장하는 함수
    function saveEventToDB(title, startDate, endDate, memberId, projectId) {
        $.ajax({
            url: '/devnote/save-event',
            method: 'POST',
            data: {
                'title': title,
                'startd': startDate,
                'endd': endDate,
                'memberId': memberId,
                'projectId': projectId
            },
            success: function(response) {
                alert('일정이 성공적으로 저장되었습니다.');
                // 저장 성공 시 추가적인 작업 수행 가능
                calendar.addEvent({
                    title: title,
                    start: startDate,
                    end: endDate
                });
                window.location.reload();
            },
            error: function(xhr, status, error) {
                alert('일정 저장 중 오류가 발생했습니다: ' + error);
                // 저장 실패 시 오류 메시지 표시
            }
        });
    }
});


</script>
