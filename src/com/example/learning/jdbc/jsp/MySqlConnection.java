package com.example.learning.jdbc.jsp;


import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MySqlConnection extends HttpServlet{
  private String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
  private String MYSQL_URL = "jdbc:mysql://localhost:3306/test";

  private Connection con;
  private Statement st;
  private ResultSet rs;
  String em,pw;
  PreparedStatement preparedStatement = null;
  String insertTableSQL = "INSERT INTO LOGIN2"
			+ "(name, pass) VALUES"
			+ "(?,?)";
  public MySqlConnection() {

    try {
      Class.forName(MYSQL_DRIVER);
      System.out.println("Class Loaded....");
      con = DriverManager.getConnection(MYSQL_URL,"root","root");
      System.out.println("Connected to the database....");
      st = con.createStatement();
     // st.executeUpdate("CREATE DATABASE IF NOT EXISTS DEMODBmysql");
      int c =st.executeUpdate("CREATE TABLE IF NOT EXISTS LOGIN2 " +
                "(name VARCHAR(255), " + 
                " pass VARCHAR(255))");
      System.out.println("LOGIN table have been created.");
      System.out.println(c+" Row(s) have been affected");
      preparedStatement = con.prepareStatement(insertTableSQL);

		preparedStatement.setString(1, "em");
		preparedStatement.setString(2, "pw");
		

		// execute insert SQL stetement
		preparedStatement.executeUpdate();
		System.out.println("Record is inserted into REGISTRATION1 table!");
      con.close();

    } catch(ClassNotFoundException ex) {
       System.out.println("ClassNotFoundException:\n"+ex.toString());
       ex.printStackTrace();

    } catch(SQLException ex) {
        System.out.println("SQLException:\n"+ex.toString());
        ex.printStackTrace();
    }
  }
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	 em=req.getParameter("email");
	pw=req.getParameter("password");
	}
//  public static void main(String...args) {
//    new MySqlConnection();
//   
//  }
}
