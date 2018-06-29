package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claseResurse.Specializare;
import database.PrelucrariDB;

/**
 * Servlet implementation class UpdateServletSpec
 */
@WebServlet("/UpdateServletSpec")
public class UpdateServletSpec extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServletSpec() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Specializare specExistenta=null;
		
		//prelucrari admin specializari
		//System.out.println("Buton adaugare submit: "+request.getParameter("adaugaSubmit"));
		//if(request.getParameter("adaugaSubmit")!=null)
		if(request.getParameter("denumire_spec") != null && request.getParameter("denumire_spec")!="" && request.getParameter("p_studii") != null)
		{
			specExistenta=PrelucrariDB.returnSpecializare(request.getParameter("denumire_spec"), request.getParameter("p_studii"));
			if(specExistenta==null||specExistenta.getCod_specializare()==0)
				{
					PrelucrariDB.insertSpecializare(request.getParameter("denumire_spec"),request.getParameter("p_studii"));
					request.setAttribute("succes", "S-a inserat specializarea "+request.getParameter("denumire_spec")+", "+request.getParameter("p_studii"));
					System.out.println("S-a inserat specializarea "+request.getParameter("denumire_spec"));		
				}
			else
				{
					request.setAttribute("exista", "Specializarea "+request.getParameter("denumire_spec") +" la programul de studii "+request.getParameter("p_studii")+" deja exista!");
				}
		}
			
		//modificare admin denumire specializare
		System.out.println(request.getParameter("Codul specializarii de modificat"+"cod_spec"));
		if(request.getParameter("cod_spec")!=null && request.getParameter("cod_spec")!="")
		{	
			if((PrelucrariDB.returnSpecializare(Integer.parseInt(request.getParameter("cod_spec"))).getDenumire_specializare()!=request.getParameter("den_spec")) && request.getParameter("den_spec")!="" && request.getParameter("den_spec")!=null)
			{	
				PrelucrariDB.actualizeazaSpecializare(request.getParameter("cod_spec"), request.getParameter("den_spec"));
				System.out.println("S-a actualizat specializarea "+request.getParameter("cod_spec")+" "+request.getParameter("den_spec"));
				request.setAttribute("succes", "Denumirea specializarii a fost modificata cu succes!");
			}
		}
			
		//prelucrari nefolosite -- Stergere
		if(request.getParameter("sterge")!=null)
		{	
			PrelucrariDB.stergereSpecializare(request.getParameter("sterge"));
			System.out.println("S-a sters specializarea "+request.getParameter("sterge"));
		}
		
		request.getRequestDispatcher("specializari.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}