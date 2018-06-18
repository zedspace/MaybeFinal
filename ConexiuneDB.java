package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexiuneDB {
	
	public static Connection conectare()
	{
		String db="jdbc:mysql://localhost:3306/catalog";
		String username = "root";
		String password = "rootpass";
		Connection con = null;
		try{  
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con=DriverManager.getConnection(db,username,password);  
//			if(con!=null)
//				System.out.println("Conexiune realizata cu succes!!");
			return con;
			//con.close();  
		}
		catch(Exception e)
		{ 	
				System.out.println("Nu s-a putut realiza conexiunea la DB");
				System.out.println(e);
				return null;
		} 
		
//		try{
//			
//			Statement stmt=con.createStatement();  
//			ResultSet rs=stmt.executeQuery("select * from cont");  
//			while(rs.next())  
//				System.out.println(rs.getString(2)+" "+rs.getString(3));
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
	}  
	
	
	public static void closeResources(Connection conn,ResultSet rs,Statement stm) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stm != null)
				 stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
	

