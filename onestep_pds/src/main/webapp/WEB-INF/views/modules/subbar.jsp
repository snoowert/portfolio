<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
<div class="collapse navbar-collapse" >
	<ul class="navbar-nav">
		<li class="nav-item">
			<a class="nav-link" href="/devnote/main?projectId=${project.projectId}">프로젝트 홈</a>
		</li>
		<div id="projectMenu" class="row">
			<li class="nav-item">
				<a class="nav-link" href="/devnote/feed?projectId=${project.projectId }">피드</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/devnote/calendar?projectId=${project.projectId }">캘린더</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/devnote/projectFile?projectId=${project.projectId }">파일&nbsp;</a>
			</li>
		</div>
	</ul>

		<div class="dropdown">
			<button class="btn btn-light rounded-circle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false" >
				&nbsp;⋮&nbsp;
			</button>
			<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				<sec:authorize access="hasAnyAuthority('manager')">
				<li><a class="dropdown-item btn" href="/devnote/deleteProject?projectId=${project.projectId }">프로젝트 삭제</a></li>
				</sec:authorize>
				<div id="projectMenu2">
					<li><a class="dropdown-item btn" href="/devnote/deleteTeam?memberid=${loginUser.memberid}&projectid=${project.projectId }">프로젝트 탈퇴</a></li>
				</div>
			</ul>
		</div>
</div>
	</nav>