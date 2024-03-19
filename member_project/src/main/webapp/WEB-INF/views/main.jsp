<%--
  Created by IntelliJ IDEA.
  User: dltjd
  Date: 2024-03-17
  Time: 오전 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>main</title>
</head>
<body>
    <!-- $ {sessionScope.loginEmail} 은세션에 담긴 이메일을 띄워준다.-->
    <h2> ${sessionScope.loginEmail} 님 환영합니다.</h2>
    <button onclick="update()"> 내 정보 수정하기</button>
    <button onclick="logout()">로그아웃</button>
</body>

<script>
    const update = () => {
        location.href = "/member/update";
    }

    const logout = () => {
        location.href = "/member/logout";
    }
</script>
</html>
