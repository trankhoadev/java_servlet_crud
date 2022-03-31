/**
 * 
 */
package com.se.util;

/**
 * @author 48 - Tran Trung Vinh - 18079461
 *
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.se.entity.Customer;


public class CustomerDbUtil {
	private DataSource dataSource;

	public CustomerDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	
public List<Customer> getStudents() throws Exception {		
	List<Customer> students = new ArrayList<>();		
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;		
	try {
		// get a connection
		myConn = dataSource.getConnection();			
		// create sql statement
		String sql = "select * from customer order by last_name";			
		myStmt = myConn.createStatement();			
		// execute query
		myRs = myStmt.executeQuery(sql);			
		// process result set
		while (myRs.next()) {				
			// retrieve data from result set row
			int id = myRs.getInt("id");
			String age = myRs.getString("age");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");				
			// create new student object
			Customer tempStudent = new Customer(id, age, email, firstName, lastName);			
			// add it to the list of students
			students.add(tempStudent);}			
		return students;}
	  finally {
		// close JDBC objects
		close(myConn, myStmt, myRs);}		
}
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close(); }			
			if (myStmt != null) {
				myStmt.close();	}			
			if (myConn != null) {
				myConn.close();   
				// doesn't really close it ... just puts back in connection pool
			}
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addStudent(Customer theStudent) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;		
		try {
			// get db connection
			myConn = dataSource.getConnection();			
			// create sql for insert
			String sql = "insert into customer "
					   + "(age, email, first_name, last_name) "
					   + "values (?, ?, ?, ?)";			
			myStmt = myConn.prepareStatement(sql);			
			// set the param values for the student
			myStmt.setString(1, theStudent.getAge());
			myStmt.setString(2, theStudent.getEmail());
			myStmt.setString(3, theStudent.getFirst_name());			
			myStmt.setString(4, theStudent.getLast_name());	
			// execute sql insert
			myStmt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}

	public Customer getStudent(String theStudentId) throws Exception {
		Customer theStudent = null;	
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;		
		try {
			// convert student id to int
			studentId = Integer.parseInt(theStudentId);			
			// get connection to database
			myConn = dataSource.getConnection();			
			// create sql to get selected student
			String sql = "select * from customer where id=?";			
			// create prepared statement
			myStmt = myConn.prepareStatement(sql);			
			// set params
			myStmt.setInt(1, studentId);			
			// execute statement
			myRs = myStmt.executeQuery();			
			// retrieve data from result set row
			if (myRs.next()) {
				String age = myRs.getString("age");
				String email = myRs.getString("email");	
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				// use the studentId during construction
				theStudent = new Customer(studentId, age, email, firstName, lastName);
			}
			else {
				throw new Exception("Could not find customer id: " + studentId);	}							
			return theStudent;
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);}
	}

	public void updateStudent(Customer theStudent) throws Exception {		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		try {
			// get db connection
			myConn = dataSource.getConnection();			
			// create SQL update statement
			String sql = "update customer "
						+ "set age=? , email=? , first_name=? , last_name=? "
						+ "where id=?";			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);			
			// set params
			myStmt.setString(1, theStudent.getAge());
			myStmt.setString(2, theStudent.getEmail());
			myStmt.setString(3, theStudent.getFirst_name());
			myStmt.setString(4, theStudent.getLast_name());
			myStmt.setInt(5, theStudent.getId());		
			System.out.println("myStmt:::" + myStmt);
			// execute SQL statement
			myStmt.execute();}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, null);}
	}

	public void deleteStudent(String theStudentId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;		
		try {
			// convert student id to int
			int studentId = Integer.parseInt(theStudentId);			
			// get connection to database
			myConn = dataSource.getConnection();			
			// create sql to delete student
			String sql = "delete from customer where id=?";			
			// prepare statement
			myStmt = myConn.prepareStatement(sql);			
			// set params
			myStmt.setInt(1, studentId);			
			// execute sql statement
			myStmt.execute();}
		finally {
			// clean up JDBC code
			close(myConn, myStmt, null);}	
	} 
}
