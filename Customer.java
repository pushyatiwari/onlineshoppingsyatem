package seo_project1;

import java.util.*;
import java.sql.*;


/**
 * 
 */

public class Customer {


    /**
     * Default constructor
     * 
     */
	String cust;
	Boolean order_status = false;
	 public Customer() {
	  
	 }
  /**
     * 
     */
    String c_id;

   String c_name;

    String c_pass;

    String c_address;

    /**
     * 
     */
    String phone;

    /**
     * 
     */
    String email;




    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * 
     */
    
    public void view_cart(String cust) throws Exception {
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
    	
    	
    	 String query = "SELECT *	 from cart where cart_cust_id = \""+cust+"\"";
  	   Statement st = con.createStatement();
  	   ResultSet rs = st.executeQuery(query);
  	   
  	   while(rs.next())
  	   {
  	   int p_id = rs.getInt("cart_id");
  	   String name = rs.getString("cart_p_id");
  	   int quant = rs.getInt("quantity");
  	   

  	   //get product name
  	  
  	 String query3 = "SELECT p_name  from products_db2.product where P_id = "+"\""+name+"\"";
	   Statement st3 = con.createStatement();
	   ResultSet rs3 = st3.executeQuery(query3);
	 rs3.next();
	 String p_name = rs3.getString("p_name");
	
  	 System.out.println(count+"      ");
  	System.out.print("product id : "+name);
  	System.out.print("	product name: "+p_name);
  	
  	  
  	  System.out.println("	quantity : "+quant);
  	  count++;
  	   }
  	   
  	   st.close();
  	   con.close();
    	
    }
    
    
    
    public void view_product() throws Exception {
        // TODO implement here
    	
  	   Product p1 = new Product();
  	   p1.getproduct();
    }

    /**
     * @throws SQLException 
     * 
     */
    public void add_to_cart() throws SQLException {
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
      	//entering product id to the cart.
      	 Scanner in = new Scanner(System.in);
      	System.out.println("Enter Product id:  ");
        String enteredP_id = in.nextLine();
        System.out.println("Enter quantity:  ");
        int quan = in.nextInt();
    	 preparedStatement = con
    	          .prepareStatement("insert into  cart values (?, ?, ?, ?)");
    	 
    	 
    	 //getting last cart id
    	 String query = "SELECT *	 from cart";
    	   Statement st = con.createStatement();
    	   ResultSet rs = st.executeQuery(query);
    	  //System.out.println("rs cart");
    	  if(!rs.next())
    	  {
   		   System.out.println("cart is null");
   		  
 	      preparedStatement.setInt(1, 1);
 	      preparedStatement.setString(2, enteredP_id);
 	      preparedStatement.setInt(3, quan);
 	     preparedStatement.setString(4, cust);
 	      
 	      preparedStatement.executeUpdate();
  
    	  }
    	  else
    	  {
    		  int cartid = 1;
    	   while(rs.next())
    	   {
    	    cartid= rs.getInt("cart_id");
    
    	   }
    	   
  	      preparedStatement.setInt(1, cartid+1);
  	      preparedStatement.setString(2, enteredP_id);
  	      preparedStatement.setInt(3, quan);
  	     preparedStatement.setString(4, cust);
  	      
  	      preparedStatement.executeUpdate();
    	   
    	  }
    	 

    }

    /**
     * @throws SQLException 
     * 
     */
    public void order() throws SQLException {
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
     	// getting total from cart
     	String query = "SELECT quantity,cart_p_id FROM cart where cart_cust_id = "+"\""+cust
     			+"\"";
     	
       Statement st = con.createStatement();
 	   ResultSet rs = st.executeQuery(query);
 	  //System.out.println("rs cart");
 	   int total = 0;
 		String p_id = null;
 	   //rs.next();
 	  while(rs.next())
 	  {
// 		 select distinct(price) from product,cart
// 		where P_id = "1b";
 	  	   int quant = rs.getInt("quantity");
 	  	 p_id = rs.getString("cart_p_id");
 	  	String query2 = "SELECT price from product " + 
 	  			"where P_id = \""+p_id+"\"";
 	  	
 	   Statement st2 = con.createStatement();
 	  ResultSet rs2 = st2.executeQuery(query2);
 	  if(rs2.next())
 	  {
 	  int price = rs2.getInt("price");
      total += (quant*price);
 	  }
 	
 	  }
 	  System.out.println("total is "+total);
 	 payment(total); 
	  
	   
 	
 		
    	
    }

    /**
     * @throws SQLException 
     * 
     */
    
    public void payment(int total) throws SQLException {
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
      	System.out.println("Enter 1 for cod,2 for online  ");
        int order_way = in.nextInt();
        if(order_way==1)
        {
        	System.out.println("you have chosen cash on delivery");
        	order_status = true;
        	 preparedStatement = con
       	          .prepareStatement("insert into  products_db2.order values (?,?,?,?,?,?)");
       	 
       	 
       	 //getting last order_id
       	 String query = "SELECT *  from products_db2.order";
       	   Statement st = con.createStatement();
       	   ResultSet rs = st.executeQuery(query);
       	  //System.out.println("rs cart");
       	   //selecting items from cart\
       	   
       	String query_i = "SELECT cart_p_id FROM cart where cart_cust_id = "+"\""+cust
     			+"\"";
     	
       Statement st_i = con.createStatement();
 	   ResultSet rs_i = st_i.executeQuery(query_i);
 	 String pid;
       	   
       	   
       	   //DELETE FROM t WHERE i = 1
 	   while(rs_i.next())
 	   {
 		  pid = rs_i.getString("cart_p_id");

 		 int cartid1 =1;
 	 	String query_i_max = "SELECT max(o_id) as o_id  FROM products_db2.order";
	
       Statement st_i_m = con.createStatement();
      ResultSet rs_i_m = st_i_m.executeQuery(query_i_max);
      
      if(rs_i_m.next())
    	  cartid1 = rs_i_m.getInt("o_id");
      
       	  if(!rs.next())
       	  {
      		//   System.out.println("order is null");
      		  
    	     preparedStatement.setInt(1,  cartid1+1);
    	     preparedStatement.setString(2, cust);
    	     preparedStatement.setString(3, "Y"	);
    	     preparedStatement.setString(4, "12-06-2020");
   	         preparedStatement.setLong(5, total);
   	      preparedStatement.setString(6, pid);
    	     preparedStatement.executeUpdate();
    	     //deleting cart after order
    	     String query_del = "delete from cart where cart_cust_id = ?";
    	      PreparedStatement preparedStmt = con.prepareStatement(query_del);
    	      preparedStmt.setString(1, cust);
              // execute the preparedstatement
    	      preparedStmt.execute();
     
       	  }
       	  else
       	  {
       		String query_i_max2 = "SELECT max(o_id) as o_id  FROM products_db2.order";
       		int cartid2 =1;
            Statement st_i_m2 = con.createStatement();
           ResultSet rs_i_m2 = st_i_m2.executeQuery(query_i_max2);
           if(rs_i_m2.next())
         	  cartid2 = rs_i_m2.getInt("o_id");
       	   
     	      preparedStatement.setInt(1, cartid2+1);
     	      preparedStatement.setString(2, cust);
  	          preparedStatement.setString(3, "Y");
  	          preparedStatement.setString(4, "12-06-2020");
  	          preparedStatement.setInt(5, total);
  	        preparedStatement.setString(6, pid);
     	      
     	      preparedStatement.executeUpdate();
     	     String query_del = "delete from cart where cart_cust_id = ?";
   	      PreparedStatement preparedStmt = con.prepareStatement(query_del);
   	      preparedStmt.setString(1, cust);
             // execute the preparedstatement
   	      preparedStmt.execute();
     	      
       	   
       	  }
 	   }
        System.out.println("enter 1 to generate slip");
       	 int slip1 = in.nextInt();
         if(slip1==1)
         {
 		Payment p1 = new Payment();
 		p1.slip_generation(cust);
   		System.out.println("order successfull");
         }
        }
        else
        {
        	System.out.println("you have chosen online payment");

        	Onlinepayment on = new Onlinepayment();
        	if(on.Bank_Authentication())
        	{
        		//inserting in order
        		System.out.println("bank authentication passed");
        		
            	 preparedStatement = con
            	          .prepareStatement("insert into   products_db2.order values (?,?,?,?,?,?)");
            	 
            	 
            	 //getting last order_id
            	 String query = "SELECT *	 from  products_db2.order";
            	   Statement st = con.createStatement();
            	   ResultSet rs = st.executeQuery(query);
            
            	  //System.out.println("rs cart");
            	   
            		String query_i = "SELECT quantity,cart_p_id FROM cart where cart_cust_id = "+"\""+cust
                 			+"\"";
                 	
                   Statement st_i = con.createStatement();
             	   ResultSet rs_i = st_i.executeQuery(query_i);
             	 String pid;
                   	   
                   	   
                   	   //DELETE FROM t WHERE i = 1
             	   while(rs_i.next())
             	   {
             		  pid = rs_i.getString("cart_p_id");
            	  if(!rs.next())
            	  {
           		   System.out.println("cart is null");
           		  
         	      preparedStatement.setInt(1, 1);
         	      preparedStatement.setString(2, cust);
         	      preparedStatement.setString(3, "Y");
         	     preparedStatement.setString(4, "12-06-2020");
        	      preparedStatement.setInt(5, total);
        	      preparedStatement.setString(6, pid);
         	      
         	      preparedStatement.executeUpdate();
         	     String query_del = "delete from cart where cart_cust_id = ?";
       	      PreparedStatement preparedStmt = con.prepareStatement(query_del);
       	      preparedStmt.setString(1, cust);
                 // execute the preparedstatement
       	      preparedStmt.execute();
          
            	  }
            	  else
            	  {
            		  int cartid = 1;
            	   while(rs.next())
            	   {
            	    cartid= rs.getInt("o_id");
            
            	   }
            	   
          	      preparedStatement.setInt(1, cartid+1);
          	    preparedStatement.setString(2, cust);
       	      preparedStatement.setString(3, "Y");
       	     preparedStatement.setString(4, "12-06-2020");
       	  preparedStatement.setInt(5, total);
          preparedStatement.setString(6, pid);
          	      
          	      preparedStatement.executeUpdate();
          	    String query_del = "delete from cart where cart_cust_id = ?";
      	      PreparedStatement preparedStmt = con.prepareStatement(query_del);
      	      preparedStmt.setString(1, cust);
                // execute the preparedstatement
      	      preparedStmt.execute();
            	   
            	  }
             	   }
                System.out.println("press 1 to generate slip");
              	
                int slip = in.nextInt();
                if(slip==1)
                {
        		Payment p1 = new Payment();
        		p1.slip_generation(cust);
                }
        		System.out.println("order successfull");
        		
        	}
        	else
        	{
        		System.out.println("error in payment! try again");
        	}
        	
        }
        
    	
    }

    /**
     * @throws SQLException 
     * 
     */
    public String loging() throws SQLException {
        // TODO implement here
    	
    	Scanner in = new Scanner(System.in);
      	System.out.println("Enter customer  name:  ");
        String c_name_entered = in.nextLine();
        System.out.println("Enter password:  ");
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
    	
    	
    	 String query = "SELECT c_id	 from customer where c_name = \""+c_name_entered+
    			 "\"and c_pass = \""+c_pass_entered+"\"";
  	   Statement st = con.createStatement();
  	   ResultSet rs = st.executeQuery(query);
  	  String Customer_id = "";
  	   if(!rs.next())
  	   {
  		   System.out.println("invalid login");
  	   }
  	   else {
  	   
  	   String c_id = rs.getString("c_id");
  	  
  		   Customer_id = c_id;
  	   
  	   System.out.println("customer id is : "+Customer_id);
  	   
  	   cust = Customer_id;
  	   }
	return Customer_id;
    }

    /**
     * @throws SQLException 
     * 
     */
    public void registration() throws SQLException {
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
      	System.out.println("enter cust_id ");
        String cust_id = in.nextLine();
        String cust_name = in.nextLine();
        String cust_pass = in.nextLine();
        String cust_add = in.nextLine();
     	 preparedStatement = con
   	          .prepareStatement("insert into   products_db2.customer values (?,?,?,?)");
     	preparedStatement.setString(1, cust_id);
     	preparedStatement.setString(2, cust_name);
     	preparedStatement.setString(3, cust_pass);
     	preparedStatement.setString(4, cust_add);
     	System.out.println("registration successful");
  }
}