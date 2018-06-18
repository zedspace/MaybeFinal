package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Securitate {

	public static boolean validareUser(String nume,String parola)
	{
		Connection con=ConexiuneDB.conectare();
		boolean valid=false;
		try{
			if(con!=null){
			PreparedStatement stmt= con.prepareStatement("select * from cont where nume_utilizator=? and parola=?");
			stmt.setString(1,nume);
			stmt.setString(2,parola);
			ResultSet rs=stmt.executeQuery(); 
			if(rs!=null)
				valid=true;
			ConexiuneDB.closeResources(con, rs, stmt);
			}	
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		return valid;	
	}
	
	public static String tipUser(String nume,String parola)
	{
		Connection con=ConexiuneDB.conectare();
		String tipUtilizator="Invalid";
		if(validareUser(nume,parola)){
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from cont where nume_utilizator=? and parola=?");
				stmt.setString(1,nume);
				stmt.setString(2,parola);
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					if(rs.getInt("student_numar_matricol") != 0)
						tipUtilizator="s";
					else
						if(rs.getInt("profesor_marca") != 0)
							tipUtilizator="p";
						else
							if(rs.getInt("student_numar_matricol") == 0 && rs.getInt("profesor_marca") == 0)
								tipUtilizator="a";			
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}	
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
		}
		return tipUtilizator;
	}
	
	public static int returnId(String nume,String parola)
	{
		Connection con=ConexiuneDB.conectare();
		int id=0;
		if(tipUser(nume, parola).equals("s")){
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select student_numar_matricol from cont where nume_utilizator=? and parola=?");
				stmt.setString(1,nume);
				stmt.setString(2,parola);
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					if(rs.getInt("student_numar_matricol") != 0)
						id=rs.getInt("student_numar_matricol");	
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}	
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
		}
		else
			if(tipUser(nume, parola).equals("p")){
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select profesor_marca from cont where nume_utilizator=? and parola=?");
					stmt.setString(1,nume);
					stmt.setString(2,parola);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						if(rs.getInt("profesor_marca") != 0)
							id=rs.getInt("profesor_marca");	
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}	
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
			}
		return id;
	}
}