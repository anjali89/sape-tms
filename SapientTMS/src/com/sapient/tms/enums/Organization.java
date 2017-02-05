package com.sapient.tms.enums;

public enum Organization {
	
	NITRO("SapientNitro"), GLOBAL("Sapient Global Markets");
	private String name;
  
	private Organization(String name) {
		this.name = name;
	}
  
	public static Organization getOrganization(String organizationString) {
		organizationString = organizationString.replace(" ", "");
		organizationString = organizationString.toLowerCase();
		switch(organizationString) {
			case "sapientnitro":
				return Organization.NITRO;
			case "sapientglobalmarkets":
				return Organization.GLOBAL;
			default:
				return null;
		}
	}

	public String getName() {
		return name;
	}
  
}
