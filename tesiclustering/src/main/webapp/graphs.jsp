<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Interface Clustering</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="main.js"></script>
</head>
<body>

	<div class="jumbotron text-center">
		<h1>Clustering</h1>
		<p>View all the topology graphs and decide if delete one of these.</p>
	</div>

	<div class="container">
		<h2>Graphs List</h2>
		<ul id="graphList">
		</ul>
		<br>
		<h2>Delete a graph by Name</h2>
		<p>Delete a topology graph from the list below by name.</p>
		<div>
			<label>Name Topology:</label> <input type="text" id="graphtodelete"
				required> <input type="button" onclick="deleteGraph()"
				value="Delete" class="btn btn-danger"></input>
		</div>
	</div>
	<br>
	<div class="container">
		<form action="index.jsp">
			<div>
				<button type="submit" class="btn btn-success">Back Home</button>
			</div>
		</form>
	</div>
	<br>
</body>
</html>
