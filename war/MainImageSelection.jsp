<!doctype html>
<%@ page import="ch.unibe.mcs.celebfinder.controller.ImageController" %>
<%@ page import="ch.unibe.mcs.celebfinder.controller.PersonController" %>
<%@ page import="ch.unibe.mcs.celebfinder.model.CelebImage" %>
<%@ page import="ch.unibe.mcs.celebfinder.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>

	<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet" href="CelebFinder.css">
<title>Web Application Starter Project</title>

<script type="text/javascript" language="javascript"
	src="celebfinder/celebfinder.nocache.js"></script>
</head>

<body>
	<%
		long imageId = ImageController.getRandomImageID();
			List<Person> persons = ImageController.getNamesFromID(imageId);
			List<Person> wrongPersons = PersonController.getRandomPersons(4 - persons.size(), persons);

			Set<Person> allPersons = new HashSet<Person>();
			allPersons.addAll(persons);
			allPersons.addAll(wrongPersons);
	%>

	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<h1>CelebFinder</h1>
	<div id="wrapper">
		<div>Who is this?</div>
		<img src="/getImage?key=" <%=imageId%> />

		<form action="/imageSelected" method="post">
			<input type="hidden" name="imageKey" value="<%=imageId%>">
			<% for (Person person : allPersons) {%>
<%-- 			<c:forEach var="person" items="${allPersons }"> --%>
				<input type="radio" name="personSelection"
					value="${person.key.id }">${person}</input>
<!-- 			</c:forEach> -->
<% } %>
		</form>
	</div>
</body>
	</html>