package dBcreate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import couponSystemException.CuponSystemException;
import dbConnectionPool.ConnectionPool;

public class DataBaseCreate {
	public static void main(String[] args) {
		

		ConnectionPool pool = ConnectionPool.getConnectionPool();
		Connection con=null;
		try {
			con = pool.getConnection();
		} catch (CuponSystemException e1) {
			
		}
		// table creation 
	// String sql = "Create table Company(ID BIGINT NOT NULL , COMP_NAME VARCHAR(30), PASSWORD VARCHAR(10), EMAIL VARCHAR(20) , PRIMARY KEY(ID))";
	// String sql = "Create table Customer(ID BIGINT NOT NULL , CUST_NAME VARCHAR(30) , PASSWORD VARCHAR(10),PRIMARY KEY(ID))";
	// String sql = "Create table Coupon(ID BIGINT NOT NULL , TITLE VARCHAR(20) , START_DATE DATE, END_DATE DATE , AMOUNT INT , TYPE VARCHAR (20), MESSAGE VARCHAR(20) , PRICE DOUBLE , IMAGE VARCHAR (50) ,PRIMARY KEY(ID))";
	// String sql = "Create table Customer_Coupon(Customer_ID BIGINT , Coupon_ID BIGINT , PRIMARY KEY(Customer_ID , Coupon_ID  ))";
	// String sql = "Create table Company_Coupon(Company_ID BIGINT , Coupon_ID BIGINT,  PRIMARY KEY(Company_ID , Coupon_ID))";
	// String sql = "Create table Company_ID (Company_ID BIGINT)";
	// String sql="Insert into Company_ID  values(1)"; // initialization first ID  	
	//	String sql = "Create table CUSTOMER_ID (CUSTOMER_ID BIGINT)";
	//	String sql="Insert into CUSTOMER_ID  values(1)"; // initialization first ID
	//	String sql = "Create table COUPON_ID (COUPON_ID BIGINT)";
		String sql="Insert into COUPON_ID values(1)"; // initialization first ID
		java.sql.Statement st;
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.returnConnection(con);
		System.out.println(pool.ConnectionAmoutCheck());



}
}
