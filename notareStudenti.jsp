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
<title>Profesor - Notare Studenti</title>
</head>
<header style="background-color: #2eb8b8;">
<div class="header">
	<p><%
		int idUser=Integer.parseInt(request.getSession().getValue("idUser").toString());
		Profesor profesor=PrelucrariDB.returnProfesorInfo(idUser);
		%>
		<%=profesor.getTitulatura()+" "+profesor.getNume()+" "+profesor.getPrenume() %>
	</p>
	<p>
		Anul universitar in curs: <%=PrelucrariDB.returnAnActual().getDenumire_an_universitar()%>
		<%request.getSession().putValue("anUniversitarActual", PrelucrariDB.returnAnActual().getId_an_universitar()); %>
		<br>
		Semestrul: <%=PrelucrariDB.returnAnActual().getSemestrul() %>
	</p>
</div>

<form id="form" method="POST" action="PondereServlet">
<ul>
  <li><a href="profesor.jsp">Home</a></li>
  <div class="dropdown">
    <button class="dropbtn">Stabilire ponderi <i class="fa fa-caret-down"></i></button>
    <div class="dropdown-content">
	    <%List<Preda> listaPredare=PrelucrariDB.returnPreda(idUser); %>
		<%List<Specializare> predareSpecializare=PrelucrariDB.predareSpecializare(listaPredare); %>
		<%for(Specializare predareSpec: predareSpecializare){ %>
			 <a href="PondereServlet?cod=<%=predareSpec.getCod_specializare()%>"><%=predareSpec.getDenumire_specializare()%></a>
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
<%int codSpec=Integer.parseInt(request.getSession().getValue("codSpec").toString()); %>
<h4>Notarea studentilor de la specializarea <%=PrelucrariDB.returnSpecializare(codSpec).getDenumire_specializare() %></h4>
<%if(request.getAttribute("incomplet")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("incomplet")%>
		</div>
<%}%>
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
	
<form action="NotareServlet" method="post">
<fieldset>
    	<legend>Selectarea anul de studiu</legend>
<table>
<tr>
	<td>
	<input type="radio" id="an_studiu" name="an_studiu" value="1">1
	<input type="radio" id="an_studiu" name="an_studiu" value="2">2
	<input type="radio" id="an_studiu" name="an_studiu" value="3">3
	</td>
	<td><button id="selectareAn" name="selectareAn" type="submit" onclick="showFunctionAdd()">Afisare grupe</button></td>
</tr>
</table>
</fieldset>
<%if(request.getAttribute("lipsaGrupa")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("lipsaGrupa")%>
		</div>
<%}%>

<%if(request.getAttribute("listaGrupePredare")!=null)
	{%> <fieldset>
    	<legend>Selectarea grupei</legend>
    	<table>
	<%
		List<Grupa> listaGrupePredare=(List<Grupa>)request.getAttribute("listaGrupePredare");
		for(Grupa grupa:listaGrupePredare)
		{ %> 
			<tr>
				<td ><input type="radio" id="grupa" name="grupa" value="<%=grupa.getId_grupa()%>"> <%=grupa.getNumar_grupa() %>
				</td>
			 </tr>
	<%}}%>
<%if(request.getAttribute("listaDisciplinePredare")!=null)
	{%> <td><label><b>Selecteaza Disciplina</b></label></td>
	<%
		Set<String> listaDisciplinePredare=(Set<String>)request.getAttribute("listaDisciplinePredare");
		for(String disciplina:listaDisciplinePredare)
		{ %> 
			<tr>
			<% SortedSet<String> tipDisciplina=PrelucrariDB.returnTipuriDisc(PrelucrariDB.returnCodDisc(disciplina));
				for(String tip:tipDisciplina){
			%>
				<td> <%=disciplina%> <%=tip%>
				</td>
				<td><button type="submit" id="catreStudenti" name="catreStudenti" value="<%=PrelucrariDB.returnIdDisciplina(PrelucrariDB.returnCodDisc(disciplina), tip)%>">Adauga nota studentilor</button></td>
			 </tr>
	<%}}}%>
	</table>
	</fieldset>
	
<%if(request.getAttribute("listaStudenti")!=null)
	{%> 
		<fieldset>
    	<legend>Adaugare note pentru disciplina <%=PrelucrariDB.returnDisciplina(Integer.parseInt(request.getSession().getValue("idDisciplina").toString())).getDenumire_disciplina()%> - studentilor din grupa <%=PrelucrariDB.returnGrupa(Integer.parseInt(request.getAttribute("grupa").toString())).getNumar_grupa() %></legend>
    	<table>
		<tr>
		<td><b>Numar matricol</b></td>
		<td><b>Nume</b></td>
		<td><b>Prenume</b></td>
		<td><b>Nota</b></td>
		<td><b></b></td>
		<td><b></b></td>
		<td><b>Numar evaluare</b></td>
		</tr>
	<%
		List<Student> listaStudenti=(List<Student>)request.getAttribute("listaStudenti");
		for(Student student:listaStudenti)
		{ %> 
			<tr>
				<td><%=student.getNumar_matricol()%></td>
				<td><%=student.getNume()%></td>
				<td><%=student.getPrenume()%></td>
				<td><input type="text" onkeypress="return isNumberKey(event)" onchange="return checkValue(<%=student.getNumar_matricol()%>)" name="<%=student.getNumar_matricol()%>" id="<%=student.getNumar_matricol()%>" value="<%=PrelucrariDB.returnNotare(student.getNumar_matricol(), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString())).getNota()%>" size="5"/>
				<td><button type="submit" id="modificaNota" name="modificaNota" value="<%=student.getNumar_matricol()%>" title="Nota poate fi modificata in cazul in care s-a introdus o valoare gresita!" onclick="return checkValue(<%=student.getNumar_matricol()%>)">Modificare nota</button></td>
				<td><button type="submit" id="reevaluare" name="reevaluare" value="<%=student.getNumar_matricol()%>" title="Reevaluare se considera in momentul in care studentul a sustinut din nou examenul. Reevaluarea se poate realiza de maximum 3 ori." onclick="return checkValue(<%=student.getNumar_matricol()%>)">Reevaluare</button></td>
			 	<td><%=PrelucrariDB.returnEvaluare(student.getNumar_matricol(), Integer.parseInt(request.getSession().getValue("idUser").toString()), Integer.parseInt(request.getSession().getValue("idDisciplina").toString())).getNumar_evaluare()%><b> din 3</b></td>
			 </tr>
	<%}%>
		<tr colspan="6"><td><button type="submit" name="adaugaNota" id="adaugaNota">Adauga notele</button></td></tr>
		<%}%>
		
</table>
</fieldset>
</form>
</body>
<script>
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;

   return true;
}
function checkValue(matricola)
{
	   var value = document.getElementById(matricola).value;
	   if (value<1 || value>10)
		{
		   alert("Nota trebuie sa fie cuprinsa intre 1 si 10! ");
		   document.getElementById(matricola).focus();
		   return false;
		}  
	      
	}
</script>
</html>