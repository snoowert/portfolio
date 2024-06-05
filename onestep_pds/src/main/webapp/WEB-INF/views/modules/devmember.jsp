<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
<div class="" style="padding: 50px; float:right;">  
    <!--참여자 (반복)-->
    <h5>프로젝트 참여자 목록</h5>
    <c:forEach var="member" items="${teamList}">
        <div class="card" style="width:250px;">
            <h3 class="card-title" style="padding: 30px;">${member.membername}</h3>
        </div>
    </c:forEach>
    
    <!--참여버튼-->
    <button id="joinButton" class="btn btn-primary" type="button" style="width:100%;" onclick="location='/devnote/insertTeam?memberid=${loginUser.memberid}&projectid=${project.projectId }'"> 참여하기 </button>                    
</div>
<script>
    // JavaScript로 teamList에 loginUser.memberid가 존재하는지 확인
    var teamList = ${checkList}; // teamList를 JavaScript 배열로 변환
    var loginUserMemberId = "${loginUser.memberid}"; // loginUser.memberid를 JavaScript 변수로 저장
    
    // teamList에 loginUser.memberid가 존재하는지 확인하는 함수
    function isMemberInTeamList(memberId) {
    // teamList 배열을 순회하며 VO 객체를 가져옵니다.
    for (var i = 0; i < teamList.length; i++) {
        var member = teamList[i];
        // VO 객체의 memberid와 projectId를 비교하여 일치하는 경우 true를 반환합니다.
        if (member == Number(memberId)) {
            return true;
        }
    }
    // 일치하는 VO 객체가 없는 경우 false를 반환합니다.
    return false;
}
    
    // 만약 loginUser.memberid가 teamList에 존재한다면 버튼 숨기기
    if (isMemberInTeamList(loginUserMemberId)) {
        document.getElementById("joinButton").style.display = "none";
    }
    else{
    	document.getElementById("projectMenu").style.display = "none";
    	document.getElementById("projectMenu2").style.display = "none";
    }
</script>