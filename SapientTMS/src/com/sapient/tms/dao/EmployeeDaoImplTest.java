package com.sapient.tms.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.sapient.tms.bean.Employee;
import com.sapient.tms.enums.Organization;
import com.sapient.tms.helper.JDBCConnection;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

public class EmployeeDaoImplTest {
	
	@Mock
    private Connection mockConn;
    @Mock
    private PreparedStatement mockPreparedStmnt;
    @Mock
    private ResultSet mockResultSet;
    
    private Employee employee;

	@Before
	public void setUp() throws SQLException, ClassNotFoundException {
		employee = new Employee(1, "Employee1", "Address1", "Senior Associate", 25000, Organization.getOrganization("SapientNitro"));
		when(JDBCConnection.getConnection()).thenReturn(mockConn);
		when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmnt);
		doNothing().when(mockPreparedStmnt).setString(anyInt(), anyString());
		doNothing().when(mockPreparedStmnt).setInt(anyInt(), anyInt());
		when(mockPreparedStmnt.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
		when(mockResultSet.getString("NAME")).thenReturn(employee.getName());
		when(mockResultSet.getString("ADDRESS")).thenReturn(employee.getAddress());
		when(mockResultSet.getString("DESIGNATION")).thenReturn(employee.getDesignation());
		when(mockResultSet.getInt("BASIC_SALARY")).thenReturn(employee.getBasicSalary());
		when(mockResultSet.getString("ORGANIZATION")).thenReturn(employee.getOrganization().getName());
	}

	@After
	public void tearDown(){
		
	}

	@Test
	public void testSearch() throws SQLException, ClassNotFoundException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		Employee newEmployee = employeeDao.search(1);
		verify(mockConn, times(1)).prepareStatement(anyString());
        verify(mockPreparedStmnt, times(1)).setInt(1, 1);
        verify(mockPreparedStmnt, times(1)).executeQuery();
        verify(mockResultSet, times(2)).next();
        verify(mockResultSet, times(4)).getString(anyString());
        verify(mockResultSet, times(1)).getInt(anyString());
        assertEquals(employee, newEmployee);
	}

}
