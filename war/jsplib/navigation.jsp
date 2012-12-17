<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<div class="navigation">
	<a href="/index.jsp" class="btn btn-large">Home</a>
	<a href="/MainImageSelection.jsp" class="btn btn-large">Play The
		Game</a>
	<%
	 	UserService userService = UserServiceFactory.getUserService();
		if (session.getAttribute("user") != null) {
	%>
	<a href="/UploadImageForm.jsp" class="btn btn-large">Upload New
		Image</a>
	<a href="/Logout.jsp" class="btn btn-large">Logout</a>
	<%
		} else {
	%>
	<a href="login" class="btn btn-large">Login</a>
	<%
		}
	%>
</div>