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
public class Payment {

    /**
     * Default constructor
     */
    public Payment() {
    }

    /**
     * 
     */
    public Long amount;

    /**
     * 
     */
    public String date;

    /**
     * 
     */
    public String payment_number;




    /**
     * 
     */
    public void payment_details() {
        // TODO implement here
    	System.out.println("payment done");
    }

    /**
     * @throws SQLException 
     * 
     */
    public void slip_generation(String Cust) throws SQLException {
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
    	
 	  if(rs.next())
 	   {
 	   String o_id = rs.getString("o_id");
 	   String c_id = rs.getString("c_id");
 	   String o_status = rs.getString("o_status");
 	  String o_total = rs.getString("total");
 	 String o_date = rs.getString("o_date");
 	
 	 //get customer name and address
 	String query2 = "SELECT c_name,c_add  from products_db2.customer";
	   Statement st2 = con.createStatement();
	   ResultSet rs2 = st2.executeQuery(query2);
 	 rs2.next();
 	 String c_name = rs2.getString("c_name");
 	 String c_add = rs2.getString("c_add");
 	 
 	System.out.println("..................Payment Receipt..........................");
 	  System.out.println("HDFC BANK");
 	System.out.println("Customer name : "+c_name);
 	System.out.println("Billing Address: "+c_add);
    System.out.println("Payment : successful");
 	
 	  System.out.println("total : "+o_total);
 	 System.out.println("Date : "+o_date);
 	 
 	   }
 	   
 	   st.close();
 	   con.close();
   
    	
    	
    }

}