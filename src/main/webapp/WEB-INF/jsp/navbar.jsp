<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/navbar.css">
    <script>
        function submitLogoutForm() {
            document.getElementById('logoutForm').submit();
        }
    </script>
</head>

<body>
    <nav class="navbar">
        <ul class="nav-main">
            <li><a href="${pageContext.request.contextPath}/">홈</a></li>
            <li><a href="${pageContext.request.contextPath}/notice">공지사항</a></li>
            <li><a href="${pageContext.request.contextPath}/free">자유게시판</a></li>
        </ul>

        <ul class="nav-auth ${sessionScope.nickname != null ? '' : 'active'}">
            <li><a href="${pageContext.request.contextPath}/login">로그인</a></li>
            <li><a href="${pageContext.request.contextPath}/join">회원가입</a></li>
        </ul>

        <ul class="nav-authed ${sessionScope.nickname != null ? 'active' : ''}">
            <li>
                ${sessionScope.nickname.equals('Admin') ? '<a href="/admin/um">사용자 관리</a>' : '' }
            </li>
            <li><a href="javascript:void(0);" onclick="submitLogoutForm()">로그아웃</a></li>
        </ul>
    </nav>

    <form id="logoutForm" action="${pageContext.request.contextPath}/logout" method="POST">
    </form>

    <div class="nav-userinfo ${sessionScope.nickname != null ? 'active' : ''}">
        <p>${sessionScope.nickname != null ? sessionScope.nickname.concat('님 환영합니다!') : ''}</p>
    </div>
</body>
</html>