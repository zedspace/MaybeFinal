<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css\profesorStyle.css">
<title>Student</title>
</head>
<header style="background-color: #2eb8b8;">
<div class="header" >
	<p>  <%
		int idUser=Integer.parseInt(request.getSession().getValue("idUser").toString());
		Student student=PrelucrariDB.returnStudentInfo(idUser);
		%>
		<b><%=student.getNume()+" "+student.getPrenume()%></b>
	</p>
	<p style="font-size:18px">
		Specializarea <%=PrelucrariDB.returnSpecializareaStudentului(student.getGrupa_id_grupa()).getDenumire_specializare() %>,
		Grupa <%=PrelucrariDB.returnGrupa(student.getGrupa_id_grupa()).getNumar_grupa() %>
		<br>
		Anul universitar in curs: <%=PrelucrariDB.returnAnActual().getDenumire_an_universitar()%>
		<br>
		Semestrul: <%=PrelucrariDB.returnAnActual().getSemestrul() %>
	</p>
</div>


<form id="form" method="POST">
<ul>
  <div class="dropdown">
    <a href="PaginaPrincipala.jsp"> <button name="deconectare" id="deconectare" type="submit" class="dropbtn" style="background-color: #ffbf80;">Deconectare <i class="fa fa-caret-down"></i></button></a>
    </div>
</ul>
</form>
</header>

<body>
<fieldset>
    	<legend style="font-size:24px">Vizualizare situatie actuala</legend>
    		<%
    		int notaFinala=0;
    		List<Disciplina> listaDisciplineNotatStudent=(List<Disciplina>)PrelucrariDB.returnDisciplinaNotatStudent(idUser);
    		if(!listaDisciplineNotatStudent.isEmpty())
	    		{
    			for(Disciplina disc:listaDisciplineNotatStudent){%>
	    			<fieldset>
	    			<legend style="font-size:24px">Disciplina <%=disc.getDenumire_disciplina()%> </legend>
	    			<table>
	    				<%
	    				notaFinala=0;
	    				SortedSet<String> tipuriDisciplina=PrelucrariDB.returnTipuriDisc(disc.getCod_disciplina());
	    				for(String tip:tipuriDisciplina){%>
	    					<tr>
	    						<td><%=tip%></td>
	    						<td><%=PrelucrariDB.returnNotareDisciplina(idUser, PrelucrariDB.returnIdDisciplina(disc.getCod_disciplina(), tip)).getNota() %></td>
	    						<%notaFinala=notaFinala+(PrelucrariDB.returnPondere(PrelucrariDB.returnIdDisciplina(disc.getCod_disciplina(), tip),PrelucrariDB.returnSpecializareaStudentului(student.getGrupa_id_grupa()).getCod_specializare() ).getPondere()*PrelucrariDB.returnNotareDisciplina(student.getNumar_matricol(),PrelucrariDB.returnIdDisciplina(disc.getCod_disciplina(), tip)).getNota()); %>
	    					</tr>
	    				<%}%>
	    				<tr>
	    					<td><b>Nota finala </b></td>
	    					<td><%=Double.valueOf(notaFinala)/100 %></td>
	    				</tr>
	    			</table>
	    			</fieldset>
	    			
	    		<%}}
    		else{%>
	    		<div class="alert">
				<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
			    <strong>Situatia dumneavoastra inca nu a fost actualizata!</strong>
				</div>
    		<%}
    		%>
    </fieldset>
</body>
</html>