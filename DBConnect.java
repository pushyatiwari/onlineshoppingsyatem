package seo_project1;


import java.sql.*;

public class DBConnect
{

	public Connection connect() throws Exception
	{
	   String url = "jdbc:mysql://localhost:3306/products_db2";
  	   String uname = "root";
  	   String pass = "test";
  	   Class.forName("com.mysql.cj.jdbc.Driver");
  	   Connection con = DriverManager.getConnection(url,uname,pass);
  	  
  	   return con;
	}
}