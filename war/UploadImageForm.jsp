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
		<div class="alert alert-info">Upload an image right here and gather points</div>
		<form name="input" action="uploadImage" method="post" enctype="multipart/form-data" class="form-horizontal">
			<input type="file" name="image"><br />
			<input type="submit" name="submit" value="Senden" class="btn btn-success">
		</form>
	</div>
</body>
</html>
