<html>
<head>
<title>Interface Clustering</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
\
<script src="jquery-3.3.1.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="main.js"></script>
</head>
<body>
	<div class="jumbotron text-center">
		<h1>Clustering</h1>
		<p>Load the topology and calculate the clusters</p>
	</div>
	<div class="container">
		<h2>Upload the topology</h2>
		<p>Choose a single topology that contains information about a
			specific ISP's network.</p>

		<label class="btn btn-secondary"><input type="file"
			onchange="previewFile()" id="customFile"></label> <label
			class="btn btn-deafault"><button onclick="addGraph()"
				id="btnSaveGraph">Load The file!</button></label> <br>
		<div class="row">
			<div class="col-sm-4">
				<h2>Random Selection</h2>
				<p>Insert a single value ranging from 10 to 100.
				<p>
				<p>(example=10,20,30,40,50,60,70,80,90,100).</p>
				<div>
					<input type="text" id="rate" name="name" required>
					<button onclick="addRate()" type="button" class="btn btn-primary">Submit</button>
				</div>
				<br>
				<div class="container" id="output_with_rate"></div>
				<br>
			</div>
			<div class="col-sm-8">
				<h2>Exact Selection</h2>
				<p>Insert a list of interfaces.
				<p>
				<p>(example=[[33,34,"in"],[33,34,"out"],[34,33,"in"],[34,33,"out"]]).</p>
				<div>
					<input type="text" id="nodes" name="name" required>
					<button onclick="addNodes()" type="button" class="btn btn-primary">Submit</button>
				</div>
				<div class="container" id="output_with_nodes"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<form action="graphs.jsp">
					<div>
						<button type="submit" class="btn btn-default">View all
							graphs</button>
					</div>
				</form>
			</div>
			<div class="col-sm-4">
				<form action="executions.jsp">
					<div>
						<button type="submit" class="btn btn-default">View all
							executions</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br>
</body>
</html>
