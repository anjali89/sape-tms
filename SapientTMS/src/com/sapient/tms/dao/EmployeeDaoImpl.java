package com.sapient.tms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sapient.tms.bean.Employee;
import com.sapient.tms.enums.Organization;
import com.sapient.tms.helper.JDBCConnection;

public class EmployeeDaoImpl implements EmployeeDao {

	private static final String SELECT_QUERY = "SELECT * FROM EMPLOYEE WHERE ID = ?";
	private static final String INSERT_QUERY = "INSERT INTO EMPLOYEE(ID, NAME, ADDRESS, CONTACT_NUMBER, DESIGNATION,"
			+ " BASIC_SALARY, ORGANIZATION, IS_ADMIN) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	@Override
	public Employee search(int employeeId) throws SQLException, ClassNotFoundException {
		Employee employee = null;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY);
		preparedStatement.setInt(1, employeeId);
		ResultSet rs = preparedStatement.executeQuery();
		if(rs.next()){
			String employeeName = rs.getString("NAME");
			String employeeAddress = rs.getString("ADDRESS");
			String employeeContactNumber = rs.getString("CONTACT_NUMBER");
			String employeeDesignation = rs.getString("DESIGNATION");
			int employeeBasicSalary = rs.getInt("BASIC_SALARY");
			String employeeOrganizationString = rs.getString("ORGANIZATION");
			Organization employeeOrganization = Organization.getOrganization(employeeOrganizationString);
			boolean isEmployeeAdmin = rs.getString("IS_ADMIN").equals("Y") ? true : false;
			employee = new Employee(employeeId, employeeName, employeeAddress, employeeContactNumber, employeeDesignation, employeeBasicSalary, employeeOrganization, isEmployeeAdmin);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		return employee;
	}
	
	@Override
	public boolean insert(Employee employee) throws ClassNotFoundException, SQLException {
		int numAffectedRows;
		Connection connection = JDBCConnection.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY);
		preparedStatement.setInt(1, employee.getId());
		preparedStatement.setString(2, employee.getName());
		preparedStatement.setString(3, employee.getAddress());
		preparedStatement.setString(4, employee.getContactNumber());
		preparedStatement.setString(5, employee.getDesignation());
		preparedStatement.setInt(6, employee.getBasicSalary());
		preparedStatement.setString(7, employee.getOrganization().getName());
		preparedStatement.setString(8, employee.isAdmin() ? "Y" : "N");
		numAffectedRows = preparedStatement.executeUpdate();
		System.out.println(numAffectedRows);
		preparedStatement.close();
		connection.close();
		return numAffectedRows > 0;
	}

}
