package com.servlet.student.dao;


import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.servlet.student.bean.student;
public class studentDao 
{
	private String jdbcURL = "jdbc:mysql://localhost:3306/students?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "1234";
	private String jdbcDriver= "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STUDENT_SQL = "INSERT INTO student" + "  (student_Name,student_Dob,student_Doj) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_STUDENT_BY_ID = "select student_No,student_Name,student_Dob,student_Doj from student where student_No =?";
	private static final String SELECT_ALL_STUDENT = "select * from student";
	private static final String DELETE_STUDENT_SQL = "delete from student where id = ?;";
	private static final String UPDATE_STUDENT_SQL = "update student set student_Name = ?,student_Dob= ?, student_Doj =? where student_No = ?;";
	
	public studentDao() 
	{
		
	}

	protected Connection getConnection() 
	{
		Connection connection = null;
		try {
			Class.forName(jdbcDriver);
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
		
	}
	
	
	public void insertStudent(com.servlet.student.bean.student student) throws SQLException {
		System.out.println(INSERT_STUDENT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
			preparedStatement.setString(1, student.getStudent_Name());
			preparedStatement.setString(2, student.getStudent_Dob());
			preparedStatement.setString(3, student.student_Doj);
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public student selectStudent(int student_No) {
		student student = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
			preparedStatement.setInt(1, student_No);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String student_Name = rs.getString("student_Name");
				String student_Dob = rs.getString("student_Dob");
				String student_Doj = rs.getString("student_Doj");
				student = new student(student_No,student_Name,student_Dob,student_Doj);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

	public List<student> selectAllStudent() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<student> student = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int student_No = rs.getInt("student_No");
				String student_Name = rs.getString("student_Name");
				String student_Dob = rs.getString("student_Dob");
				String student_Doj = rs.getString("student_Doj");
				student.add(new student(student_No, student_Name, student_Dob, student_Doj));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
		
	}

	//update student
	public boolean updateStudent(student student) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, student.getStudent_Name());
			statement.setString(2, student.student_Dob);
			statement.setString(3, student.student_Doj);
			statement.setInt(4, student.getStudent_No());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	//delete student
	
	public boolean deleteStudent(int student_No) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
			statement.setInt(1, student_No);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	
}
