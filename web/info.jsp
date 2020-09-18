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
<link rel="stylesheet" href="/static/css/bootstrap.min.css">
<link rel="stylesheet" href="/static/css/users.css">
<body>
<div class="container">
    <div class="row row-info">
        <div class="info-center">
            <div>
                <div class="col-lg-2">
                    <img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1600314127342&di=3113df7e6be597c6ab63a0b6dfd6be73&imgtype=0&src=http%3A%2F%2Fbpic.588ku.com%2Felement_origin_min_pic%2F01%2F31%2F76%2F99573b52787e93b.jpg" alt="" width="100px" height="100px">
                </div>
                <div class="col-lg-10">
                    <form action="${pageContext.request.contextPath}/modify.users?name=${name}" class="form-info">
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
                            <input type="button" class="btn btn-lg  btn-info" style="margin-left:300px"onclick="update()" value="修改">
                            <input class="btn btn-primary btn-lg" type="reset" value="重置">
                            <input class="btn btn-danger btn-lg" type="button" onclick="remove()" value="注销账号">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script src="static/js/jquery-3.2.1.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootbox.js/5.4.0/bootbox.min.js"></script>
<script>
function remove() {
/*    bootbox.confirm("确定删除吗？",
        function(result){
        $.post(
            "delete.users",
            {"name":$("#name").val()},
            function (data) {
                console.log(data.result);
                bootbox.alert("删除成功！");
                window.location.replace("login.jsp")
            },"json"
        )
    })*/
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
                    {"name":$("#name").val(),},
                    function (data) {
                        bootbox.alert("删除成功！");
                        window.location.replace("login.jsp")
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
                    {"name":$("#name").val(),"password":$("#password").val(),"phone":$("#phone").val()},
                    function (data) {
                        if (data.result>0){
                            bootbox.alert("修改成功！");
                            window.location.replace("info.jsp")
                        }
                        bootbox.alert("修改失败！")
                    },"json"
                )
            }
        }
    })
}
</script>
</body>
</html>