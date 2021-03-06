<%--
  Created by IntelliJ IDEA.
  User: lcw
  Date: 2020/9/16
  Time: 17:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>个人中心</title>
</head>
<link rel="stylesheet" href="/static/user/css/bootstrap.min.css">
<link rel="stylesheet" href="/static/user/css/users.css">
<style>
    p{
      font-size: 10px;
    }
    body::-webkit-scrollbar {
        display: none;
    }
</style>
<body style="background: #ededed;">
<div class="container" >
        <div class="row row-info" style="background: #fff;height: 500px">
            <div class="info-top">
                <h2>修改资料</h2>
            </div>
            <div class="info-center">
                <div>
                    <div class="col-lg-3">
                        <embed src="static/user/img/info.svg" style="display:block;width:250px;height:250px"/>
                    </div>
                    <div class="col-lg-9">
                        <form action="${pageContext.request.contextPath}/modify.users?name=${name}" class="form-info">
                            <input type="hidden" class="form-control" name="id" id="id"  value="${user.user_id}">
                            <div class="form-group row ">
                                <label for="name" class="col-sm-2 col-form-label" >用户名</label>
                                <div class="col-sm-10">
                                    <input type="text" readonly class="form-control" id="name" name="name" value="${user.name}">
                                </div>
                            </div>
                            <div class="form-group row checked-2">
                                <label for="password" class="col-sm-2 col-form-label">密码</label>
                                <div class="col-sm-10">
                                    <input type="text" name="password" class="form-control" id="password" value="${user.password}">
                                </div>
                            </div>
                            <div class="form-group row checked-2">
                                <label for="phone" class="col-sm-2 col-form-label">手机号码</label>
                                <div class="col-sm-10">
                                    <input type="text" name="phone" class="form-control" id="phone" value="${user.phone}">
                                </div>
                            </div>
                            <div style="margin-top: 40px;display: block">
                                <input type="button" class="btn btn-lg  btn-group" style="margin-left:355px"onclick="window.parent.location.href = '${pageContext.request.contextPath}/index.jsp' " value="返回首页">
                                <input type="button" class="btn btn-lg  btn-info" onclick="update()" value="修改">
                                <input class="btn btn-primary btn-lg" type="reset" value="重置">
                                <input class="btn btn-danger btn-lg" type="button" onclick="remove()" value="注销账号">
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
</div>

<footer class="panel-footer" style="width: 100%; overflow: hidden; background: #ededed;margin-top: 30px">
    <div class="container">
        <div class="row footer-top" style="text-align: center;">
            <h5>Design By 007 </h5>
            <p>本网站内容均收集于互联网，007影视不承担任何由于内容的合法性及健康性所引起的争议和法律责任。</p>
            <p>欢迎大家对网站内容侵犯版权等不合法和不健康行为进行监督和举报。</p>
            <p>Copyright © 2020 </p>
        </div>
    </div>
</footer>
<script src="static/user/js/jquery-3.2.1.js"></script>
<script src="static/user/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
<script>

    $('#password').blur(function () {
        var pwd = $("#password").val();
        var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,}$/;
        if(!reg.test(pwd)){
            bootbox.alert("密码长度要大于6位，由数字和字母组成");
            return ;
        }
    })
    $("#phone").blur(function () {
        var phone =  $("#phone").val();
        if(!(/^1[3456789]\d{9}$/.test(phone))){
            bootbox.alert("手机号码有误，请重填");
            return;
        }
    })


function remove() {
    bootbox.confirm({
        size: "small",
        message:"确定注销账号吗?",
        buttons:{
            confirm: {
                label:'确定',
                    className :'btn-danger'
                    },
            cancel:{
                label:'取消',
                className:'btn-success'
            }
        },
        callback:function(result){
            if (result){
                $.post(
                    "delete.users",
                    {"id":$("#id").val(),},
                    function (data) {
                        bootbox.alert("删除成功！");
                        setTimeout(function () {
                            $.post("logout.users");
                            window.parent.location.href="index.jsp";
                        },2000)
                    },"json"
                )
            }
        }
    })
}
function update() {
    bootbox.confirm({
        size:"small",
        message:"确定修改吗?",
        buttons:{
            confirm: {
                label:'确定'
            },
            cancel:{
                label:'取消'
            }
        },
        callback:function(result){
            if (result){
                $.post(
                    "modify.users",
                    {"id":$("#id").val(),"name":$("#name").val(),"password":$("#password").val(),"phone":$("#phone").val()},
                    function (data) {
                        if (data.result>0){
                            bootbox.alert("修改成功！");
                            setTimeout(function () {
                                window.location.reload();
                            },2000)
                        }else {
                            bootbox.alert("修改失败！")
                        }
                    },"json"
                )
            }
        }
    })
}
</script>
</body>
</html>
