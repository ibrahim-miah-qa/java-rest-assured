package com.globalids.forms;

public class Application {
	private String name = "---";
	private String id;
	private String description;
	
	private String email;
	private String first_name;
	
	public String getEmail() {
		return email;
	}
	
	public String getFirst_name() {
		return first_name;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
