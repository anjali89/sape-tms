package com.sapient.tms.client;

import java.sql.SQLException;

import com.sapient.tms.bean.Employee;
import com.sapient.tms.dao.EmployeeDao;
import com.sapient.tms.dao.EmployeeDaoImpl;
import com.sapient.tms.enums.Organization;

public class TMSMain {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Employee employee = new Employee(2, "Employee 2", "543, Sector-14, Gurgaon", "00919875673210",
				"Senior Manager", 105000, Organization.getOrganization("SapientNitro"), false);
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		employeeDao.insert(employee);
	}
}
