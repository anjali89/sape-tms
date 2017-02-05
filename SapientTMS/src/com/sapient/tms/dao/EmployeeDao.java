package com.sapient.tms.dao;

import java.sql.SQLException;

import com.sapient.tms.bean.Employee;

public interface EmployeeDao {
	public Employee search(int id) throws SQLException, ClassNotFoundException;
}
