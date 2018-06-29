package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import claseResurse.AnUniversitar;
import database.PrelucrariDB;

/**
 * Servlet implementation class UpdateServletAn
 */
@WebServlet("/UpdateServletAn")
public class UpdateServletAn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServletAn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		AnUniversitar anExistent=new AnUniversitar();
		String input=null;
		System.out.println(request.getParameter("den_an")+" "+request.getParameter("sem"));
		if(request.getParameter("den_an")!=null && request.getParameter("den_an")!="" && request.getParameter("sem")!=null && request.getParameter("sem")!="")
		{
			System.out.println("Se incearca introducerea anului!");
			input=request.getParameter("den_an");
			if(formatAn(input)==true && (Integer.parseInt(request.getParameter("sem"))==1 || Integer.parseInt(request.getParameter("sem"))==2))
			{
				anExistent=PrelucrariDB.returnAn(request.getParameter("den_an"), Integer.parseInt(request.getParameter("sem")));
				if(anExistent==null||anExistent.getId_an_universitar()==0)
				{
					PrelucrariDB.insertAn(request.getParameter("den_an"), request.getParameter("sem"));
					request.setAttribute("succes", "S-a inserat anul "+request.getParameter("den_an")+" semestrul "+request.getParameter("sem"));
					System.out.println("S-a inserat anul "+request.getParameter("den_an")+" semestrul "+request.getParameter("sem"));
				}
				else
				{
					request.setAttribute("exista", "In baza de date exista deja: "+request.getParameter("den_an")+" "+request.getParameter("sem"));
				}
			}
			else
			{
				request.setAttribute("invalid", "Anul universitar introdus nu respecta formatul!");
				System.out.println("Anul universitar introdus nu respecta formatul!");
			}
		}
		else
		{
			request.setAttribute("invalid", "Pentru adugarea unui nou an universitar trebuie completate campurile 'Denumirea anului' si 'Semestrul'!");
		}
		request.getRequestDispatcher("ani_universitari.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private boolean formatAn(String an)
	{
		System.out.println("Lungimea sirului de caractere: "+an.length());
		if(an.length()==9)
		{
			System.out.println(an.substring(0, 1));
			System.out.println(an.substring(1, 2));
			System.out.println(an.substring(2, 4));
			System.out.println(an.substring(4, 5));
			System.out.println(an.substring(5, 6));
			System.out.println(an.substring(6, 7));
			System.out.println(an.substring(7, 9));
			if(Integer.parseInt(an.substring(0, 1))==2 && Integer.parseInt(an.substring(1, 2))==0 && Integer.parseInt(an.substring(2, 4))>10 && Integer.parseInt(an.substring(2, 4))<20 && an.substring(4, 5).equals("-") && Integer.parseInt(an.substring(5, 6))==2 && Integer.parseInt(an.substring(6, 7))==0 && Integer.parseInt(an.substring(7,9))>10 && Integer.parseInt(an.substring(7, 9))<20)
			{
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
}