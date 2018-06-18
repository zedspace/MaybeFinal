package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claseResurse.Student;
import database.PrelucrariDB;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Student> studenti= new ArrayList<Student>();
		if(request.getParameter("cauta")!=null)
		{
			System.out.println("Se cauta studentul "+request.getParameter("nume"));
			studenti=PrelucrariDB.returnStudenti(request.getParameter("nume"));
			
			if(!studenti.isEmpty())
				{
				request.setAttribute("listaRezultat", studenti);
				System.out.println("Lista studentilor care corespund numelui "+studenti.get(0).getNume());
				}
			else 
				request.setAttribute("notFound", "Studentul nu exista");
			request.getRequestDispatcher("studenti.jsp").forward(request,response);
		}
		if(request.getParameter("adaugaStud")!=null)
		{
			if(request.getParameter("cnp")!=null&&request.getParameter("nume_stud")!=null&&request.getParameter("prenume")!=null&&request.getParameter("formaFinantare")!=null){
				if(PrelucrariDB.returnStudent(request.getParameter("cnp")).getCnp()==null)
				{
					PrelucrariDB.insertStudent(request.getParameter("cnp"), request.getParameter("nume_stud"), request.getParameter("prenume"), request.getParameter("formaFinantare"));
					request.setAttribute("succes", "Studentul "+request.getParameter("nume_stud")+" "+request.getParameter("prenume")+" a fost adaugat in baza de date!");
					System.out.println("S-a inserat studentul");
				}
				else
					{
						request.setAttribute("exista", "Studentul exista deja in baza de date!");
					}
			}
			else
				request.setAttribute("incomplet", "Toate campurile sunt obligatorii");
			request.getRequestDispatcher("studenti.jsp").forward(request,response);
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
