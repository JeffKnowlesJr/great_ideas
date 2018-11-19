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
		<title>Great Ideas</title>
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
			<div id="dash-container">
				<div class="small-dash-container">
					<h6>${idea.name }</h6>
					<p>Created by: ${idea.creator_name }</p>
					<table>
						<h5>Users who liked the idea:</h5>
						<thead>
							<th class="hcell">Name</th>
						</thead>
						<c:forEach var="person" items="${ idea.users }" >
							<tr>
								<td class="tcell">${ person.name }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div id="bot-nav">
					<c:if test="${ idea.creator_id == user  }">
						<a href="/ideas/edit/${ idea.id }/" class="nav-item nav-list" class="add-btn">Edit</a>
					</c:if>
					<c:if test="${ idea.creator_id == user }">
						<a href="/ideas/delete/${ idea.id }/" class="nav-item nav-list" class="add-btn">Delete</a>
					</c:if>
					<a href="/users/dashboard" class="nav-item nav-list" class="add-btn">Back</a>
				</div>
			</div>
		</div>
	</body>
</html>