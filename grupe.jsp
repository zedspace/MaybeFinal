<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<!DOCTYPE html SYSTEM "about:legacy-compat">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css\adminStyle.css">
	<title>Administrator - Grupe</title>
</head>
<body>
<div class="wrap">
    <div class="meniu"> 
    <a href="departamente.jsp">Departamente</a> 
    <a href="specializari.jsp">Specializari</a> 
    <a href="ani_universitari.jsp">Ani Universitari</a> 
    <a class="active" href="grupe.jsp">Grupe</a>
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
		    <strong><%=request.getAttribute("invalid")%></strong>
		</div>
	<%}%>
	
	<form id="form" name="form" method="post" action="UpdateServletGrupa">
	<fieldset>
 		<legend>Prelucrari Grupe</legend>
	    <button type="button" id="vizualizare_total" name="vizualizare_total" onclick="showFunction('toate_grupele','adaugareGrupa','afisareFiltru','rezultatFiltru')">Vizualizeaza toate grupele</button>
	    <button type="button" id="vizualizare_filtru" name="vizualizare_filtru" onclick="showFunctionFilter('afisareFiltru','adaugareGrupa','toate_grupele','rezultatFiltru')">Vizualizeaza grupele - Filtru</button>
	    <button type="button" id="adaugare_grupa" name="adaugare_grupa" onclick="showFunction('adaugareGrupa','afisareFiltru','toate_grupele','rezultatFiltru')">Adauga o grupa</button>
    </fieldset>
    
    <div id="toate_grupele" style="display: none;">
    <fieldset>
 		<legend>Vizualizare grupe</legend>
		    <table >
		    	<tr>
		    		<td>Specializarea</td>
		    		<td>Program Studiu</td>
		    		<td>An studiu</td>
		    		<td>Numar Grupa</td>
		    		<td>Numar Studenti</td>
		    	</tr>
		    	<tr>
		    	<%List<Grupa> listaGrupe=PrelucrariDB.returnGrupeSpec(); %>
		    	<%for(Grupa grupa: listaGrupe){ %>
		    		<td><input type="text" name="den_specializare" id="den_specializare" value="<%=PrelucrariDB.returnSpecializare(grupa.getSpecializare_cod_specializare()).getDenumire_specializare() %>" disabled size="45"/></td>
		 			<td><input type="text" name="prog_studii" id="prog_studii" value="<%=PrelucrariDB.returnSpecializare(grupa.getSpecializare_cod_specializare()).getProgram_studii() %>" disabled size="5"/></td>
		 			<td><input type="text" name="an_studiu" id="an_studiu" value="<%=grupa.getAn_studiu() %>" disabled size="3"/></td> 
		 			<td><input type="text" name="nr_grupa" id="nr_grupa" value="<%=grupa.getNumar_grupa() %>" disabled size="3"/></td>   
		 			<td><input type="text" name="nr_studenti" id="nr_studenti" value="<%=grupa.getNumar_studenti() %>" disabled size="3"/></td>
		 			
		    	</tr>
		    	<%}%>
		    </table>   	
		 </fieldset>
    </div>
    
   <div id="adaugareGrupa" style="display: none;">
   <fieldset>
 		<legend>Adaugare Grupa</legend>
			<table>
				<tr>
				<td><label>Selecteaza specializarea : </label></td>
				<td>
				<%List<Specializare> listaSpec= PrelucrariDB.returnSpecializari(); %>
					<select name="specializare_add" id="specializare_add" style="font-size:20px;">
					<%for(Specializare specializare: listaSpec){ %>
					<option value = "<%=specializare.getCod_specializare()%>"><%=(specializare.getDenumire_specializare())%> &rarr; <%=(specializare.getProgram_studii())%></option>
					<%}%>
					</select>
				</td>
				</tr>
				<tr>
				<td><label>Selecteaza anul de studiu</label></td>
				<td>
					<input type="radio" id="an_studiu_add1" name="an_studiu_add" value="1">1
					<input type="radio" id="an_studiu_add2" name="an_studiu_add" value="2">2
					<input type="radio" id="an_studiu_add3" name="an_studiu_add" value="3">3
				</td>
				</tr>
				<tr>
				<td><label>Introdu numarul grupei</label></td>
				<td>
					<input type="text" id="numar_grupa_add" name="numar_grupa_add" onkeypress="return isNumberKey(event)" >
				</td>
				</tr>
				<tr>
					<td><label>Introdu numarul de studenti</label></td>
				<td>
					<input type="text" id="numar_studenti_add" name="numar_studenti_add" onkeypress="return isNumberKey(event)" >
				</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button type="button" name="adaugaBtn" id="adaugaBtn" onclick="showFunctionAdd()" >Adauga grupa</button></td>
				</tr>
			</table>
		</fieldset>
	</div>
	
	<div id="afisareFiltru" style="display: none;">
		<fieldset>
 		<legend>Vizualizare filtrata a grupelor</legend>
			<table>
				<tr>
				<td><label>Selecteaza specializarea : </label></td>
				<td>
				<%List<Specializare> listaSpecializari= PrelucrariDB.returnSpecializari(); %>
					<select name="specializare" id="specializare" style="font-size:20px;">
					<%for(Specializare specializare: listaSpecializari){ %>
					<option value = "<%=specializare.getCod_specializare()%>"> 
					<%=(specializare.getDenumire_specializare())%> &rarr; <%=(specializare.getProgram_studii())%>
					</option>
					<%}%>
					</select>
				</td>
				</tr>
				<tr>
				<td><label>Selecteaza anul de studiu</label></td>
				<td>
					<input type="radio" id="an_studiu_filtru1" name="an_studiu_filtru" value="1">1
					<input type="radio" id="an_studiu_filtru2" name="an_studiu_filtru" value="2">2
					<input type="radio" id="an_studiu_filtru3" name="an_studiu_filtru" value="3">3
				</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button type="button" name="vizualizareBtn" id="vizualizareBtn" onclick="verificareCampuri()">Vizualizeaza grupele</button></td>
				</tr>
			</table>
		</fieldset>
		</div>
		
		
		<div id="rezultatFiltru" style="display: block;">
		<%List<Grupa> listaRezultat=(ArrayList<Grupa>)request.getAttribute("listaRezultat");%>
   		<%if(listaRezultat!=null) { %>
	    <fieldset>
	 		<legend>Rezultat - Vizualizare filtrata a grupelor</legend>
		    <table id="grupe_filtrate">
		    	<tr>
		    		<td>Specializarea</td>
		    		<td>Program Studiu</td>
		    		<td>An studiu</td>
		    		<td>Numar Grupa</td>
		    		<td>Numar Studenti</td>
		    	</tr>
		    	<tr>
		    	<%for(Grupa grupa: listaRezultat){ %>
		    		<td><input type="text" name="den_specializare" id="den_specializare" value="<%=PrelucrariDB.returnSpecializare(grupa.getSpecializare_cod_specializare()).getDenumire_specializare() %>" disabled size="45"/></td>
		    		<td><input type="text" name="prog_studii" id="prog_studii" value="<%=PrelucrariDB.returnSpecializare(grupa.getSpecializare_cod_specializare()).getProgram_studii() %>" disabled size="5"/></td>
	 				<td><input type="text" name="an_studiu" id="an_studiu" value="<%=grupa.getAn_studiu() %>" disabled size="3"/></td> 
		 			<td><input type="text" name="nr_grupa" id="nr_grupa" value="<%=grupa.getNumar_grupa() %>" disabled size="3"/></td>   
		 			<td><input type="text" name="nr_studenti" id="nr_studenti" value="<%=grupa.getNumar_studenti() %>" disabled size="3"/></td>
		    	</tr>
		    	<%}%>
		    </table>
    <%} else{%>
    <%if(request.getAttribute("invalid")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong><%=request.getAttribute("invalid")%></strong>
		</div>
	<%}%>
    <%} %>
    </fieldset>
    </div>
    </form>
</div>
</div>
<script>
function showFunction(afisat,ascuns1,ascuns2,ascuns3) {
    var x = document.getElementById(afisat);
	var y = document.getElementById(ascuns1);
	var z = document.getElementById(ascuns2);
	var q = document.getElementById(ascuns3);
	z.style.display = "none";
	y.style.display = "none";
	z.style.display = "none";
	q.style.display = "none";
    if (x.style.display == "none") {
        x.style.display = "block";
    } 
  
}
function showFunctionAdd() {
    if(document.getElementById("numar_grupa_add").value!=null && document.getElementById("numar_grupa_add").value!="" && document.getElementById("numar_studenti_add").value!=null && document.getElementById("numar_studenti_add").value!="" && document.getElementById("an_studiu_add1").value!=null && document.getElementById("an_studiu_add1").value!="" && document.getElementById("an_studiu_add2").value!=null && document.getElementById("an_studiu_add2").value!="" && document.getElementById("an_studiu_add3").value!=null && document.getElementById("an_studiu_add3").value!="")
    	document.form.submit();
    else
    	alert("Toate campurile sunt obligatorii!");    	
  
}
function showFunctionFilter(afisat,ascuns1,ascuns2,ascuns3) {
    var x = document.getElementById(afisat);
    var y = document.getElementById(ascuns1);
    var z = document.getElementById(ascuns2);
    var q = document.getElementById(ascuns3);
    y.style.display = "none";
	z.style.display = "none";
	q.style.display = "none";
	if(<%=request.getAttribute("listaRezultat")!=null%>)
		{
		x.style.display = "block";
		}
	else
	    if (x.style.display === "none") {
	        x.style.display = "block";
	    } else {
	        x.style.display = "none";
    }
		
}
function verificareCampuri(){
	var x = document.getElementById("an_studiu_filtru1").checked;
	var y = document.getElementById("an_studiu_filtru2").checked;
	var z = document.getElementById("an_studiu_filtru3").checked;
	if(x==true || y==true || z==true)
		document.form.submit();
	else
		alert("Selectarea unui an de studiu este obligatorie!");
}
function isNumberKey(evt)
{
   var charCode = (evt.which) ? evt.which : event.keyCode
   if (charCode > 31 && (charCode < 48 || charCode > 57))
      return false;
   return true;
}
</script>
</body>
</html>