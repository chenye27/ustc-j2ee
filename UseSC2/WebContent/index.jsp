<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录</title>
</head>
<body>
	<form action="login.sc" method="post">
		<label>UserName:</label><input type="text" name="name"><br>
		<label>Password:</label><input type="text" name="password"><br>
		<input type="submit" value="login">
	</form>	
	<br><br><br>
	<form action="register.sc" method="post">
		<input type="submit" value="register">
	</form>
</body>
</html>