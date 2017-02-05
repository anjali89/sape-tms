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

	private static final String SELECT_QUERY = "SELECT * FROM PRODUCT WHERE ID = ?";
	
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
			String employeeDesignation = rs.getString("DESIGNATION");
			int employeeBasicSalary = rs.getInt("BASIC_SALARY");
			String employeeOrganizationString = rs.getString("ORGANIZATION");
			Organization employeeOrganization = Organization.getOrganization(employeeOrganizationString);
			employee = new Employee(employeeId, employeeName, employeeAddress, employeeDesignation, employeeBasicSalary, employeeOrganization);
		}
		rs.close();
		preparedStatement.close();
		connection.close();
		return employee;
	}

}
