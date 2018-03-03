package it.polito.infrastructure;

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

import it.polito.model.Execution;
import it.polito.model.Graph;
import it.polito.model.Node;
import it.polito.model.InfoCluster;

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
		execution.setId(graphNode.get("id").intValue());
		execution.setRate(graphNode.get("rate").intValue());
		execution.setNum_clusters(graphNode.get("num_clusters").intValue());
		execution.setNodes(graphNode.get("nodes").textValue());
		execution.setInfo_clusters((List<InfoCluster>) mapper
				.convertValue(graphNode.get("info_clusters").findValues("id"), new TypeReference<List<InfoCluster>>() {
				}));
		return execution;
	}

}
