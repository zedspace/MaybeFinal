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
<title>Administrator</title>
</head>
<body>
<nav>
</nav>
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
    <a href="PaginaPrincipala.jsp">Delogare</a>
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
	
	<fieldset>
 		<legend>Prelucrari Grupe</legend>
    <button type="button" id="vizualizare_total" name="vizualizare_total" onclick="showFunctionAll()">Vizualizeaza toate grupele</button>
    <button type="button" id="vizualizare_filtru" name="vizualizare_filtru" onclick="showFunctionFilter()">Vizualizeaza grupele - Filtru</button>
    <button type="button" id="adaugare_grupa" name="adaugare_grupa" onclick="showFunctionAdd()">Adauga o grupa</button>
    </fieldset>
    
    <div id="toate_grupele" style="display: none;">
    <fieldset>
 		<legend>Vizualizare grupe</legend>
		    <table >
		    	<tr>
		    		<td>Id Grupa</td>
		    		<td>Numar Grupa</td>
		    		<td>An studiu</td>
		    		<td>Numar Studenti</td>
		    		<td>Specializarea</td>
		    	</tr>
		    	<tr>
		    	<%List<Grupa> listaGrupe=PrelucrariDB.returnGrupe(); %>
		    	<%for(Grupa grupa: listaGrupe){ %>
		 			<td><input type="text" name="id_grupa" id="id_grupa" value="<%=grupa.getId_grupa() %>" disabled size="3"/></td>
		 			<td><input type="text" name="nr_grupa" id="nr_grupa" value="<%=grupa.getNumar_grupa() %>" disabled size="3"/></td>   
		 			<td><input type="text" name="an_studiu" id="an_studiu" value="<%=grupa.getAn_studiu() %>" disabled size="3"/></td> 
		 			<td><input type="text" name="nr_studenti" id="nr_studenti" value="<%=grupa.getNumar_studenti() %>" disabled size="3"/></td>
		 			<td><input type="text" name="den_specializare" id="den_specializare" value="<%=PrelucrariDB.returnSpecializare(grupa.getSpecializare_cod_specializare()).getDenumire_specializare() %>" disabled size="45"/></td>
		    	</tr>
		    	<%}%>
		    </table>   	
		 </fieldset>
    </div>
    
   <div id="adaugareGrupa" style="display: none;">
   <fieldset>
 		<legend>Adaugare Grupa</legend>
			<form id="adaugaGrupa" method="post" action="UpdateServletGrupa">
			<table>
				<tr>
				<td><label>Selecteaza specializarea : </label></td>
				<td>
				<%List<Specializare> listaSpec= PrelucrariDB.returnSpecializari(); %>
					<select name="specializare_add" id="specializare_add" style="font-size:20px;">
					<%for(Specializare specializare: listaSpec){ %>
					<option value = "<%=specializare.getCod_specializare()%>"> 
					<%=(specializare.getDenumire_specializare())%>
					</option>
					<%}%>
					</select>
				</td>
				</tr>
				<tr>
				<td><label>Selecteaza anul de studiu</label></td>
				<td>
				<input type="radio" id="an_studiu_add" name="an_studiu_add" value="1">1
				<input type="radio" id="an_studiu_add" name="an_studiu_add" value="2">2
				<input type="radio" id="an_studiu_add" name="an_studiu_add" value="3">3
				</td>
				</tr>
				<tr>
				<td><label>Introdu numarul grupei</label></td>
				<td>
				<input type="text" id="numar_grupa_add" name="numar_grupa_add">
				</td>
				</tr>
				<tr>
				<td><label>Introdu numarul de studenti</label></td>
				<td>
				<input type="text" id="numar_studenti_add" name="numar_studenti_add">
				</td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button type="submit" name="adaugaBtn" id="adaugaBtn" >Adauga grupa</button></td>
				</tr>
			</table>
			</form>
		</fieldset>
	</div>
	
	<div id="afisareFiltru" style="display: none;">
		<fieldset>
 		<legend>Vizualizare filtrata a grupelor</legend>
			<form id="adaugaGrupa" method="post" action="UpdateServletGrupa">
			<table>
				<tr>
				<td><label>Selecteaza specializarea : </label></td>
				<td>
				<%List<Specializare> listaSpecializari= PrelucrariDB.returnSpecializari(); %>
					<select name="specializare" id="specializare" style="font-size:20px;">
					<%for(Specializare specializare: listaSpecializari){ %>
					<option value = "<%=specializare.getCod_specializare()%>"> 
					<%=(specializare.getDenumire_specializare())%>
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
					<td colspan="2" align="center"><button type="submit" name="vizualizareBtn" id="vizualizareBtn" >Vizualizeaza grupele</button></td>
				</tr>
			</table>
			</form>
		</fieldset>
		</div>
		
		<%List<Grupa> listaRezultat=(ArrayList<Grupa>)request.getAttribute("listaRezultat");%>
   		 <%if(listaRezultat!=null) { %>
    <fieldset>
 		<legend>Rezultat - Vizualizare filtrata a grupelor</legend>
    <table id="grupe_filtrate">
    	<tr>
    		<td>Id Grupa</td>
    		<td>Numar Grupa</td>
    		<td>An studiu</td>
    		<td>Numar Studenti</td>
    		<td>Specializarea</td>
    	</tr>
    	<tr>
    	<%for(Grupa grupa: listaRezultat){ %>
 			<td><input type="text" name="id_grupa" id="id_grupa" value="<%=grupa.getId_grupa() %>" disabled size="3"/></td>
 			<td><input type="text" name="nr_grupa" id="nr_grupa" value="<%=grupa.getNumar_grupa() %>" disabled size="3"/></td>   
 			<td><input type="text" name="an_studiu" id="an_studiu" value="<%=grupa.getAn_studiu() %>" disabled size="3"/></td> 
 			<td><input type="text" name="nr_studenti" id="nr_studenti" value="<%=grupa.getNumar_studenti() %>" disabled size="3"/></td>
 			<td><input type="text" name="den_specializare" id="den_specializare" value="<%=PrelucrariDB.returnSpecializare(grupa.getSpecializare_cod_specializare()).getDenumire_specializare() %>" disabled size="45"/></td>
    	</tr>
    	<%}%>
    </table>
    
    <%}%>
    </fieldset>
</div>
</div>
<script>
function showFunctionAll() {
    var x = document.getElementById("toate_grupele");
//     var y = document.getElementById("adaugareGrupa");
//     var z = document.getElementById("afisareFiltru");
//     var q = document.getElementById("grupe_filtrate");
//     q.style.display = "none";
//     z.style.display = "none";
// 	y.style.display = "none";	
	  if (x.style.display === "none") {
	        x.style.display = "block";
	    } else {
	        x.style.display = "none";
	    }
    
}
function showFunctionAdd() {
    var x = document.getElementById("adaugareGrupa");
//     var y = document.getElementById("toate_grupele");
//     var z = document.getElementById("afisareFiltru");
//     var q = document.getElementById("grupe_filtrate");
// //     q.style.display = "none";
//     z.style.display = "none";
//     y.style.display = "none";
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
  
}
function showFunctionFilter() {
    var x = document.getElementById("afisareFiltru");
    var y = document.getElementById("adaugareGrupa");
    var z = document.getElementById("toate_grupele");
    y.style.display = "none";
	z.style.display = "none";
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
function showFiltredGroup(){
	var x = document.getElementById("vizualizare");
    var y = document.getElementById("adaugareGrupa");
    var z = document.getElementById("toate_grupele");
    y.style.display = "none";
	z.style.display = "none";
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
</script>
</body>
</html>