<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profesori Alocati</title>
</head>
<body>
<h3>Alocarea profesorilor la discipline si specializari s-a realizat astfel :</h3>
<table>
<tr>
			<td>Titulatura</td>
			<td>Nume</td>
			<td>Prenume</td>
			<td>Anul Universitar</td>
			<td>Specializarea</td>
			<td>Grupa</td>
			<td>Disciplina</td>
			<td>Tipul Disciplinei</td>
		</tr>
<%List<Preda> listaPredare=PrelucrariDB.returnPreda(); %>
<%List<AfisarePredare> predareProfesori=PrelucrariDB.afisarePredare(listaPredare); %>
	<%for(AfisarePredare predareProfesor: predareProfesori){ %>
		<tr>
			<td><%=predareProfesor.getTitulatura()%></td>
			<td><%=predareProfesor.getNumeProfesor()%></td>
			<td><%=predareProfesor.getPrenumeProfesor()%></td>
			<td><%=predareProfesor.getAn()%></td>
			<td><%=predareProfesor.getSpecializare()%></td>
			<td><%=predareProfesor.getGrupa()%></td>
			<td><%=predareProfesor.getNumeDisciplina()%></td>
			<td><%=predareProfesor.getTipDisciplina()%></td>
		</tr>
	<%}%>
</table>	
</body>
</html>