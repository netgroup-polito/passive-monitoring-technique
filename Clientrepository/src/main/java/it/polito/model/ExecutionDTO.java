package it.polito.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ExecutionDTO {

	Integer id;
	Integer rate;
	String nodes;
	Graph topology;
	Integer num_clusters;

	List<InfoClusterDTO> info_clusters;

	public ExecutionDTO() {
	}

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

	public List<InfoClusterDTO> getInfo_clusters() {
		return info_clusters;
	}

	public void setInfo_clusters(List<InfoClusterDTO> info_clusters) {
		this.info_clusters = info_clusters;
	}

	@Override
	public String toString() {
		return "ExecutionDTO [id=" + id + ", rate=" + rate + ", nodes=" + nodes + ", topology=" + topology
				+ ", num_clusters=" + num_clusters + ", info_clusters=" + info_clusters + "]";
	}

}
