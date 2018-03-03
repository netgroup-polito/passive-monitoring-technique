package it.polito.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import it.polito.model.Graph;
import it.polito.model.Node;

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

		generator.writeStringField("name", graph.getName());
		generator.writeStringField("xml_file", graph.getXml_file());

		generator.writeEndObject();

	}

}
