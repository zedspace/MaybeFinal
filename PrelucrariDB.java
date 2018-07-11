package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import claseResurse.AfisarePondere;
import claseResurse.AfisarePredare;
import claseResurse.AnUniversitar;
import claseResurse.Cont;
import claseResurse.Departament;
import claseResurse.Disciplina;
import claseResurse.Grupa;
import claseResurse.Notare;
import claseResurse.Pondere;
import claseResurse.Preda;
import claseResurse.Profesor;
import claseResurse.Specializare;
import claseResurse.Student;

public class PrelucrariDB {
	
	//returneaza lista profesorilor
	public static List<Profesor> returnProfesor(){
		List<Profesor> listaProfesori=new ArrayList<Profesor>();
		Connection con=ConexiuneDB.conectare();
		
		try{
			if(con!=null){
			PreparedStatement stmt= con.prepareStatement("select * from profesor");
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())  
			{	
				Profesor profesor=new Profesor();
				profesor.setMarca(Integer.parseInt(rs.getString("marca")));
				profesor.setNume(rs.getString("nume"));
				profesor.setPrenume(rs.getString("prenume"));
				profesor.setTitulatura(rs.getString("titulatura"));
				profesor.setCod_departament(Integer.parseInt(rs.getString("departament_cod_departament")));
				profesor.setNume_utilizator(rs.getString("cont_nume_utilizator"));
				listaProfesori.add(profesor);			
			}
			ConexiuneDB.closeResources(con, rs, stmt);
			}	
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		return listaProfesori;		
	}
	
	//returneaza lista specializarilor
	public static List<Specializare> returnSpecializari(){	
		List<Specializare> listaSpecializari=new ArrayList<Specializare>();
		Connection con=ConexiuneDB.conectare();		
		try{
			if(con!=null){
			PreparedStatement stmt= con.prepareStatement("select * from specializare order by program_studii,denumire_specializare");
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())  
			{	
				Specializare specializare=new Specializare();
				specializare.setCod_specializare(Integer.parseInt(rs.getString("cod_specializare")));
				specializare.setDenumire_specializare(rs.getString("denumire_specializare"));
				specializare.setProgram_studii(rs.getString("program_studii"));
				listaSpecializari.add(specializare);			
			}
			ConexiuneDB.closeResources(con, rs, stmt);
			}	
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		return listaSpecializari;		
	}
	
	//returneaza specializarea cu un anumit cod
		public static Specializare returnSpecializare(int cod_Specializare){	
			Connection con=ConexiuneDB.conectare();	
			Specializare specializare=new Specializare();
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from specializare where cod_specializare=? order by denumire_specializare");
				stmt.setInt(1, cod_Specializare);
				ResultSet rs=stmt.executeQuery();
				while(rs.next())  
				{	
					
					specializare.setCod_specializare(Integer.parseInt(rs.getString("cod_specializare")));
					specializare.setDenumire_specializare(rs.getString("denumire_specializare"));
					specializare.setProgram_studii(rs.getString("program_studii"));	
					
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}	
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return specializare;		
		}

		public static Specializare returnSpecializare(String denumire_specializare,String forma_invatamant){	
			Connection con=ConexiuneDB.conectare();	
			Specializare specializare=new Specializare();
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from specializare where denumire_specializare=? and program_studii=?");
				stmt.setString(1, denumire_specializare);
				stmt.setString(2, forma_invatamant);
				System.out.println(stmt);
				ResultSet rs=stmt.executeQuery();
				while(rs.next())  
				{	
					
					specializare.setCod_specializare(Integer.parseInt(rs.getString("cod_specializare")));
					specializare.setDenumire_specializare(rs.getString("denumire_specializare"));
					specializare.setProgram_studii(rs.getString("program_studii"));	
					
				}
				System.out.println(specializare.getCod_specializare());
				ConexiuneDB.closeResources(con, rs, stmt);
				}	
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return specializare;		
		}
	
		public static Specializare returnSpecializareaStudentului(int matricol){	
			Connection con=ConexiuneDB.conectare();	
			Specializare specializare=new Specializare();
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("SELECT * FROM `specializare` join grupa on grupa.specializare_cod_specializare=specializare.cod_specializare join student on grupa.id_grupa=student.grupa_id_grupa where numar_matricol=?");
				stmt.setInt(1,matricol);
				System.out.println(stmt);
				ResultSet rs=stmt.executeQuery();
				while(rs.next())  
				{	
					
					specializare.setCod_specializare(Integer.parseInt(rs.getString("cod_specializare")));
					specializare.setDenumire_specializare(rs.getString("denumire_specializare"));
					specializare.setProgram_studii(rs.getString("program_studii"));	
					
				}
				System.out.println(specializare.getCod_specializare());
				ConexiuneDB.closeResources(con, rs, stmt);
				}	
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return specializare;		
		}	
		
	//returneaza lista anilor universitari
	public static List<AnUniversitar> returnAni(){
		List<AnUniversitar> listaAni=new ArrayList<AnUniversitar>();
		Connection con=ConexiuneDB.conectare();		
		try{
			if(con!=null){
			PreparedStatement stmt= con.prepareStatement("select * from an_universitar");
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())  
			{	
				AnUniversitar an=new AnUniversitar();
				an.setId_an_universitar(rs.getInt("id_an_universitar"));
				an.setDenumire_an_universitar(rs.getString("denumire_an_universitar"));
				an.setSemestrul(rs.getInt("semestrul"));
				listaAni.add(an);			
			}
			ConexiuneDB.closeResources(con, rs, stmt);
			}	
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		return listaAni;		
	}
	
	public static AnUniversitar returnAn(String denumire_an,int semestrul){
		AnUniversitar an=new AnUniversitar();
		Connection con=ConexiuneDB.conectare();		
		try{
			if(con!=null){
			PreparedStatement stmt= con.prepareStatement("select * from an_universitar where denumire_an_universitar=? and semestrul=?");
			stmt.setString(1, denumire_an);
			stmt.setInt(2, semestrul);
			System.out.println(stmt);
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())  
			{	
				an.setId_an_universitar(rs.getInt("id_an_universitar"));
				an.setDenumire_an_universitar(rs.getString("denumire_an_universitar"));
				an.setSemestrul(rs.getInt("semestrul"));
							
			}
			ConexiuneDB.closeResources(con, rs, stmt);
			}	
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		return an;		
	}
	
	
	
	public static AnUniversitar returnAnActual() throws ParseException{
	
//		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
//		String dateInString = "2/03/2018";
//		Date date = sdf.parse(dateInString);
//		Calendar calendar=Calendar.getInstance();
//		calendar.setTime(date);
//		System.out.println(calendar.getTime());
		
		Calendar calendar=Calendar.getInstance();
		int an_prezent = calendar.get(Calendar.YEAR);
		int luna_prezenta = calendar.get(Calendar.MONTH);
		int luna=0,semestrul=0;
		String an ="";
		
		AnUniversitar anActual=new AnUniversitar();
		try{
			List<AnUniversitar> listaAni=returnAni();
			if(luna_prezenta<=1)
				{
					an=(an_prezent-1)+"-"+an_prezent;
					semestrul=1;
				}
			else
				if(luna_prezenta>=9){
					an=an_prezent+"-"+(an_prezent+1);
					semestrul=1;
				}
				else
					if(luna_prezenta>=2&&luna_prezenta<=9){
						an=(an_prezent-1)+"-"+an_prezent;
						semestrul=2;
				}
			for(AnUniversitar anItem: listaAni){
					if(anItem.getDenumire_an_universitar().equals(an)&&anItem.getSemestrul()==semestrul)
					{
						anActual.setId_an_universitar(anItem.getId_an_universitar());
						anActual.setDenumire_an_universitar(anItem.getDenumire_an_universitar());
						anActual.setSemestrul(anItem.getSemestrul());
					}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
		return 	anActual;	
	}
	
	//returneaza toate grupele
		public static List<Grupa> returnGrupe(){
			List<Grupa> listaGrupe=new ArrayList<Grupa>();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from grupa");
				ResultSet rs=stmt.executeQuery();
				while(rs.next())  
				{	
					Grupa grupa=new Grupa();
					grupa.setId_grupa(rs.getInt("id_grupa"));
					grupa.setNumar_grupa(rs.getInt("numar_grupa"));
					grupa.setAn_studiu(rs.getInt("an_studiu"));
					grupa.setNumar_studenti(rs.getInt("numar_studenti"));
					grupa.setSpecializare_cod_specializare(rs.getInt("specializare_cod_specializare"));				
					listaGrupe.add(grupa);			
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaGrupe;		
		}

		public static List<Grupa> returnGrupeSpec(){
			List<Grupa> listaGrupe=new ArrayList<Grupa>();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from grupa join specializare on grupa.specializare_cod_specializare=specializare.cod_specializare order by denumire_specializare");
				ResultSet rs=stmt.executeQuery();
				while(rs.next())  
				{	
					Grupa grupa=new Grupa();
					grupa.setId_grupa(rs.getInt("id_grupa"));
					grupa.setNumar_grupa(rs.getInt("numar_grupa"));
					grupa.setAn_studiu(rs.getInt("an_studiu"));
					grupa.setNumar_studenti(rs.getInt("numar_studenti"));
					grupa.setSpecializare_cod_specializare(rs.getInt("specializare_cod_specializare"));				
					listaGrupe.add(grupa);			
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaGrupe;		
		}
		
		public static Grupa returnGrupa(int id_grupa){
			Grupa grupa=new Grupa();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from grupa where id_grupa=?");
				stmt.setInt(1, id_grupa);
				System.out.println(stmt);
				ResultSet rs=stmt.executeQuery();
				while(rs.next())  
				{	
					grupa.setId_grupa(rs.getInt("id_grupa"));
					grupa.setNumar_grupa(rs.getInt("numar_grupa"));
					grupa.setAn_studiu(rs.getInt("an_studiu"));
					grupa.setNumar_studenti(rs.getInt("numar_studenti"));
					grupa.setSpecializare_cod_specializare(rs.getInt("specializare_cod_specializare"));						
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return grupa;		
		}
	
	//returneaza lista grupelor in functie de anul de studiu selectat si specializare
	public static List<Grupa> returnGrupe(int cod_specializare,int an_studiu){
		List<Grupa> listaGrupe=new ArrayList<Grupa>();
		Connection con=ConexiuneDB.conectare();		
		try{
			if(con!=null){
			PreparedStatement stmt= con.prepareStatement("select * from grupa"
														+" where specializare_cod_specializare=?"
														+" and an_studiu=?");
			stmt.setInt(1,cod_specializare);
			stmt.setInt(2,an_studiu);
			ResultSet rs=stmt.executeQuery(); 
			while(rs.next())  
			{	
				Grupa grupa=new Grupa();
				grupa.setId_grupa(rs.getInt("id_grupa"));
				grupa.setNumar_grupa(rs.getInt("numar_grupa"));
				grupa.setAn_studiu(rs.getInt("an_studiu"));
				grupa.setNumar_studenti(rs.getInt("numar_studenti"));
				grupa.setSpecializare_cod_specializare(rs.getInt("specializare_cod_specializare"));				
				listaGrupe.add(grupa);			
			}
			ConexiuneDB.closeResources(con, rs, stmt);
			}				
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}	
		return listaGrupe;		
	}
	
	//returneaza lista disciplinelor
		public static List<Disciplina> returnDiscipline(){
			List<Disciplina> listaDiscipline=new ArrayList<Disciplina>();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from disciplina order by denumire_disciplina");
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					Disciplina disciplina= new Disciplina();
					disciplina.setId_disciplina(rs.getInt("id_disciplina"));
					disciplina.setCod_disciplina(rs.getInt("cod_disciplina"));
					disciplina.setDenumire_disciplina(rs.getString("denumire_disciplina"));
					disciplina.setTip_disciplina(rs.getString("tip_disciplina"));
					listaDiscipline.add(disciplina);					
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaDiscipline;		
		}
		
		public static void insertPreda(int profesor,int disciplina,int an_universitar,int grupa)
		{
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("insert into preda"+
			"(profesor_marca,disciplina_id_disciplina,an_universitar_id_an_universitar,grupa_id_grupa) "+
			"values (?,?,?,?)");
				stmt.setInt(1,profesor);
				stmt.setInt(2,disciplina);
				stmt.setInt(3,an_universitar);
				stmt.setInt(4,grupa);
				System.out.println(stmt);
				stmt.executeUpdate(); 
				con.close();
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
		}
		
		public static void updatePreda(int numar_credite,int id_preda)
		{
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("update preda set numar_credite=? where id_preda=?");
				stmt.setInt(1,numar_credite);
				stmt.setInt(2,id_preda);
				System.out.println(stmt);
				stmt.executeUpdate(); 
				con.close();
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
		}
		
		public static List<Preda> returnPreda(){
			List<Preda> listaPredare=new ArrayList<Preda>();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from preda join disciplina on disciplina.id_disciplina=preda.disciplina_id_disciplina order by disciplina.denumire_disciplina");
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					Preda predare= new Preda();
					predare.setId_preda(rs.getInt("id_preda"));
					predare.setProfesor_marca(rs.getInt("profesor_marca"));
					predare.setDisciplina_id_disciplina(rs.getInt("disciplina_id_disciplina"));
					predare.setAn_universitar_id_an_universitar(rs.getInt("an_universitar_id_an_universitar"));
					predare.setGrupa_id_grupa(rs.getInt("grupa_id_grupa"));
					listaPredare.add(predare);					
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaPredare;		
		}
		
		
		public static List<Preda> returnPreda(int marca){
			List<Preda> listaPredare=new ArrayList<Preda>();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from preda where profesor_marca=?");
				stmt.setInt(1, marca);
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					Preda predare= new Preda();
					predare.setId_preda(rs.getInt("id_preda"));
					predare.setProfesor_marca(rs.getInt("profesor_marca"));
					predare.setDisciplina_id_disciplina(rs.getInt("disciplina_id_disciplina"));
					predare.setAn_universitar_id_an_universitar(rs.getInt("an_universitar_id_an_universitar"));
					predare.setGrupa_id_grupa(rs.getInt("grupa_id_grupa"));
					listaPredare.add(predare);					
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaPredare;		
		}
		
		public static List<Preda> returnPreda(int marca,int id_disciplina,int id_grupa,int an_univ){
			List<Preda> listaPredare=new ArrayList<Preda>();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from preda where profesor_marca=? and disciplina_id_disciplina=? and an_universitar_id_an_universitar=? and grupa_id_grupa=?");
				stmt.setInt(1,marca);
				stmt.setInt(2,id_disciplina);
				stmt.setInt(3,an_univ);
				stmt.setInt(4,id_grupa);
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					Preda predare= new Preda();
					predare.setId_preda(rs.getInt("id_preda"));
					predare.setProfesor_marca(rs.getInt("profesor_marca"));
					predare.setDisciplina_id_disciplina(rs.getInt("disciplina_id_disciplina"));
					predare.setAn_universitar_id_an_universitar(rs.getInt("an_universitar_id_an_universitar"));
					predare.setGrupa_id_grupa(rs.getInt("grupa_id_grupa"));
					listaPredare.add(predare);					
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaPredare;		
		}
		
		public static List<AfisarePredare> afisarePredare(List<Preda> listaPredare)
		{
			List<AfisarePredare> predareProfesori=new ArrayList<AfisarePredare>();
			Connection con=ConexiuneDB.conectare();	
			int marca;
			int id_disciplina;
			int id_an;
			int id_grupa;
			int id_specializare=0;
			try{
				if(con!=null){
					for(Preda preda:listaPredare)
					{
						marca=preda.getProfesor_marca();
						id_disciplina=preda.getDisciplina_id_disciplina();
						id_an=preda.getAn_universitar_id_an_universitar();
						id_grupa=preda.getGrupa_id_grupa();
						AfisarePredare ap=new AfisarePredare();
						PreparedStatement stmt= con.prepareStatement("select nume,prenume,titulatura from profesor where marca =?");
						stmt.setInt(1,marca);
						ResultSet rs=stmt.executeQuery(); 
						while(rs.next())  
						{	
							ap.setNumeProfesor(rs.getString("nume"));
							ap.setPrenumeProfesor(rs.getString("prenume"));
							ap.setTitulatura(rs.getString("titulatura"));
						}
						PreparedStatement stmt1= con.prepareStatement("select denumire_disciplina,tip_disciplina from disciplina where id_disciplina =?");
						stmt1.setInt(1,id_disciplina);
						ResultSet rs1=stmt1.executeQuery(); 
						while(rs1.next())  
						{	
							ap.setNumeDisciplina(rs1.getString("denumire_disciplina"));	
							ap.setTipDisciplina(rs1.getString("tip_disciplina"));
						}
						PreparedStatement stmt2= con.prepareStatement("select denumire_an_universitar from an_universitar where id_an_universitar =?");
						stmt2.setInt(1,id_an);
						ResultSet rs2=stmt2.executeQuery(); 
						while(rs2.next())  
						{	
							ap.setAn(rs2.getString("denumire_an_universitar"));
						}
						PreparedStatement stmt3= con.prepareStatement("select numar_grupa,specializare_cod_specializare from grupa where id_grupa =?");
						stmt3.setInt(1,id_grupa);
						ResultSet rs3=stmt3.executeQuery(); 
						while(rs3.next())  
						{	
							ap.setGrupa(rs3.getInt("numar_grupa"));
							id_specializare=rs3.getInt("specializare_cod_specializare");
						}
						PreparedStatement stmt4= con.prepareStatement("select denumire_specializare from specializare where cod_specializare =?");
						stmt4.setInt(1,id_specializare);
						ResultSet rs4=stmt4.executeQuery(); 
						while(rs4.next())  
						{	
							ap.setSpecializare(rs4.getString("denumire_specializare"));
							
						}
						predareProfesori.add(ap);
					}
								
				con.close();
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return predareProfesori;
		}
		
		public static List<Specializare> predareSpecializare(List<Preda> listaPredare)
		{
			List<Specializare> predareSpecializare=new ArrayList<Specializare>();
			Connection con=ConexiuneDB.conectare();	

			int id_grupa;
			int id_specializare=0;
			boolean exista=false;
			try{
				if(con!=null){
					for(Preda preda:listaPredare)
					{
						id_grupa=preda.getGrupa_id_grupa();
						Specializare spec=new Specializare();
						PreparedStatement stmt3= con.prepareStatement("select specializare_cod_specializare from grupa where id_grupa =?");
						stmt3.setInt(1,id_grupa);
						ResultSet rs3=stmt3.executeQuery(); 
						while(rs3.next())  
						{	
							id_specializare=rs3.getInt("specializare_cod_specializare");
						}
						PreparedStatement stmt4= con.prepareStatement("select * from specializare where cod_specializare =?");
						stmt4.setInt(1,id_specializare);
						ResultSet rs4=stmt4.executeQuery(); 
						while(rs4.next())  
						{	
							spec.setCod_specializare(Integer.parseInt(rs4.getString("cod_specializare")));
							spec.setDenumire_specializare(rs4.getString("denumire_specializare"));
							spec.setProgram_studii(rs4.getString("program_studii"));
							
						}
						for(Specializare sp: predareSpecializare)
							if(sp.getCod_specializare()==spec.getCod_specializare())
								exista=true;
							else
								exista=false;
						if(exista==false)		
							predareSpecializare.add(spec);
					}
								
				con.close();
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return predareSpecializare;
		}
		
		public static void insertPondere(int profesor,int disciplina,int grupa,int id_an_universitar,int pondere)
		{
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("insert into stabileste_pondere"+
			"(profesor_marca,disciplina_id_disciplina,grupa_id_grupa,an_universitar_id_an_universitar,pondere) "+
			"values (?,?,?,?,?)");
				stmt.setInt(1,profesor);
				stmt.setInt(2,disciplina);
				stmt.setInt(3,grupa);
				stmt.setInt(4,id_an_universitar);
				stmt.setInt(5,pondere);
				System.out.println(stmt);
				stmt.execute(); 
				con.close();
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
		}
		
		public static void insertNota(Date data,int numar_matricol,int marca_profesor,int id_disciplina,int nota)
		{
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("insert into notare"+
			"(data,student_numar_matricol,profesor_marca,disciplina_id_disciplina,nota) "+
			"values (?,?,?,?,?)");
				stmt.setDate(1,data);
				stmt.setInt(2,numar_matricol);
				stmt.setInt(3,marca_profesor);
				stmt.setInt(4,id_disciplina);
				stmt.setInt(5,nota);
				System.out.println(stmt);
				stmt.execute(); 
				con.close();
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
		}
		
		public static Notare returnNotare(int student_numar_matricol,int profesor_marca,int disciplina_id_disciplina){
		
			Connection con=ConexiuneDB.conectare();	
			Notare notare= new Notare();
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from notare where student_numar_matricol=? and profesor_marca=? and disciplina_id_disciplina=?");
				stmt.setInt(1, student_numar_matricol);
				stmt.setInt(2, profesor_marca);
				stmt.setInt(3, disciplina_id_disciplina);
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					notare.setId_notare(rs.getInt("id_notare"));
					notare.setData(rs.getDate("data"));
					notare.setStudent_numar_matricol(rs.getInt("student_numar_matricol"));
					notare.setProfesor_marca(rs.getInt("profesor_marca"));
					notare.setDisciplina_id_disciplina(rs.getInt("disciplina_id_disciplina"));
					notare.setNota(rs.getInt("nota"));
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return notare;		
		}
		
		public static List<Pondere> returnPondere(){
			List<Pondere> listaPondere=new ArrayList<Pondere>();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from stabileste_pondere");
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					Pondere pondere= new Pondere();
					pondere.setId_pondere(rs.getInt("id_pondere"));
					pondere.setProfesor_marca(rs.getInt("profesor_marca"));
					pondere.setDisciplina_id_disciplina(rs.getInt("disciplina_id_disciplina"));
					pondere.setAn_universitar_id_an_universitar(rs.getInt("an_universitar_id_an_universitar"));
					pondere.setGrupa_id_grupa(rs.getInt("grupa_id_grupa"));
					pondere.setPondere(rs.getInt("pondere"));
					listaPondere.add(pondere);					
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaPondere;		
		}
		
		public static List<AfisarePondere> afisarePondere(List<Pondere> listaPondere)
		{
			List<AfisarePondere> ponderi=new ArrayList<AfisarePondere>();
			Connection con=ConexiuneDB.conectare();	
			int marca;
			int id_disciplina;
			int id_an;
			int id_grupa;
			int ponderevar;
			int id_specializare=0;
			try{
				if(con!=null){
					for(Pondere pondere:listaPondere)
					{
						marca=pondere.getProfesor_marca();
						id_disciplina=pondere.getDisciplina_id_disciplina();
						id_an=pondere.getAn_universitar_id_an_universitar();
						id_grupa=pondere.getGrupa_id_grupa();
						ponderevar=pondere.getPondere();
						AfisarePondere ap=new AfisarePondere();
						PreparedStatement stmt= con.prepareStatement("select nume,prenume,titulatura from profesor where marca =?");
						stmt.setInt(1,marca);
						ResultSet rs=stmt.executeQuery(); 
						while(rs.next())  
						{	
							ap.setNumeProfesor(rs.getString("nume"));
							ap.setPrenumeProfesor(rs.getString("prenume"));
							ap.setTitulatura(rs.getString("titulatura"));
						}
						PreparedStatement stmt1= con.prepareStatement("select denumire_disciplina,tip_disciplina from disciplina where id_disciplina =?");
						stmt1.setInt(1,id_disciplina);
						ResultSet rs1=stmt1.executeQuery(); 
						while(rs1.next())  
						{	
							ap.setNumeDisciplina(rs1.getString("denumire_disciplina"));	
							ap.setTipDisciplina(rs1.getString("tip_disciplina"));
						}
						PreparedStatement stmt2= con.prepareStatement("select denumire_an_universitar from an_universitar where id_an_universitar =?");
						stmt2.setInt(1,id_an);
						ResultSet rs2=stmt2.executeQuery(); 
						while(rs2.next())  
						{	
							ap.setAn(rs2.getString("denumire_an_universitar"));
						}
						PreparedStatement stmt3= con.prepareStatement("select numar_grupa,specializare_cod_specializare from grupa where id_grupa =?");
						stmt3.setInt(1,id_grupa);
						ResultSet rs3=stmt3.executeQuery(); 
						while(rs3.next())  
						{	
							ap.setGrupa(rs3.getInt("numar_grupa"));
							id_specializare=rs3.getInt("specializare_cod_specializare");
						}
						PreparedStatement stmt4= con.prepareStatement("select denumire_specializare from specializare where cod_specializare =?");
						stmt4.setInt(1,id_specializare);
						ResultSet rs4=stmt4.executeQuery(); 
						while(rs4.next())  
						{	
							ap.setSpecializare(rs4.getString("denumire_specializare"));
							
						}
						PreparedStatement stmt5= con.prepareStatement("select pondere from stabileste_pondere");
						ResultSet rs5=stmt5.executeQuery(); 
						while(rs5.next())  
						{	
							ap.setPondere(rs5.getInt("pondere"));
							
						}
						ponderi.add(ap);
					}
								
				con.close();
				}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return ponderi;
		}
		
		public static List<Departament> returnDepartamente()
		{
			List<Departament> listaDepartamente=new ArrayList<Departament>();
			Connection con=ConexiuneDB.conectare();		
			try{
				if(con!=null){
				PreparedStatement stmt= con.prepareStatement("select * from departament order by denumire_departament");
				ResultSet rs=stmt.executeQuery(); 
				while(rs.next())  
				{	
					Departament departament=new Departament();
					departament.setCod_departament(Integer.parseInt(rs.getString("cod_departament")));
					departament.setDenumire_departament(rs.getString("denumire_departament"));
					listaDepartamente.add(departament);		
				}
				ConexiuneDB.closeResources(con, rs, stmt);
				}	
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaDepartamente;	
		}
		
		//returneaza departamentul corespunzator codului
				public static Departament returnDepartamente(int cod_departament)
				{
					Departament departament=new Departament();
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("select * from departament where cod_departament=?");
						stmt.setInt(1, cod_departament);
						ResultSet rs=stmt.executeQuery();
						while(rs.next())  
						{	
							departament.setCod_departament(Integer.parseInt(rs.getString("cod_departament")));
							departament.setDenumire_departament(rs.getString("denumire_departament"));	
						}
						ConexiuneDB.closeResources(con, rs, stmt);
						}	
						
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}	
					return departament;	
				}
				
				public static Departament returnDepartamente(String denumire_dep)
				{
					Departament departament=new Departament();
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("select * from departament where denumire_departament=?");
						stmt.setString(1, denumire_dep);
						ResultSet rs=stmt.executeQuery();
						System.out.println(stmt);
						while(rs.next())  
						{	
							departament.setCod_departament(Integer.parseInt(rs.getString("cod_departament")));
							departament.setDenumire_departament(rs.getString("denumire_departament"));	
						}
						ConexiuneDB.closeResources(con, rs, stmt);
						}	
						
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}	
					return departament;	
				}
				
				public static List<Student> returnStudenti()
				{
					List<Student> listaStudenti=new ArrayList<Student>();
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("select * from student");
						ResultSet rs=stmt.executeQuery();
						while(rs.next())  
						{	
							Student student=new Student();
							student.setNumar_matricol(Integer.parseInt(rs.getString("numar_matricol")));	
							student.setCnp(rs.getString("cnp"));
							student.setNume(rs.getString("nume"));
							student.setPrenume(rs.getString("prenume"));
							student.setForma_finantare(rs.getString("forma_finantare"));
							listaStudenti.add(student);
						}
						ConexiuneDB.closeResources(con, rs, stmt);
						}	
						
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}	
					return listaStudenti;
				}
				
				public static List<Student> returnStudentiGrupa(int id_grupa)
				{
					List<Student> listaStudenti=new ArrayList<Student>();
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("select * from student where grupa_id_grupa=?");
						stmt.setInt(1, id_grupa);
						ResultSet rs=stmt.executeQuery();
						while(rs.next())  
						{	
							Student student=new Student();
							student.setNumar_matricol(Integer.parseInt(rs.getString("numar_matricol")));	
							student.setCnp(rs.getString("cnp"));
							student.setNume(rs.getString("nume"));
							student.setPrenume(rs.getString("prenume"));
							student.setForma_finantare(rs.getString("forma_finantare"));
							listaStudenti.add(student);
						}
						ConexiuneDB.closeResources(con, rs, stmt);
						}	
						
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}	
					return listaStudenti;
				}
				
				public static Student returnStudent(String cnp)
				{
					Student student=new Student();
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("select * from student where cnp=?");
						stmt.setString(1, cnp);
						ResultSet rs=stmt.executeQuery();
						while(rs.next())  
						{					
							student.setNumar_matricol(Integer.parseInt(rs.getString("numar_matricol")));	
							student.setCnp(rs.getString("cnp"));
							student.setNume(rs.getString("nume"));
							student.setPrenume(rs.getString("prenume"));
							student.setForma_finantare(rs.getString("forma_finantare"));
							
						}
						ConexiuneDB.closeResources(con, rs, stmt);
						}	
						
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}	
					return student;
				}
				
				public static List<Student> returnStudentiSpecializare(int cod_specializare)
				{
					List<Student> studentiSpecializare=new ArrayList<Student>();
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("SELECT * FROM student join grupa on student.grupa_id_grupa=grupa.id_grupa join specializare on specializare.cod_specializare=grupa.specializare_cod_specializare where cod_specializare=?");
						stmt.setInt(1, cod_specializare);
						ResultSet rs=stmt.executeQuery();
						while(rs.next())  
						{		
							Student student=new Student();
							student.setNumar_matricol(Integer.parseInt(rs.getString("numar_matricol")));	
							student.setCnp(rs.getString("cnp"));
							student.setNume(rs.getString("nume"));
							student.setPrenume(rs.getString("prenume"));
							student.setForma_finantare(rs.getString("forma_finantare"));
							studentiSpecializare.add(student);
							
						}
						ConexiuneDB.closeResources(con, rs, stmt);
						}	
						
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}	
					return studentiSpecializare;
				}
				
				public static List<Cont> returnConturi(){
					
					List<Cont> listaConturi=new ArrayList<Cont>();
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("select * from cont");
						ResultSet rs=stmt.executeQuery();
						while(rs.next())  
						{	
							Cont cont=new Cont();
							cont.setUtilizator(rs.getString("nume_utilizator"));
							cont.setParola(rs.getString("parola"));
							cont.setMarca(Integer.parseInt(rs.getString("profesor_marca")));
							cont.setInformatii(rs.getString("alte_infromatii"));
							cont.setNumar_matricol(Integer.parseInt(rs.getString("student_numar_matricol")));
							listaConturi.add(cont);
						}
						ConexiuneDB.closeResources(con, rs, stmt);
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}	
					return listaConturi;
				}
				
			public static Cont returnCont(int nr_matricol,int marca){
					
					Cont cont=new Cont();
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("select * from cont where student_numar_matricol=? or profesor_marca=?");
						stmt.setInt(1,nr_matricol);
						stmt.setInt(2,marca);
						ResultSet rs=stmt.executeQuery();
						while(rs.next())  
						{	
					
							cont.setUtilizator(rs.getString("nume_utilizator"));
							cont.setParola(rs.getString("parola"));
							cont.setMarca(Integer.parseInt(rs.getString("profesor_marca")));
							cont.setInformatii(rs.getString("alte_infromatii"));
							cont.setNumar_matricol(Integer.parseInt(rs.getString("student_numar_matricol")));
						
						}
						ConexiuneDB.closeResources(con, rs, stmt);
						}
					}
					catch(SQLException e)
					{
						e.printStackTrace();
					}	
					return cont;
				}
				//prelucrari departamente
				public static void insertDepartament(String denumire_deparatment)
				{
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("insert into departament (denumire_departament) values (?)");
						stmt.setString(1,denumire_deparatment);
						System.out.println(stmt);
						stmt.executeUpdate();
						con.close();
						}				
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						System.out.println("Departamentul nu a putut fi inserat");
					}	
				}
				
				public static void stergereDepartament(String cod_deparatment)
				{
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("delete from departament where cod_departament = ?");
						stmt.setString(1,cod_deparatment);
						stmt.executeUpdate();
						con.close();
						}				
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						System.out.println("Departamentul nu a putut fi sters");
					}	
				}
				
				public static void actualizeazaDepartament(String cod_departament,String denumire_departament)
				{
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("update departament set denumire_departament = ? where cod_departament = ?");
						stmt.setString(1, denumire_departament);
						stmt.setString(2,cod_departament);
						System.out.println(stmt);
						stmt.executeUpdate();
						con.close();
						}				
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						System.out.println("Nu s-a actualizat departamentul");
					}
				}
				//prelucrari specializari
				public static void insertSpecializare(String denumire_specializare,String program_studii)
				{
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("insert into specializare (denumire_specializare,program_studii) values (?,?)");
						stmt.setString(1,denumire_specializare);
						stmt.setString(2,program_studii);
						stmt.executeUpdate();
						con.close();
						}		
						
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						System.out.println("Specializarea nu a putut fi inserata");
					}	
				}
				
				public static void stergereSpecializare(String cod_specializare)
				{
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("delete from specializare where cod_specializare = ?");
						stmt.setString(1,cod_specializare);
						stmt.executeUpdate();
						con.close();
						}				
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						System.out.println("Specializarea nu a putut fi stearsa");
					}	
				}
				
				public static void actualizeazaSpecializare(String cod_specializare,String denumire_specializare)
				{
					Connection con=ConexiuneDB.conectare();		
					try{
						if(con!=null){
						PreparedStatement stmt= con.prepareStatement("update specializare set denumire_specializare = ? where cod_specializare = ?");
						stmt.setString(1, denumire_specializare);
						stmt.setString(2,cod_specializare);
						stmt.executeUpdate();
						con.close();
						}				
					}
					catch(SQLException e)
					{
						e.printStackTrace();
						System.out.println("Nu s-a actualizat specializarea");
					}
				}
				
			//prelucrari ani
			public static void insertAn(String denumire_an,String semestrul)
			{
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("insert into an_universitar (denumire_an_universitar,semestrul) values (?,?)");
					stmt.setString(1,denumire_an);
					stmt.setString(2,semestrul);
					stmt.executeUpdate();
					con.close();
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					System.out.println("Anul universitar nu a putut fi inserat");
				}
			}
	
			public static List<Student> returnStudenti(String nume)
			{
				List<Student> listaStudenti=new ArrayList<Student>();
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from student where nume like ? or prenume like ?");
					stmt.setString(1,"%"+nume+"%");
					stmt.setString(2,"%"+nume+"%");
					ResultSet rs=stmt.executeQuery();
					while(rs.next())  
					{	
						Student student=new Student();
						student.setNumar_matricol(Integer.parseInt(rs.getString("numar_matricol")));	
						student.setCnp(rs.getString("cnp"));
						student.setNume(rs.getString("nume"));
						student.setPrenume(rs.getString("prenume"));
						student.setForma_finantare(rs.getString("forma_finantare"));
						listaStudenti.add(student);
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}	
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return listaStudenti;
			}
			
			public static void insertStudent(String cnp,String nume,String prenume,String forma_finantare)
			{
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmForeignD=con.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
					stmForeignD.executeUpdate();
					PreparedStatement stmt= con.prepareStatement("insert into student (cnp,nume,prenume,forma_finantare) values (?,?,?,?)");
					stmt.setString(1,cnp);
					stmt.setString(2,nume);
					stmt.setString(3,prenume);
					stmt.setString(4,forma_finantare);
					stmt.executeUpdate();
					PreparedStatement stmForeignA=con.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
					stmForeignA.executeUpdate();
					con.close();
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					System.out.println("Studentul nu a putut fi inserat");
				}
			}
			
			public static void insertGrupa(String numar_grupa,String an_studiu,String numar_studenti,String specializare_cod_specializare)
			{
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("insert into grupa (numar_grupa,an_studiu,numar_studenti,specializare_cod_specializare) values (?,?,?,?)");
					stmt.setString(1,numar_grupa);
					stmt.setString(2,an_studiu);
					stmt.setString(3,numar_studenti);
					stmt.setString(4,specializare_cod_specializare);
					stmt.executeUpdate();
					con.close();
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					System.out.println("Grupa nu a putut fi inserata");
				}
			}
			
			public static void actualizareGrupa(String id_grupa,String numar_studenti)
			{
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("update grupa set numar_studenti = ? where id_grupa = ?");
					stmt.setString(1, numar_studenti);
					stmt.setString(2,id_grupa);
					stmt.executeUpdate();
					con.close();
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					System.out.println("Nu s-a actualizat specializarea");
				}
			}
			
			public static List<Disciplina> returnDiscipline(String tip_disciplina){
				List<Disciplina> listaDiscipline=new ArrayList<Disciplina>();
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from disciplina where tip_disciplina=?");
					stmt.setString(1, tip_disciplina);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						Disciplina disciplina= new Disciplina();
						disciplina.setId_disciplina(rs.getInt("id_disciplina"));
						disciplina.setCod_disciplina(rs.getInt("cod_disciplina"));
						disciplina.setDenumire_disciplina(rs.getString("denumire_disciplina"));
						disciplina.setTip_disciplina(rs.getString("tip_disciplina"));
						listaDiscipline.add(disciplina);					
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return listaDiscipline;		
			}
			
			public static Disciplina returnDisciplina(int id_disciplina){
				Disciplina disciplina=new Disciplina();
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select DISTINCT denumire_disciplina,cod_disciplina from disciplina where id_disciplina=?");
					stmt.setInt(1, id_disciplina);
					System.out.println(stmt);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						disciplina.setId_disciplina(id_disciplina);
						disciplina.setCod_disciplina(rs.getInt("cod_disciplina"));
						disciplina.setDenumire_disciplina(rs.getString("denumire_disciplina"));
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return disciplina;		
			}
			
			public static Disciplina returnDisciplina(String denumire_disciplina){
				Disciplina disciplina=new Disciplina();
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from disciplina where denumire_disciplina=? group by cod_disciplina");
					stmt.setString(1, denumire_disciplina);
					System.out.println(stmt);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						disciplina.setId_disciplina(rs.getInt("id_disciplina"));
						disciplina.setCod_disciplina(rs.getInt("cod_disciplina"));
						disciplina.setDenumire_disciplina(rs.getString("denumire_disciplina"));
						disciplina.setTip_disciplina(rs.getString("tip_disciplina"));
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return disciplina;		
			}
			
			public static List<Disciplina> returnDisciplineCautate(String denumire_disciplina){
				List<Disciplina> listaDiscipline=new ArrayList<Disciplina>();
		 		Connection con=ConexiuneDB.conectare();		
		 			try{
		 				if(con!=null){
		 				PreparedStatement stmt= con.prepareStatement("select * from disciplina where denumire_disciplina LIKE ? order by denumire_disciplina");
						stmt.setString(1, "%"+denumire_disciplina+"%");
		 				ResultSet rs=stmt.executeQuery(); 
		 				while(rs.next())  
		 				{	
		 					Disciplina disciplina= new Disciplina();
		 					disciplina.setId_disciplina(rs.getInt("id_disciplina"));
		 					disciplina.setCod_disciplina(rs.getInt("cod_disciplina"));
		 					disciplina.setDenumire_disciplina(rs.getString("denumire_disciplina"));
		 					disciplina.setTip_disciplina(rs.getString("tip_disciplina"));
		 					listaDiscipline.add(disciplina);					
						}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}	
			return listaDiscipline;		
		 	}
			
			public static SortedSet<String> returnTipuriDisc(int cod_disciplina){
				SortedSet<String> listaTipuriDisciplina=new TreeSet();
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from disciplina where cod_disciplina=? order by tip_disciplina asc");
					stmt.setInt(1, cod_disciplina);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						listaTipuriDisciplina.add(rs.getString("tip_disciplina"));
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return listaTipuriDisciplina;		
			}
			
			public static int returnCodDisc(String denumire_disciplina){
				int codDisc=0;
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from disciplina where denumire_disciplina=?");
					stmt.setString(1, denumire_disciplina);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						codDisc=Integer.parseInt(rs.getString("cod_disciplina"));
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return codDisc;		
			}
			
			
			
			public static List<Disciplina> returnDiscipline(String denumire_disciplina,String tip_disciplina){
				List<Disciplina> listaDiscipline=new ArrayList<Disciplina>();
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from disciplina where denumire_disciplina=? and tip_disciplina=?");
					stmt.setString(1, denumire_disciplina);
					stmt.setString(2, tip_disciplina);
					System.out.println(stmt);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						Disciplina disciplina= new Disciplina();
						disciplina.setId_disciplina(rs.getInt("id_disciplina"));
						disciplina.setCod_disciplina(rs.getInt("cod_disciplina"));
						disciplina.setDenumire_disciplina(rs.getString("denumire_disciplina"));
						disciplina.setTip_disciplina(rs.getString("tip_disciplina"));
						listaDiscipline.add(disciplina);					
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return listaDiscipline;		
			}
			
			public static int returnIdDisciplina(int cod_disciplina,String tip_disciplina){
				Connection con=ConexiuneDB.conectare();	
				int idDisciplina=0;
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from disciplina where cod_disciplina=? and tip_disciplina=?");
					stmt.setInt(1, cod_disciplina);
					stmt.setString(2, tip_disciplina);
					System.out.println(stmt);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						idDisciplina=rs.getInt("id_disciplina");						
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return idDisciplina;		
			}
			
			public static int returnMaxCodDisc(){
				Connection con=ConexiuneDB.conectare();	
				int codDisciplina=0;
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select max(cod_disciplina) from disciplina");
					System.out.println(stmt);
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						codDisciplina=rs.getInt("max(cod_disciplina)");						
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return codDisciplina;		
			}
			
			
			public static void insertDisciplina(String denumire,String tip_disciplina,int cod_diciplina)
			{
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("insert into disciplina (denumire_disciplina,tip_disciplina,cod_disciplina) values (?,?,?)");
					stmt.setString(1,denumire);
					stmt.setString(2,tip_disciplina);
					stmt.setInt(3,cod_diciplina);
					System.out.println(stmt);
					stmt.executeUpdate();
					con.close();
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					System.out.println("Profesorul nu a putut fi inserat");
				}
			}
			
			
			
			public static List<Profesor> returnProfesor(String nume){
				List<Profesor> listaProfesori=new ArrayList<Profesor>();
				Connection con=ConexiuneDB.conectare();
				
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from profesor where nume LIKE ? or prenume LIKE ? ");
					stmt.setString(1, "%"+nume+"%");
					stmt.setString(2, "%"+nume+"%");
					System.out.println(stmt.toString());
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						Profesor profesor=new Profesor();
						profesor.setMarca(Integer.parseInt(rs.getString("marca")));
						profesor.setNume(rs.getString("nume"));
						profesor.setPrenume(rs.getString("prenume"));
						profesor.setTitulatura(rs.getString("titulatura"));
						profesor.setCod_departament(Integer.parseInt(rs.getString("departament_cod_departament")));
						profesor.setNume_utilizator(rs.getString("cont_nume_utilizator"));
						listaProfesori.add(profesor);			
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}	
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return listaProfesori;		
			}
			
			public static List<Profesor> returnProfesor(int cod_departament){
				List<Profesor> listaProfesori=new ArrayList<Profesor>();
				Connection con=ConexiuneDB.conectare();
				
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from profesor where departament_cod_departament=? order by titulatura ASC,nume ASC");
					stmt.setInt(1, cod_departament);
					System.out.println(stmt.toString());
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						Profesor profesor=new Profesor();
						profesor.setMarca(Integer.parseInt(rs.getString("marca")));
						profesor.setNume(rs.getString("nume"));
						profesor.setPrenume(rs.getString("prenume"));
						profesor.setTitulatura(rs.getString("titulatura"));
						profesor.setCod_departament(Integer.parseInt(rs.getString("departament_cod_departament")));
						profesor.setNume_utilizator(rs.getString("cont_nume_utilizator"));
						listaProfesori.add(profesor);			
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}	
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return listaProfesori;		
			}
			
			public static Profesor returnProfesorInfo(int marca){

				Connection con=ConexiuneDB.conectare();
				Profesor profesor=new Profesor();
				
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from profesor where marca=?");
					stmt.setInt(1, marca);
					System.out.println(stmt.toString());
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						profesor.setMarca(Integer.parseInt(rs.getString("marca")));
						profesor.setNume(rs.getString("nume"));
						profesor.setPrenume(rs.getString("prenume"));
						profesor.setTitulatura(rs.getString("titulatura"));
						profesor.setCod_departament(Integer.parseInt(rs.getString("departament_cod_departament")));
						profesor.setNume_utilizator(rs.getString("cont_nume_utilizator"));		
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}	
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return profesor;		
			}
			
			
			public static List<Profesor> returnProfesor(String titulatura,String departament){
				List<Profesor> listaProfesori=new ArrayList<Profesor>();
				Connection con=ConexiuneDB.conectare();
				
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from profesor where titulatura LIKE ? and departament_cod_departament LIKE ? ");
					stmt.setString(1, "%"+titulatura+"%");
					stmt.setString(2, "%"+departament+"%");
					System.out.println(stmt.toString());
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						Profesor profesor=new Profesor();
						profesor.setMarca(Integer.parseInt(rs.getString("marca")));
						profesor.setNume(rs.getString("nume"));
						profesor.setPrenume(rs.getString("prenume"));
						profesor.setTitulatura(rs.getString("titulatura"));
						profesor.setCod_departament(Integer.parseInt(rs.getString("departament_cod_departament")));
						profesor.setNume_utilizator(rs.getString("cont_nume_utilizator"));
						listaProfesori.add(profesor);			
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}	
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return listaProfesori;		
			}
			
			public static List<Profesor> returnProfesorDublura(String nume,String prenume,String titulatura,String departament){
				List<Profesor> listaProfesori=new ArrayList<Profesor>();
				Connection con=ConexiuneDB.conectare();
				
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("select * from profesor where nume=? and prenume=? and titulatura=? and departament_cod_departament=? ");
					stmt.setString(1, nume);
					stmt.setString(2, prenume);
					stmt.setString(3, titulatura);
					stmt.setString(4, departament);
					System.out.println(stmt.toString());
					ResultSet rs=stmt.executeQuery(); 
					while(rs.next())  
					{	
						Profesor profesor=new Profesor();
						profesor.setMarca(Integer.parseInt(rs.getString("marca")));
						profesor.setNume(rs.getString("nume"));
						profesor.setPrenume(rs.getString("prenume"));
						profesor.setTitulatura(rs.getString("titulatura"));
						profesor.setCod_departament(Integer.parseInt(rs.getString("departament_cod_departament")));
						profesor.setNume_utilizator(rs.getString("cont_nume_utilizator"));
						listaProfesori.add(profesor);			
					}
					ConexiuneDB.closeResources(con, rs, stmt);
					}	
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}	
				return listaProfesori;		
			}
			
			public static void insertProfesor(String nume,String prenume,String titulatura,String cod_departament)
			{
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("insert into profesor (nume,prenume,titulatura,departament_cod_departament) values (?,?,?,?)");
					stmt.setString(1,nume);
					stmt.setString(2,prenume);
					stmt.setString(3,titulatura);
					stmt.setString(4,cod_departament);
					stmt.executeUpdate();
					con.close();
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					System.out.println("Profesorul nu a putut fi inserat");
				}
			}
			
			public static void insertCont(String utilizator,String parola,String informatii,String matricol,String marca)
			{
				Connection con=ConexiuneDB.conectare();		
				try{
					if(con!=null){
					PreparedStatement stmt= con.prepareStatement("insert into cont(nume_utilizator,parola,alte_infromatii,student_numar_matricol,profesor_marca) values (?,?,?,?,?)");
					stmt.setString(1,utilizator);
					stmt.setString(2,parola);
					stmt.setString(3,informatii);
					stmt.setString(4,matricol);
					stmt.setString(5,marca);
					System.out.println(stmt);
					stmt.executeUpdate();
					con.close();
					}				
				}
				catch(SQLException e)
				{
					e.printStackTrace();
					System.out.println("Contul nu a putut fi creat");
				}
			}
}