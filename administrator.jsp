<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<%@page import="java.util.List"%>
<jsp:useBean id="profesori" class="database.PrelucrariDB" scope="page"/>
<%-- <jsp:include page="AdminServlet.java" /> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator</title>
</head>
<body>
<h3>Alocarea profesorilor la materii</h3>
<form action="AdminServlet" name="admin" id="admin" method="POST" >
<table>
<tr>
<td><label>Selecteaza anul universitar : </label></td>
<td>
<%List<AnUniversitar> listaAni= PrelucrariDB.returnAni(); %>
	<select name="an_universitar">
	<%for(AnUniversitar an: listaAni){ %>
	<option value = "<%=an.getId_an_universitar()%>"> 
	<%=(an.getDenumire_an_universitar()+" semestrul "+an.getSemestrul())%>
	</option>
	<%}%>
	</select>
</td>
</tr>
<tr>
<td><label>Selecteaza disciplina : </label></td>
<td>
<%List<Disciplina> listaDiscipline= PrelucrariDB.returnDiscipline(); %>
	<select name="disciplina" id="disciplina">
	<%for(Disciplina disc: listaDiscipline){ %>
	<option value = "<%=disc.getId_disciplina()%>"> 
	<%=(disc.getDenumire_disciplina()+" "+disc.getTip_disciplina())%>
	</option>
	<%}%>
	</select>
</td>
</tr>
<tr>
<td><label>Selecteaza anul de studiu</label></td>
<td>
<input type="radio" id="an_studiu" name="an_studiu" value="1">1
<input type="radio" id="an_studiu" name="an_studiu" value="2">2
<input type="radio" id="an_studiu" name="an_studiu" value="3">3
</td>
</tr>
<tr>
<td><label>Selecteaza specializarea : </label></td>
<td>
<%List<Specializare> listaSpecializari= PrelucrariDB.returnSpecializari(); %>
	<select name="specializare" id="specializare">
	<%for(Specializare specializare: listaSpecializari){ %>
	<option value = "<%=specializare.getCod_specializare()%>"> 
	<%=(specializare.getDenumire_specializare())%>
	</option>
	<%}%>
	</select>
</td>
</tr>
<tr>
<td>Selecteaza grupa</td>
<td>
<!-- trebuie preluate valorile slectate la an_studiu si specializare -->
<input type="hidden" name="radioVal" id="radioVal" value=valoareRadio()/>
<%	
	List<Grupa> listaGrupe= PrelucrariDB.returnGrupe(1,1); %>
	<select name="grupa">
	<%for(Grupa grupa: listaGrupe){ %>
	<option value = "<%=grupa.getId_grupa()%>"> 
	<%=(grupa.getNumar_grupa())%>
	</option>
	<%}%>
	</select>
</td>
</tr>
<tr>
<td><label>Selecteaza profesorul : </label>
<td>
<select name="profesor" id="profesor">
<%List<Profesor> listaProfesori= PrelucrariDB.returnProfesor(); %>
	<%for(Profesor profesor:listaProfesori){ %>
	<option value = "<%=profesor.getMarca()%>"> 
	<%=(profesor.getTitulatura()+" "+profesor.getNume()+" "+profesor.getPrenume())%>
	</option>
	<%}%>
	</select>
	
</td>
</tr>
</table>
<input type="submit" value="Confirma">
</form>
</body>
</html>