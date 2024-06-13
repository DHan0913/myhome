<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home: Login</title>
</head>
<body>
	<h1>Login</h1>
	<form method="POST" action="">
		<input type="hidden" name="a" value="login" />
		<label for="email">이메일</label>
		<input type="text" name="email" /><br>
		<label for="password">Password</label>
		<input type="password" name="password" /><br/>
		<input type="submit" value="login" />
	</form>
</body>
</html>