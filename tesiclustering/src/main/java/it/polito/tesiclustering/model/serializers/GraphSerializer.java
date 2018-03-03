package it.polito.tesiclustering.model.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import it.polito.tesiclustering.model.Graph;
import it.polito.tesiclustering.model.Node;

public class GraphSerializer extends StdSerializer<Graph> {

	public GraphSerializer() {
		this(null);
	}

	protected GraphSerializer(Class<Graph> t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void serialize(Graph graph, JsonGenerator generator, SerializerProvider provider) throws IOException {
		// TODO Auto-generated method stub

		generator.writeStartObject();
		if (graph.getName() != null) {
			generator.writeStringField("name", graph.getName());
		}
		if (graph.getXml_file() != null) {
			generator.writeStringField("xml_file", graph.getXml_file());
		}
		generator.writeEndObject();

	}

}
