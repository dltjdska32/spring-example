<%--
  Created by IntelliJ IDEA.
  User: dltjd
  Date: 2024-03-17
  Time: 오후 7:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>
  <form action="/member/update" method="post" name="updateForm">
    id: <input type="text" name="id" value="${member.id}"readonly>
    <!-- readonly 는 읽기만 가능하고 수정불가능하도록함 -->
    email: <input type="text" name="memberEmail" value="${member.memberEmail}"readonly>
    password: <input type="text" name="memberPassword" id="memberPassword" >
    name: <input type="text" name="memberName" value="${member.memberName}"readonly>
    age: <input type="text" name="memberAge" value="${member.memberAge}">
    mobile: <input type="text" name="memberMobile" value="${member.memberMobile}">
    <input type="button" value="수정" onclick="update()">
    <!-- input type을 submit으로 하거나 form안에 button태그를 만들경우 어떠한 기능을 수행하지않고 action을 바로 실행해버림
       따라서 input type button을 통해서 기능을 주어 기능을 수행하도록 할 수 있다.-->
  </form>

</body>

<script>
  //디비의 비밀번호와 사용자가 입력한 비밀번호가 같은경우 submit하도록
  const update = () => {
    const passwordDB = '${member.memberPassword}';
    const password = document.getElementById("memberPassword").value;
    if (passwordDB == password) {
      document.updateForm.submit();
    } else {
      alter("비밀번호가 일치하지 않습니다");
    }
  }
</script>
</html>
