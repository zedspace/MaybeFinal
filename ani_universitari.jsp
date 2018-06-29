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
<title>Administrator - Ani Universitari</title>
</head>
<body>
<nav>
</nav>
<div class="wrap">
    <div class="meniu"> 
    <a href="departamente.jsp">Departamente</a> 
    <a href="specializari.jsp">Specializari</a> 
    <a class="active" href="ani_universitari.jsp">Ani Universitari</a> 
    <a href="grupe.jsp">Grupe</a>
    <a href="discipline.jsp">Discipline</a>
    <a href="studenti.jsp">Studenti</a>
    <a href="profesori.jsp">Profesori</a> 
    <a href="conturi.jsp">Conturi</a>
    <a href="PaginaPrincipala.jsp">Deconectare</a>
    </div>
    
    <div class="continut" align="center">
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
		    <strong>Atentie!</strong><%=request.getAttribute("invalid")%>
		</div>
	<%}%>
	
	<form name="form" method="post" action="UpdateServletAn">
	<fieldset>
 		<legend>Ani universitari disponibili</legend>
    <table>
    	<tr>
    		<td>Id An</td>
    		<td>Denumire an</td>
    		<td>Semestrul</td>
    	</tr>
    	<tr>
    	<%List<AnUniversitar> listaAni=PrelucrariDB.returnAni(); %>
    	<%for(AnUniversitar an: listaAni){ %>
 			<td><input type="text" name="id_an" id="id_an" value="<%=an.getId_an_universitar() %>" disabled/></td>
 			<td><input type="text" name="denumire_an" id="denumire_an" value="<%=an.getDenumire_an_universitar() %>" disabled/></td>   
 			<td><input type="text" name="semestrul" id="semestrul" value="<%=an.getSemestrul() %>" disabled/></td> 
    	</tr>
    	<%}%> 	
	</table>  

	</fieldset> 
	
	<button type="button" onclick="location.href = 'admin.jsp';">Inapoi</button>
	<button type="button" onclick="showFunctionAdd()">Adauga un an universitar</button>
	<br><br><br>
	
	<div id="adaugareAnUniv" style="display: none;">
		<fieldset>
 		<legend>Adauga un an universitar</legend>
			
				<table>
					<tr>
						<td colspan="2">Adauga un an universitar</td>
					</tr>
					<tr>
						<td>Denumirea anului</td>
						<td><input type="text" name="den_an" id="den_an"/></td>
					</tr>
					<tr>
						<td>Semestrul</td>
						<td><input type="text" name="sem" id="sem"/></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><button type="button" name="adaugaSubmit" id="adaugaSubmit"  onclick="myFunction()">Adauga anul</button></td>
					</tr>
				</table>
			
		</fieldset>
	</div>
    </form>
</div>  
</div>

<script>
function showFunctionAdd() {
    var x = document.getElementById("adaugareAnUniv");
    if (x.style.display === "none") {
        x.style.display = "block";
    } 
}
function myFunction(){
	var denumire_adaugare=document.getElementById("den_an").value;
	var semestrul=document.getElementById("sem").value;
	if(denumire_adaugare!=null && denumire_adaugare!='' && semestrul!=null && semestrul!=""){
		if (confirm('Confirmati adaugarea noului an universitar: '+document.getElementById("den_an").value+" sem "+document.getElementById("sem").value+'?'))
		{	
			accept = true;
		}
		else
			accept = false;
	}
	else
		{
			alert("Pentru adugarea unui nou an universitar trebuie completate campurile 'Denumirea anului' si 'Semestrul'!");
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