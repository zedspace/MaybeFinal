<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css\profesorStyle.css">
<title>Profesor</title>
</head>
<header style="background-color: #2eb8b8;">
<div class="header" >
	<p>  <%
		int idUser=Integer.parseInt(request.getSession().getValue("idUser").toString());
		Profesor profesor=PrelucrariDB.returnProfesorInfo(idUser);
		%>
		<%=profesor.getTitulatura()+" "+profesor.getNume()+" "+profesor.getPrenume() %>
	</p>
	<p>
		Anul universitar in curs: <%=PrelucrariDB.returnAnActual().getDenumire_an_universitar()%>
		<br>
		Semestrul: <%=PrelucrariDB.returnAnActual().getSemestrul() %>
	</p>
</div>

<form id="form" method="POST">
<ul>
  <li><a href="profesor.jsp">Home</a></li>
  <div class="dropdown">
    <button class="dropbtn">Stabilire ponderi <i class="fa fa-caret-down"></i></button>
    <div class="dropdown-content">
	    <%List<Preda> listaPredare=PrelucrariDB.returnPreda(idUser); %>
		<%List<Specializare> predareSpecializare=PrelucrariDB.predareSpecializare(listaPredare); %>
		<%for(Specializare predareSpec: predareSpecializare){ %>
			 <a href="PondereServlet?cod=<%=predareSpec.getCod_specializare()%>"><%=predareSpec.getDenumire_specializare()%></a>
<!-- 			 <input type="hidden" name="cod" id="cod" value="0"/> -->
		<%}%>
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn">Notare Studenti <i class="fa fa-caret-down"></i></button>
    <div class="dropdown-content">
		<%for(Specializare predareSpec: predareSpecializare){ %>
			 <a href="NotareServlet?cod=<%=predareSpec.getCod_specializare()%>"><%=predareSpec.getDenumire_specializare()%></a>
		<%}%>
    </div>
  </div> 
  
  <div class="dropdown">
    <a href="PaginaPrincipala.jsp"> <button name="deconectare" id="deconectare" type="submit" class="dropbtn" style="background-color: #ffbf80;">Deconectare <i class="fa fa-caret-down"></i></button></a>
    </div>
</ul>
</form>
</header>
<body>
    <fieldset>
    	<legend style="font-size:24px">Prelucrari disponibile</legend>
    		<p>&rarr; Vizualizarea specializarilor disponibile</p>
    		<p>&rarr; Vizualizarea disciplinelor disponibile</p>
    		<p>&rarr; Stabilirea ponderilor pentru toate tipurile disciplinelor (Doar pentru profesorul care tine cursul)</p>
    		<p>&rarr; Adaugarea notelor pentru studentii de la specializarile disponibile</p>
    		<p>&rarr; Posibilitatea modificarii notelor studentilor, cu titlul de reevaluare</p>
    </fieldset>
    <%if(request.getAttribute("inexistent")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("inexistent")%>
		</div>
	<%}%>
	<%if(request.getAttribute("invalid")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("invalid")%>
		</div>
	<%}%>
    <form action="ProfesorHomeServlet">
    <fieldset>
    	<legend style="font-size:24px">Vizualizare situatii studenti - Selectati specializarea si anul de studiu</legend>
    		<%for(Specializare predareSpec: predareSpecializare){ %>
    			<p>
    			<input type="radio" id="an_studiu" name="an_studiu" value="1">1
				<input type="radio" id="an_studiu" name="an_studiu" value="2">2
				<input type="radio" id="an_studiu" name="an_studiu" value="3">3
    			<button type="submit" id="specializareVizualizare" name="specializareVizualizare" value="<%=predareSpec.getCod_specializare()%>" > <%=predareSpec.getDenumire_specializare()%> </button></p>
		<%}%>
		
		<% if(request.getAttribute("listaDisciplinePredate")!=null)
			{%> Selectati o disciplina:<br> <% 
				List<Disciplina> listaDisciplinePredate=(List<Disciplina>) (request.getAttribute("listaDisciplinePredate"));
				for(Disciplina disc:listaDisciplinePredate)
				{%>
					<button type="Submit" name="disciplina" id="disciplina" value="<%=disc.getId_disciplina()%>"> <%=disc.getDenumire_disciplina()%> <%=PrelucrariDB.returnTipDisciplina(disc.getId_disciplina())%></button><br>
			<% 	}
			}
			%>
	 </fieldset>
    </form>
	<% if(request.getAttribute("listaSituatieStudenti")!=null)   
	{ %>
	<fieldset>
    	<legend style="font-size:24px">Vizualizare situatii studenti, specializarea <%=PrelucrariDB.returnSpecializare(Integer.parseInt(request.getSession().getValue("specializareVizualizare").toString())).getDenumire_specializare()%> , la disciplina <%=PrelucrariDB.returnDisciplina(Integer.parseInt(request.getSession().getValue("disciplinaVizualizare").toString())).getDenumire_disciplina()%>, in anul <%=Integer.parseInt(request.getSession().getValue("an_studiu_vizualizare").toString()) %></legend>
    	<table>
    	<tr>
    		<td>Numar matricol</td>
    		<td>Nume</td>
    		<td>Prenume</td>
    	<%SortedSet<String> tipuriDisciplina=PrelucrariDB.returnTipuriDisc(PrelucrariDB.returnDisciplina(Integer.parseInt(request.getSession().getValue("disciplinaVizualizare").toString())).getCod_disciplina()); 
    	for(String tip:tipuriDisciplina)
    	{%> <td><%=tip%> (<%=PrelucrariDB.returnPondere(PrelucrariDB.returnIdDisciplina(PrelucrariDB.returnCodDisc(PrelucrariDB.returnDisciplina(Integer.parseInt(request.getSession().getValue("disciplinaVizualizare").toString())).getDenumire_disciplina()), tip), Integer.parseInt(request.getSession().getValue("specializareVizualizare").toString())).getPondere()%> %)</td>
    	<%} %>
    	<td>Nota finala</td>
    	</tr>
	<% 	int notaFinala=0;
		List<Student> listaSituatieStudenti=(List<Student>) request.getAttribute("listaSituatieStudenti"); 
		for(Student stud:listaSituatieStudenti) 
		{notaFinala=0;%><tr> 
			<td><%=stud.getNumar_matricol()%></td>
			<td><%=stud.getNume()%></td>  
			<td><%=stud.getPrenume()%></td>
			<%for(String tip:tipuriDisciplina)
	    	{%> 
		    	<td>
		    		<%=PrelucrariDB.returnNotareDisciplina(stud.getNumar_matricol(),PrelucrariDB.returnIdDisciplina(PrelucrariDB.returnCodDisc(PrelucrariDB.returnDisciplina(Integer.parseInt(request.getSession().getValue("disciplinaVizualizare").toString())).getDenumire_disciplina()), tip)).getNota()%>
					<%notaFinala=notaFinala+(PrelucrariDB.returnPondere(PrelucrariDB.returnIdDisciplina(PrelucrariDB.returnCodDisc(PrelucrariDB.returnDisciplina(Integer.parseInt(request.getSession().getValue("disciplinaVizualizare").toString())).getDenumire_disciplina()), tip), Integer.parseInt(request.getSession().getValue("specializareVizualizare").toString())).getPondere()*PrelucrariDB.returnNotareDisciplina(stud.getNumar_matricol(),PrelucrariDB.returnIdDisciplina(PrelucrariDB.returnCodDisc(PrelucrariDB.returnDisciplina(Integer.parseInt(request.getSession().getValue("disciplinaVizualizare").toString())).getDenumire_disciplina()), tip)).getNota()); %>
				</td>
			<%} %>
			<td><%=Double.valueOf(notaFinala)/100 %></td>
			</tr>
	<%}}%> 
	</table>
		</fieldset>
	
   
</body>
<script>
$(document).ready(function(){
    $("link").click(function(){
        $("name").hide();
    });
});
</script>
</html>