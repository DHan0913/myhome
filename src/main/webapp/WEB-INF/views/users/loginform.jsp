<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home: Login</title>
</head>
<body>
	<h1>Login</h1>


	<c:if test="${not empty param.errorMsg}">
		<p style="color:red; font-weight:bold;">${param.errorMsg } </p>
	</c:if>
	

	<form method="POST" action="<c:url value = "/users" />" >
		<input type="hidden" name="a" value="login" />
		<label for="email">이메일</label>
		<input type="text" name="email" /><br>
		<label for="password">Password</label>
		<input type="password" name="password" /><br/>
		<input type="submit" value="로그인" />
	</form>
</body>
</html>