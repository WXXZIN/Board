<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>메인 페이지</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>

<body>
    <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
    <div class="board_wrap">
        <div class="board_title">
            <strong>공지사항</strong>
        </div>
        <div class="board_list_wrap">
            <div class="board_list">
                <c:choose>
                    <c:when test="${not empty requestScope.noticeList}">
                        <c:forEach var="notice" items="${requestScope.noticeList}">
                            <div>
                                <div class="title"><a href="/notice/view?p_id=${notice.p_id}">${notice.title}</a></div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div>공지사항이 없습니다.</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <p class="more"><a href="/notice">더보기</a></p>
        </div>

        <br><br>

        <div class="board_title">
            <strong>자유게시판</strong>
        </div>
        <div class="board_list_wrap">
            <div class="board_list">
                <c:choose>
                    <c:when test="${not empty requestScope.freeList}">
                        <c:forEach var="free" items="${requestScope.freeList}">
                            <div>
                                <div class="title"><a href="/free/view?p_id=${free.p_id}">${free.title}</a></div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <div>게시글이 없습니다.</div>
                    </c:otherwise>
                </c:choose>
            </div>
            <p class="more"><a href="/free">더보기</a></p>
        </div>
    </div>
</body>
</html>