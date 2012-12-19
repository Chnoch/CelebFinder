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

	<div id="fb-root"></div>
	<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>

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
			<%=((User) session.getAttribute("user")).getNickname()%>. You
			currently have
			<%=UserController.getCelebUserFromAuth(
						(User) session.getAttribute("user")).getScore()%>
			points.
		</div>
		<%
			}
		%>
		<div>
			Welcome to <b>CelebFinder</b>! Show your knowledge of yesterday's,
			today's and tomorrow's celebrities by climbing the top of the high
			score. <b>Simply log in with your Google Account and start
				collecting points.</b> You can get <b>10 points</b> for successfully
			uploading an image of a celebrity with their name. If you play the
			game you can get <b>1 point</b> for every correct answer. Let the fun
			begin!
		</div>
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
		<div id="social">

			<!-- +1 -->
			<div class="plusone">

				<!-- Place this tag where you want the +1 button to render. -->
				<div class="g-plusone" data-size="medium"></div>

				<!-- Place this tag after the last +1 button tag. -->
				<script type="text/javascript">
		  (function() {
		    var po = document.createElement('script'); po.type = 'text/javascript'; po.async = true;
		    po.src = 'https://apis.google.com/js/plusone.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(po, s);
		  })();
		</script>

			</div>
			<!-- end +1 -->


			<!-- twitter -->


			<div class="twitter">

				<a href="https://twitter.com/share" class="twitter-share-button"
					data-text="CelebFinder - The Game for celebrities experts!">Tweet</a>


				<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id))
		
		{js=d.createElement
		
		(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}
		
		(document,"script","twitter-wjs");</script>


			</div>
			<!-- end twitter -->

			<!-- like -->


			<div class="fb-like" data-href="http://celebfinder-sc.appspot.com/"
				data-send="false" data- layout="button_count" data-width="450"
				data-show-faces="false"></div>



			<div id="fb-root"></div>
			<script>(function(d, s, id) {
		  var js, fjs = d.getElementsByTagName(s)[0];
		  if (d.getElementById(id)) return;
		  js = d.createElement(s); js.id = id;
		  js.src = "//connect.facebook.net/de_DE/all.js#xfbml=1";
		  fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));</script>





		</div>




		<!-- end like -->






		<div style="clear: both" />



	</div>
	</div>
</body>
</html>