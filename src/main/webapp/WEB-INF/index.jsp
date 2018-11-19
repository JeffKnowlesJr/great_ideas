<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<link rel="stylesheet" href="css/style.css">
		<link
		href="https://fonts.googleapis.com/css?family=Finger+Paint|Pacifico|Press+Start+2P|Roboto|Roboto+Slab|Trade+Winds"
		rel="stylesheet">
		<title>Great Ideas</title>
	</head>
	<body>
		<div id="wrapper">
			<nav id="top-nav">
				<nav class="nav-container">
				<h6>Great Ideas</h6>
				<div class="nav-list">
					<div class="nav-item"></div>
				</div>
				</nav>
			</nav>
			<div id="container">
				<div class="small-container">
					<h5>Registration</h5>
					<!-- REGISTRATION FORM -->
					<form:form method="POST" action="/users" modelAttribute="user" class="form-container">
						<c:if test="${ exists != null }">
							<p>${exists }</p>
						</c:if>
						<c:if test="${ regError != null }">
							<p>${regError }</p>
						</c:if>
						<p><form:errors path="email" ></form:errors></p>
						<p><form:input path="email" class="input-field" placeholder="Email"></form:input></p>
						<p><form:errors path="name" ></form:errors></p>
						<p><form:input path="name" class="input-field" placeholder="Full Name"></form:input></p>
						<p><form:errors path="password" ></form:errors></p>
						<p><form:input path="password" class="input-field" placeholder="Password"></form:input></p>
						<p><form:errors path="confirmPassword" ></form:errors></p>
						<p><form:input path="confirmPassword" class="input-field" placeholder="Confirm Password"></form:input></p>
						<input type="submit" value="Register" class="input-btn"/>
					</form:form>
				</div>
				<div class="small-container">
					<h5>Login</h5>
					<!-- LOGIN FORM -->
					<form method="POST" action="/users/login" class="form-container">
						<c:if test="${ loginError != null }">
							<p>${exists }</p>
						</c:if>
						<c:if test="${ loginError2 != null }">
							<p>${regError }</p>
						</c:if>
						<input name="email" class="input-field" placeholder="Email"/>
						<input name="password" class="input-field" placeholder="Password"/>
						<input type="submit" value="Login" class="input-btn"/>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>