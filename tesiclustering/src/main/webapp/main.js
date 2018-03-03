
// The URLs for the RESTful services
var graphURL = "http://localhost:8080/tesiclustering/rest/graphs";
var contentFile;
var currentUser;

// Retrieve graph list when application starts
findAllGraphs();

// Nothing to delete in initial application state
// $('#btnDelete').hide();

// Nothing to delete in initial application state
// $('#btnDeleteGraph').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

function previewFile() {
	var preview = document.querySelector('img');
	var file = document.querySelector('input[type=file]').files[0];
	var reader = new FileReader();

	reader.addEventListener("load", function() {
		preview.src = reader.result;
	}, false);

	if (file) {
		reader.readAsDataURL(file);
	}
}

function readTextFile(file) {
	var rawFile = new XMLHttpRequest();
	rawFile.open("GET", file, false);
	rawFile.onreadystatechange = function() {
		if (rawFile.readyState === 4) {
			if (rawFile.status === 200 || rawFile.status == 0) {
				var allText = rawFile.responseText;
				alert(allText);
			}
		}
	}
	rawFile.send(null);
}

$('#customFile').change(function(event) {

	var filename = $('#customFile').val();

	alert('Ho cambiato il file');

	/*
	 * if (filename.substring(3,11) == 'fakepath') { filename =
	 * filename.substring(12); } // Remove c:\fake at beginning from localhost
	 * chrome
	 */
	var reader = new FileReader();
	reader.onload = function(e) {
		alert(e.target.result);
	};
	reader.readAsDataURL(event.target.files[0]);
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e) {
	if (e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
	}
});

$('#btnAddGraph').click(function() {
	newGraph();
	return false;
});

$('#btnSaveGraph').click(function() {

	console.log('addGraph');

	addGraph();

	return false;
});

$('#btnDeleteGraph').click(function() {

	deleteGraph();

	return false;
});

$('#btnDeleteExecution').click(function() {

	deleteExecution();

	return false;
});

// live was removed in jQuery 1.9
// use on with selector on descendant of list passed as second parameter

$('#graphList').on('click', 'li', function() {
	findGraphByName($(this).data('identity'));
});

$('#executionList').on('click', 'li', function() {
	findExecutionByGraphName($(this).data('identity'));
});

function search(searchKey) {
	if (searchKey == '')
		findAllGraphs();
	else
		findGraphByName(searchKey);
}

function newGraph() {
	$('#btnDeleteGraph').hide();
	currentGraph = {};
	graphDetails(currentGraph); // Display empty form
}

function findAllGraphs() {
	console.log('findAllgraphs');
	$.ajax({
		type : 'GET',
		url : graphURL,
		dataType : "json", // data type of response
		success : graphList
	});
}

function findAllExecution() {
	console.log('findAllExecutions');
	var x = document.getElementById("graph").value;
	console.log("Grafo inserito : " + x);
	$.ajax({
		type : 'GET',
		url : graphURL + '/' + x + '/executions',
		dataType : "json", // data type of response
		success : executionList
	});
}

function findGraphByName(id) {
	console.log('findGraphByName: ' + name);
	$.ajax({
		type : 'GET',
		url : graphURL + '/' + name,
		dataType : "json",
		success : function(data) {
			$('#btnDeleteGraph').show();
			console.log('findGraphById success: ' + data.name);
			currentGraph = data;
			renderDetails(currentGraph);
		}
	});
}

function findExecutionByGraphName(id) {

	var graph = document.getElementById("graph").value;
	console.log('findExecutionByGraphName: ' + graph);

	$.ajax({
		type : 'GET',
		url : graphURL + '/' + graph + '/executions',
		dataType : "json",
		success : function(data) {
			$('#btnDeleteExecution').show();
			console.log('findExecutionByGraphName success: ' + data.id);
			currentExecution = data;
			renderDetails(currentExecution);
		}
	});
}

function previewFile() {

	var preview = document.querySelector('img');
	var file = document.querySelector('input[type=file]').files[0];
	var reader = new FileReader();

	reader.addEventListener("load", function() {

		console.log(reader.result);
		contentFile = reader.result.split(',')[1];
	}, false);

	if (file) {

		reader.readAsDataURL(file);

		alert('File ultrastraletto');

	}

}

function getBase64(file) {
	var reader = new FileReader();
	reader.readAsDataURL(file);
	reader.onload = function() {
		console.log(reader.result);

	};
	reader.onerror = function(error) {
		console.log('Error: ', error);
	};

}

function addGraph() {
	console.log('addGraph');
	console.log('addGraph');
	var x = document.getElementById("customFile").value;

	console.log('Valore file : ' + x);
	if (x.substring(3, 11) == 'fakepath') {
		x = x.substring(12);
	} // Remove c:\fake at beginning from localhost chrome
	console.log('Valore file : ' + x);

	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : graphURL,
		dataType : "json",
		data : formToJSONGraph(x, contentFile),
		success : function(data, textStatus, jqXHR) {
			alert('Graph created successfully');
			$('#customFile').val(data.name);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('addGraph error: ' + textStatus);
		}
	});
}

function addNodes() {
	console.log('addNodes');
	console.log('addNodes');

	var n = document.getElementById("nodes").value;
	var graph = $('#customFile').val();
	console.log('Valore file : ' + graph);
	if (graph.substring(3, 11) == 'fakepath') {
		graph = graph.substring(12);
	} // Remove c:\fake at beginning from localhost chrome
	console.log('Valore file : ' + graph);

	console.log('Valore nodes : ' + n);

	$
			.ajax({
				type : 'POST',
				contentType : 'application/json',
				url : graphURL + '/' + graph + '/executions',
				dataType : "json",
				data : formToJSONNodes(n),
				success : function(data, textStatus, jqXHR) {
					alert('Execution created successfully');
					console.log('Output....');
					console.log(data);
					$('#output_with_nodes').append(
							'<label>Id: </label><a href="#" data-identity="'
									+ data.id + '">' + data.id + '</a><br>');
					$('#output_with_nodes').append(
							'<label>Interfaces: </label><a href="#" data-identity="'
									+ data.id + '">' + data.nodes + '</a><br>');
					$('#output_with_nodes').append(
							'<label>Topology Name: </label><a href="#" data-identity="'
									+ data.id + '">' + data.topology.name
									+ '</a><br>');
					$('#output_with_nodes').append(
							'<label>Num Clusters: </label><a href="#" data-identity="'
									+ data.id + '">' + data.num_clusters
									+ '</a><br>');
					console.log(data.info_clusters[0]);
					var i = 0;
					var len = objectLength(data.info_clusters);
					console.log(len);
					for (i = 0; i < len; i++) {
						console.log(data.info_clusters[i]);
						$('#output_with_nodes').append(
								'<label>Info about each cluster:</label><br>');
						console.log(data.info_clusters[i].id);
						$('#output_with_nodes')
								.append(
										'<label>Id: </label><a href="#" data-identity="'
												+ data.id + '">'
												+ data.info_clusters[i].id
												+ '</a><br>');
						console.log(data.info_clusters[i].num_extended_edges);
						$('#output_with_nodes')
								.append(
										'<label>Num Extended Edges: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].num_extended_edges
												+ '</a><br>');
						console.log(data.info_clusters[i].num_extended_nodes);
						$('#output_with_nodes')
								.append(
										'<label>Num Extended Nodes: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].num_extended_nodes
												+ '</a><br>');
						console.log(data.info_clusters[i].extended_diameter);
						$('#output_with_nodes')
								.append(
										'<label>Extended Diameter: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].extended_diameter
												+ '</a><br>');
						console.log(data.info_clusters[i].num_monitored_edges);
						$('#output_with_nodes')
								.append(
										'<label>Num Monitored Edges: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].num_monitored_edges
												+ '</a><br>');
						console.log(data.info_clusters[i].num_monitored_nodes);
						$('#output_with_nodes')
								.append(
										'<label>Num Monitored Nodes: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].num_monitored_nodes
												+ '</a><br>');
						console.log(data.info_clusters[i].monitored_diameter);
						$('#output_with_nodes')
								.append(
										'<label>Monitored Diameter: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].monitored_diameter
												+ '</a><br>');

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert('addExecution error: ' + textStatus);
				}
			});
}

function addRate() {

	console.log('addRate');
	console.log('addRate');

	var r = document.getElementById("rate").value;
	var graph = $('#customFile').val();
	console.log('Valore file : ' + graph);
	if (graph.substring(3, 11) == 'fakepath') {
		graph = graph.substring(12);
	} // Remove c:\fake at beginning from localhost chrome
	console.log('Valore file : ' + graph);

	console.log('Valore nodes : ' + r);

	$
			.ajax({
				type : 'POST',
				contentType : 'application/json',
				url : graphURL + '/' + graph + '/executions',
				dataType : "json",
				data : formToJSONRate(r),
				success : function(data, textStatus, jqXHR) {
					alert('Execution created successfully');
					console.log('Output....');
					console.log(data);
					$('#output_with_rate').append(
							'<label>Id: </label><a href="#" data-identity="'
									+ data.id + '">' + data.id + '</a><br>');
					$('#output_with_rate').append(
							'<label>Rate: </label><a href="#" data-identity="'
									+ data.id + '">' + data.rate + '</a><br>');
					$('#output_with_rate').append(
							'<label>Topology Name: </label><a href="#" data-identity="'
									+ data.id + '">' + data.topology.name
									+ '</a><br>');
					$('#output_with_rate').append(
							'<label>Num Clusters: </label><a href="#" data-identity="'
									+ data.id + '">' + data.num_clusters
									+ '</a><br>');
					console.log(data.info_clusters[0]);
					var i = 0;
					var len = objectLength(data.info_clusters);
					console.log(len);
					for (i = 0; i < len; i++) {
						console.log(data.info_clusters[i]);
						$('#output_with_rate').append(
								'<label>Info about each cluster:</label><br>');
						console.log(data.info_clusters[i].id);
						$('#output_with_rate')
								.append(
										'<label>Id: </label><a href="#" data-identity="'
												+ data.id + '">'
												+ data.info_clusters[i].id
												+ '</a><br>');
						console.log(data.info_clusters[i].num_extended_edges);
						$('#output_with_rate')
								.append(
										'<label>Num Extended Edges: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].num_extended_edges
												+ '</a><br>');
						console.log(data.info_clusters[i].num_extended_nodes);
						$('#output_with_rate')
								.append(
										'<label>Num Extended Nodes: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].num_extended_nodes
												+ '</a><br>');
						console.log(data.info_clusters[i].extended_diameter);
						$('#output_with_rate')
								.append(
										'<label>Extended Diameter: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].extended_diameter
												+ '</a><br>');
						console.log(data.info_clusters[i].num_monitored_edges);
						$('#output_with_rate')
								.append(
										'<label>Num Monitored Edges: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].num_monitored_edges
												+ '</a><br>');
						console.log(data.info_clusters[i].num_monitored_nodes);
						$('#output_with_rate')
								.append(
										'<label>Num Monitored Nodes: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].num_monitored_nodes
												+ '</a><br>');
						console.log(data.info_clusters[i].monitored_diameter);
						$('#output_with_rate')
								.append(
										'<label>Monitored Diameter: </label><a href="#" data-identity="'
												+ data.id
												+ '">'
												+ data.info_clusters[i].monitored_diameter
												+ '</a><br>');

					}
				},
				error : function(jqXHR, textStatus, errorThrown) {
					alert('addExecution error: ' + textStatus);
				}
			});
}

function objectLength(obj) {
	var result = 0;
	for ( var prop in obj) {
		if (obj.hasOwnProperty(prop)) {
			// or Object.prototype.hasOwnProperty.call(obj, prop)
			result++;
		}
	}
	return result;
}

function deleteGraph() {

	console.log('DeleteGraph');
	console.log('deleteGraph');
	// prebdere nome grafo dalla lista e metterlo nell'url pre fare la delete
	var graphdeleted;

	console.log($('#graphList').val());

	console.log($('#graphtodelete').val());

	graphdeleted = ($('#graphtodelete').val());

	$.ajax({
		type : 'DELETE',
		url : graphURL + '/' + graphdeleted,
		success : function(data, textStatus, jqXHR) {
			alert('Graph deleted successfully');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('deleteGraph error');
		}
	});
}

function deleteExecution() {

	console.log('DeleteExecution');
	console.log('deleteExecution');
	// prebdere nome grafo dalla lista e metterlo nell'url pre fare la delete
	var executiondeleted;
	var graph;
	console.log($('#executiontodelete').val());
	executiondeleted = ($('#executiontodelete').val());
	graph = ($('#graph').val());
	console.log("Grafo " + graph);

	$.ajax({
		type : 'DELETE',
		url : graphURL + '/' + graph + '/executions/' + executiondeleted,
		success : function(data, textStatus, jqXHR) {
			alert('Execution deleted successfully');
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert('deleteExecution error');
		}
	});
}

function executionList(data) {

	console.log("Entro nella function");

	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#executionList li').remove();

	$.each(list, function(index, execution) {
		console.log(execution);
		console.log(execution.id);
		console.log(execution.num_clusters);
		$('#executionList').append(
				'<li><a href="#" data-identity="' + execution.id + '">'
						+ execution.id + ' Num clusters: '
						+ execution.num_clusters + '</a></li>');
	});
}

function graphList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an
	// object (not an 'array of one')
	var list = data == null ? [] : (data instanceof Array ? data : [ data ]);

	$('#graphList li').remove();
	$.each(list, function(index, graph) {
		$('#graphList').append(
				'<li><a href="#" data-identity="' + graph.id + '">'
						+ graph.name + '</a></li>');
	});
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var userid = $('#id').val();
	return JSON.stringify({
		"id" : userid == "" ? null : userid,
		"username" : $('#username').val()
	});
}

// Helper function to serialize all the form fields into a JSON string
function formToJSONGraph(filename, content) {

	return JSON.stringify({
		"name" : filename,
		"xml_file" : content
	});
}

// Helper function to serialize all the form fields into a JSON string
function formToJSONNodes(nodes) {
	console.log(JSON.stringify({
		"nodes" : nodes
	}));
	return JSON.stringify({
		"nodes" : nodes
	});
}

// Helper function to serialize all the form fields into a JSON string
function formToJSONRate(rate) {
	var parser = parseInt(rate);
	console.log(JSON.stringify({
		"rate" : parser
	}));
	return JSON.stringify({
		"rate" : parser
	});
}

// Changes XML to JSON
function xmlToJson(xml) {

	// Create the return object
	var obj = {};

	if (xml.nodeType == 1) { // element
		// do attributes
		if (xml.attributes.length > 0) {
			obj["@attributes"] = {};
			for (var j = 0; j < xml.attributes.length; j++) {
				var attribute = xml.attributes.item(j);
				obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
			}
		}
	} else if (xml.nodeType == 3) { // text
		obj = xml.nodeValue;
	}

	// do children
	// If just one text node inside
	if (xml.hasChildNodes() && xml.childNodes.length === 1
			&& xml.childNodes[0].nodeType === 3) {
		obj = xml.childNodes[0].nodeValue;
	} else if (xml.hasChildNodes()) {
		for (var i = 0; i < xml.childNodes.length; i++) {
			var item = xml.childNodes.item(i);
			var nodeName = item.nodeName;
			if (typeof (obj[nodeName]) == "undefined") {
				obj[nodeName] = xmlToJson(item);
			} else {
				if (typeof (obj[nodeName].push) == "undefined") {
					var old = obj[nodeName];
					obj[nodeName] = [];
					obj[nodeName].push(old);
				}
				obj[nodeName].push(xmlToJson(item));
			}
		}
	}
	return obj;
}
