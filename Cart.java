package seo_project1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * 
 */
public class Cart {

    /**
     * Default constructor
     */
    public Cart() {
    }

    /**
     * 
     */
    public String cart_id;

    /**
     * 
     */
    public String date;



    /**
     * @throws SQLException 
     * 
     */
    public void delete_cart(String c_id) throws SQLException {
        // TODO implement here
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
    	 String query_del = "delete from cart where cart_cust_id = ?";
 	      PreparedStatement preparedStmt = con.prepareStatement(query_del);
 	      preparedStmt.setString(1, c_id);
           // execute the preparedstatement
 	      preparedStmt.execute();
    	
    }

    /**
     * 
     */
    public void update_cart() {
        // TODO implement here
    	//code to update
    }

}