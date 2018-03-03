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

public class ExecutionDeserializer extends StdDeserializer<Execution> {

	public ExecutionDeserializer() {
		this(null);
	}

	public ExecutionDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Execution deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		JsonNode graphNode = jp.getCodec().readTree(jp);
		Execution execution = new Execution();
		ObjectMapper mapper = new ObjectMapper();
		if (existField(graphNode, "id"))
			execution.setId(graphNode.get("id").intValue());
		if (existField(graphNode, "rate"))
			execution.setRate(graphNode.get("rate").intValue());
		if (existField(graphNode, "num_clusters"))
			execution.setNum_clusters(graphNode.get("num_clusters").intValue());
		if (existField(graphNode, "nodes"))
			execution.setNodes(graphNode.get("nodes").textValue());
		if (existField(graphNode, "info_clusters"))
			execution.setInfo_clusters((List<InfoCluster>) mapper
					.convertValue(graphNode.get("info_clusters").findValues("id"), List.class));
		return execution;
	}

	private boolean existField(JsonNode node, String field) {

		return node.get(field) != null;

	}

}
