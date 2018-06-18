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
    <a href="PaginaPrincipala.jsp">Delogare</a>
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
			<!-- 	<td style="font-size:20px;"><b>Codul departamentului</b></td> -->
					<td><b>Denumire departament</b></td>
					<td><b>Modifica</b></td>
			<!-- 	<td>Sterge</td> -->
						
			</tr>
			<%int i=1;List<Departament> listaDepartamente=PrelucrariDB.returnDepartamente(); %>
				<%for(Departament departament: listaDepartamente){ %>
					<tr id="dep<%=i%>">
						<input type="hidden" name="cod_departament" id="cod_departament" value="<%=departament.getCod_departament() %>"/>
						<td>
							<input type="text" name="denumire_departament" id="denumire_departament" value="<%=departament.getDenumire_departament()%>" size="30"  disabled/>
						</td>
						<td align="center">
							<button type="button" name="modifica" id="modifica" value="<%=departament.getCod_departament() %>" onclick="updateFunction(this,<%=i%>)">Modifica</button>
						</td>
			<%-- 		<td><button type="submit" name="sterge" id="sterge" value="<%=departament.getCod_departament() %>" onclick="return deleteFunction()"><img src="C:\eclipse\Workspace\remove-icon.png" alt="sterge" height="20" width="20"/></button></td> --%>
					</tr>
				<%i++;}%>
		</table>
		<input type="hidden" name="cod_dept" id="cod_dept"/>
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
	
	</form> 
</div>

<script>
function showFunctionAdd(numeRegiune) {
    var x = document.getElementById(numeRegiune);
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
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
			cod_dep.disabled=cod_dep.disabled;
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
function myFunction(){
	if (confirm('Confirmati adaugarea noului departament: '+document.getElementById("denumire_dep").value+'?'))
	{	
		accept = true;
	}
	else
		accept = false;
	if(accept==true)
		{
			document.form.submit();
		}
	else
		{
			alert("Nu s-a adaugat departamentul"+document.getElementById("denumire_dep").value);
		}
}
</script>
</body>
</html>