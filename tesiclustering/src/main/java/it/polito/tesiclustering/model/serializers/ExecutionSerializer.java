package it.polito.tesiclustering.model.serializers;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import it.polito.tesiclustering.model.Execution;
import it.polito.tesiclustering.model.Graph;
import it.polito.tesiclustering.model.InfoCluster;

public class ExecutionSerializer extends StdSerializer<Execution> {

	public ExecutionSerializer() {
		this(null);
	}

	protected ExecutionSerializer(Class<Execution> t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	public void serialize(Execution execution, JsonGenerator generator, SerializerProvider provider)
			throws IOException {
		// TODO Auto-generated method stub

		generator.writeStartObject();

		if (execution.getId() != null) {

			generator.writeNumberField("id", execution.getId());
		}

		if (execution.getRate() != null) {
			generator.writeNumberField("rate", execution.getRate());
		}
		if (execution.getNum_clusters() != null) {
			generator.writeNumberField("num_clusters", execution.getNum_clusters());
		}
		if (execution.getNodes() != null) {
			generator.writeStringField("nodes", execution.getNodes());
		}
		if (execution.getTopology() != null) {
			generator.writeStringField("topology", execution.getTopology().getName());
		}

		if (execution.getInfo_clusters() != null) {

			generator.writeFieldName("info_clusters");
			generator.writeStartArray();

			for (InfoCluster cluster : execution.getInfo_clusters()) {

				generator.writeNumber(cluster.getId());

			}

			generator.writeEndArray();

		}

		generator.writeEndObject();

	}

}
