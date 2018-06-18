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
import claseResurse.Grupa;
import claseResurse.Preda;
import database.PrelucrariDB;

/**
 * Servlet implementation class DisciplinaServlet
 */
@WebServlet("/DisciplinaServlet")
public class DisciplinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisciplinaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Preda> listaPreda=PrelucrariDB.returnPreda();
		if(request.getParameter("vizualizare")!=null)
			request.setAttribute("listaPredare", listaPreda);
		List<Disciplina> listaDiscipline=new ArrayList<Disciplina>();
		List<Disciplina> listaCompletaDisc=new ArrayList<Disciplina>();
		List<Disciplina> disciplinaPreda=new ArrayList<Disciplina>();
		if(request.getParameter("vizualizare")!=null)
			{
				listaCompletaDisc=PrelucrariDB.returnDiscipline();
				request.setAttribute("listaCompletaDisc", listaCompletaDisc);
				
			}
		if(request.getParameter("adaugaFinal")!=null){
		
			System.out.println("Se incearca adaugarea disciplinei cu: "
					+" Denumire: "+request.getParameter("den_disc")
					+" An de studiu: "+request.getParameter("an_studiu")
					+" Semestrul "+request.getParameter("semestrul")
					+" Tipul disciplinei "+request.getParameter("tip_disc")
					+" Numarul de credite pentru curs: "+request.getParameter("credite"));
			List<Grupa> listaGrupa=new ArrayList<Grupa>();
			List<Preda> listaPredare=new ArrayList<Preda>();
			int id_grupa=0;
			int id_disciplina=0;
			int id_preda=0;
			if(request.getParameter("den_disc")!=null&&request.getParameter("den_disc")!=""&&request.getParameter("an_studiu")!=null&&request.getParameter("semestrul")!=null&&request.getParameter("tip_disc")!=null)
				{
					System.out.println("Adaugarea disciplinei se face cu: "
											+" Denumire: "+request.getParameter("den_disc")
											+" An de studiu: "+request.getParameter("an_studiu")
											+" Semestrul "+request.getParameter("semestrul")
											+" Tipul disciplinei "+request.getParameter("tip_disc")
											+" Numarul de credite pentru curs: "+request.getParameter("credite"));
					listaDiscipline=PrelucrariDB.returnDiscipline(request.getParameter("den_disc"), request.getParameter("tip_disc"));
					if(!listaDiscipline.isEmpty())
						{
							request.setAttribute("notFound", "Disciplina nu poate fi adaugata! Exista deja!");
							System.out.println("Disciplina inserata exista deja");
						}
					else
					{
						System.out.println("--Se insereaza disciplina--");
						PrelucrariDB.insertDisciplina(request.getParameter("den_disc"), request.getParameter("tip_disc"), "0");
						request.setAttribute("succes", "Disciplina "+request.getParameter("den_disc")+ " a fost inserata cu succes!");
						disciplinaPreda=PrelucrariDB.returnDiscipline(request.getParameter("den_disc"), request.getParameter("tip_disc"));
						for(Disciplina disc:disciplinaPreda)
							id_disciplina=disc.getId_disciplina();
						System.out.println("Disciplina inserata are codul: "+id_disciplina);
						listaGrupa=PrelucrariDB.returnGrupe(Integer.parseInt(request.getParameter("specializare")), Integer.parseInt(request.getParameter("an_studiu")));
						for(Grupa grupa:listaGrupa)
						{
							id_grupa=grupa.getId_grupa();
							System.out.println("Grupa pentru care se aloca profesorul are codul: "+id_grupa);
							//insereaza pentru anul in curs
							System.out.println("Semestrul:"+request.getParameter("semestrul"));
							if(Integer.parseInt(request.getParameter("semestrul"))==1)
								{
									System.out.println("Se insereaza pentru semestrul 1:");
									PrelucrariDB.insertPreda(Integer.parseInt(request.getParameter("profesor")), id_disciplina,1,id_grupa);
								}
							else
								if(Integer.parseInt(request.getParameter("semestrul"))==2)
									{
										System.out.println("Se insereaza pentru semestrul 2:");
										PrelucrariDB.insertPreda(Integer.parseInt(request.getParameter("profesor")),id_disciplina,2, id_grupa);
									}
						}
						System.out.println("Se actualizeaza tabela cu numarul de credite: "+request.getParameter("credite"));
						
						if(request.getParameter("tip_disc").equals("curs")&&request.getParameter("credite")!=null&&request.getParameter("credite")!="")
						{
							System.out.println("UPDATE PREDA!!!");
							for(Grupa grupa:listaGrupa)
							{
								id_grupa=grupa.getId_grupa();
								if(Integer.parseInt(request.getParameter("semestrul"))==1)
									{
										listaPredare=PrelucrariDB.returnPreda(Integer.parseInt(request.getParameter("profesor")), id_disciplina, id_grupa, 1);
										for(Preda preda:listaPredare)
										{
											id_preda=preda.getId_preda();
											PrelucrariDB.updatePreda(Integer.parseInt(request.getParameter("credite")), id_preda);
										}
										
									}
								else
									if(Integer.parseInt(request.getParameter("semestrul"))==2)
									{
										listaPredare=PrelucrariDB.returnPreda(Integer.parseInt(request.getParameter("profesor")), id_disciplina, id_grupa, 2);
										for(Preda preda:listaPredare)
										{
											id_preda=preda.getId_preda();
											PrelucrariDB.updatePreda(Integer.parseInt(request.getParameter("credite")), id_preda);
										}
									}
							}
						}
					}
					
				}
			else
				request.setAttribute("notFound", "Toate campurile sunt obligatorii pentru adugarea disciplinei!");
		}
		
		if(request.getParameter("cauta")!=null){
			//listaDiscipline=PrelucrariDB.returnDiscipline(request.getParameter("tipDisciplina"));
			if(request.getParameter("disciplina_cautata")!=null)
			{
				listaDiscipline=PrelucrariDB.returnDisciplineCautate(request.getParameter("disciplina_cautata"));
				request.setAttribute("listaDiscipline", listaDiscipline);
			}
			if(listaDiscipline.isEmpty())
				request.setAttribute("notFound", "Nu exista discipline care sa corespunda criteriilor de cautare!");
		}
//		if(request.getParameter("cauta")==null)
//		{
//			listaDiscipline=PrelucrariDB.returnDiscipline();
//			request.setAttribute("listaDiscipline", listaDiscipline);
//		}
		request.getRequestDispatcher("discipline.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}