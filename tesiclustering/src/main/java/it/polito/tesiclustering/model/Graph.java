package it.polito.tesiclustering.model;

import it.polito.tesiclustering.model.serializers.GraphSerializer;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import it.polito.tesiclustering.infrastructure.GraphDeserializer;

@JsonSerialize(using = GraphSerializer.class)
@JsonDeserialize(using = GraphDeserializer.class)
public class Graph {

	String name;
	String xml_file;

	public Graph() {
	}

	public Graph(String name, String xml_file) {
		super();
		this.name = name;
		this.xml_file = xml_file;
	}

	// methods getter and setter
	public String getXml_file() {
		return xml_file;
	}

	public void setXml_file(String xml_file) {
		this.xml_file = xml_file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// method toString()
	@Override
	public String toString() {
		return "Graph [name=" + name + ", xml_file=" + xml_file + "]";
	}

}
