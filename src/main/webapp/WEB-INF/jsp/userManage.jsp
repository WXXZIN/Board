<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 관리</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
    <script>
        function submitDeleteForm() {
            let result = confirm('정말 삭제하시겠습니까?');

            if (result > 0) {
                document.getElementById('deleteForm').submit();
            }
        }
    </script>
</head>

<body>
    <c:if test="${not empty sessionScope.deleteMessage}">
        <script>
            alert("${sessionScope.deleteMessage}");
        </script>

        <c:remove var="deleteMessage" scope="session"/>
    </c:if>

    <%@ include file="/WEB-INF/jsp/navbar.jsp" %>
    <div class="board_wrap">
        <div class="board_title">
            <strong>사용자 관리</strong>
        </div>
        <div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="uid">ID</div>
                    <div class="nickname">닉네임</div>
                    <div class="manage">관리</div>
                </div>

                <c:choose>
                    <c:when test="${not empty requestScope.userList}">
                        <c:forEach var="user" items="${requestScope.userList}">
                            <div>
                                <div class="uid">${user.id}</div>
                                <div class="nickname">${user.nickname}</div>
                                <div class="manage"><a href="javascript:void(0);" class="bt" onclick="submitDeleteForm()">삭제</a></div>

                                <form id="deleteForm" action="/admin/um/delete" method="POST">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="hidden" name="nickname" value="${user.nickname}">
                                </form>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p class="empty">사용자가 없습니다.</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>

        <div class="board_page">
            <a href="/admin/um?page=${requestScope.currentPage - 1}" class="bt prev ${requestScope.currentPage <= 1 ? 'disabled' : ''}"
               ${requestScope.currentPage <= 1 ? 'onclick="return false;"' : ''}>이전</a>

            <c:set var="currentPage" value="${requestScope.currentPage > 0 ? requestScope.currentPage : 1}" />
            <c:set var="totalPages" value="${requestScope.totalPages > 0 ? requestScope.totalPages : 1}" />

            <c:if test="${totalPages <= 6}">
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="/admin/um?page=${i}" class="num ${currentPage == i ? 'current' : ''}">${i}</a>
                </c:forEach>
            </c:if>
            <c:if test="${totalPages > 6}">
                <c:forEach var="i" begin="1" end="5">
                    <a href="/admin/um?page=${i}" class="num ${currentPage == i ? 'current' : ''}">${i}</a>
                </c:forEach>
                <span class="dots">...</span>
                <a href="/admin/um?page=${totalPages}" class="num ${currentPage == totalPages ? 'current' : ''}">${totalPages}</a>
            </c:if>

            <a href="/admin/um?page=${currentPage + 1}" class="bt next ${currentPage >= totalPages ? 'disabled' : ''}"
               ${currentPage >= totalPages ? 'onclick="return false;"' : ''}>다음</a>
        </div>
    </div>
</body>
</html>