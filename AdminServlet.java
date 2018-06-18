package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.PrelucrariDB;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//if(request.getParameter("profesorSelected")!=null)
		String profesor = request.getParameter("profesor");
		System.out.println(profesor);
	    String an_universitar =request.getParameter("an_universitar");
	    System.out.println(an_universitar);
	    String grupa =request.getParameter("grupa");
	    System.out.println(grupa);
	    String disciplina = request.getParameter("disciplina");
	    System.out.println(disciplina);
	    PrelucrariDB.insertPreda(Integer.parseInt(profesor), Integer.parseInt(disciplina), Integer.parseInt(an_universitar), Integer.parseInt(grupa));
	    request.setAttribute("alocare", "Profesorul a fost alocat cu succes");
	    request.getRequestDispatcher("profesori.jsp").forward(request,response);
		//else
		//	response.sendRedirect("profesoriAlocati.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
