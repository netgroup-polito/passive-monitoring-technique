package it.polito.tesiclustering.model;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

public class InfoCluster {

	// id and all the parameters returned from a json file

	Integer id;

	Integer id_cluster;

	@JsonIgnoreProperties("info_clusters")
	Execution execution_id;

	Integer num_monitored_nodes;
	Integer num_extended_nodes;
	Integer num_monitored_edges;
	Integer num_extended_edges;
	Integer monitored_diameter;
	Integer extended_diameter;

	public InfoCluster() {
	}

	// methods getter and setter
	public Integer getId() {
		return id;
	}

	public Integer getId_cluster() {
		return id_cluster;
	}

	public void setId_cluster(Integer id_cluster) {
		this.id_cluster = id_cluster;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	public Execution getExecution_id() {
		return execution_id;
	}

	public void setExecution_id(Execution execution_id) {
		this.execution_id = execution_id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNum_monitored_nodes() {
		return num_monitored_nodes;
	}

	public void setNum_monitored_nodes(Integer num_monitored_nodes) {
		this.num_monitored_nodes = num_monitored_nodes;
	}

	public Integer getNum_extended_nodes() {
		return num_extended_nodes;
	}

	public void setNum_extended_nodes(Integer num_extended_nodes) {
		this.num_extended_nodes = num_extended_nodes;
	}

	public Integer getNum_monitored_edges() {
		return num_monitored_edges;
	}

	public void setNum_monitored_edges(Integer num_monitored_edges) {
		this.num_monitored_edges = num_monitored_edges;
	}

	public Integer getNum_extended_edges() {
		return num_extended_edges;
	}

	public void setNum_extended_edges(Integer num_extended_edges) {
		this.num_extended_edges = num_extended_edges;
	}

	public Integer getMonitored_diameter() {
		return monitored_diameter;
	}

	public void setMonitored_diameter(Integer monitored_diameter) {
		this.monitored_diameter = monitored_diameter;
	}

	public Integer getExtended_diameter() {
		return extended_diameter;
	}

	public void setExtended_diameter(Integer extended_diameter) {
		this.extended_diameter = extended_diameter;
	}

	// method toString()
	@Override
	public String toString() {
		return "InfoCluster [id=" + id + ", id_cluster=" + id_cluster + ", execution_id=" + execution_id
				+ ", num_monitored_nodes=" + num_monitored_nodes + ", num_extended_nodes=" + num_extended_nodes
				+ ", num_monitored_edges=" + num_monitored_edges + ", num_extended_edges=" + num_extended_edges
				+ ", monitored_diameter=" + monitored_diameter + ", extended_diameter=" + extended_diameter + "]";
	}

}
