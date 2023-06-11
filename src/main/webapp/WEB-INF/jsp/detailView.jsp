<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>${requestScope.post.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <script>
        function editPost() {
            window.location.href = '/${requestScope.post.boardType}/edit?p_id=${requestScope.post.p_id}';
        }

        function submitDeleteForm() {
            let result = confirm('정말 삭제하시겠습니까?');

            if (result > 0) {
                document.getElementById('deleteForm').submit();
            }
        }
    </script>
</head>

<body>
    <c:if test="${not empty sessionScope.editMessage}">
        <script>
            alert("${sessionScope.editMessage}");
        </script>

        <c:remove var="editMessage" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.deleteMessage}">
        <script>
            alert("${sessionScope.deleteMessage}");
        </script>

        <c:remove var="deleteMessage" scope="session"/>
    </c:if>

    <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
    <div class="board_wrap">
        <div class="board_title">
            <strong>
                <c:choose>
                    <c:when test="${requestScope.boardType == 'notice'}">공지사항</c:when>
                    <c:otherwise>자유게시판</c:otherwise>
                </c:choose>
            </strong>
            <p>
                <c:choose>
                    <c:when test="${requestScope.boardType == 'notice'}">공지사항을 빠르고 정확하게 안내해드립니다.</c:when>
                    <c:otherwise>자유로운 글쓰기가 가능한 게시판입니다.</c:otherwise>
                </c:choose>
            </p>
        </div>
        <div class="board_view_wrap">
            <div class="board_view">
                <div class="title">${requestScope.post.title}</div>
                <div class="detail">
                    <dl>
                        <dt>작성자</dt>
                        <dd>${requestScope.post.writer}</dd>
                    </dl>
                    <dl>
                        <dt>작성일</dt>
                        <dd>
                            <fmt:parseDate value="${requestScope.post.regDate}" pattern="yyyy-MM-dd" var="parsedDateTime" type="both" />
                            <fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd" />
                        </dd>
                    </dl>
                </div>
                <div class="content">${requestScope.post.content}</div>
            </div>
            <div class="bt_wrap">
                <c:choose>
                    <c:when test="${requestScope.post.boardType == 'notice' && sessionScope.nickname == 'Admin'}">
                        <a href="javascript:void(0);" class="bt" onclick="editPost()">수정</a>
                        <a href="javascript:void(0);" class="bt" onclick="submitDeleteForm()">삭제</a>
                    </c:when>
                    <c:when test="${requestScope.post.boardType == 'free' && (sessionScope.nickname == requestScope.post.writer || sessionScope.nickname == 'Admin')}">
                        <a href="javascript:void(0);" class="bt" onclick="editPost()">수정</a>
                        <a href="javascript:void(0);" class="bt" onclick="submitDeleteForm()">삭제</a>
                    </c:when>
                </c:choose>
                <a href="/${requestScope.post.boardType}" class="bt">목록</a>
            </div>
        </div>

        <form id="deleteForm" action="/${requestScope.post.boardType}/delete?p_id=${requestScope.post.p_id}" method="POST">
        </form>
    </div>
</body>
</html>