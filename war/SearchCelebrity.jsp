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

	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<h1>CelebFinder</h1>
	<jsp:include page="/jsplib/navigation.jsp" />
	<div id="wrapper">
		<div class="alert">Who do you want to find?</div>
		<div class="search-form">
			<form name="input" action="/SearchCelebrity.jsp" method="post"
				class="form-horizontal">
				<input type="text" name="name">
				<input type="submit" name="submit" value="Search"
					class="btn ">
			</form>
		</div>
		
		<div class="results">
			<% if (request.getParameter("name")!=null) {%>
			<div class="results-for">Results for <i><%=request.getParameter("name") %></i></div> 
					<%String name = request.getParameter("name");
					List<Person> persons = PersonController.getAllPersonsFromName(name);
					if (persons != null) {
					for (Person person : persons) {
						if (person.getImage()!= null) {
					%>
					
						<div class="image">
							<img src="/getImage?key=<%=person.getImage().getKey().getId()%>"
								height="480" width="320" class="img-polaroid" />
						</div>
			
			<% }}}} %>
			</div>
	</div>
</body>
</html>
