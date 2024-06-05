<%@ include file="/WEB-INF/views/modules/footer.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/qna/qnaBanner.jsp" %>
<section class="content w-75 p-3 mx-auto">
    <div class="card">
        <div class="card-header with-border">
            <div id="keyword" class="card-tools" style="width:450px;">
                <div class="input-group row">
                    <select class="form-control col-md-3" name="searchType" id="searchType">
                        <option value="t" ${pageMaker.searchType eq 't' ? 'selected':'' }>제 목</option>
                        <option value="c" ${pageMaker.searchType eq 'c' ? 'selected':'' }>내용</option>
                        <option value="tc" ${pageMaker.searchType eq 'tc' ? 'selected':'' }>제목 + 내용</option>																				
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
                    <c:if test="${empty qnaList}">
                        <tr>
                            <td colspan="5">해당 내용이 없습니다.</td>
                        </tr>
                    </c:if>
                    <c:forEach items="${qnaList}" var="qna">
                        <tr>
                            <td>${qna.qnaid }</td>
                            <td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden;
                            white-space: nowrap; text-overflow: ellipsis;">
                                <a href="/qna/detail?qnaid=${qna.qnaid }&from=list">
                                    <span class="col-sm-12 ">${qna.qnatitle }</span>
                                </a>
                            </td>
                            <td>${qna.writer }</td>
                            <td>
                                <fmt:formatDate value="${qna.qnaregdate }" pattern="yyyy-MM-dd"/>
                            </td>
                            <td><span class="badge bg-red">${qna.qnaviewpoint }</span></td>
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
    <button type="button" class="btn btn-primary btn float-right" id="registBtn" onclick="location='/qna/registForm'">질문 작성</button>
	</sec:authorize>
</section>

<%@ include file="/WEB-INF/views/modules/footer.jsp" %>

