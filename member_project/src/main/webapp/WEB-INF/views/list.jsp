<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: dltjd
  Date: 2024-03-17
  Time: 오후 4:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<body>
    <table>
        <tr>
            <th>id</th>
            <th>email</th>
            <th>password</th>
            <th>name</th>
            <th>age</th>
            <th>mobile</th>
            <th>조회</th>
            <th>삭제</th>
        </tr>
        <!-- items -> 반복대상 (controller에서 모델에 실어온 데이터), member -> 변수이름 -->
        <c:forEach items="${memberList}" var="member">
            <tr>
                <td>${member.id}</td>
                <td>
                    <a href="/member?id=${member.id}">${member.memberEmail}</a>
                </td>
                <td>${member.memberPassword}</td>
                <td>${member.memberName}</td>
                <td>${member.memberAge}</td>
                <td>${member.memberMobile}</td>
                <td>
                    <a href="/member?id=${member.id}">조회</a>
                </td>
                <td>
                    <button onclick="deleteMember('${member.id}')">삭제</button>
                </td>
            </tr>
        </c:forEach>

    </table>
</body>

<script>
    // deleteMember의 매개변수로 id값을 받는다.
    const deleteMember = (id) => {

        console.log(id);
        location.href = "/member/delete?id=" + id;
    }
</script>
</html>
