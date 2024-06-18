<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>삭제</title>
</head>
<body>
    <form method="post" action="<c:url value = "/gb" />" >
        <input type="hidden" name="no" value="${param.no }">
        <input type="hidden" name="a" value="delete">
        <table>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="pass"></td>
                <td><input type="submit" value="삭제"></td>
                <td><a href="<c:url value ="/gb"/>">목록</a></td>
            </tr>
        </table>
    </form>
</body>
</html>
