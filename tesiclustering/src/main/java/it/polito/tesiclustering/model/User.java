package it.polito.tesiclustering.model;

import java.util.Date;

public class User {

	// id and username of User
	String username;
	Integer id;
	String createdBy;
	Date createdDate;

	public User() {
	}

	public User(String username, Integer id) {
		super();
		this.username = username;
		this.id = id;
	}

	// methods getter and setter
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	// method toString()
	@Override
	public String toString() {
		return "User [username=" + username + ", id=" + id + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ "]";
	}

}
