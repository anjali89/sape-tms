package com.sapient.tms.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sapient.tms.bean.Employee;
import com.sapient.tms.enums.Organization;

public class EmployeeDaoImplTest {
	
	private Employee employee;

	@Before
	public void setUp() throws SQLException, ClassNotFoundException {
		employee = new Employee(1, "Employee 1", "288, Sector-25, Gurgaon", "00919876543210",
				"Senior Associate", 65000, Organization.getOrganization("SapientNitro"), false);
	}

	@After
	public void tearDown(){
		employee = null;
	}

	@Test
	public void testSearchForNonEqualityOnNonExistingObject() throws SQLException, ClassNotFoundException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		assertNull(employeeDao.search(252)); //Employee ID 252 does not exist in the DB
	}
	
	@Test
	public void testInsertForSuccessOnCompleteObject() throws SQLException, ClassNotFoundException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		employeeDao.insert(employee);
		Employee newEmployee = employeeDao.search(1);
		assertEquals(employee, newEmployee);
	}
	
	@Test(expected=SQLException.class)
	public void testInsertForFailureOnIncompleteObject() throws SQLException, ClassNotFoundException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		Employee incompleteEmployee = new Employee(1, null, "288, Sector-25, Gurgaon", "00919876543210",
				"Senior Associate", 65000, Organization.getOrganization("SapientNitro"), false);
		employeeDao.insert(incompleteEmployee);
	}
	
	@Test
	public void testSearchForEqualityOnExistingObject() throws SQLException, ClassNotFoundException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		Employee newEmployee = employeeDao.search(1);
		assertEquals(employee, newEmployee);
	}
}
