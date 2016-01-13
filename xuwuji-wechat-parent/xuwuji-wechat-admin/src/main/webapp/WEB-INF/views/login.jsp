<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/login.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />

<title>登录</title>
</head>
<body >
	<div class="text-center" style="padding: 50px 0">

		<div class="logo">login</div>
		<!-- login Form Starts Here ....-->
		<div class="login-form-1">
			<form id="login-form" class="text-left" method='post' action='${pageContext.request.contextPath}/login/validate' >
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<div class="form-group">
							<label for="lg_username" class="sr-only">Username</label>
							 <input
								type="text" class="form-control" id="username"
								name="username" placeholder="username">
						</div>
						<div class="form-group">
							<label for="lg_password" class="sr-only">Password</label> <input
								type="password" class="form-control" id="password"
								name="password" placeholder="password">
						</div>
						<div class="form-group login-group-checkbox">
							<input type="checkbox" id="lg_remember" name="remember_me">
							<label for="lg_remember">remember</label>
						</div>
					</div>
					<button  type='submit' class="login-button" >
						<i class="fa fa-chevron-right" ></i>
					</button>
				</div>
				<div class="etc-login-form">
					<p>
						forgot your password? <a href="#">click here</a>
					</p>
					<p>
						new user? <a href="#">create new account</a>
					</p>
				</div>
			</form>
		</div>
		<!-- login Form Ends Here ....-->
	</div>
	</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.3.min.js"></script>

</body>
<script type="text/javascript">

function login(){
var username=$('#username').val();
var password=$('#password').val();
var remember=$('#remember').val();

$.ajax({
	url: '${pageContext.request.contextPath}/login/validate',
	type: 'POST',
	dataType: 'application/x-www-form-urlencoded',
	data: {username: username,
		password:password,
		remember:remember
		},
})
.done(function(data) {
	alert("dsadas");
	console.log("success");
	console.log("data");
})
.fail(function() {
	console.log("error");
})
.always(function() {
	console.log("complete");
});


console.log(username);
console.log(password);
console.log(remember);
}

</script>
</html>