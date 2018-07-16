<%@page import="database.Securitate"%>
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
	<title>Administrator - Conturi</title>
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
     <%if(request.getAttribute("succes")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong><%=request.getAttribute("succes")%></strong>
		</div>
	<%}%>
	<form name="form" action="ContServlet" method="post">
	<fieldset>
 		<legend>Conturi existente</legend>
    <table id="table">
    	<tr>
    		<th><b>Utilizator</b></th>
    		<th><b>Parola</b></th>
    		<th><b>Alte informatii</b></th>
    		<th><b>Marca</b></th>
    		<th><b>Numar Matricol</b></th>
    	</tr>
    	<tr>
    	<%List<Cont> listaConturi=PrelucrariDB.returnConturi(); %>
    	<%for(Cont cont: listaConturi){ %>
 			<td><%=cont.getUtilizator() %></td>
 			<td><%=Securitate.decriptare(cont.getParola())%></td>   
 			<td><%=cont.getInformatii() %></td>
 			<td><%=cont.getMarca() %></td>
 			<td><%=cont.getNumar_matricol() %></td>
    	</tr>
    	<%}%>
    </table>
    </fieldset>
   		 <button type="button" name="adaugaCont" onclick="showFunctionAdd()" style="font-size:20px;">Creeaza cont</button>
    
     <div id="adaugareCont" style="display: none;">
    	<fieldset>
 			<legend>Crearea unui cont nou</legend>
   			 <table>
		    	<tr>	<td style="font-size:20px;">Selecteaza tipul contului</td>
		    			<td><input type="radio" name="tipCont" id="tipCont_P" value="profesor" onclick="document.getElementById('profesor').disabled = false;document.getElementById('student').disabled = true"/><label style="font-size:20px;">Profesor</label><br>
		    			<input type="radio" name="tipCont" id="tipCont_S" value="student" onclick="document.getElementById('student').disabled = false ; document.getElementById('profesor').disabled = true"/><label style="font-size:20px;">Student</label></td>
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
		    	<td><button type="button" id="adaugaCont" name="adaugaCont" style="font-size:20px;" onclick="verificare()">Creeaza contul</button></td>
		    	</tr>
    </table>
    </fieldset>
    </div>
    </form>
 </div></div>
 <script>
function showFunctionAdd() {
    var x = document.getElementById("adaugareCont");
    if (x.style.display === "none") {
        x.style.display = "block";
    }
}
function verificare(){
	 var x = document.getElementById("tipCont_P").checked;
	 var y = document.getElementById("tipCont_S").checked;
	 var z = document.getElementById("utilizator").value;
	 var q = document.getElementById("parola").value;
	 if((x==false && y==false) || z==null || z=="" || q==null || q=="")
		 alert("Pentru crearea unui cont se alege tipul de utilizator, iar apoi se completeaza campurile Nume Utilizator si Parola!");
	 else
		document.form.submit(); 
}
</script>
</body>
</html>