<!doctype html>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet" href="CelebFinder.css">
<title>Web Application Starter Project</title>

<script type="text/javascript" language="javascript"
	src="celebfinder/celebfinder.nocache.js"></script>
</head>

<body>

	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1'
		style="position: absolute; width: 0; height: 0; border: 0"></iframe>

	<h1>CelebFinder</h1>
	<div id="wrapper">
		<span>Upload an image right here and gather points</span>
		<form name="input" action="uploadImage" method="post" enctype="multipart/form-data">
			<input type="file" name="image"><br />
			<input type="submit" name="submit" value="Senden">
		</form>
	</div>
</body>
</html>
