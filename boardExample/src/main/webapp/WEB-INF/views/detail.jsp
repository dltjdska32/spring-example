<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: dltjd
  Date: 2024-03-20
  Time: 오후 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>detail</title>
    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

</head>
<body>
    <table>
        <tr>
            <th>id</th>
            <td>${board.id}</td>
        </tr>
        <tr>
            <th>writer</th>
            <td>${board.boardWriter}</td>
        </tr>
        <tr>
            <th>date</th>
            <td>${board.boardCreatedTime}</td>
        </tr>
        <tr>
            <th>hits</th>
            <td>${board.boardHits}</td>
        </tr>
        <tr>
            <th>title</th>
            <td>${board.boardTitle}</td>
        </tr>
        <tr>
            <th>contents</th>
            <td>${board.boardContents}</td>
        </tr>
    </table>

    <button onclick="listFn()"> 목록 </button>
    <button onclick="updateFn()"> 수정 </button>
    <button onclick="deleteFn()"> 삭제 </button>

    <div>
        <input type="text" id="commentWriter" placeholder="작성자">
        <input type="text" id="commentContents" placeholder="내용">
        <button id="comment-write-btn" onclick="commentWrite()">댓글작성</button>
    </div>

    <div id="comment-List">
        <table>
            <tr>
                <th>댓글번호</th>
                <th>작성자</th>
                <th>내용</th>
                <th>작성시간</th>
            </tr>

            <c:forEach items="${commentList}" var="comment">
                <tr>
                    <td>${comment.id}</td>
                    <td>${comment.commentWriter}</td>
                    <td>${comment.commentContents}</td>
                    <td>${comment.commentCreatedTime}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>

    <script>
        function listFn() {
            const page = "${page}";
            location.href = "/board/paging?page=" + page;
        }

        function  updateFn() {
            const id = "${board.id}";
            location.href = "/board/update?id=" + id;
        }

        function deleteFn() {
            const id = "${board.id}";
            location.href = "/board/delete?id=" + id;
        }

        function commentWrite() {
            const writer = document.getElementById("commentWriter").value;
            const contents = document.getElementById("commentContents").value;
            const board = "${board.id}";

            $.ajax({
                type: "post",
                url: "/comment/save",
                data: {
                    // 키값과 dto 필드와 일치해야한다.
                    commentWriter: writer,
                    commentContents: contents,
                    boardId: board
                },
                dataType: "json",
                success: function (commentList)  {
                    console.log("작성완료");
                    console.log(commentList);

                    let output = "<table>";
                    output += "<tr><th>댓글 번호</th>";
                    output += "<th>작성자</th>";
                    output += "<th>내용</th>";
                    output += "<th>작성시간</th></tr>";

                    for(let i in commentList) {
                        output += "<tr>";
                        output += "<td>" + commentList[i].id + "</td>";
                        output += "<td>" + commentList[i].commentWriter + "</td>";
                        output += "<td>" + commentList[i].commentContents + "</td>";
                        output += "<td>" + commentList[i].commentCreatedTime + "</td>";
                        output += "</tr>";
                    }

                    output += "</table>";

                    document.getElementById("comment-List").innerHTML = output;
                    //댓글 작성을 마치고 작성자 text 태그와 댓글 text태그를 비운다.
                    document.getElementById("commentWriter").value = "";
                    document.getElementById("commentContents").value = "";

                },
                error: function () {
                    console.log("실패");
                }

            });

        }
    </script>
</html>
