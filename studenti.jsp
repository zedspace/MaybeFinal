<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/adminStyle.css">
	<title>Administrator - Studenti</title>
</head>
<body>
<div class="wrap">
    <div class="meniu"> 
    <a href="departamente.jsp">Departamente</a> 
    <a href="specializari.jsp">Specializari</a> 
    <a href="ani_universitari.jsp">Ani Universitari</a> 
    <a href="grupe.jsp">Grupe</a>
    <a href="discipline.jsp">Discipline</a>
    <a class="active" href="studenti.jsp">Studenti</a>
    <a href="profesori.jsp">Profesori</a> 
    <a href="conturi.jsp">Conturi</a>
    <a href="PaginaPrincipala.jsp">Deconectare</a>
    </div>
    
    <div class="continut" align="center">
    <h2>Studenti</h2>
    <form name="form" action="StudentServlet">
    <%if(request.getAttribute("notFound")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("notFound")%>
		</div>
	<%}%>
	<%if(request.getAttribute("incomplet")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("incomplet")%>
		</div>
	<%}%>
	<%if(request.getAttribute("exista")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("exista")%>
		</div>
	<%}%>
		<%if(request.getAttribute("succes")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong><%=request.getAttribute("succes")%></strong>
		</div>
	<%}%>
		<%if(request.getAttribute("invalid")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong><%=request.getAttribute("invalid")%></strong>
		</div>
	<%}%>
	
	<fieldset>
 		<legend>Vizualizarea studentilor</legend>
 			<table>
 			<tr>
 				<td><b>Selecteaza specializarea</b></td>
 				<td><button type="button" name="vizualizareStudenti" id="vizualizareStudenti" onclick="showFunctionAdd('listaCompletaStud','studentiDeLaSpecializare')" style="font-size:20px;">Vizualizare studenti</button></td>
 			</tr>
 			<tr>
				<td><%List<Specializare> listaSpecializari= PrelucrariDB.returnSpecializari(); %>
					<select name="specializare" id="specializare" style="font-size:20px;">
					<%for(Specializare specializare: listaSpecializari){ %>
					<option value = "<%=specializare.getCod_specializare()%>"> 
					<%=(specializare.getDenumire_specializare())%> &rarr; <%=(specializare.getProgram_studii())%>
					</option>
					<%}%>
					</select>
				</td>
				<td><button type="submit" name="vizualizareStudentiSpec" id="vizualizareStudentiSpec" onclick="" style="font-size:20px;">Vizualizare filtrata studenti</button></td>
			</tr>
			</table>
	</fieldset>
	
	
	<div id="studentiDeLaSpecializare" name="studentiDeLaSpecializare">
	<%if(request.getAttribute("studentiSpecializare")!=null){ %>
	<fieldset>
 		<legend>Studentii inregistrati la specializarea <%=request.getAttribute("specializareSelectata") %>:</legend>
    <table>
    	<tr>
    		<td><b>Numar Matricol</b></td>
<!--     		<td><b>CNP</b></td> -->
    		<td><b>Nume</b></td>
    		<td><b>Prenume</b></td>
    		<td><b>Specializarea</b></td>
    		<td><b>Program de studii</b></td>
    		<td><b>Forma de Finantare</b></td>
    	</tr>
    	<tr>
    	<%List<Student> studentiSpecializare=(List<Student>)request.getAttribute("studentiSpecializare"); %>
    	<%for(Student student: studentiSpecializare){ %>
 			<td><input type="text" value="<%=student.getNumar_matricol() %>" disabled size=5/></td>
<%--  			<td><input type="text" value="<%=student.getCnp() %>" disabled size=13/></td>    --%>
 			<td><input type="text" value="<%=student.getNume() %>" disabled size=15/></td>
 			<td><input type="text" value="<%=student.getPrenume() %>" disabled size=20/></td>
 			<td><input type="text" value="<%=PrelucrariDB.returnSpecializareaStudentului(student.getNumar_matricol()).getDenumire_specializare()%>" disabled size=20/></td>
 			<td><input type="text" value="<%=PrelucrariDB.returnSpecializareaStudentului(student.getNumar_matricol()).getProgram_studii()%>" disabled size=20/></td>
 			<td><input type="text" value="<%=student.getForma_finantare() %>" disabled size=5/></td>
    	</tr>
    	<%}%>
    	</table>
    	</fieldset>
	<%} %>
	</div>
	
	<div id="listaCompletaStud" name="listaCompletaStud" style="display: none;">
	<fieldset>
 		<legend>Lista completa a studentilor</legend>
    <table>
    	<tr>
    		<td><b>Numar Matricol</b></td>
<!--     		<td><b>CNP</b></td> -->
    		<td><b>Nume</b></td>
    		<td><b>Prenume</b></td>
    		<td><b>Specializarea</b></td>
    		<td><b>Program de studii</b></td>
    		<td><b>Forma de Finantare</b></td>
    	</tr>
    	<tr>
    	<%List<Student> listaStudenti=PrelucrariDB.returnStudenti(); %>
    	<%for(Student student: listaStudenti){ %>
 			<td><input type="text" value="<%=student.getNumar_matricol() %>" disabled size=5/></td>
<%--  			<td><input type="text" value="<%=student.getCnp() %>" disabled size=13/></td>    --%>
 			<td><input type="text" value="<%=student.getNume() %>" disabled size=15/></td>
 			<td><input type="text" value="<%=student.getPrenume() %>" disabled size=20/></td>
 			<td><input type="text" value="<%=PrelucrariDB.returnSpecializareaStudentului(student.getNumar_matricol()).getDenumire_specializare()%>" disabled size=20/></td>
 				<td><input type="text" value="<%=PrelucrariDB.returnSpecializareaStudentului(student.getNumar_matricol()).getProgram_studii()%>" disabled size=20/></td>
 			<td><input type="text" value="<%=student.getForma_finantare() %>" disabled size=5/></td>
    	</tr>
    	<%}%>
    	</table>
    	</fieldset>
    	</div>
    	
    	<table align="left">
	    	<tr>
	    		<td><button type="button" name="adaugaStudent" onclick="showFunctionAdd('adaugareStudent')" style="font-size:20px;">Adauga un student</button></td>
	    	</tr>
    	</table>
    	<br><br><br>
    	
        <div id="adaugareStudent" style="display: none;">
    	<fieldset>
 		<legend>Adaugarea unui nou student</legend>
	    	<table align="left">
	    	<tr>
	    	<td style="font-size:20px;">CNP</td>
	    	<td><input type="text" id="cnp" name="cnp" size="50" maxlength="13" style="font-size:20px;"/></td>
	    	</tr>
	    	<tr>
	    	<td style="font-size:20px;">Nume</td>
	    	<td><input type="text" id="nume_stud" name="nume_stud" size="50" style="font-size:20px;"/></td>
	    	</tr>
	    	<tr>
	    	<td style="font-size:20px;">Prenume</td>
	    	<td><input type="text" id="prenume" name="prenume" size="50" style="font-size:20px;"/></td>
	    	</tr>
	    	<tr>
	    	<td style="font-size:20px;">Forma de finantare</td>
	    	<td><input type="radio" name="formaFinantare" id="formaFinantare" value="Buget"/><label style="font-size:20px;">Buget</label><br>
	    	<input type="radio" name="formaFinantare" id="formaFinantare" value="Taxa"/><label style="font-size:20px;">Taxa</label></td>
	    	</tr>
	    	<tr>
	    		<td><button type="Submit" id="adaugaStud" name="adaugaStud" style="font-size:20px;">Adauga Studentul</button></td>
	    	</tr>
	    </table>
	    </fieldset>
  	</div>
  	
  	<fieldset>
  		<legend>Cauta un student</legend>
	    	<table align="left">
	    	<tr>
	    	<td style="font-size:20px;"><b>Nume/Prenume</b></td>
	    	<td><input type="text" name="nume" id="nume" style="font-size:20px;"/></td>
	    	<td><button type="submit" name="cauta" value="cauta" style="font-size:20px;">Cauta</button></td>
	    	</tr>
	    	</table>
	    </fieldset>
    <%List<Student> listaRezultat=(ArrayList<Student>)request.getAttribute("listaRezultat");%>
    <%if(listaRezultat!=null) { %>
    <fieldset>
  		<legend>Lista studentilor cautati</legend>
    <table>
    	<tr>
    		<td style="font-size:20px;">Numar Matricol</td>
    		<td style="font-size:20px;">CNP</td>
    		<td style="font-size:20px;">Nume</td>
    		<td style="font-size:20px;">Prenume</td>
    		<td style="font-size:20px;">Forma de Finantare</td>
    	</tr>
    	<tr>
    	<%for(Student student: listaRezultat){ %>
			<td><input type="text" value="<%=student.getNumar_matricol() %>" style="font-size:20px;" disabled size=5/></td>
 			<td><input type="text" value="<%=student.getCnp() %>" style="font-size:20px;" disabled size=13/></td>   
 			<td><input type="text" value="<%=student.getNume() %>" style="font-size:20px;" disabled size=15/></td>
 			<td><input type="text" value="<%=student.getPrenume() %>" style="font-size:20px;" disabled size=20/></td>
 			<td><input type="text" value="<%=student.getForma_finantare() %>" style="font-size:20px;" disabled size=5/></td>
	</tr>
	<%}%>
	</table>
    <%}%> 
    </fieldset>
  </form> 
</div>  
</div>
<script>
function showFunctionAdd(camp,ascuns1) {
    var x = document.getElementById(camp);
    var y = document.getElementById(ascuns1);
    y.style.display = "none";
    if (x.style.display === "none") {
        x.style.display = "block";
    } 
}
</script>
</body>
</html>