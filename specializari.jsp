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
<title>Administrator - Specializari</title>
</head>
<body>
<div class="wrap">
    <div class="meniu"> 
    <a href="departamente.jsp">Departamente</a> 
    <a class="active" href="specializari.jsp">Specializari</a> 
    <a href="ani_universitari.jsp">Ani Universitari</a> 
    <a href="grupe.jsp">Grupe</a>
    <a href="discipline.jsp">Discipline</a>
    <a href="studenti.jsp">Studenti</a>
    <a href="profesori.jsp">Profesori</a> 
    <a href="conturi.jsp">Conturi</a>
    <a href="PaginaPrincipala.jsp">Delogare</a>
    </div>
    
    <div class="continut" align="center">
    <form id="form" name="form" method="post" action="UpdateServletSpec">
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
 		<legend>Specializari disponibile</legend>
	    <table>
	    	<tr>
	<!--     		<td>Id Specializare</td> -->
	    		<td style="font-size:20px;"><b>Denumire Specializare</b></td>
	    		<td style="font-size:20px;"><b>Forma de invatamant</b></td>
	    		<td style="font-size:20px;"><b>Modifica</b></td>
	<!-- 			<td>Sterge</td> -->
	    	</tr>
	    	<%int i=1;List<Specializare> listaSpecializari=PrelucrariDB.returnSpecializari(); %>
	    	<%for(Specializare specializare: listaSpecializari){ %>
	    	<tr id="spec<%=i%>">
	<%--  			<td><input type="text" name="cod_specializare" id="cod_specializare" value="<%=specializare.getCod_specializare() %>" disabled/></td> --%>
	 			<td><input type="text" name="denumire_specializare" id="denumire_specializare" value="<%=specializare.getDenumire_specializare() %>" size="50" style="font-size:20px;" disabled/></td>   
	    		<td><input type="text" name="forma_invatamant" id="forma_invatamant" value="<%=specializare.getForma_invatamant() %>" size="10" style="font-size:20px;" disabled/></td>
	    		<input type="hidden" name="cod_spec" id="cod_spec"/>
				<td align="center"><button type="button" name="modifica" id="modifica" value="<%=specializare.getCod_specializare() %>" onclick="updateFunction(this,<%=i%>)" style="font-size:20px;">Modifica</button></td>
	<%-- 			<td><button type="submit" name="sterge" id="sterge" value="<%=specializare.getCod_specializare() %>" onclick="return deleteFunction()"><img src="C:\eclipse\Workspace\remove-icon.png" alt="sterge" height="20" width="20"/></button></td> --%>
	    	</tr>
	    	<%i++;}%>
	    </table>
    </fieldset>
    
		<button type="button" id="inapoi" onclick="location.href = 'admin.jsp';" >Inapoi</button>
		<button type="button" id="adauga" onclick="showFunctionAdd()">Adauga</button>
		<br><br><br>
		
	
	<div id="adaugareSpecializare" style="display: none;">
	<fieldset>
 		<legend>Adaugare specializare</legend>	
			<form id="adaugaSpec" method="post" action="UpdateServletSpec">
				<table>
					<tr>
						<td colspan="2" style="font-size:20px;">Adauga o noua specializare</td>
					</tr>
					<tr>
						<td style="font-size:20px;">Denumirea specializarii</td>
						<td><input type="text" name="denumire_spec" id="denumire_spec" style="font-size:20px;"/></td>
					</tr>
					<tr>
						<td style="font-size:20px;">Forma de invatamant</td>
						<td><input type="radio" name="f_invatamant" id="f_invatamant" value="licenta"><label style="font-size:20px;">Licenta</label>
						<input type="radio" name="f_invatamant" id="f_invatamant" value="master"><label style="font-size:20px;">Master</label></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><button type="submit" name="adaugaSubmit" id="adaugaSubmit" style="font-size:16px;">Adauga specializarea</button></td>
					</tr>
				</table>
			</form>
	</fieldset>
	</div>
	
	
    </form>
</div>  
</div>

<script>
function showFunctionAdd() {
    var x = document.getElementById("adaugareSpecializare");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
var countClicks = 0;
function updateFunction(btn,i) {
	var spec=document.getElementById("spec"+i);
	var den_spec=spec.querySelector("#denumire_specializare");
	if(countClicks==0)
		{
			den_spec.disabled=!den_spec.disabled;
			countClicks++;
		}
	else
		{
			document.getElementById("denumire_specializare").disabled = true;
			document.getElementById("cod_spec").value=btn.value;
			document.form.submit();
		}
	btn.value=cod_spec.value;
	countClicks++;
}
function deleteFunction() {
    var r = confirm("Confirmi stergerea specializarii?");
    var cod= document.getElementById(cod_specializare).value;
    if (r == true) {
        alert("S-a sters specializarea!");
        document.getElementById(confirm_box).value="true";
        return true;
    } else {
        alert("Nu se va sterge specializarea!");
        document.getElementById(confirm_box).value="false";
        return false;
    }
}
</script>
</body>
</html>