package it.polito.model;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import it.polito.infrastructure.ExecutionDeserializer;
import it.polito.model.ExecutionSerializer;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Execution {

	Integer id;

	Integer rate;

	String nodes;

	Graph topology;

	Integer num_clusters;

	List<InfoCluster> info_clusters;

	public Execution() {
	}

	// methods getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	public Graph getTopology() {
		return topology;
	}

	public void setTopology(Graph topology) {
		this.topology = topology;
	}

	public Integer getNum_clusters() {
		return num_clusters;
	}

	public void setNum_clusters(Integer num_clusters) {
		this.num_clusters = num_clusters;
	}

	public List<InfoCluster> getInfo_clusters() {
		return info_clusters;
	}

	public void setInfo_clusters(List<InfoCluster> info_clusters) {
		this.info_clusters = info_clusters;
	}

	// method toString()
	@Override
	public String toString() {
		return "Execution [id=" + id + ", rate=" + rate + ", nodes=" + nodes + ", topology=" + topology
				+ ", num_clusters=" + num_clusters + ", info_clusters=" + info_clusters + "]";
	}

}
