<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@ page import="ch.unibe.mcs.celebfinder.controller.UserController"%>
<%@ page import="ch.unibe.mcs.celebfinder.model.CelebUser"%>

<html>
<header>
	<jsp:include page="/jsplib/header.jsp" />
</header>
<body>

	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<h1>CelebFinder</h1>
	<jsp:include page="/jsplib/navigation.jsp" />
	<div id="wrapper">
		<%
			if (session.getAttribute("user") != null) {
		%>
		<div class="alert alert-info">
			Welcome back,
			<%=((User) session.getAttribute("user")).getNickname()%></div>
		<%
			}
		%>
		<div>Welcome to CelebFinder! Text what this is about! Login and
			start.</div>
		<div class="highscore">
			<h2>Highscore</h2>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Nickname</th>
						<th>Points</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (CelebUser user : UserController.getHighscore()) {
					%>
					<tr>
						<td><%=user.getNickname()%></td>
						<td><%=user.getScore()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>