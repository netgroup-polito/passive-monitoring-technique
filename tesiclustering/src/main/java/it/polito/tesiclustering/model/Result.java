package it.polito.tesiclustering.model;

import java.util.List;

public class Result {

	// id execution_id numbers of clusters and info about each clusters
	Integer id;

	// add rate and topology
	Integer rate;
	String topology;

	Integer execution_id;
	Integer num_clusters;
	List<InfoCluster> info_clusters;

	// methods getter and setter
	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getTopology() {
		return topology;
	}

	public void setTopology(String topology) {
		this.topology = topology;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExecution_id() {
		return execution_id;
	}

	public void setExecution_id(Integer execution_id) {
		this.execution_id = execution_id;
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
		return "Result [id=" + id + ", rate=" + rate + ", topology=" + topology + ", execution_id=" + execution_id
				+ ", num_clusters=" + num_clusters + ", info_clusters=" + info_clusters + "]";
	}

}