<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Dojos</title>
</head>
<body>
	<h1>All Dojos</h1>
	<ul>
		<c:forEach items="${dojos}" var="dojo">
			<li><a href="/dojos/${dojo.id}">${dojo.name}</a></li>
		</c:forEach>
	</ul>
	<a href="/dojos/new">Add Dojo</a><br>
	<a href="/ninjas/new">Create Ninja</a>
</body>
</html>