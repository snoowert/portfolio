<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/modules/header.jsp" %>
<%@ include file="/WEB-INF/views/base.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navigation Bar Example</title>
    <style>
        /* body에 배경 이미지 설정 */
        body {
            background-image: url('<%=request.getContextPath() %>/resources/img/banner/메인화면.jpg');
            background-size: cover;
            background-position: center;
            margin: 0;
            padding: 0;
        }
        
        section {
            padding-top: 400px; /* 네비게이션 바의 높이만큼 여백 추가 */
        }
    </style>
</head>
<body>
    
    <br><br><br><br><br><br><br>
    <!-- 페이지 내용 -->
    <section>
    <div class="row justify-content-center">
        <!-- 좌측 영역 -->
        <div class="col-md-5 col-md-offset-1">
            <div class="card">
            	<div class="card-header">
                    <h5 class="card-title">자유게시판</h5>
                </div>
                <div class="card-body">
                    <!-- 좌측 테이블 -->
                    <table class="table table-bordered text-center">
                        <tbody>
                            <c:if test="${empty freeList}">
                                <tr>
                                    <td colspan="5">해당 내용이 없습니다.</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${freeList}" var="free">
                                <tr>
                                    <td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
                                        <a href="/free/detail.do?freeid=${free.freeid }&from=list">
                                            <span class="col-sm-12 ">${free.freetitle }</span>
                                        </a>
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${free.freeregdate }" pattern="yyyy-MM-dd"/>
                                    </td>
                                    <td><span class="badge bg-red">${free.freeviewpoint }</span></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- 우측 영역 -->
        <div class="col-md-5">
            <div class="card">
            	<div class="card-header">
                    <h5 class="card-title">질문게시판</h5>
                </div>
                <div class="card-body">
                    <!-- 우측 테이블 -->
                    <table class="table table-bordered text-center">
                        <tbody>
                            <c:if test="${empty qnaList}">
                                <tr>
                                    <td colspan="3">해당 내용이 없습니다.</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${qnaList}" var="qna">
                                <tr>
                                    <td id="boardTitle" style="text-align:left;max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;">
                                        <a href="/qna/detail.do?qnaid=${qna.qnaid }&from=list">
                                            <span class="col-sm-12 ">${qna.qnatitle }</span>
                                        </a>
                                    </td>
                                    <td><fmt:formatDate value="${qna.qnaregdate }" pattern="yyyy-MM-dd"/></td>
                                    <td><span class="badge bg-red">${qna.qnaviewpoint }</span></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>



<%@ include file="/WEB-INF/views/modules/footer.jsp" %>