package it.polito.tesiclustering.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class InfoClusterDTO {

	Integer id;
	Integer id_cluster;
	Integer num_monitored_nodes;
	Integer num_extended_nodes;
	Integer num_monitored_edges;
	Integer num_extended_edges;
	Integer monitored_diameter;
	Integer extended_diameter;

	public InfoClusterDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_cluster() {
		return id_cluster;
	}

	public void setId_cluster(Integer id_cluster) {
		this.id_cluster = id_cluster;
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

	@Override
	public String toString() {
		return "InfoClusterDTO [id=" + id + ", id_cluster=" + id_cluster + ", num_monitored_nodes="
				+ num_monitored_nodes + ", num_extended_nodes=" + num_extended_nodes + ", num_monitored_edges="
				+ num_monitored_edges + ", num_extended_edges=" + num_extended_edges + ", monitored_diameter="
				+ monitored_diameter + ", extended_diameter=" + extended_diameter + "]";
	}

}
