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
		if(request.getParameter("adaugaSubmit")!=null)
			{
				if(request.getParameter("denumire_spec") != null && request.getParameter("denumire_spec")!="" && request.getParameter("f_invatamant") != null)
				{
					specExistenta=PrelucrariDB.returnSpecializare(request.getParameter("denumire_spec"), request.getParameter("f_invatamant"));
					if(specExistenta==null||specExistenta.getCod_specializare()==0)
						{
							PrelucrariDB.insertSpecializare(request.getParameter("denumire_spec"),request.getParameter("f_invatamant"));
							request.setAttribute("succes", "S-a inserat specializarea "+request.getParameter("denumire_spec")+", "+request.getParameter("f_invatamant"));
							System.out.println("S-a inserat specializarea "+request.getParameter("denumire_spec"));		
						}
					else
						{
							request.setAttribute("exista", "Specializarea "+request.getParameter("denumire_spec") +" la forma de invatamant "+request.getParameter("f_invatamant")+" deja exista!");
						}
				}
			}
		else
		{
			if(request.getParameter("cod_spec")!=null)
			{	
				PrelucrariDB.actualizeazaSpecializare(request.getParameter("cod_spec"), request.getParameter("denumire_specializare"));
				System.out.println("S-a actualizat specializarea "+request.getParameter("cod_spec")+" "+request.getParameter("denumire_specializare"));
				
			}
		}
			
			//prelucrari nefolosite
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