package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claseResurse.Departament;
import claseResurse.Profesor;
import database.PrelucrariDB;

/**
 * Servlet implementation class UpdateServletDep
 */
@WebServlet("/UpdateServletDep")
public class UpdateServletDep extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServletDep() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Departament depExistent=null;
		List<Profesor> listaProfesori=new ArrayList<Profesor>();
		String denumire_departament=null;
		String cod_departament;
		//prelucrari admin departamente
	
		
		//System.out.println("Butonul de adugare are valoarea: "+request.getParameter("adaugaSubmit"));
		//if(request.getParameter("adaugaSubmit")!=null){
			if(request.getParameter("denumire_dep") != null && request.getParameter("denumire_dep")!="")
			{
				depExistent=PrelucrariDB.returnDepartamente(request.getParameter("denumire_dep"));
				System.out.println(depExistent.getCod_departament());
				if(depExistent==null||depExistent.getCod_departament()==0)
				{
					PrelucrariDB.insertDepartament(request.getParameter("denumire_dep"));
					System.out.println("S-a inserat departamentul "+request.getParameter("denumire_dep"));
					request.setAttribute("succes","Departamentul "+ request.getParameter("denumire_dep")+" a fost inserat cu succes!" );
				}
				else
					{
						request.setAttribute("exista","Departamenul exista deja" );
					}
			}
		//}
		
		//Modificarea denumirii departamentului
		if(request.getParameter("cod_dept")!=null)
		{	
			if(PrelucrariDB.returnDepartamente(request.getParameter("cod_dept")).getDenumire_departament()!=request.getParameter("den_dep") && request.getParameter("den_dep")!="" && request.getParameter("den_dep")!=null){
				PrelucrariDB.actualizeazaDepartament(request.getParameter("cod_dept"), request.getParameter("den_dep"));
				System.out.println("S-a actualizat departamentul "+request.getParameter("cod_dept"));
				request.setAttribute("succes", "Denumirea departamentului a fost modificata cu succes!");
			}
		}
			
		if(request.getParameter("vizualizare")!=null)
		{	
			if(request.getParameter("vizualizare")!=null&&request.getParameter("vizualizare")!="")
			{	 
				cod_departament=request.getParameter("vizualizare");
				System.out.println("S-au afisat profesorii din departamentul cu codul: "+cod_departament);
				listaProfesori=PrelucrariDB.returnProfesor(Integer.parseInt(cod_departament));
				System.out.println(listaProfesori);
				denumire_departament=PrelucrariDB.returnDepartamente(Integer.parseInt(cod_departament)).getDenumire_departament();
				request.setAttribute("departament", denumire_departament);
				request.setAttribute("listaProfesori", listaProfesori);
			}
			if(listaProfesori.isEmpty())
				request.setAttribute("inexistent", "Nu exista profesori in departamentul "+ denumire_departament);
			System.out.println("S-au afisat profesorii din departamentul: "+denumire_departament);
		}
		
		//Prelucrari nefolosite
		if(request.getParameter("sterge")!=null)
		{	
			PrelucrariDB.stergereDepartament(request.getParameter("sterge"));
			System.out.println("S-a sters departamentul "+request.getParameter("sterge"));
			request.getRequestDispatcher("departamente.jsp").forward(request,response);
		}
		request.getRequestDispatcher("departamente.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}