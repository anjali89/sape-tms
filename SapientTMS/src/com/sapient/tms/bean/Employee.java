package com.sapient.tms.bean;

public abstract class Employee {
	private int id;
	private int projectId;
	private int name;
	
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
	
}
