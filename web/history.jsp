<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 2020/9/17
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>浏览历史</title>
    <script type="text/javascript" src="static/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="static/js/history.js"></script>
    <script src="static/js/pagination.js"></script>
    <link rel="stylesheet" href="static/css/pagination.css">
    <link rel="stylesheet" type="text/css" href="static/css/history.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div id="history">
    <div class="historyText">历史记录</div>
    <div class="historyButton">
        <input type="button" value="全选" class="chooseAll btn btn-info">
        <input type="button" value="反选" class="chooseReverse btn btn-info">
        <input type="button" value="删除" class="delete btn btn-danger">
        <input type="button" value="清空" class="empty btn btn-danger">
    </div>
    <ul id="historyBody">
        <!--动态生成6个数据-->
    </ul>
    <div id="historyPage">

    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@1.12.4/dist/jquery.min.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js"></script>
<script>
    $(document).ready(getcount());
</script>
</body>
</html>
