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
	<title>Administrator - Profesori</title>
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
    <a class="active" href="profesori.jsp">Profesori</a> 
    <a href="conturi.jsp">Conturi</a>
    <a href="PaginaPrincipala.jsp">Deconectare</a>
</div>

   	 <div class="continut" align="center"> 
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
    
    <form  name="form" action="ProfesorServlet">
    <fieldset style="width: 800px;">
 		<legend>Cautare - Profesor</legend>
	    <table align="center">
	<!--     START---Cautare dupa nume sau prenume -->
	    	<tr>
	    	<td width="50%" style="font-size:20px;"><b>Cauta profesor (nume/prenume) :</b></td>
	    	<td width="40%"><input type="text" name="nume" id="nume" style="font-size:20px;"/></td>
	    	<td width="10%"><button type="submit" name="cauta" value="cauta" style="font-size:20px;">Cauta</button></td>
	    	</tr>
	    </table>
	    </fieldset>
    
    <div id="rezultat" name="rezultat">
    <%List<Profesor> listaRezultat=(ArrayList<Profesor>)request.getAttribute("listaRezultat");%>
    <%if(listaRezultat!=null) {	%> 
    <fieldset style="width: 800px;">
 		<legend>Rezultate - Profesorii care corespund criteriilor de cautare</legend>
    <table id="table">
    	<tr>
<!--     		<td>Marca</td> -->
    		<th><b>Grad didactic</b></th>
    		<th><b>Nume</b></th>
    		<th><b>Prenume</b></th>
    		<th><b>Departament</b></th>
    	</tr>
    	<tr>
    	<%for(Profesor profesor: listaRezultat){ %>
<%-- 			<td><%=profesor.getMarca() %></td> --%>
			<td><%=profesor.getTitulatura() %></td>
 			<td><%=profesor.getNume() %></td>   
 			<td><%=profesor.getPrenume() %></td>
 			<td><%=PrelucrariDB.returnDepartamente(profesor.getCod_departament()).getDenumire_departament() %></td>
	</tr>
	<%}%>
	</table>
    <%}%>
    </fieldset>
    </div>
<!-- 	STOP----Cautare dupa nume/prenume -->

<!-- Afisarea tuturor profesorilor -->
<!--     <table> -->
<!--     	<tr> -->
<!--     		<td style="font-size:20px;"><b>Grad didactic</b></td> -->
<!--     		<td style="font-size:20px;"><b>Nume</b></td> -->
<!--     		<td style="font-size:20px;"><b>Prenume</b></td> -->
<!--     		<td style="font-size:20px;"><b>Departament</b></td> -->

<!--     	</tr> -->
<!--     	<tr> -->
<%--     	<%List<Profesor> listaProfesori=PrelucrariDB.returnProfesor(); %> --%>
<%--     	<%for(Profesor profesor: listaProfesori){ %> --%>
<%--  			<td><input type="text" name="titulatura" id="titulatura" value="<%=profesor.getTitulatura() %>" disabled size="10" style="font-size:20px;"/></td> --%>
<%--  			<td><input type="text" name="nume" id="nume" value="<%=profesor.getNume() %>" disabled size="20" style="font-size:20px;"/></td>    --%>
<%--  			<td><input type="text" name="prenume" id="prenume" value="<%=profesor.getPrenume() %>" disabled size="30" style="font-size:20px;"/></td> --%>
<%--  			<td><input type="text" name="departament" id="departament" value="<%=PrelucrariDB.returnDepartamente(profesor.getCod_departament()).getDenumire_departament() %>" disabled size="30" style="font-size:20px;"/></td> --%>
<!--     	</tr> -->
<%--     	<%}%> --%>
<!--     	</table> -->
    	
<!--     	STOP ----Afisarea tuturor profesorilor -->
    	
    	
<!--     	START --- Cautare filtrata -->
		<fieldset style="width: 800px;">
 		<legend>Cautare filtrata - Profesori</legend>
		<table align="center">
			<tr>
	    		<td width="50%" style="font-size:20px;">
	    			<b>Seleteaza gradul didactic : </b>
				</td>	
	   			<td width="40%">
	    			<select name="grad_didactic" id="grad_didactic" style="font-size:20px;">
		    			<option value = ""></option>
						<option value = "Drd.">Drd.</option>
						<option value = "Asist.drd.">Asist.drd.</option>
						<option value = "Asist.dr.">Asist.dr.</option>
						<option value = "Lect.dr.">Lect.dr.</option>
						<option value = "Conf.dr.">Conf.dr.</option>
						<option value = "Prof.dr.">Prof.dr.</option>
					</select>
	    		</td>
	    	</tr>
	    	<tr>
	    		<td width="50%" style="font-size:20px;">
	    			<b>Seleteaza departamentul : </b>
	    		</td>
	    		<td width="40%"> <%List<Departament> listaDepFiltru= PrelucrariDB.returnDepartamente(); %>
					<select name="dep_cautare" id="dep_cautare" style="font-size:20px;">
						<option value=""></option>
						<%for(Departament departament: listaDepFiltru){ %>
							<option value = "<%=departament.getCod_departament()%>"> 
								<%=(departament.getDenumire_departament())%>
							</option>
						<%}%>
					</select> 
				</td>
	    		<td width="10%">
	    			<button type="submit" name="cautaFiltru" value="cautaFiltru" style="font-size:20px;">Cauta</button>
	    		</td>
	    	</tr>
	   </table>
	   </fieldset>
	   
	   	<div id="rezultatFiltrare" name="rezultatFiltrare">
	    <%List<Profesor> listaRezultatFiltrat=(ArrayList<Profesor>)request.getAttribute("listaRezultatFiltrat");%>
	    <%if(listaRezultatFiltrat!=null) {%>  
	    <fieldset style="width: 800px;">
 		<legend>Rezultate - Profesorii care corespund criteriilor de cautare </legend>
	    <table id="table">
	    	<tr>
	<!--     		<td>Marca</td> -->
	    		<th><b>Grad didactic</b></th>
	    		<th><b>Nume</b></th>
	    		<th><b>Prenume</b></th>
	    		<th><b>Departament</b></th>
	    	</tr>
	    	<tr>
	    	<%for(Profesor profesor: listaRezultatFiltrat){ %>
	<%-- 			<td><%=profesor.getMarca() %></td> --%>
				<td><%=profesor.getTitulatura() %></td>
	 			<td><%=profesor.getNume() %></td>   
	 			<td><%=profesor.getPrenume() %></td>
	 			<td><%=PrelucrariDB.returnDepartamente(profesor.getCod_departament()).getDenumire_departament() %></td>
		</tr>
		<%}%>
		<tr></tr>
		</table>
		</fieldset>
	    <%}%>
	    </div>
	
<!--     	STOP --- Cautare filtrata -->
    	
    	<fieldset style="width: 800px;">
 		<legend>Prelucrari - Profesori</legend>
    	<table align="center">
    	<tr>
	    	<td><button type="button" name="adaugaProfesor" onclick="showFunction('adaugareProfesor','rezultat','rezultatFiltrare','profesoriAlocati','alocareProfesor')" style="font-size:20px;">Adauga un profesor</button></td>
	    	<td><button type="button" name="alocareProfesori" onclick="showFunction('alocareProfesor','adaugareProfesor','rezultat','rezultatFiltrare','profesoriAlocati')" style="font-size:20px;">Aloca profesorii la discipline</button></td>
	    	<td><button type="button" name="VizualizareAlocare" onclick="showFunction('profesoriAlocati','alocareProfesor','adaugareProfesor','rezultat','rezultatFiltrare')" style="font-size:20px;">Vizualizare profesori care predau</button></td>
    	</tr>
    	</table>
    	</fieldset>
    	
    <div id="adaugareProfesor" style="display: none;">
    <fieldset style="width: 800px;">
 		<legend>Adaugare profesor</legend>
    <table>
    	<tr>
    	<td style="font-size:20px;"><b>Nume</b></td>
    	<td><input type="text" id="nume_prof" name="nume_prof" size="50" style="font-size:20px;"/></td>
    	</tr>
    	<tr>
    	<td style="font-size:20px;"><b>Prenume</b></td>
    	<td><input type="text" id="prenume_prof" name="prenume_prof" size="50" style="font-size:20px;"/></td>
    	</tr>
    	<tr>
    	<td style="font-size:20px;"><b>Grad didactic</b></td>
    	<td>
    		<select name="titulatura_prof" id="titulatura_prof" style="font-size:20px;">
				<option value = "Drd.">Drd.</option>
				<option value = "Asist.drd.">Asist.drd.</option>
				<option value = "Asist.dr.">Asist.dr.</option>
				<option value = "Lect.dr.">Lect.dr.</option>
				<option value = "Conf.dr.">Conf.dr.</option>
				<option value = "Prof.dr.">Prof.dr.</option>
			</select>
    	</td>
    	</tr>
    	<tr>
    	<td style="font-size:20px;"><b>Departament</b></td>
    	<td>
    		<%List<Departament> listaDep= PrelucrariDB.returnDepartamente(); %>
				<select name="dep_add" id="dep_add" style="font-size:20px;">
				<%for(Departament departament: listaDep){ %>
				<option value = "<%=departament.getCod_departament()%>"> 
				<%=(departament.getDenumire_departament())%>
				</option>
				<%}%>
				</select>
    	</td>
    	</tr>
    	<tr>
    	<td><button type="button" id="adaugaProf" name="adaugaProf" style="font-size:20px;" onclick="verificare()">Adauga Profesorul</button></td>
    	</tr>
    	
    </table>
    </fieldset>
    </div>
    </form>
    
    <div id="alocareProfesor" style="display: none;">
    <fieldset style="width: 800px;">
 		<legend>Alocarea profesorilor la materii</legend>
		<table>
			<tr>
			<td><label>Selecteaza anul universitar : </label></td>
			<td>
				<%List<AnUniversitar> listaAni= PrelucrariDB.returnAni(); %>
				<select name="an_universitar" id="an_universitar" style="font-size:20px;">
				<%for(AnUniversitar an: listaAni){ %>
				<option value = "<%=an.getId_an_universitar()%>"> 
				<%=(an.getDenumire_an_universitar()+" semestrul "+an.getSemestrul())%>
				</option>
				<%}%>
				</select>
			</td>
			</tr>
			<tr>
			<td><label>Selecteaza disciplina : </label></td>
			<td>
			<%List<Disciplina> listaDiscipline= PrelucrariDB.returnDiscipline(); %>
				<select name="disciplina" id="disciplina" style="font-size:20px;">
				<%for(Disciplina disc: listaDiscipline){ %>
				<option value = "<%=disc.getId_disciplina()%>"> 
				<%=(disc.getDenumire_disciplina()+" "+disc.getTip_disciplina())%>
				</option>
				<%}%>
				</select>
			</td>
			</tr>
			<tr>
			<td><label>Selecteaza anul de studiu</label></td>
			<td>
			<input type="radio" id="an_studiu1" name="an_studiu" value="1" style="font-size:20px;">1
			<input type="radio" id="an_studiu2" name="an_studiu" value="2" style="font-size:20px;">2
			<input type="radio" id="an_studiu3" name="an_studiu" value="3" style="font-size:20px;">3
			</td>
			</tr>
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
			<td>Selecteaza grupa</td>
			<td>
			<!-- trebuie preluate valorile slectate la an_studiu si specializare -->
			<input type="hidden" name="radioVal" id="radioVal" value=valoareRadio()/>
			<%	
				List<Grupa> listaGrupe= PrelucrariDB.returnGrupe(1,1); %>
				<select id="grupa" name="grupa" style="font-size:20px;">
				<%for(Grupa grupa: listaGrupe){ %>
				<option value = "<%=grupa.getId_grupa()%>"> 
				<%=(grupa.getNumar_grupa())%>
				</option>
				<%}%>
				</select>
			</td>
			</tr>
			<tr>
			<td><label>Selecteaza profesorul : </label>
			<td>
			<select name="profesor" id="profesor" style="font-size:20px;">
			<%List<Profesor> listaProfesori1= PrelucrariDB.returnProfesor();  %>
				<%for(Profesor profesor:listaProfesori1){ %>
				<option value = "<%=profesor.getMarca()%>"> 
				<%=(profesor.getTitulatura()+" "+profesor.getNume()+" "+profesor.getPrenume())%>
				</option>
				<%}%>
				</select>
				
			</td>
			</tr>
		</table>
		<button type="button" onclick="verificareAlocare()">Confirma</button>
		</fieldset>
		
    </div>
    <div id="profesoriAlocati" style="display: none;">
    	<fieldset style="width: 800px;">
 		<legend>Alocarea profesorilor la discipline si specializari s-a realizat astfel :</legend>
		<table id="table">
		<tr>
					<th><b>Grad didactic</b></th>
					<th><b>Nume</b></th>
					<th><b>Prenume</b></th>
					<th><b>Anul Universitar</b></th>
					<th><b>Specializarea</b></th>
					<th><b>Grupa</b></th>
					<th><b>Disciplina</b></th>
					<th><b>Tipul Disciplinei</b></th>
				</tr>
		<%List<Preda> listaPredare=PrelucrariDB.returnPreda(); %>
		<%List<AfisarePredare> predareProfesori=PrelucrariDB.afisarePredare(listaPredare); %>
			<%for(AfisarePredare predareProfesor: predareProfesori){ %>
				<tr>
					<td><%=predareProfesor.getTitulatura()%></td>
					<td><%=predareProfesor.getNumeProfesor()%></td>
					<td><%=predareProfesor.getPrenumeProfesor()%></td>
					<td><%=predareProfesor.getAn()%></td>
					<td><%=predareProfesor.getSpecializare()%></td>
					<td><%=predareProfesor.getGrupa()%></td>
					<td><%=predareProfesor.getNumeDisciplina()%></td>
					<td><%=predareProfesor.getTipDisciplina()%></td>
				</tr>
			<%}%>
		</table>
		</fieldset>	
    </div>
</div>  
</div>
<script>
function showFunction(camp,ascuns1,ascuns2,ascuns3,ascuns4) {
    var x = document.getElementById(camp);
    var y = document.getElementById(ascuns1);
    var z = document.getElementById(ascuns2);
    var q = document.getElementById(ascuns3);
    var v = document.getElementById(ascuns4);
    y.style.display = "none";
    z.style.display = "none";
    q.style.display = "none";
    v.style.display = "none";
    if (x.style.display === "none") {
        x.style.display = "block";
    } 
}
function verificare(){
	var x=document.getElementById("nume_prof").value;
	var y=document.getElementById("prenume_prof").value;
	if(x==null || x=="" || y==null || y=="")
		alert("Pentru adaugarea unui profesor, completarea campurilor Nume si Prenume este obligatorie!");
	else
		document.form.submit();
}
function verificareAlocare(){
	var x=document.getElementById("an_universitar").value;
	var y=document.getElementById("disciplina").value;
	var a=document.getElementById("an_studiu1").checked;
	var b=document.getElementById("an_studiu2").checked;
	var c=document.getElementById("an_studiu3").checked;
	var z=document.getElementById("specializare").value;
	var d=document.getElementById("grupa").value;
	var e=document.getElementById("profesor").value;
	alert(x+" "+y+" "+a+" "+b+" "+c+" "+z+" "+d+" "+e);
	if(x==null || x=="" || y==null || y=="" || (a==false && b==false && c==false) || z==null || z=="" || d==null || d=="" || e==null || e=="")
		alert("Pentru alocarea unei discipline unui profesor este obligatorie completarea tuturor campurilor!");
	else
		document.form.submit();
	
}
</script>
</body>
</html>