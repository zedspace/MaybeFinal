package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.PrelucrariDB;
import database.Securitate;

/**
 * Servlet implementation class ContServlet
 */
@WebServlet("/ContServlet")
public class ContServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int profesor;
		int student;
		
			System.out.println("Se fac prelucrari pentru categoria: "+request.getParameter("tipCont"));
			System.out.println("Informatiile introduse: matricol: "+request.getParameter("student")+" si marca: "+request.getParameter("profesor"));
			System.out.println(request.getParameter("utilizator"));
			System.out.println(Securitate.criptare(request.getParameter("parola")));
			if(request.getParameter("utilizator")!=null && request.getParameter("parola") !=null && request.getParameter("utilizator")!="" && request.getParameter("parola")!=""){
				System.out.println("---Inserarea contului!---");
				if(request.getParameter("profesor")==null)
					profesor=0;
				else
					profesor=Integer.parseInt(request.getParameter("profesor"));
				if(request.getParameter("student")==null)
					student=0;
				else
					student=Integer.parseInt(request.getParameter("student"));
				System.out.println(PrelucrariDB.returnCont(request.getParameter("utilizator")).getUtilizator());
				if(PrelucrariDB.returnCont(request.getParameter("utilizator")).getUtilizator()==null){
					if(PrelucrariDB.returnCont(profesor,student).getMarca()==profesor || PrelucrariDB.returnCont(profesor,student).getNumar_matricol()==student)
					{
						request.setAttribute("incomplet", "Utilizatorul are deja un cont creat");
						System.out.println("Utilizatorul are deja un cont creat");
					}
					else{
						if(request.getParameter("tipCont").equals("profesor"))
						{
							PrelucrariDB.insertCont(request.getParameter("utilizator"), Securitate.criptare(request.getParameter("parola")), request.getParameter("informatii"), "0", request.getParameter("profesor"));
							System.out.println("S-a creat cont pentru profesorul "+request.getParameter("profesor"));
							request.setAttribute("succes", "S-a creat cont pentru profesorul "+request.getParameter("profesor"));
						}
						if(request.getParameter("tipCont").equals("student"))
						{	
							PrelucrariDB.insertCont(request.getParameter("utilizator"), Securitate.criptare(request.getParameter("parola")), request.getParameter("informatii"),request.getParameter("student"), "0");
							System.out.println("S-a creat cont pentru studentul "+request.getParameter("student"));
							request.setAttribute("succes","S-a creat cont pentru studentul "+request.getParameter("student"));
						}
					}
					System.out.println("---Sfarsit---Inserarea contului!---");
				}
				else{
					request.setAttribute("invalid", "Exista deja un utilizator cu acest nume!");
				}}
			else
				request.setAttribute("incomplet", "Toate campurile sunt obligatorii");
			request.getRequestDispatcher("conturi.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}