<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title><c:choose>
        <c:when test="${requestScope.editMode}">게시글 수정</c:when>
        <c:otherwise>게시글 작성</c:otherwise>
    </c:choose></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>

<body>
    <c:if test="${not empty sessionScope.writeMessage}">
        <script>
            alert("${sessionScope.writeMessage}");
        </script>

        <c:remove var="writeMessage" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.editMessage}">
        <script>
            alert("${sessionScope.editMessage}");
        </script>

        <c:remove var="editMessage" scope="session"/>
    </c:if>

    <%@ include file = "/WEB-INF/jsp/navbar.jsp" %>
    <form action="<c:choose>
        <c:when test="${requestScope.editMode}">/${requestScope.boardType}/edit</c:when>
        <c:otherwise>/${requestScope.boardType}/write</c:otherwise>
    </c:choose>" method="POST">
        <div class="board_wrap">
            <input type="hidden" name="boardType" value="${requestScope.boardType}" />
            <input type="hidden" name="writer" value="${sessionScope.nickname}" />
            <c:if test="${requestScope.editMode}">
                <input type="hidden" name="p_id" value="${requestScope.post.p_id}" />
            </c:if>

            <div class="board_title">
                <strong><c:choose>
                    <c:when test="${requestScope.boardType == 'notice'}">공지사항</c:when>
                    <c:otherwise>자유게시판</c:otherwise>
                </c:choose></strong>
            </div>
            <div class="board_write_wrap">
                <div class="board_write">
                    <div class="title">
                        <dl>
                            <dt>제목</dt>
                            <dd><input type="text" name="title" placeholder="제목 입력" value="${requestScope.post.title}"></dd>
                        </dl>
                    </div>
                    <div class="content">
                        <textarea name="content" placeholder="내용 입력" >${requestScope.post.content}</textarea>
                    </div>
                </div>
                <div class="bt_wrap">
                    <button type="submit">
                        <c:choose>
                            <c:when test="${requestScope.editMode}">수정</c:when>
                            <c:otherwise>작성</c:otherwise>
                        </c:choose>
                    </button>
                    <a href="<c:choose>
                        <c:when test="${requestScope.boardType == 'notice'}">/notice</c:when>
                        <c:otherwise>/free</c:otherwise>
                    </c:choose>" class="cancel">취소</a>
                </div>
            </div>
        </div>
    </form>
</body>
</html>