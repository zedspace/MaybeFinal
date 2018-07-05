package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claseResurse.Grupa;
import database.PrelucrariDB;

/**
 * Servlet implementation class UpdateServletGrupa
 */
@WebServlet("/UpdateServletGrupa")
public class UpdateServletGrupa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServletGrupa() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Grupa> listaGrupe=new ArrayList<Grupa>();
		//cautare filtrata a grupelor
		if(request.getParameter("specializare")!=null && request.getParameter("specializare")!="" && request.getParameter("an_studiu_filtru")!=null && request.getParameter("an_studiu_filtru")!="")
		{
			System.out.println(Integer.parseInt(request.getParameter("specializare"))+" "+Integer.parseInt(request.getParameter("an_studiu_filtru")));
			listaGrupe=PrelucrariDB.returnGrupe(Integer.parseInt(request.getParameter("specializare")), Integer.parseInt(request.getParameter("an_studiu_filtru")));
			if(!listaGrupe.isEmpty())
			{
				System.out.println("Grupe dupa filtrul "+Integer.parseInt(request.getParameter("specializare"))+" "+request.getParameter("an_studiu_filtru"));
				request.setAttribute("listaRezultat", listaGrupe);
			}
			else
				request.setAttribute("invalid", "Nu exista grupe de studenti in cadrul specializarii selectate!");
			
			
			//response.sendRedirect("grupe.jsp");
		}
		if(request.getParameter("numar_grupa_add")!=null && request.getParameter("numar_grupa_add")!="" && request.getParameter("an_studiu_add")!=null && request.getParameter("an_studiu_add")!="" && request.getParameter("numar_studenti_add")!=null && request.getParameter("numar_studenti_add")!="")
		{
			PrelucrariDB.insertGrupa(request.getParameter("numar_grupa_add"), request.getParameter("an_studiu_add"), request.getParameter("numar_studenti_add"), request.getParameter("specializare_add"));
			request.setAttribute("succes", "Grupa "+request.getParameter("numar_grupa_add")+ " a fost inserata cu succes!");
			System.out.println("S-a inserat grupa "+request.getParameter("numar_grupa_add")+" "+request.getParameter("an_studiu_add")+" "+request.getParameter("numar_studenti_add")+" "+request.getParameter("specializare_add"));
		}
		else
		{
			request.setAttribute("invalid", "Toate campurile sunt obligatorii!");

		}
		request.getRequestDispatcher("grupe.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}