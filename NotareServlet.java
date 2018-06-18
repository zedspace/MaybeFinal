package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.DatabaseMetaDataUsingInfoSchema;

import claseResurse.Disciplina;
import claseResurse.Evaluare;
import claseResurse.Grupa;
import claseResurse.Preda;
import claseResurse.Student;
import database.PrelucrariDB;

/**
 * Servlet implementation class NotareServlet
 */
@WebServlet("/NotareServlet")
public class NotareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NotareServlet() {
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
		List<Student> listaStudenti=new ArrayList<>();
		if(request.getParameter("cod")!=null)
		{	
			request.getSession().putValue("codSpec", request.getParameter("cod"));
		}
		
		if(request.getParameter("selectareAn")!=null)
		{
			request.getSession().putValue("anSelectatBtn", request.getParameter("selectareAn"));
			if(request.getParameter("an_studiu")!=null)
			{
				System.out.println(request.getParameter("an_studiu"));
				List<Grupa> listaGrupePredare=PrelucrariDB.returnGrupe(Integer.parseInt(request.getSession().getValue("codSpec").toString()), Integer.parseInt(request.getParameter("an_studiu")));
				request.getSession().putValue("an_studiu",request.getParameter("an_studiu"));
				if(!listaGrupePredare.isEmpty())
				{
					request.setAttribute("listaGrupePredare", listaGrupePredare);
					System.out.println(listaGrupePredare.toString());
				}
				else
				{
					request.setAttribute("inexistent", "Nu predati discipline la aceasta specializare in anul "+request.getSession().getValue("an_studiu"));
				}
				
				Set<String> listaDisciplinePredare=new HashSet<>();
				for(Preda preda:listaPredare)
				{
					listaDisciplinePredare.add(PrelucrariDB.returnDisciplina(preda.getDisciplina_id_disciplina()).getDenumire_disciplina());
				}
				if(!listaDisciplinePredare.isEmpty())
				{
					request.setAttribute("listaDisciplinePredare", listaDisciplinePredare);
					System.out.println(listaDisciplinePredare.toString());
				}
				else
				{
					request.setAttribute("inexistent", "Nu predati discipline la aceasta specializare in anul "+request.getSession().getValue("an_studiu"));
				}
			}
		}
		
		if(request.getParameter("catreStudenti")!=null && request.getParameter("grupa")!=null)
		{
			System.out.println("Disciplina selectata a fost: "+request.getParameter("catreStudenti"));
			request.getSession().putValue("idDisciplina", request.getParameter("catreStudenti"));
			request.setAttribute("grupa", request.getParameter("grupa"));
			request.getSession().putValue("grupa", request.getParameter("grupa"));
			listaStudenti=PrelucrariDB.returnStudentiGrupa(Integer.parseInt(request.getParameter("grupa")));
			if(listaStudenti!=null)
				{
					request.setAttribute("listaStudenti", listaStudenti);
					request.getSession().putValue("listaStudenti", listaStudenti);
				}
			System.out.println(listaStudenti);	
		}
		else
			if(request.getSession().getValue("anSelectatBtn")!=null && request.getSession().getValue("grupa")==null)
			{
				request.getSession().removeAttribute("anSelectatBtn");
				request.setAttribute("lipsaGrupa", "Pentru a adauga o nota, trebuie sa selectati grupa");
				System.out.println("Pentru a adauga o nota, trebuie sa selectati grupa");
			}
		
		if(request.getParameter("adaugaNota")!=null)
		{
			listaStudenti=(List<Student>) request.getSession().getValue("listaStudenti");
			int notaExista=0;
			for(Student stud:listaStudenti)
			{
				System.out.println(stud.getNume()+" "+stud.getPrenume());
				if(request.getParameter(String.valueOf(stud.getNumar_matricol()))!=null && request.getParameter(String.valueOf(stud.getNumar_matricol()))!="0")
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date now = new Date();
				    String strDate = sdf.format(now);
					System.out.println("data :"+strDate);
					notaExista=PrelucrariDB.returnNotare(stud.getNumar_matricol(), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString())).getNota();
					if(notaExista==0)
					{
						System.out.println("Se insereaza nota "+request.getParameter(String.valueOf(stud.getNumar_matricol()))+" pentru studentul "+stud.getNumar_matricol());
						if(Integer.parseInt(request.getParameter(String.valueOf(stud.getNumar_matricol())))>0 && Integer.parseInt(request.getParameter(String.valueOf(stud.getNumar_matricol())))<=10)
						{
							try {
								PrelucrariDB.insertNota(getDateFromString(strDate), stud.getNumar_matricol() , Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString()), Integer.parseInt(request.getParameter(String.valueOf(stud.getNumar_matricol()))));
								PrelucrariDB.insertEvaluare(Integer.parseInt(request.getSession().getValue("idUser").toString()), stud.getNumar_matricol(), Integer.parseInt(request.getSession().getValue("idDisciplina").toString()), Integer.parseInt(request.getSession().getValue("grupa").toString()), Integer.parseInt(request.getSession().getValue("anUniversitarActual").toString()), 1, getDateFromString(strDate));
							} catch (NumberFormatException | ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else
						{
							request.setAttribute("invalid", "Notele introduse trebuie sa se incadreze in intervalul [1,10] !");
						}
					}
					else
					{
						System.out.println("Studentul "+stud.getNumar_matricol()+" are deja nota "+notaExista+" la disciplina "+Integer.parseInt(request.getSession().getValue("idDisciplina").toString()));
					}
				}
				else
				{
					request.setAttribute("invalid", "Introducerea tuturor notelor studentilor este obligatorie! In cazul studentilor absenti se va introduce 1!");
				}
			}
		}
		
		//Modificare nota gresita
		if(request.getParameter("modificaNota")!=null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
		    String strDate = sdf.format(now);
			System.out.println("data :"+strDate);
			Evaluare ultimaEvaluare=new Evaluare();
			
			if((request.getParameter(request.getParameter("modificaNota"))!="0" && request.getParameter(request.getParameter("modificaNota"))!=""))
			{
				if(PrelucrariDB.returnNotare(Integer.parseInt(request.getParameter("modificaNota").toString()), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString())).getNota() != Integer.parseInt(request.getParameter(request.getParameter("modificaNota"))))
				{
					System.out.println("Se actualizeaza nota studentului "+request.getParameter("modificaNota")+" cu "+request.getParameter(request.getParameter("modificaNota")));
					if(Integer.parseInt(request.getParameter(request.getParameter("modificaNota")))>0 && Integer.parseInt(request.getParameter(request.getParameter("modificaNota")))<=10)
					{
						try {
							PrelucrariDB.updateNota(getDateFromString(strDate), Integer.parseInt(request.getParameter("modificaNota").toString()), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString()), Integer.parseInt(request.getParameter(request.getParameter("modificaNota"))));
						} catch (NumberFormatException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						request.setAttribute("invalid", "Notele introduse trebuie sa se incadreze in intervalul [1,10] !");
					}
				}
			}
			else
			{
				request.setAttribute("invalid", "Pentru a putea modifica nota unui student, acesta trebuie sa fi sustinut un examen. Nota studentului trebuie sa fie diferita de 0");
			}
				
		}
		
		//Modificare nota cu reevaluare
		if(request.getParameter("reevaluare")!=null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
		    String strDate = sdf.format(now);
			System.out.println("data :"+strDate);
			Evaluare ultimaEvaluare=new Evaluare();
			if(request.getParameter(request.getParameter("reevaluare"))!="0" && request.getParameter(request.getParameter("reevaluare"))!="")
			{
				if(PrelucrariDB.returnNotare(Integer.parseInt(request.getParameter("reevaluare").toString()), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString())).getNota() != Integer.parseInt(request.getParameter(request.getParameter("reevaluare"))))
				{
					System.out.println("Se actualizeaza nota studentului "+request.getParameter("reevaluare")+" cu "+request.getParameter(request.getParameter("reevaluare")));
					if(Integer.parseInt(request.getParameter(request.getParameter("reevaluare")))>0 && Integer.parseInt(request.getParameter(request.getParameter("reevaluare")))<=10)
					{
						try {
							ultimaEvaluare=PrelucrariDB.returnEvaluare(Integer.parseInt(request.getParameter("reevaluare").toString()), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString()));
							if(ultimaEvaluare.getNumar_evaluare()<3)
							{
								PrelucrariDB.updateNota(getDateFromString(strDate), Integer.parseInt(request.getParameter("reevaluare").toString()), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString()), Integer.parseInt(request.getParameter(request.getParameter("reevaluare"))));
								System.out.println("Studentul "+ultimaEvaluare.getStudent_numar_matricol()+" a ajuns la evaluarea "+ultimaEvaluare.getNumar_evaluare()+1);
								PrelucrariDB.updateEvaluare(ultimaEvaluare.getId_evaluare(), getDateFromString(strDate), ultimaEvaluare.getNumar_evaluare()+1);
							}
							else
								request.setAttribute("invalid", "Nota unui student se poate modifica de maximum 3 ori!");
						} catch (NumberFormatException | ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else{
						request.setAttribute("invalid", "Notele introduse trebuie sa se incadreze in intervalul [1,10] !");
					}
				}
			}
		}
		if(request.getParameter("deconectare")!=null)
		{
			request.getRequestDispatcher("PaginaPrincipala.jsp").forward(request,response);
		}
		
		request.getRequestDispatcher("notareStudenti.jsp").forward(request,response);
	}

	
		public static java.sql.Date getDateFromString(String date) throws ParseException {
			 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		        Date parsed = format.parse(date);
		        java.sql.Date sql = new java.sql.Date(parsed.getTime());
		        return sql;
		} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}