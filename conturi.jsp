<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css\adminStyle.css">
<title>Administrator</title>
</head>
<body>
<div class="wrap">
    <div class="meniu"> 
    <a href="departamente.jsp">Departamente</a> 
    <a href="specializari.jsp">Specializari</a> 
    <a href="ani_universitari.jsp">Ani Universitari</a> 
    <a href="grupe.jsp">Grupe</a>
    <a href="discipline.jsp">Discipline</a>
    <a href="studenti.jsp">Studenti</a>
    <a href="profesori.jsp">Profesori</a> 
    <a class="active" href="conturi.jsp">Conturi</a>
    <a href="PaginaPrincipala.jsp">Delogare</a>
    </div>
    <div class="continut" align="center">
    <%if(request.getAttribute("incomplet")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("incomplet")%>
		</div>
	<%}%>
    
	<form  action="ContServlet" method="post">
	<fieldset>
 		<legend>Conturi existente</legend>
    <table>
    	<tr>
    		<td style="font-size:20px;"><b>Utilizator</b></td>
    		<td style="font-size:20px;"><b>Parola</b></td>
    		<td style="font-size:20px;"><b>Alte informatii</b></td>
    		<td style="font-size:20px;"><b>Marca</b></td>
    		<td style="font-size:20px;"><b>Numar Matricol</b></td>

    	</tr>
    	<tr>
    	<%List<Cont> listaConturi=PrelucrariDB.returnConturi(); %>
    	<%for(Cont cont: listaConturi){ %>
 			<td><input type="text" value="<%=cont.getUtilizator() %>" style="font-size:20px;" size=15 disabled/></td>
 			<td><input type="text" value="<%=cont.getParola() %>" style="font-size:20px;" size=15 disabled/></td>   
 			<td><input type="text" value="<%=cont.getInformatii() %>" style="font-size:20px;" size=15 disabled/></td>
 			<td><input type="text" value="<%=cont.getMarca() %>" style="font-size:20px;" size=5 disabled/></td>
 			<td><input type="text" value="<%=cont.getNumar_matricol() %>" style="font-size:20px;" size=3 disabled/></td>
    	</tr>
    	<%}%>
    </table>
    </fieldset>
    <button type="button" name="adaugaCont" onclick="showFunctionAdd()" style="font-size:20px;">Creeaza cont</button>
    </form>
     <div id="adaugareCont" style="display: none;">
    <form name="adaugaForm" action="ContServlet" method="post">
    <fieldset>
 		<legend>Crearea unui cont nou</legend>
    <table>
    	<tr>
    	<td style="font-size:20px;">Selecteaza tipul contului</td>
    	<td><input type="radio" name="tipCont" id="tipCont" value="profesor" onclick="document.getElementById('profesor').disabled = false;document.getElementById('student').disabled = true"/><label style="font-size:20px;">Profesor</label><br>
    	<input type="radio" name="tipCont" id="tipCont" value="student" onclick="document.getElementById('student').disabled = false ; document.getElementById('profesor').disabled = true"/><label style="font-size:20px;">Student</label></td>
    	</tr>
    	<tr>
    	<td style="font-size:20px;">Nume utilizator</td>
    	<td><input type="text" id="utilizator" name="utilizator" size="50" style="font-size:20px;"/></td>
    	</tr>
    	<tr>
    	<td style="font-size:20px;">Parola</td>
    	<td><input type="text" id="parola" name="parola" size="50" style="font-size:20px;"/></td>
    	</tr>
    	<tr>
    	<td style="font-size:20px;">Alte informatii</td>
    	<td><textarea id="informatii" rows="4" cols="50"></textarea></td>
    	</tr>
    	<tr>
    	<td style="font-size:20px;">Numele si prenumele studentului</td>
    	<td><select name="student" id="student" disabled style="font-size:20px;">
		<%List<Student> listaStudenti= PrelucrariDB.returnStudenti(); %>
			<%for(Student student:listaStudenti){ %>
			<option value = "<%=student.getNumar_matricol()%>"> 
			<%=(student.getNume()+" "+student.getPrenume())%>
			</option>
			<%}%>
			</select></td>
    	</tr>
    	<tr>
    	<td style="font-size:20px;">Numele si prenumele profesorului</td>
    	<td><select name="profesor" id="profesor" disabled style="font-size:20px;">
		<%List<Profesor> listaProfesori= PrelucrariDB.returnProfesor(); %>
			<%for(Profesor profesor:listaProfesori){ %>
			<option value = "<%=profesor.getMarca()%>"> 
			<%=(profesor.getTitulatura()+" "+profesor.getNume()+" "+profesor.getPrenume())%>
			</option>
			<%}%>
			</select>
			</td>
    	</tr>
    	<tr>
    	<td><button type="Submit" id="adaugaCont" name="adaugaCont" style="font-size:20px;">Creeaza contul</button></td>
    	</tr>
	
    </table>
    </fieldset>
    </form>
    	</div>
    
    <div></div>
 <script>
function showFunctionAdd() {
    var x = document.getElementById("adaugareCont");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
</script>
</body>
</html>