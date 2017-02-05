package com.sapient.tms.bean;

import com.sapient.tms.enums.Organization;

public class Employee {
	private int id;
	private String name;
	private String address;
	private String designation;
	private int basicSalary;
	private Organization organization;
  
	/**
	 * @param id : Employee id
	 * @param name : Employee name
	 * @param address : Employee address
	 * @param designation : Employee designation
	 * @param basicSalary : Employee salary
	 * @param organization : Employee organization (SapientNitro 
	 * 							or SapientGM)
	 */
	public Employee(int id, String name, String address, 
			String designation, int basicSalary,
			Organization organization) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.designation = designation;
		this.basicSalary = basicSalary;
		this.organization = organization;
	}
  
	public String getName() {
		return name;
	}
  
	public void setName(String name) {
		this.name = name;
	}
  
	public int getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(int basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
}
