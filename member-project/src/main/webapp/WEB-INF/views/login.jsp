<%--
  Created by IntelliJ IDEA.
  User: dltjd
  Date: 2024-03-16
  Time: 오전 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
    <form action="/member/login" method="post">
        <input type="text" name="memberEmail" placeholder="이메일"><br>
        <input type="text" name="memberPassword" placeholder="비밀번호"><br>
        <input type="submit" value="로그인">
    </form>

</body>
</html>
