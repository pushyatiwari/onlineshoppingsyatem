package seo_project1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class driverClass {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("press 1 for Customer login, press 2 for admin login");
		Scanner in = new Scanner(System.in);
		int login_type = in.nextInt();
		if(login_type==1)
		{
		
		//select customer
		Customer cust1 = new Customer();
		String id_cust = cust1.loging();
		if(id_cust!="")
		{
		System.out.println();
		try {
			System.out.println("Available Products:");
			cust1.view_product();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ch = 1;
		order o1 =new order();
        System.out.println("press 0 to exit, 1 to continue");
        ch = in.nextInt();
		while(ch!=0)
		{
			System.out.println("press 1 to add to cart,"
					+ "2 to order"+" 3 to view product"+" , 4 to view cart"+" ,5 to show orders"
							+ "\n 6 to delete cart");
			 ch = in.nextInt();
			switch(ch)
			{
			case 1 : cust1.add_to_cart();
			break;
			case 2 : cust1.order();
			break;
			case 3 : cust1.view_product();
			break;
			case 4: cust1.view_cart(id_cust);
			break;
			case 5: o1.find_details(id_cust);
			break;
			case 6: Cart c1 = new Cart();
			c1.delete_cart(id_cust);
			break;
			}
			System.out.println("press 0 to exit, 1 to continue");

			 ch = in.nextInt();
			
		}
		
	}else {
		System.out.println("try again!");
		
	}
		}
	else if(login_type==2)
	{
		Admin a1 = new Admin();
		String ad_id = a1.login();
		if(ad_id!="")
		{
		System.out.println("press 0 to exit, 1 to continue");
		int ch  = in.nextInt();
		
		while(ch!=0)
		{	System.out.println("press 1 for add product. press 2 for manage order ,press 3 for manage product"
				+ "press 4 for view product");
            ch = in.nextInt();
			switch(ch)
			{
			case 1 : a1.add_product();
			break;
			case 2 :a1.manage_order();
			break;
			case 3 :a1.manage_product();
			break;
			case 4 :Product p1 = new Product();
			p1.getproduct();
			break;
			
			}
			System.out.println("press 0 to exit, 1 to continue");

			 ch = in.nextInt();
	    }	
		
	  }else {
			System.out.println("try again!");
			
		}
		
	}
	
   }
  }
