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
public class order {

    /**
     * Default constructor
     */
    public order() {
    }

    /**
     * 
     */
    public String O_id;

    /**
     * 
     */
    public boolean O_status;

    /**
     * 
     */
    public String O_date;




    /**
     * @throws SQLException 
     * 
     */
    public void find_details(String Cust) throws SQLException {
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
   	 String query = "SELECT *  from products_db2.order where c_id = \""+Cust+"\"";
 	   Statement st = con.createStatement();
 	   ResultSet rs = st.executeQuery(query);
 	  String c_id = null ,c_name = null,c_add = null,o_id,o_total = null;
 	  System.out.println("..................Order Details..........................");
 	  while(rs.next())
 	   {
 	    o_id = rs.getString("o_id");
 	    c_id = rs.getString("c_id");
 	   String o_status = rs.getString("o_status");
 	   o_total = rs.getString("total");
 	 String o_date = rs.getString("o_date");
	 String pid = rs.getString("p_id");
 	
 	 //get customer name and address
 	String query2 = "SELECT c_name,c_add  from products_db2.customer";
	   Statement st2 = con.createStatement();
	   ResultSet rs2 = st2.executeQuery(query2);
 	 rs2.next();
 	  c_name = rs2.getString("c_name");
 	  c_add = rs2.getString("c_add");
 	 
 	 //getting product name
 	String query3 = "SELECT p_name  from products_db2.product where P_id = "+"\""+pid+"\"";
	   Statement st3 = con.createStatement();
	   ResultSet rs3 = st3.executeQuery(query3);
	 rs3.next();
 	 String p_name = rs3.getString("p_name");

 	 
 	
 	 System.out.println(" Order ID : "+o_id);
 
 	 System.out.println("Product : "+p_name);
 	
 	 System.out.println("Date : "+o_date);
 	 
 	  count++;
 	   }
 		System.out.println("Customer ID: " + c_id);
 	 	System.out.println("Customer name : "+c_name);
 	 	System.out.println("Shipping Address: "+c_add);
 	   System.out.println("Payment : successful");
  	  System.out.println("total : "+o_total);
 	   st.close();
 	   con.close();
   

    	
    	

    	
    	
    }

}