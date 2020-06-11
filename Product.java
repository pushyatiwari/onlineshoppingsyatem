package seo_project1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

/**
 * 
 */
public class Product {

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * 
     */
    public String p_name;

    /**
     * 
     */
    public String p_id;

    /**
     * 
     */
    public long p_price;




    /**
     * 
     */
    public void available_stock() {
        // TODO implement here
    	
    	
    }

    /**
     * 
     */
    public void getproduct() throws Exception {
        // TODO implement here
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
    	
    	
    	 String query = "SELECT *	 from product";
  	   Statement st = con.createStatement();
  	   ResultSet rs = st.executeQuery(query);
  	  
  	   while(rs.next())
  	   {
  	   String p_id = rs.getString("P_id");
  	   String name = rs.getString("p_name");
  	   String price = rs.getString("price");
  	 System.out.print(count+"      ");
  	System.out.print(p_id+"      ");
  	  System.out.print(name+"      ");
  	  System.out.println(price);
  	  count++;
  	   }
  	   
  	   st.close();
  	   con.close();
    }

}