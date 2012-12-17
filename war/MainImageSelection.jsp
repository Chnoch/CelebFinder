<!doctype html>
<%@ page import="ch.unibe.mcs.celebfinder.controller.ImageController"%>
<%@ page import="ch.unibe.mcs.celebfinder.controller.PersonController"%>
<%@ page import="ch.unibe.mcs.celebfinder.model.CelebImage"%>
<%@ page import="ch.unibe.mcs.celebfinder.model.Person"%>
<%@ page import="ch.unibe.mcs.celebfinder.model.Candidate"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>

<html>
<head>
<jsp:include page="/jsplib/header.jsp" />
</head>

<body>
	<%
		long imageId = ImageController.getRandomImageID();

		List<Person> persons = ImageController.getCandidatePersonsFromID(imageId);
		List<Person> wrongPersons = PersonController.getRandomPersons(
				4 - persons.size(), persons);

		Set<Person> allPersons = new HashSet<Person>();
		allPersons.addAll(persons);
		allPersons.addAll(wrongPersons);

		pageContext.setAttribute("imageId", imageId);
		pageContext.setAttribute("allPersons", wrongPersons);
	%>

	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<h1>CelebFinder</h1>
	<jsp:include page="/jsplib/navigation.jsp" />
	<div id="wrapper">
		<div class="alert alert-info">Who is this?</div>
		<div class="image">
			<img src="/getImage?key=<%=imageId%>" height="480" width="320"
				class="img-polaroid" />
		</div>

		<form action="/imageSelected" method="post">
			<input type="hidden" name="imageKey" value="<%=imageId%>">
			<fieldset>
				<%
					for (Person person : allPersons) {

						pageContext.setAttribute("person", person);
				%>
				<%-- 			<c:forEach var="person" items="${allPersons }"> --%>
				<label class="radio"> <input type="radio"
					name="personSelection" value="${person}">${person}
				</label>
				<!-- 			</c:forEach> -->
				<%
					}
				%>

				<button type="submit" class="btn btn-success">Submit</button>
			</fieldset>
		</form>
	</div>
</body>
</html>