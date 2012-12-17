<!doctype html>

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
		<div class="alert alert-info">Who is in the image?</div>
		<div class="image">
			<img src="/getImage?key=<%=request.getAttribute("imageKey")%>"
				height="480" width="320" class="img-polaroid" />
		</div>
		<form name="input" action="suggestName" method="post"
			class="form-horizontal">
			<input type="hidden" name="imageKey"
				value="<%=request.getAttribute("imageKey")%>">
			<div class="control-group">
				<label class="control-label" for="firstname">First Name</label>
				<div class="controls">
					<input type="text" name="firstname"><br />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="lastname">Last Name</label>
				<div class="controls">
					<input type="text" name="lastname"><br />
				</div>
			</div>
			<input type="submit" name="submit" value="Submit"
				class="btn btn-success">
		</form>
	</div>
</body>
</html>
