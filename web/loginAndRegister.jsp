<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <title>007影院登录注册</title>
    <link rel="stylesheet" href="static/user/css/style.css">
    <link rel="stylesheet" href="static/user/css/users.css">
</head>

<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
<div class="content">
    <form action="${pageContext.request.contextPath}/login.users" method="post">
        <div class="form sign-in">
            <h2>欢迎回来</h2>
            <label>
                <span>用户名</span>
                <input type="text" name="name"/>
            </label>
            <label>
                <span>密码</span>
                <input type="password" name="password"/>
            </label>
            <label>
                <span>验证码</span>
                <input type="text" name="code"/>
                <img style="margin-top: 30px" class="checked checked-1" src="${pageContext.request.contextPath}/code"
                     onclick="changecode()"><br>
            </label>
            <p class="forgot-pass"><a href="javascript:changecode()">看不清,换一张</a></p>
            <label>
                <span style="color: red">${msg}</span>
            </label>
            <button type="submit" class="submit">登 录</button>
        </div>
    </form>
    <form action="${pageContext.request.contextPath}/register.users" method="post">
        <div class="sub-cont">
            <div class="img">
                <div class="img__text m--up">
                    <h2>还未注册？</h2>
                    <p>立即注册，看你想看！</p>
                </div>
                <div class="img__text m--in">
                    <h2>已有帐号？</h2>
                    <p>有帐号就登录吧，好久不见了！</p>
                </div>
                <div class="img__btn">
                    <span class="m--up">注 册</span>
                    <span class="m--in">登 录</span>
                </div>
            </div>
            <div class="form sign-up">
                <h2>立即注册</h2>
                <label>
                    <span>用户名</span>
                    <input type="text" id="name"/>
                </label>
                <label>
                    <span>密码</span>
                    <input type="password" id="password"/>
                </label>
                <label>
                    <span>确认密码</span>
                    <input type="password" id="password1"/>
                </label>
                <label>
                    <span>电话号码</span>
                    <input type="tel" id="phone"/>
                </label>
                <label>
                    <span style="color: red" id="msg">${msg1}</span>
                </label>
                <button type="button" class="submit" onclick="register()">注 册</button>
            </div>
        </div>
    </form>
</div>

<script src="static/user/js/jquery-3.2.1.js"></script>
<script src="static/user/js/script.js"></script>
<script type="text/javascript">
    function changecode() {
        //得到图片元素
        var img = document.getElementsByTagName("img")[0];
        img.src = "${pageContext.request.contextPath}/code?time=" + new Date().getTime();//最新
    }

    function register() {

        $.post(
            "register.users",
            {
                "name": $("#name").val(),
                "password": $("#password").val(),
                "password1": $("#password1").val(),
                "phone": $("#phone").val()
            },
            function (data) {
                let result = data.result;
                if (result == 3) {
                    $("#msg").html("密码不一致！")
                } else if (result == 2) {
                    $("#msg").html("用户名已被占用！")
                } else if (result == 1) {
                    alert("注册成功！");
                    window.location.replace("${pageContext.request.contextPath}/loginAndRegister.jsp");
                } else if (result == 4) {
                    $('#msg').html("密码和用户名都错误")
                } else if (result == 5) {
                    $('#msg').html("注册失败！")
                }
            }, "json"
        )
    }
</script>
</body>

</html>

