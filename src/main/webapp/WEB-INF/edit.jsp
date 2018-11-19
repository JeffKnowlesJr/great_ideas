<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<link rel="stylesheet" href="/css/style.css">
		<link href="https://fonts.googleapis.com/css?family=Finger+Paint|Pacifico|Press+Start+2P|Roboto|Roboto+Slab|Trade+Winds" rel="stylesheet">
		<title>Great Ideas - Add Idea</title>
	</head>
	<body>
		<div id="wrapper">
			<nav id="top-nav">
				<nav class="nav-container">
					<h6>Great Ideas</h6>
					<div class="nav-list">
						<a href="/users/dashboard" class="nav-item">Home</a>
						<a href="/logout" class="nav-item">Logout</a>
					</div>
				</nav>
			</nav>
			<h6>${idea.name }</h6>
			<div id="dash-container">
				<div class="small-dash-container">
					<!-- CLASS CREATION FORM -->
					<form:form method="POST" action="/ideas/update/${ postId }/" modelAttribute="idea" class="form-add-travel">
						<h5>Edit</h5>
						<c:if test="${ exists != null }">
							<p>${exists }</p>
						</c:if>
						<c:if test="${ regError != null }">
							<p>${regError }</p>
						</c:if>
						<p><form:errors path="name" ></form:errors></p>
						<p><form:input path="name" class="input-field" value="${ ideaOld.name }"></form:input></p>
						<form:input path="creator_id" type="hidden" value="${ideaOld.creator_id}"></form:input>
						<form:input path="creator_name" type="hidden" value="${ideaOld.creator_name}"></form:input>
						<input type="submit" value="Update" class="input-btn"/>
					</form:form>
				</div>
				<div id="bot-nav">
					<a href="/users/dashboard" class="nav-item nav-list" id="add-btn">Back</a>
				</div>
			</div>
		</div>
	</body>
</html>