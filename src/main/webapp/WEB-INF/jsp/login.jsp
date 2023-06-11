<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty sessionScope.nickname}">
    <c:redirect url="${pageContext.request.contextPath}/" />
</c:if>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>로그인</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/loginPage.css">
</head>

<body>
    <c:if test="${not empty sessionScope.loginMessage}">
        <script>
            alert("${sessionScope.loginMessage}");
        </script>

        <c:remove var="loginMessage" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.errorMessage}">
        <script>
            alert("${sessionScope.errorMessage}");
        </script>

        <c:remove var="errorMessage" scope="session"/>
    </c:if>

    <section class="login">
        <h1>Login</h1>
        <form action="login" method="POST">
            <div class="input-area">
                <input type="text" name="id" id="id" autocomplete="off" required maxlength="20">
                <label for="id">ID</label>
            </div>
            <div class="input-area">
                <input type="password" name="pw" id="pw" autocomplete="off" required maxlength="20">
                <label for="pw">PASSWORD</label>
            </div>
            <div class="btn-area">
                <button id="login" type="submit">LOGIN</button>
            </div>
        </form>
    </section>
</body>
</html>