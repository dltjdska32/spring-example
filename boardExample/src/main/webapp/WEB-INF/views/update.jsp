<%--
  Created by IntelliJ IDEA.
  User: dltjd
  Date: 2024-03-21
  Time: 오전 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
    <form action="/board/update" method="post" name="updateForm">
        <!-- type hidden의 경우 값을 보여주지 않는것. -->
        <input type="hidden" name="id" value="${board.id}" readonly> <br>
        <input type="text" name="boardWriter" value="${board.boardWriter}" readonly> <br>
        <input type="text" name="boardPass" id="boardPass" placeholder="비밀번호"> <br>
        <input type="text" name="boardTitle" value="${board.boardTitle}">
        <textarea name="boardContents" cols="30" rows="10"> ${board.boardContents}</textarea>
        <input type="button" value="수정" onclick="updateReqFn()">
    </form>

</body>

<script>
    //비밀번호 검증을 한 후, 수정을 진행
    function updateReqFn() {
        console.log("들어왔다...........!!!!!!!!!!!!!!!")
        var passInput = document.getElementById("boardPass").value;
        const passDB = "${board.boardPass}";
        if (passInput == passDB) {
            document.updateForm.submit();
        } else {
            alert("비밀번호가 일치하지 않습니다.");
        }
    }
</script>

</html>
