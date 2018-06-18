package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claseResurse.Disciplina;
import claseResurse.Preda;
import claseResurse.Student;
import database.PrelucrariDB;

/**
 * Servlet implementation class ProfesorHomeServlet
 */
@WebServlet("/ProfesorHomeServlet")
public class ProfesorHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfesorHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Student> listaStudenti=new ArrayList<Student>();
		List<Disciplina> listaDisciplinePredate=new ArrayList<Disciplina>();
		List<Preda> listaPreda=new ArrayList<Preda>();
		List<Preda> listaPredaVizulizare=new ArrayList<Preda>();
		if(request.getParameter("specializareVizualizare")!=null && request.getParameter("an_studiu")!=null)
		{
			request.getSession().putValue("specializareVizualizare", request.getParameter("specializareVizualizare"));
			request.getSession().putValue("an_studiu_vizualizare", request.getParameter("an_studiu"));
			listaPreda=PrelucrariDB.returnPredaLaSpecializare(Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getParameter("specializareVizualizare")) , Integer.parseInt(request.getParameter("an_studiu")));
			if(!listaPreda.isEmpty())
			{
				request.getSession().putValue("listaPredaVizualizare", listaPreda);
				for(Preda preda:listaPreda)
				{
					listaDisciplinePredate.add(PrelucrariDB.returnDisciplina(preda.getDisciplina_id_disciplina()));
				}
				if(listaDisciplinePredate!=null)
				{
					request.setAttribute("listaDisciplinePredate", listaDisciplinePredate);
				}
			}
			else
			{
				request.setAttribute("invalid", "Nu predati discipline la aceasta specializare!");
			}
		}
		if(request.getParameter("specializareVizualizare")!=null && request.getParameter("an_studiu")==null)
		{
			request.setAttribute("invalid", "Pentru a vizualiza situatia studentilor, este necesar sa selectati anul de studiu!");
		}
		
		if(request.getParameter("disciplina")!=null)
		{
			listaStudenti=PrelucrariDB.returnStudenti(Integer.parseInt(request.getSession().getValue("specializareVizualizare").toString()), Integer.parseInt(request.getSession().getValue("an_studiu_vizualizare").toString()), Integer.parseInt(request.getParameter("disciplina")), Integer.parseInt(request.getSession().getValue("idUser").toString()));
			request.getSession().putValue("disciplinaVizualizare", request.getParameter("disciplina"));
			if(!listaStudenti.isEmpty())
			{
				request.setAttribute("listaSituatieStudenti", listaStudenti);
			}
			else
			{
				request.setAttribute("inexistent", "A aparut o eroare. Nu exista studenti la aceasta specializare, in anul de studiu selectat!");
			}
		}
		request.getRequestDispatcher("profesor.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
