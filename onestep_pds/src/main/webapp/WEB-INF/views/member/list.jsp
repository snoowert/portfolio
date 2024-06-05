<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<%@ include file="/WEB-INF/views/base.jsp" %>
</head>
<body>
<section class="container">
	<div class="row mx-auto">
		<div class="bg-secondary col-sm-10" style="height:100%;">
			<h1 style="margin-top:3rem">회원목록</h1>
		</div>	
	</div>
	<br>
	<div class="row mx-auto">
		<div class="col-md-10 ">
			<form action="" role="form">
				<div class="input-group">
					<select style="width:150px; height:50px; font-size: large;"  name="searchType" onchange="search_list(1)">
						<option value="nd" ${pageMaker.searchType eq 'nd' ? 'selected':'' }>이용중인 회원</option>
						<option value="d" ${pageMaker.searchType eq 'd' ? 'selected':'' }>탈퇴회원</option>
					</select>
					<input type="hidden" class="form-control form-control-lg" placeholder="검색어" name="keyword" value="">
					
				</div>
			</form>
		</div>
	</div>
	<div class="row mx-auto">
		<div class="card-body col-sm-10">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th class="col-sm-1">번호</th>
						<th class="col-sm-4">회원명</th>
						<th class="col-sm-2">탈퇴여부</th>
						<th class="col-sm-2">가입일자</th>
						<th class="col-sm-3">권한</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${empty memberList}">
						<td colspan="5">회원이 존재하지 않습니다.</td>
					</c:if>
					<c:forEach var="member" items="${memberList}">
					<tr>
						<td>${member.memberid}</td>
						<td><a href="Mdetail?memberid=${member.memberid}">${member.username }</a></td>
						<td>${member.isDelete }</td>
						<td><fmt:formatDate pattern="yyyy.MM.dd" value="${member.regdate}"/></td>
						<td>${member.authority }</td>
					<tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<nav aria-label="Navigation" class="row col-sm-10">
		<ul class="pagination justify-content-center my-2" style="width:80%; margin-left: 5rem">
			<li class="page-item">
				<a class="page-link" href="javascript:search_list(1);">
					<i class="fas fa-angle-double-left"></i>
				</a>
			</li>
			
			<li class="page-item">
				<a class="page-link" href="javascript:search_list(${pageMaker.prev ? pageMaker.startPage-1 : pageMaker.page});">
					<i class="fas fa-angle-left"></i>
				</a>						
			</li>
			<c:forEach var="pageNum" begin="${pageMaker.startPage }" 
									 end="${pageMaker.endPage }" >
			<li class="page-item ${pageMaker.page == pageNum?'active':''}">
				<a class="page-link" href="javascript:search_list(${pageNum });">
					${pageNum }
				</a>
			</li>
			</c:forEach>
				
			<li class="page-item">
				<a class="page-link" href="javascript:search_list(${pageMaker.next ? pageMaker.endPage+1 :pageMaker.page});">
					<i class="fas fa-angle-right"></i>
				</a>						
			</li>	
			<li class="page-item">
				<a class="page-link" href="javascript:search_list(${pageMaker.realEndPage});">
					<i class="fas fa-angle-double-right"></i>
				</a>
			</li>
		</ul>
		
	</nav>  
</section>
<%@ include file="/WEB-INF/views/modules/pagination3.jsp" %>
</body>
</html>