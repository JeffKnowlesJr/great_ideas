<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta http-equiv="X-UA-Compatible" content="ie=edge">
		<link rel="stylesheet" href="/css/style.css">
		<link href="https://fonts.googleapis.com/css?family=Finger+Paint|Pacifico|Press+Start+2P|Roboto|Roboto+Slab|Trade+Winds" rel="stylesheet">
		<title>Great Ideas Dashboard</title>
	</head>
	<body>
		<div id="wrapper">
			<nav id="top-nav">
				<nav class="nav-container">
					<h6>Great Ideas</h6>
					<div class="nav-list">
						<p class="nav-item">Hi ${ user.name }</p>
						<a href="/users/dashboard" class="nav-item">Home</a>
						<a href="/logout" class="nav-item">Logout</a>
					</div>
				</nav>
			</nav>
			<div id="dash-container">
				<div class="small-dash-container">
					<h5>Great Ideas</h5>
					<table>
						<thead>
							<th class="hcell">Idea</th>
							<th class="hcell">Created By:</th>
							<th class="hcell">Likes</th>
							<th class="hcell">Action</th>
						</thead>
						<c:forEach var="idea" items="${ ideas }" >
							<tr>
								<c:set scope="page" var="creator_name" value=""></c:set>
								<td class="tcell"><a href="/ideas/${ idea.id }">${ idea.name }</a></td>
								<td class="tcell">${ idea.creator_name }</td>
								<td class="tcell">${ idea.users.size()}</td>
								<td class="tcell">
									<c:if test="${ idea.creator_id == user.id }">
										<p>Your Idea!</p>
									</c:if>
									<c:if test="${ !idea.users.contains(user) && idea.creator_id != user.id  }">
										<p><a href="/users/${ user.id }/ideas/${ idea.id }">Like</a></p>
									</c:if>
									<c:if test="${ idea.users.contains(user) && idea.creator_id != user.id }">
										<p><a href="/users/${ user.id }/ideas/${ idea.id }/unlike">Unlike</a></p>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<div id="bot-nav">
				<a href="/users/dashboard/high" class="nav-item nav-list" class="add-btn">Most Likes</a>
				<a href="/users/dashboard/low" class="nav-item nav-list" class="add-btn">Least Likes</a>
				<a href="/ideas/add" class="nav-item nav-list" class="add-btn">Add a New Idea</a>
			</div>
		</div>
	</body>
</html>