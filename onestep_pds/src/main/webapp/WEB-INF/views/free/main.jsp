<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/free/freeBanner.jsp" %>
<section class="content w-75 p-3 mx-auto">
    <div class="card">
        <div class="card-header">
            <div id="keyword" class="card-tools" style="width:450px;border:none !important;">
                <div class="input-group row">
                    <select class="form-control col-md-3" name="searchType" id="searchType">
                        <option value="t" ${pageMaker.searchType eq 't' ? 'selected':'' }>제 목</option>
																	
                    </select>
                    <input class="form-control" type="text" name="keyword" placeholder="검색어를 입력하세요." value="${pageMaker.keyword }"/>
                    <span class="input-group-append">
                        <button class="btn btn-primary" type="button" id="searchBtn" data-card-widget="search" onclick="search_list(1);">
                            <i class="fa fa-fw fa-search"></i>
                        </button>
                    </span>
                </div>
            </div>
        </div>
        <div class="card-body">
            <table class="table table-bordered text-center">
                <thead>
                    <tr>
                        <th style="width:10%;">번 호</th>
                        <th style="width:40%;">제 목</th>
                        <th style="width:15%;">작성자</th>
                        <th>작성일자</th>
                        <th style="width:10%;">조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty freeList}">
                        <tr>
                            <td colspan="5">해당 내용이 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${freeList}" var="free">
                        <tr>
                            <td>${free.freeid }</td>
                            <td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
                            white-space: nowrap; text-overflow: ellipsis;">
                                <a href="detail.do?freeid=${free.freeid }&from=list">
                                    <span class="col-sm-12 ">${free.freetitle }</span>
                                </a>
                            </td>
                            <td>${free.writer }</td>
                            <td>
                                <fmt:formatDate value="${free.freeregdate }" pattern="yyyy-MM-dd"/>
                            </td>
                            <td><span class="badge bg-red">${free.freeviewpoint }</span></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="card-footer">
            <%@ include file="/WEB-INF/views/modules/pagination2.jsp" %>
        </div>
    </div>
    <sec:authorize access="isAuthenticated()">
    <button type="button" class="btn btn-primary btn float-right" id="registBtn" onclick="location='registForm'">글쓰기</button>
	</sec:authorize>
</section>

<%@ include file="/WEB-INF/views/modules/footer.jsp" %>
