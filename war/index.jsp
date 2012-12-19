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
			Hello,
			<%=((User) session.getAttribute("user")).getNickname()%>. You currently have <%=UserController.getCelebUserFromAuth((User) session.getAttribute("user")).getScore() %> points.</div>
		<%
			}
		%>
		<div>Welcome to <b>CelebFinder</b>! Show your knowledge of yesterday's, today's and tomorrow's celebrities by climbing the top of the high score. <b>Simply log in with your Google Account and start collecting points.</b> You can get <b>10 points</b> for successfully uploading an image of a celebrity with their name. If you play the game you can get <b>1 point</b> for every correct answer.
		Let the fun begin!</div>
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