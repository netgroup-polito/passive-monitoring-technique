package it.polito.model;

public class Node {

	// id of Node in the topology graph
	Integer id;

	// methods getter and setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// method toString()
	@Override
	public String toString() {
		return "Node [id=" + id + "]";
	}

}
