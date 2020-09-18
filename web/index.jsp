<%--
  Created by IntelliJ IDEA.
  User: lcw
  Date: 2020/9/16
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
<h1>欢迎你 ${sessionScope.user.user_id}</h1>
<a href="${pageContext.request.contextPath}/loginAndRegister.jsp">登录</a>
<a href="${pageContext.request.contextPath}/update.users?name=${sessionScope.user.name}">修改资料</a>
<a href="${pageContext.request.contextPath}/logout.users">退出登录</a>
</body>
</html>
