<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/views/project/pmsBanner.jsp" %>
<section class="content w-75 p-3 mx-auto">
    <div class="card">
        <div class="card-header with-border">
            <div id="keyword" class="card-tools" style="width:450px;">
                <div class="input-group row">
                    <select class="form-control col-md-3" name="searchType" id="searchType">
                        <option value="n" ${pageMaker.searchType eq 'n' ? 'selected':'' }>제 목</option>                                                                
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
            <div class="row">
                <c:forEach items="${projectList}" var="project">
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title"style="font-weight: bold;">${project.projectName }</h5>
                                <p class="card-text">${project.projectDescription }</p>
                            </div>
                            <div class="card-footer">
                            	<sec:authorize access="hasAnyAuthority('developer', 'manager')">
							    <a href="/devnote/main?projectId=${project.projectId }" class="btn btn-primary">자세히 보기</a>
								</sec:authorize>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <c:if test="${empty projectList}">
                <p>해당 내용이 없습니다.</p>
            </c:if>
        </div>
        <div class="card-footer">
            <%@ include file="/WEB-INF/views/modules/pagination1.jsp" %>
        </div>
    </div>
    <sec:authorize access="hasAnyAuthority('developer', 'manager')">
    <button type="button" class="btn btn-primary btn float-right" id="registBtn" onclick="location='registProject'">새 프로젝트</button>
    </sec:authorize>
</section>

<%@ include file="/WEB-INF/views/modules/footer.jsp" %>
