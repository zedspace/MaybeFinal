<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css\adminStyle.css">
		<title>Administrator - Departamente</title>
	</head>
	<body>
	    <div class="meniu"> 
		    <a class="active" href="departamente.jsp">Departamente</a> 
		    <a href="specializari.jsp">Specializari</a> 
		    <a href="ani_universitari.jsp">Ani Universitari</a> 
		    <a href="grupe.jsp">Grupe</a>
		    <a href="discipline.jsp">Discipline</a>
		    <a href="studenti.jsp">Studenti</a>
		    <a href="profesori.jsp">Profesori</a> 
		    <a href="conturi.jsp">Conturi</a>
		    <a href="PaginaPrincipala.jsp">Deconectare</a>
	    </div>
	    
	    <div class="continut" align="center">
		<form id="form" name="form" method="post" action="UpdateServletDep" >
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
		    
		    <fieldset>
	 		<legend>Departamente disponibile</legend>
				<table id="departamente" class="departamente">
					<tr>
						<td><b>Denumire departament</b></td>
						<td><b>Modifica</b></td>			
						<td align="center"><b>Viziualizarea profesorilor din cadrul unui departament</b></td>		
					</tr>
					<%int i=1;List<Departament> listaDepartamente=PrelucrariDB.returnDepartamente(); %>
						<%for(Departament departament: listaDepartamente){ %>
							<tr id="dep<%=i%>">
								<input type="hidden" name="cod_departament" id="cod_departament" value="<%=departament.getCod_departament() %>"/>
								<td>
									<input type="text" name="denumire_departament" id="denumire_departament" value="<%=departament.getDenumire_departament()%>" size="30" onchange="changeFunction(this,<%=i%>)" disabled/>
								</td>
								<td align="center">
									<button type="button" name="modifica" id="modifica" value="<%=departament.getCod_departament() %>" onclick="updateFunction(this,<%=i%>)">Modifica</button>
								</td>
								<td align="center">
									<button type="submit" name="vizualizare" id="vizualizare" value="<%=departament.getCod_departament() %>">Vizualizare</button>
								</td>
							</tr>
						<%i++;}%>
				</table>
				<input type="hidden" name="cod_dept" id="cod_dept"/>
				<input type="hidden" name="den_dep" id="den_dep"/>
			</fieldset>
			 
			<button type="button" id="inapoi" onclick="location.href = 'admin.jsp';">Inapoi</button>
			<button type="button" id="adauga" onclick="showFunctionAdd('adaugareDepartament')">Adauga</button>
			<br><br><br>
		
			<div id="adaugareDepartament" style="display: none;">
			<fieldset>
	 			<legend>Adauga un nou departament</legend>
				<table>
					<tr>
						<td><b>Denumirea departamentului</b></td>
						<td><input type="text" name="denumire_dep" id="denumire_dep" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><button type="button" name="adaugaSubmit" id="adaugaSubmit" onclick="myFunction()">Adauga departamentul</button></td>
					</tr>
				</table>
			</fieldset>
			</div>
			
			<div id="vizualizareProfesoriDepartament" style="display: block;">
			<%if(request.getAttribute("listaProfesori")!=null){
				List<Profesor> listaProfesori=(List<Profesor>)request.getAttribute("listaProfesori");
				if(!listaProfesori.isEmpty()){%>
			<fieldset>
	 			<legend>Profesorii din departamentul <%=request.getAttribute("departament")%>:</legend>
				<table>
					<tr>
						<td><b>Titulatura</b></td>
						<td><b>Nume</b></td>
						<td><b>Prenume</b></td>
					</tr>
					<%for(Profesor prof:listaProfesori){ %>
					<tr>
						<td><%=prof.getTitulatura()%></td>
						<td><%=prof.getNume()%></td>
						<td><%=prof.getPrenume()%></td>
					</tr>
					<%}%>
				</table>
			</fieldset>
			<%}
			else{%>
				<%if(request.getAttribute("inexistent")!=null){ %>
			    <div class="alert">
					<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
				    <strong><%=request.getAttribute("inexistent")%></strong>
				</div>
				<%}%>
			<%}}%>
			</div>
			
		</form> 
	</div>
	
<script>
function showFunctionAdd(numeRegiune) {
    var x = document.getElementById(numeRegiune);
    var y = document.getElementById("vizualizareProfesoriDepartament");
    if (x.style.display === "none") {
        x.style.display = "block";
    }
    if (y.style.display === "block") {
        y.style.display = "none";
    }
}
var countClicks = 0;
function updateFunction(btn,i) {
	var dep=document.getElementById("dep"+i);
	var den_dep=dep.querySelector("#denumire_departament");
	var cod_dep=dep.querySelector("#cod_departament");
	if(countClicks==0)
		{
			den_dep.disabled=!den_dep.disabled;
		}
	else
		{
			document.getElementById("denumire_departament").disabled = true;
			document.getElementById("cod_dept").value=cod_dep.value;
			document.form.submit();
		}
	btn.value=cod_dep.value;
	countClicks++;
}
function changeFunction(input,i) {
	var dep=document.getElementById("dep"+i);
	var den_dep=dep.querySelector("#denumire_departament");
	den_dep.value=input.value;
	document.getElementById("den_dep").value=input.value;
}
function myFunction(){
	var denumire_adaugare=document.getElementById("denumire_dep").value;
	if(denumire_adaugare!=null && denumire_adaugare!=''){
		if (confirm('Confirmati adaugarea noului departament: '+document.getElementById("denumire_dep").value+'?'))
		{	
			accept = true;
		}
		else
			accept = false;
	}
	else
		{
			alert("Pentru adugarea unui nou departament trebuie introdusa denumirea departamentului!");
			accept = false;
		}
	if(accept==true)
		{
			document.form.submit();
		}
}
	</script>
	</body>
	</html>