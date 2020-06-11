package seo_project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * 
 */
public class Admin {

    /**
     * Default constructor
     */
    public Admin() {
    }

    /**
     * 
     */
    public String A_id;

    /**
     * 
     */
    public String A_name;

    /**
     * 
     */
    public String A_pass;

    /**
     * 
     */
    public long A_phone;

    /**
     * 
     */
    public String A_Email;



    /**
     * @throws SQLException 
     * 
     */
    public void add_product() throws SQLException {
        // TODO implement here
    	PreparedStatement preparedStatement = null;
   	    int count = 1; 
     	DBConnect connect1;
     	Connection con = null;
     	try {
     		 connect1 = new DBConnect();
              con = connect1.connect();
     	}
     	catch(Exception e)
     	{
     		e.printStackTrace();
     	}
    	
     	Scanner in = new Scanner(System.in);
     	System.out.println("enter product id");
     	String pid = in.nextLine();
     	System.out.println("enter product name");
     	String pname = in.nextLine();
     	System.out.println("enter product price");
     	String price = in.nextLine();
    	 preparedStatement = con
      	          .prepareStatement("insert into  products_db2.product values (?,?,?)");
    	preparedStatement.setString(1, pid);
	      preparedStatement.setString(2, pname);
          preparedStatement.setString(3, price);
        
	      
	      preparedStatement.executeUpdate();
    }

    /**
     * @throws SQLException 
     * 
     */
    public void manage_order() throws SQLException {
        // TODO implement here
    	//Code to modify order
    	DBConnect connect1;
     	Connection con = null;
     	try {
     		 connect1 = new DBConnect();
              con = connect1.connect();
     	}
     	catch(Exception e)
     	{
     		e.printStackTrace();
     	}
     	System.out.println("enter customer id whose order to be deleted");
     	Scanner in = new Scanner(System.in);
     	String cust_id = in.nextLine();
    	  String query_del = "delete from products_db2.order where c_id = ?";
   	      PreparedStatement preparedStmt = con.prepareStatement(query_del);
   	      preparedStmt.setString(1, cust_id);
             // execute the preparedstatement
   	      preparedStmt.execute();
    }

    /**
     * @throws SQLException 
     * 
     */
    public void manage_product() throws SQLException {
        // TODO implement here
    	//Code to modify product
    	DBConnect connect1;
     	Connection con = null;
     	try {
     		 connect1 = new DBConnect();
              con = connect1.connect();
     	}
     	catch(Exception e)
     	{
     		e.printStackTrace();
     	}
     	System.out.println("enter product id to delete");
     	Scanner in = new Scanner(System.in);
     	String p_id = in.nextLine();
     	
    	  String query_del = "delete from products_db2.product where P_id = ?";
   	      PreparedStatement preparedStmt = con.prepareStatement(query_del);
   	      preparedStmt.setString(1, p_id);
             // execute the preparedstatement
   	      preparedStmt.execute();
    }

    /**
     * @throws SQLException 
     * 
     */
    public String login() throws SQLException {
        // TODO implement here
    	
    	Scanner in = new Scanner(System.in);
      	System.out.println("Enter admin  name:  ");
        String c_name_entered = in.nextLine();
        System.out.println("Enter admin password:  ");
        String c_pass_entered = in.nextLine();
         
    	DBConnect connect1;
    	Connection con = null;
    	try {
    		 connect1 = new DBConnect();
             con = connect1.connect();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    	 String query = "SELECT a_id	 from products_db2.admin where a_name = \""+c_name_entered+
    			 "\"and a_pass = \""+c_pass_entered+"\"";
  	   Statement st = con.createStatement();
  	   ResultSet rs = st.executeQuery(query);
  	  String Customer_id = "";
  	   if(!rs.next())
  	   {
  		   System.out.println("invalid login");
  	   }
  	   else {
  	   
  	   String c_id = rs.getString("a_id");
  	  
  		   Customer_id = c_id;
  	   
  	   System.out.println("customer id is : "+Customer_id);
  	   
  	 A_id = Customer_id;
  	   }
	return Customer_id;
    }

}