package it.polito.tesiclustering.infrastructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import it.polito.tesiclustering.model.Execution;
import it.polito.tesiclustering.model.Graph;
import it.polito.tesiclustering.model.Node;
import it.polito.tesiclustering.model.InfoCluster;

public class GraphDeserializer extends StdDeserializer<Graph> {

	public GraphDeserializer() {
		this(null);
	}

	public GraphDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Graph deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		JsonNode graphNode = jp.getCodec().readTree(jp);
		Graph graph = new Graph();
		Execution execution = new Execution();
		ObjectMapper mapper = new ObjectMapper();
		if (existField(graphNode, "name"))
			graph.setName(graphNode.get("name").textValue());
		if (existField(graphNode, "xml_file"))
			graph.setXml_file(graphNode.get("xml_file").textValue());

		return graph;
	}

	private boolean existField(JsonNode node, String field) {

		return node.get(field) != null;

	}

}
