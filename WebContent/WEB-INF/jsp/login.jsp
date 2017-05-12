<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/css/main.css" var="mainCss" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Search</title>
<link href="${mainCss}" rel="stylesheet" />

</head>
<body>
	<div id="divHeader"><h1>Flight Search</h1></div>
	<fieldset class="formField">
	<legend>LOGIN</legend>
	<springForm:form action="login" method="POST" commandName="flightSearchUser">
		<springForm:label path="username">Username: </springForm:label>
		<springForm:input path="username" /> <springForm:errors path="username" cssClass="error" /><br><br>
		<springForm:label path="password">Password:  </springForm:label>
		<springForm:password path="password" />	<springForm:errors path="password" cssClass="error" /><br><br>
		<input type="submit" value="Submit">
	</springForm:form>
	<div class="errorMsg"><h3>${errorMsg}</h3></div>
	<a href="./register">New User?</a>
	</fieldset>
</body>
</html>