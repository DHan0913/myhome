<%@ page language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Home: Join Success</title>
<link type="text/css"
	rel="stylesheet"
	href="<c:url value ="/css/users.css" />" />
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		
		<div id="wrapper">
			<div id="content">
	<h1>Join Success</h1>
	<p>가입해 주셔서 감사합니다.</p>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
		
	</div>
</body>
</html>
