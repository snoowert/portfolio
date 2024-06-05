<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/dist/css/adminlte.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/bootstrap/plugins/summernote/summernote-bs4.min.css">
  <nav class="navbar navbar-expand-lg navbar-light bg-light">
  &nbsp;&nbsp;<a class="navbar-brand" href="/main/"><img src="<%=request.getContextPath() %>/resources/img/onestep_logo.png" style="width:50px;height:50px;"></a>
  

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="/free/main">자유게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/qna/main">질문게시판</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/pds/list">자료실</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/devnote/list">개발자노트</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/exp/main">체험관</a>
      </li>
    </ul>
    <ul class="navbar-nav ml-auto">
    	<sec:authorize access="isAnonymous()">
	    	<li class="nav-item">
	    		<a class="nav-link" href="/member/registSelect">회원가입</a>
	    	</li>
	    	<li class="nav-item">
	    		<a class="nav-link" href="/member/loginForm">로그인</a>
	    	</li>
    	</sec:authorize>
    	<sec:authorize access="isAuthenticated()">
    		<li class="nav-item">
	    		<a class="nav-link" href="/member/mypage">${loginUser.username }</a>
	    	</li>
	    	<li class="nav-item">
	    		<a class="nav-link" href="/member/logout">로그아웃</a>
	    	</li>
    	</sec:authorize>
    </ul>
  </div>
</nav>
