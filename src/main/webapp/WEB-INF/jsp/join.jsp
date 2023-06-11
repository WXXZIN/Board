<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/joinPage.css">
</head>

<body>
    <c:if test="${not empty sessionScope.joinMessage}">
        <script>
            alert("${sessionScope.joinMessage}");
        </script>

        <c:remove var="joinMessage" scope="session"/>
    </c:if>

    <section class="join">
        <h1>Join</h1>
        <form action="join" method = "POST">
            <div class="input-area">
                <input type="text" name="id" id="id" autocomplete="off" required maxlength="20">
                <label for="id">ID</label>
            </div>
            <div class="input-area">
                <input type="password" name="pw" id="pw" autocomplete="off" required maxlength="20">
                <label for="pw">PASSWORD</label>
            </div>

            <div class="input-area">
                <input type="text" name="nickname" id="nickname" autocomplete="off" required maxlength="20">
                <label for="pw">NICKNAME</label>
            </div>
            <div class="btn-area">
                <button id="join" type="submit">JOIN</button>
                <button id="cancel" onclick ="location.href='/'" type = "button">취소</button>
            </div>
        </form>
    </section>
</body>
</html>