<%--
  Created by IntelliJ IDEA.
  User: dltjd
  Date: 2024-03-19
  Time: 오후 8:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>
</head>
<body>
    <form action="/board/save" method="post">
        <input type="text" name="boardWriter" placeholder="작성자"> <br>
        <input type="text" name="boardPass" placeholder="비밀번호"> <br>
        <input type="text" name="boardTitle" placeholder="제목"> <br>
        <textarea name="boardContents" cols="30" rows="10" placeholder="내용입력하세요"> </textarea>
        <input type="submit" value="작성"> <br>

    </form>
</body>
</html>
