<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
	<script src="js/jquery-3.5.1.js" type="text/javascript"></script>

<!-- Loding font -->
<link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">

<!-- Custom Styles -->
<link rel="stylesheet" type="text/css" href="css/styles.css">

<title>Home</title>
</head>
<body >

<%
	Cookie[] cookies = request.getCookies();
	for(Cookie cookie:cookies)
	{
		if(cookie.getName().equals("name")){
			session.setAttribute("name",cookie.getValue());
		}
		if(cookie.getName().equals("password")){
			session.setAttribute("password",cookie.getValue());
		}
	}
%>

<!-- Backgrounds-->

<!--<div id="login-bg" class="container-fluid">-->

<!--  <div class="bg-img"></div>-->
<!--  <div class="bg-color"></div>-->
<!--</div>-->

<!-- End Backgrounds-->

<div class="container" id="login">
	<div class="row justify-content-center">
	<div class="col-lg-8">
	  <div class="login">

		<h1>登录</h1>
		
		<!-- Loging form -->
			  <form action="login.admin" method="post" >
				<div class="form-group">
				  <input type="text" class="form-control" name="username" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="用户名" value="${name}">
				  
				</div>
				<div class="form-group">
				  <input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="密码" value="${password}">
				</div>

				  <div class="form-check">

				  <label class="switch">
				  <input type="checkbox" name="check" checked="true">
				  <span class="slider round"></span>
				</label>
				  <label class="form-check-label" >记住密码</label>
				  
				  <label class="forgot-password"><a href="#">忘记密码?</a></label>

				</div>
			  
				<br>
				<button type="submit" class="btn btn-lg btn-block btn-success">登录</button>
			  </form>
		 <!-- End Loging form -->

	  </div>
	</div>
	</div>
</div>
<script>


</script>




</body>
</html>