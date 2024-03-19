<%--
  Created by IntelliJ IDEA.
  User: dltjd
  Date: 2024-03-13
  Time: 오후 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>

    <!-- ajax 사용을 위한 jquery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>

</head>

<body>
    <form action="/member/save" method="post">
        <!-- onblur 포커스가 해제될경우 실행 -->
        <input type="text" name="memberEmail" placeholder="이메일" id="memberEmail" onblur="emailCheck()"><br>
        <p id="check-result"></p>
        <input type="text" name="memberPassword" placeholder="비밀번호"><br>
        <input type="text" name="memberName" placeholder="이름"><br>
        <input type="text" name="memberAge" placeholder="나이"><br>
        <input type="text" name="memberMobile" placeholder="전화번호"><br>
        <input type="submit" value="회원가입">

    </form>

</body>


<script>
    //이메일 입력값을 가져오고
    // 입력값을 서버로 전송 후, 똑같은 이메일이 있는지 체크한 후,
    // 사용가능 여부를 이메일 입력창 아래 표시

    const emailCheck = () => {
        const email = document.getElementById("memberEmail").value;
        const checkResult = document.getElementById("check-result");
        console.log("입력한 이메일", email);
        $.ajax({
            // 요청방식 : post, url : "email-check", 데이터: 이메일
            type: "post",
            url: "/member/email-check",
            data: {
                "memberEmail": email
            },
            // 요청성공시 응답 처리
            success: function (res) {
                console.log("요청 성공", res);
                //요청이 성공해서 ok값이 담겨오면 DB에 없는 이메일 즉 중복 이메일 아니다.
                if (res == "ok") {
                    console.log("사용 가능한 이메일");
                    //check-result에 사용가능한 이메일(문자) + 녹색 표시
                    checkResult.style.color = "green";
                    checkResult.innerHTML = "사용가능한 이메일";
                } else {
                    console.log("이미 사용중인 이메일");
                    checkResult.style.color = "red";
                    checkResult.innerHTML = "이미 사용중인 이메일";
                }
            } ,
            //통신 오류 발생
            error: function (err) {
                console.log("에러발생", err);
            }
        })
    }
</script>
</html>
