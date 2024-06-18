<%@ page language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home: Join Form</title>
<link type="text/css"
	rel="stylesheet"
	href="<%= request.getContextPath() %>/css/users.css" />
</head>
<body>
	<div id="container">
		<c:import url ="/WEB-INF/views/includes/header.jsp" />
		<c:import url ="/WEB-INF/views/includes/navigation.jsp" />
		
		<div id="wrapper">
			<div id="content">
			
			<h1>Join Form</h1> 
			
			<form method="POST" action="<c:url value = "/users" />" >
				<input type="hidden" name="a" value="join" />
				<label for="name">이름</label>
				<input type="text" name="name" id="name" /><br/>
				<label for="password">비밀번호</label>
				<input type="password" name="password" id="password"/><br/>
				<label for="email">이메일</label>
				<input type="text" name="email" id="email"/><br/>
				<label for="gender">성별</label>
				<input type="radio" name="gender" value="M" checked> 남성
				<input type="radio" name="gender" value="F"> 여성
				<input type="submit" value="가입" />
				
			</form>
			<!-- TODO: 폼 검증(Validation) -->
			
			</div>
		</div>
		<c:import url ="/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>