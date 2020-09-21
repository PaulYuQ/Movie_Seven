<%--
  Created by IntelliJ IDEA.
  User: lcw
  Date: 2020/9/16
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>个人中心页面</title>

    <link rel="stylesheet" href="/static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/css/users.css">
</head>
<body>
<div class="container">
    <nav class="navbar navbar-default" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">菜鸟教程</a>
            </div>
            <div>
                <!--向左对齐-->
                <ul class="nav navbar-nav navbar-left">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Java
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="#">jmeter</a></li>
                            <li><a href="#">EJB</a></li>
                            <li><a href="#">Jasper Report</a></li>
                            <li class="divider"></li>
                            <li><a href="#">分离的链接</a></li>
                            <li class="divider"></li>
                            <li><a href="#">另一个分离的链接</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-left" role="search">
                    <button type="submit" class="btn btn-default">
                        向左对齐-提交按钮
                    </button>
                </form>
                <p class="navbar-text navbar-left">向左对齐-文本</p>
                <!--向右对齐-->
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            Java <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="${pageContext.request.contextPath}/collection.jsp?id=${user.getId}">jmeter</a></li>
                            <li><a href="#">EJB</a></li>
                            <li><a href="#">Jasper Report</a></li>
                            <li class="divider"></li>
                            <li><a href="#">分离的链接</a></li>
                            <li class="divider"></li>
                            <li><a href="#">另一个分离的链接</a></li>
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <button type="submit" class="btn btn-default">
                        向右对齐-提交按钮
                    </button>
                </form>
                <p class="navbar-text navbar-right">向右对齐-文本</p>
            </div>
        </div>
    </nav>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="list-group">
                <a class="list-group-item" href="#1">历史记录</a>
                <a class="list-group-item" href="#2">收藏</a>
                <a class="list-group-item" href="#3">3.联系方式</a>
            </div>
        </div>
        <div class="col-md-9">
            <div>
                <h1>颠三倒四多所多所多2323</h1>
            </div>
        </div>
    </div>
</div>

<script src="static/js/jquery-3.2.1.js" ></script>
<script src="static/js/bootstrap.min.js"></script>
</body>
</html>
