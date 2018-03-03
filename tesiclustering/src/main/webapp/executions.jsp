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
		<p>View all the executions of a graph and decide if delete one of
			these.</p>
	</div>

	<div class="container">
		<h2>Executions List</h2>
		<p>Insert the name of graph to show its executions
		<p>
			<label>Graph Name :</label> <input type="text" id="graph" required>
			<input type="button" onclick="findAllExecution()" value="Submit"
				class="btn btn-primary"></input>
		<ul id="executionList">
		</ul>
		<br>
		<h2>Delete an execution by Id</h2>
		<p>Delete an execution from the list below by id.</p>
		<div>
			<label>Id :</label> <input type="text" id="executiontodelete"
				required> <input type="button" onclick="deleteExecution()"
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
		<br>
	</div>
</body>
</html>
