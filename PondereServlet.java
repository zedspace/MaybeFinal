package servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claseResurse.Grupa;
import claseResurse.Preda;
import database.PrelucrariDB;

/**
 * Servlet implementation class PondereServlet
 */
@WebServlet("/PondereServlet")
public class PondereServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PondereServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Preda> listaPredare=PrelucrariDB.returnPreda(Integer.parseInt(request.getSession().getValue("idUser").toString()));
		Set<String> listaDiscipline=new HashSet<>();
		SortedSet<String> tipuriDisc=new TreeSet();
		String input=null;
		boolean ok=true;
		
		System.out.println(request.getParameter("cod"));
		if(request.getParameter("cod")!=null)
		{	
			request.getSession().putValue("codSpec", request.getParameter("cod"));
			request.getRequestDispatcher("ponderiStabilite.jsp").forward(request,response);
		}
		
		if(request.getParameter("selectareAn")!=null)
		{
			System.out.println("An de studiu: "+ request.getParameter("an_studiu"));
			if(request.getParameter("an_studiu")==null)
			{
				request.setAttribute("anNecompletat", "Selectati obligatoriu anul de studiu!");
				request.getRequestDispatcher("ponderiStabilite.jsp").forward(request,response);
			}
			else
				if(request.getParameter("an_studiu")!=null)
				{
					System.out.println(request.getParameter("an_studiu"));
					List<Grupa> listaGrupe=PrelucrariDB.returnGrupe(Integer.parseInt(request.getSession().getValue("codSpec").toString()), Integer.parseInt(request.getParameter("an_studiu")));
					request.getSession().putValue("an_studiu",request.getParameter("an_studiu"));
					for(Preda preda:listaPredare)
					{
						for(Grupa grupa:listaGrupe)
						{
							if(grupa.getId_grupa()==preda.getGrupa_id_grupa())
							{	
								listaDiscipline.add(PrelucrariDB.returnDisciplina(preda.getDisciplina_id_disciplina()).getDenumire_disciplina());
							}
						}
					}
					request.setAttribute("listaDiscipline", listaDiscipline);
					System.out.println(listaDiscipline.toString());
					if(listaDiscipline.isEmpty())
					{
						request.setAttribute("inexistent", "Nu predati discipline la aceasta specializare in anul de studiu "+request.getParameter("an_studiu"));
					}
					request.getRequestDispatcher("ponderiStabilite.jsp").forward(request,response);
				}
		}
		if((request.getParameter("selectareAn")==null || request.getParameter("selectareAn")=="") && request.getParameter("pondere")==null)
		{
			request.setAttribute("anNecompletat", "Selectati obligatoriu anul de studiu!");
		}
		
		if(request.getParameter("pondere")!=null)
		{
			int sumaInput=0;
			System.out.println("Se doreste introducerea ponderilor pentru disciplina: "+request.getParameter("pondere"));
			tipuriDisc=PrelucrariDB.returnTipuriDisc(Integer.parseInt(request.getParameter("pondere")));
			System.out.println(tipuriDisc.toString());
			for(String tip:tipuriDisc)
			{
				input=tip+request.getParameter("pondere");
				System.out.println("Pentru "+tip+request.getParameter("pondere")+" s-a introdus ponderea "+request.getParameter(input));
				sumaInput=sumaInput+Integer.parseInt(request.getParameter(input));
				System.out.println(sumaInput);
				if(!(sumaInput==100))
				{
					request.setAttribute("incomplet","Suma ponderilor pentru tipurile disciplinelor trebuie sa fie 100!!" );
				}
			}
			if(sumaInput==100)
			{
				List<Grupa> listaGrupe=PrelucrariDB.returnGrupe(Integer.parseInt(request.getSession().getValue("codSpec").toString()), Integer.parseInt(request.getSession().getValue("an_studiu").toString()));
				request.getSession().putValue("listaGrupePondere", listaGrupe);
				for(Grupa grupa:listaGrupe)
				{
					{
							for(String tip:tipuriDisc)
							{
								int id_disc=PrelucrariDB.returnIdDisciplina(Integer.parseInt(request.getParameter("pondere")), tip);
								input=tip+request.getParameter("pondere");
								System.out.println("Se stabileste ponderea: profesorul: "+ request.getSession().getValue("idUser")+" disciplina "+id_disc+" grupa: "+grupa.getId_grupa()+" ponderea: "+request.getParameter(input)+" anul universitar "+Integer.parseInt(request.getSession().getValue("idUser").toString()));
								PrelucrariDB.insertPondere(Integer.parseInt(request.getSession().getValue("idUser").toString()), id_disc, grupa.getId_grupa(), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getParameter(input)));
								request.setAttribute("complet","A fost introdusa ponderea pentru tipurile disciplinei disciplinei "+ PrelucrariDB.returnDisciplina(id_disc).getDenumire_disciplina());
							}
					}
				}
			}
			request.getRequestDispatcher("ponderiStabilite.jsp").forward(request,response);
		}
		
		if(request.getParameter("deconectare")!=null)
		{
			request.getRequestDispatcher("PaginaPrincipala.jsp").forward(request,response);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		}
			
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}