package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claseResurse.Profesor;
import database.PrelucrariDB;

/**
 * Servlet implementation class ProfesorServlet
 */
@WebServlet("/ProfesorServlet")
public class ProfesorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfesorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Profesor> profesori= new ArrayList<Profesor>();
		List<Profesor> profesorDuplicat=new ArrayList<Profesor>();
		List<Profesor> profesorFiltru=new ArrayList<Profesor>();
		if(request.getParameter("cauta")!=null)
		{
			System.out.println("Se cauta profesorul "+request.getParameter("nume"));
			profesori=PrelucrariDB.returnProfesor(request.getParameter("nume"));
			
			if(!profesori.isEmpty())
				{
				request.setAttribute("listaRezultat", profesori);
				System.out.println("Lista profesorilor care corespund numelui "+profesori.get(0).getNume());
				}
			else 
				request.setAttribute("notFound", "Profesorul nu exista");
			request.getRequestDispatcher("profesori.jsp").forward(request,response);
		}
		if(request.getParameter("adaugaProf")!=null)
		{
			
			if(request.getParameter("nume_prof")!=null&&request.getParameter("prenume_prof")!=null&&request.getParameter("nume_prof")!=""&&request.getParameter("prenume_prof")!=""){
				profesorDuplicat=PrelucrariDB.returnProfesorDublura(request.getParameter("nume_prof"),request.getParameter("prenume_prof"),request.getParameter("titulatura_prof"),request.getParameter("dep_add"));
				if(!profesorDuplicat.isEmpty())
				{
					request.setAttribute("incomplet", "Profesorul pe care ati dorit sa il inserati exista deja in baza de date!");
					System.out.println(PrelucrariDB.returnProfesorDublura(request.getParameter("nume_prof"),request.getParameter("prenume_prof"),request.getParameter("titulatura_prof"),request.getParameter("dep_add")));
				}
				else
				{
					PrelucrariDB.insertProfesor(request.getParameter("nume_prof"), request.getParameter("prenume_prof"), request.getParameter("titulatura_prof"), request.getParameter("dep_add"));
					request.setAttribute("succes", "S-a inserat profesorul "+request.getParameter("nume_prof")+" "+request.getParameter("prenume_prof"));
					System.out.println("S-a inserat profesorul "+request.getParameter("nume_prof")+" "+request.getParameter("prenume_prof"));
				}
			}
			else
				request.setAttribute("incomplet", "Toate campurile sunt obligatorii");
			request.getRequestDispatcher("profesori.jsp").forward(request,response);
		}
		
		if(request.getParameter("cautaFiltru")!=null)
		{
			if((request.getParameter("grad_didactic")!=null&&request.getParameter("grad_didactic")!="")||(request.getParameter("dep_cautare")!=null&&request.getParameter("dep_cautare")!="")){
				System.out.println("Cautarea se face dupa filtrul: "+request.getParameter("grad_didactic")+" "+request.getParameter("dep_cautare"));
				profesorFiltru=PrelucrariDB.returnProfesor(request.getParameter("grad_didactic"), request.getParameter("dep_cautare"));
				if(!profesorFiltru.isEmpty())
					{	
						request.setAttribute("listaRezultatFiltrat", profesorFiltru);
						System.out.println("Lista Filtru: "+profesorFiltru);
					}
				else
					request.setAttribute("incomplet", "Nu exista profesori care sa corespunda criterilor de selectie");
			}
			else
				request.setAttribute("incomplet", "Imposibil de aplicat filtrul!");
			request.getRequestDispatcher("profesori.jsp").forward(request,response);
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
