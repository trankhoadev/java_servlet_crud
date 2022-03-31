/**
 * 
 */
package com.se.test;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * @author 48 - Tran Trung Vinh - 18079461
 *
 */

@WebServlet("/testdb")
public class TestServlet extends HttpServlet{
private static final long serialVersionUID = 1L;
	
	//define datasource/connection pool for resource   
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Step 1:  Set up the printwriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");				
		// Step 2:  Get a connection to the database
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=web_student_tracker", "sa", "sapassword");
			myConn = dataSource.getConnection();
			// Step 3:  Create a SQL statements
			String sql = "select * from customer";
			
			myStmt = myConn.createStatement();			
			// Step 4:  Execute SQL query
			myRs = myStmt.executeQuery(sql);
			// Step 5:  Process the result set
			while (myRs.next()) {
				
				String email = myRs.getString("email");
				System.out.println("SQL:::" + email);
				out.println(email);	}
		}
		catch (Exception exc) {
			exc.printStackTrace();}
	}

}
