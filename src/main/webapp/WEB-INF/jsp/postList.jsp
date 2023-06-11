<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>
        <c:choose>
            <c:when test="${requestScope.boardType == 'notice'}">공지사항</c:when>
            <c:otherwise>자유게시판</c:otherwise>
        </c:choose>
    </title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css">
</head>

<body>
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
        <div class="board_list_wrap">
            <div class="board_list">
                <div class="top">
                    <div class="num">번호</div>
                    <div class="title">제목</div>
                    <div class="writer">글쓴이</div>
                    <div class="date">작성일</div>
                </div>

                <c:choose>
                    <c:when test="${not empty requestScope.postList}">
                        <c:forEach var="post" items="${requestScope.postList}">
                            <div>
                                <div class="num">${post.p_id}</div>
                                <div class="title"><a href="${requestScope.boardType}/view?p_id=${post.p_id}">${post.title}</a></div>
                                <div class="writer">${post.writer}</div>
                                <div class="date">
                                    <fmt:parseDate value="${post.regDate}" pattern="yyyy-MM-dd" var="parsedDateTime" type="both" />
                                    <fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd" />
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p class="empty">게시글이 없습니다.</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <%--${requestScope.searchType != null && !requestScope.searchType.isEmpty() && requestScope.searchKey != null && !requestScope.searchKey.isEmpty() ? '&searchType=' + requestScope.searchType + '&searchKey=' + requestScope.searchKey : ''}--%>

            <%--${requestScope.searchTyep != null ? '?searchType=' + requestScope.searchType + '&searchKey=' + requestScope.searchKey + '&page=' + requestScope.currentPage - 1 : '?page=' + requestScope.currentPage - 1}--%>

            <div class="board_page">
                <a href="${requestScope.boardType == 'notice' ? '/notice' : '/free'}<c:if test="${requestScope.searchType != null}">?searchType=${requestScope.searchType}&searchKey=${requestScope.searchKey}&page=${requestScope.currentPage - 1}</c:if><c:if test="${requestScope.searchType == null}">?page=${requestScope.currentPage - 1}</c:if>"
                   class="bt prev ${requestScope.currentPage <= 1 ? 'disabled' : ''}"
                   ${requestScope.currentPage <= 1 ? 'onclick="return false;"' : ''}>이전</a>

                <c:set var="currentPage" value="${requestScope.currentPage > 0 ? requestScope.currentPage : 1}" />
                <c:set var="totalPages" value="${requestScope.totalPages > 0 ? requestScope.totalPages : 1}" />

                <c:if test="${totalPages > 5}">
                    <c:choose>
                        <c:when test="${currentPage <= 3}">
                            <c:forEach var="i" begin="1" end="5">
                                <a href="${requestScope.boardType == 'notice' ? '/notice' : '/free'}<c:if test="${requestScope.searchType != null}">?searchType=${requestScope.searchType}&searchKey=${requestScope.searchKey}&page=${i}</c:if><c:if test="${requestScope.searchType == null}">?page=${i}</c:if>"
                                   class="num ${currentPage == i ? 'current' : ''}">${i}</a>
                            </c:forEach>
                        </c:when>
                        <c:when test="${currentPage >= totalPages - 2}">
                            <c:forEach var="i" begin="${totalPages - 4}" end="${totalPages}">
                                <a href="${requestScope.boardType == 'notice' ? '/notice' : '/free'}<c:if test="${requestScope.searchType != null}">?searchType=${requestScope.searchType}&searchKey=${requestScope.searchKey}&page=${i}</c:if><c:if test="${requestScope.searchType == null}">?page=${i}</c:if>"
                                   class="num ${currentPage == i ? 'current' : ''}">${i}</a>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="i" begin="${currentPage - 2}" end="${currentPage + 2}">
                                <a href="${requestScope.boardType == 'notice' ? '/notice' : '/free'}<c:if test="${requestScope.searchType != null}">?searchType=${requestScope.searchType}&searchKey=${requestScope.searchKey}&page=${i}</c:if><c:if test="${requestScope.searchType == null}">?page=${i}</c:if>"
                                   class="num ${currentPage == i ? 'current' : ''}">${i}</a>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                <c:if test="${totalPages <= 5}">
                    <c:forEach var="i" begin="1" end="${totalPages}">
                        <a href="${requestScope.boardType == 'notice' ? '/notice' : '/free'}<c:if test="${requestScope.searchType != null}">?searchType=${requestScope.searchType}&searchKey=${requestScope.searchKey}&page=${i}</c:if><c:if test="${requestScope.searchType == null}">?page=${i}</c:if>"
                           class="num ${currentPage == i ? 'current' : ''}">${i}</a>
                    </c:forEach>
                </c:if>

                <a href="${requestScope.boardType == 'notice' ? '/notice' : '/free'}<c:if test="${requestScope.searchType != null}">?searchType=${requestScope.searchType}&searchKey=${requestScope.searchKey}&page=${requestScope.currentPage + 1}</c:if><c:if test="${requestScope.searchType == null}">?page=${requestScope.currentPage + 1}</c:if>"
                   class="bt next ${requestScope.currentPage >= requestScope.totalPages ? 'disabled' : ''}"
                ${requestScope.currentPage >= requestScope.totalPages ? 'onclick="return false;"' : ''}>다음</a>
            </div>

            <div class="search_wrap">
                <form action="${requestScope.boardType == 'notice' ? '/notice' : '/free'}" method="GET">
                    <select id="searchType" name="searchType">
                        <option value="title" ${requestScope.searchType == 'title' ? 'selected' : ''}>제목</option>
                        <option value="writer" ${requestScope.searchType == 'writer' ? 'selected' : ''}>작성자</option>
                        <option value="content" ${requestScope.searchType == 'content' ? 'selected' : ''}>내용</option>
                    </select>
                    <input type="text" name="searchKey" maxlength="20" value="${requestScope.searchKey}">
                    <button id="btn_search">검색</button>
                </form>
            </div>

            <div class="bt_wrap active">
                <c:if test="${requestScope.boardType == 'notice' && sessionScope.nickname == 'Admin'}">
                    <a href="/notice/write">글쓰기</a>
                </c:if>
                <c:if test="${requestScope.boardType != 'notice' && not empty sessionScope.nickname}">
                    <a href="/free/write">글쓰기</a>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>