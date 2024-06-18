<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록</title>

<link type="text/css" 
	rel="stylesheet" 
	href="<c:url value = "/css/home.css" />" />
</head>
<body>
  <div id="container">
   <!--  <div id="header">
      <h1>My Homepage</h1>
    </div> <!-- /header -->
    <c:import url ="/WEB-INF/views/includes/header.jsp" >
    <c:param name="param1" value="value1"/>
    <c:param name="param2" value="value2"/>
    </c:import>
     <c:import url ="/WEB-INF/views/includes/navigation.jsp"></c:import>

	<!-- 방명록 추가 폼 -->

	<h1>작성</h1>
	<form action="<c:url value = "/gb"/>" method="post">
		<input type="hidden" name="a" value="add">
		<table border="1" width="500">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
				<td>비밀번호</td>
				<td><input type="password" name="pass"></td>
			</tr>
			<tr>
				<td colspan="4"><textarea name="content" cols="60" rows="5"></textarea></td>
			</tr>
			<tr>
				<td colspan="4" align="right"><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
	<br />

	<h1>방명록</h1>
	<c:forEach items="${list }" var="vo" varStatus="status">
	<table width="510" border="1">
		<tr>
			<td>[${status.count }]
			</td>
			<td>${vo.name}</td>
			<td>${vo.regDate }</td>
			<td><a
				href="<c:url value = "/gb?a=deleteform&no=${vo.no}" />"> 삭제</a></td>
		</tr>
		<tr>
			<td colspan="4">${vo.content}</td>
		</tr>
	</table>
	<br />
	</c:forEach>
	<div id="wrapper">
      <div id="content">
			<!-- Content 영역 -->
      </div>
	</div>
	<c:import url="/WEB-INF/views/includes/footer.jsp"/> 
	</div>
</body>
</html>