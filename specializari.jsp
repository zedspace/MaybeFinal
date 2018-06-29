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
	    		<td style="font-size:20px;"><b>Denumire Specializare</b></td>
	    		<td style="font-size:20px;"><b>Program de studii</b></td>
	    		<td style="font-size:20px;"><b>Modifica</b></td>
	    	</tr>
	    	<%int i=1;List<Specializare> listaSpecializari=PrelucrariDB.returnSpecializari(); %>
	    	<%for(Specializare specializare: listaSpecializari){ %>
	    	<tr id="spec<%=i%>">
	    		<input type="hidden" name="cod_specializare" id="cod_specializare" value="<%=specializare.getCod_specializare() %>"/>
	 			<td><input type="text" name="denumire_specializare" id="denumire_specializare" value="<%=specializare.getDenumire_specializare() %>" size="50" style="font-size:20px;"  onchange="changeFunction(this,<%=i%>)" disabled/></td>   
	    		<td><input type="text" name="program_studii" id="program_studii" value="<%=specializare.getProgram_studii()%>" size="10" style="font-size:20px;" disabled/></td>
				<td align="center"><button type="button" name="modifica" id="modifica" value="<%=specializare.getCod_specializare() %>" onclick="updateFunction(this,<%=i%>)" style="font-size:20px;">Modifica</button></td>
	    	</tr>
	    	<%i++;}%>
	    </table>
	    <input type="hidden" name="cod_spec" id="cod_spec"/>
	    <input type="hidden" name="den_spec" id="den_spec"/>
    </fieldset>
    
		<button type="button" id="inapoi" onclick="location.href = 'admin.jsp';" >Inapoi</button>
		<button type="button" id="adauga" onclick="showFunctionAdd('adaugareSpecializare')">Adauga</button>
		<br><br><br>
		
	
	<div id="adaugareSpecializare" style="display: none;">
	<fieldset>
 		<legend>Adaugare specializare</legend>	
				<table>
					<tr>
						<td colspan="2" style="font-size:20px;">Adauga o noua specializare</td>
					</tr>
					<tr>
						<td style="font-size:20px;">Denumirea specializarii</td>
						<td><input type="text" name="denumire_spec" id="denumire_spec" style="font-size:20px;"/></td>
					</tr>
					<tr>
						<td style="font-size:20px;">Program de studii</td>
						<td><input type="radio" name="p_studii" id="p_studiil" value="Licenta"><label style="font-size:20px;">Licenta</label>
						<input type="radio" name="p_studii" id="p_studiim" value="Master"><label style="font-size:20px;">Master</label></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><button type="button" name="adaugaSubmit" id="adaugaSubmit" style="font-size:16px;" onclick="myFunction()">Adauga specializarea</button></td>
					</tr>
				</table>
	</fieldset>
	</div>
    </form>
</div>  
</div>

<script>
function showFunctionAdd(numeRegiune) {
    var x = document.getElementById(numeRegiune);
    if (x.style.display === "none") {
        x.style.display = "block";
    }
}
var countClicks = 0;
function updateFunction(btn,i) {
	var spec=document.getElementById("spec"+i);
	var den_spec=spec.querySelector("#denumire_specializare");
	var cod_spec=spec.querySelector("#cod_specializare");
	if(countClicks==0)
		{
			den_spec.disabled=!den_spec.disabled;
		}
	else
		{
			document.getElementById("denumire_specializare").disabled = true;
			document.getElementById("cod_spec").value=cod_spec.value;
			document.forms["form"].submit();
		}
	btn.value=cod_spec.value;
	countClicks++;
}
function changeFunction(input,i) {
	var spec=document.getElementById("spec"+i);
	var den_spec=spec.querySelector("#denumire_specializare");
	den_spec.value=input.value;
	document.getElementById("den_spec").value=input.value;
}
function myFunction(){
	var denumire_adaugare=document.getElementById("denumire_spec").value;
	var program_stud_l=document.getElementById("p_studiil").checked;
	var program_stud_m=document.getElementById("p_studiim").checked;
	if(denumire_adaugare!=null && denumire_adaugare!='' && (program_stud_l!=false || program_stud_m!=false)){
		if (confirm('Confirmati adaugarea noii specializari: '+document.getElementById("denumire_spec").value+'?'))
		{	
			accept = true;
		}
		else
			accept = false;
	}
	else
		{
			alert("Pentru adugarea unei noi specializari trebuie introdusa denumirea specializarii si trebuie selectat programul de studii!");
			accept = false;
		}
	if(accept==true)
		{
			document.getElementById("adaugaSubmit").value=1;
			document.form.submit();
		}
}
</script>
</body>
</html>