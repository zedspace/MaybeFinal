<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="database.PrelucrariDB" %>
<%@ page import="claseResurse.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css\profesorStyle.css">
<title>Ponderi Stabilite</title>
</head>
<header style="background-color: #2eb8b8;">

<div class="header">
	<p><%
		int idUser=Integer.parseInt(request.getSession().getValue("idUser").toString());
		Profesor profesor=PrelucrariDB.returnProfesorInfo(idUser);
		%>
		<%=profesor.getTitulatura()+" "+profesor.getNume()+" "+profesor.getPrenume() %>
	</p>
		<p>
		Anul universitar in curs: <%=PrelucrariDB.returnAnActual().getDenumire_an_universitar()%>
		<br>
		Semestrul: <%=PrelucrariDB.returnAnActual().getSemestrul() %>
	</p>
</div>

<form id="form" method="POST" action="PondereServlet">
<ul>
  <li><a href="profesor.jsp">Home</a></li>
  <div class="dropdown">
    <button class="dropbtn">Stabilire ponderi <i class="fa fa-caret-down"></i></button>
    <div class="dropdown-content">
	    <%List<Preda> listaPredare=PrelucrariDB.returnPreda(idUser); %>
		<%List<Specializare> predareSpecializare=PrelucrariDB.predareSpecializare(listaPredare); %>
		<%for(Specializare predareSpec: predareSpecializare){ %>
			 <a href="PondereServlet?cod=<%=predareSpec.getCod_specializare()%>"><%=predareSpec.getDenumire_specializare()%></a>
<!-- 			 <input type="hidden" name="cod" id="cod" value="0"/> -->
			 <%request.getSession().putValue("spec",predareSpec.getCod_specializare());%>
		<%}%>
    </div>
  </div> 
  <div class="dropdown">
    <button class="dropbtn">Notare Studenti <i class="fa fa-caret-down"></i></button>
    <div class="dropdown-content">
		<%for(Specializare predareSpec: predareSpecializare){ %>
			 <a href="NotareServlet?cod=<%=predareSpec.getCod_specializare()%>"><%=predareSpec.getDenumire_specializare()%></a>
		<%}%>
    </div>
  </div> 
  
  <div class="dropdown">
    <a href="PaginaPrincipala.jsp"> <button name="deconectare" id="deconectare" type="submit" class="dropbtn" style="background-color: #ffbf80;">Deconectare <i class="fa fa-caret-down"></i></button></a>
    </div>
</ul>
</form>
</header>

<body>
<%int codSpec=Integer.parseInt(request.getSession().getValue("codSpec").toString()); %>
<h4>Stabilirea ponderilor pentru specializarea <%=PrelucrariDB.returnSpecializare(codSpec).getDenumire_specializare() %></h4>

 <%if(request.getAttribute("inexistent")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("inexistent")%>
		</div>
	<%}%>
	
	<%if(request.getAttribute("invalid")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("invalid")%>
		</div>
	<%}%>
	
	<%if(request.getAttribute("anNecompletat")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("anNecompletat")%>
		</div>
	<%}%>
	
	<%if(request.getAttribute("complet")!=null){ %>
	    <div class="alert">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("complet")%>
		</div>
	<%}%>
	
	<%if(request.getAttribute("complet")==null){ %>
		<%if(request.getAttribute("incomplet")!=null){ %>
		    <div class="alert">
				<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
			    <strong>Atentie!</strong><%=request.getAttribute("incomplet")%>
			</div>
	<%}}%>
<form action="PondereServlet" method="post">
<fieldset>
    	<legend>Selectarea anul de studiu</legend>
	<table>
		<tr><td>
		<input type="radio" id="an_studiu" name="an_studiu" value="1">1
		<input type="radio" id="an_studiu" name="an_studiu" value="2">2
		<input type="radio" id="an_studiu" name="an_studiu" value="3">3
		</td></tr>
		<tr>
		<td><button id="selectareAn" name="selectareAn" type="submit" onclick="showFunctionAdd()">Afisare discipline disponibile</button></td>
		</tr>
	</table>
</fieldset>

<%if(request.getAttribute("listaDiscipline")!=null)
	{%>
		<fieldset>
    	<legend>Selectarea anul de studiu</legend>
    		<table style="border: 1px solid black;border-collapse: collapse;">
		<tr style="border: 1px solid black;border-collapse: collapse;">
			<td style="border-right:1px solid black;">Denumirea Disciplinei</td>
			<td colspan="4">Tipul Disciplinei</td>
			<td>Adauga Ponderea</td>
		</tr>
	<%
		Set<String> listaDiscipline=(Set<String>)request.getAttribute("listaDiscipline");
		SortedSet<String> tipuriDisc=new TreeSet();
		List<Grupa> listaGrupePondere=(List<Grupa>)request.getSession().getValue("listaGrupePondere");
		for(String disciplina:listaDiscipline)
		{ %> <tr style="border: 1px solid black;border-collapse: collapse;"> 
<%-- 					<td><%=PrelucrariDB.returnGrupa(disciplina.getId_disciplina()).getNumar_grupa()%></td> --%>
					<td rowspan="2" style="border-right:1px solid black;"><%=disciplina %></td>
<%-- 			 		<td><%=disciplina.getTip_disciplina() %></td>  --%>
			<% tipuriDisc=PrelucrariDB.returnTipuriDisc(PrelucrariDB.returnCodDisc(disciplina));
				for(String tip:tipuriDisc)
				{
					%>	
						<td ><%=tip%></td>
					<% 
				}
				
			%> <tr style="border: 1px solid black;border-collapse: collapse;" ><% 
				
				for(String tip:tipuriDisc)
				{
					%>	
						<td><input type="text" name="<%=tip+PrelucrariDB.returnCodDisc(disciplina) %>" id="<%=tip+PrelucrariDB.returnCodDisc(disciplina) %>" value="<%=PrelucrariDB.returnPondere(PrelucrariDB.returnIdDisciplina(PrelucrariDB.returnCodDisc(disciplina), tip), PrelucrariDB.returnSpecializare(codSpec).getCod_specializare()).getPondere() %>" placeholder="Pondere <%=tip%>" style="font-size:20px;"/> </td>
					<% 
					
				}
				if(tipuriDisc.size()==0)
					for(int i=0;i<4;i++)
						%><td>  </td><%
						else if(tipuriDisc.size()==1)
							for(int i=0;i<3;i++)
								%><td>  </td><%
								else if(tipuriDisc.size()==2)
									for(int i=0;i<2;i++)
										%><td>  </td><%
										else if(tipuriDisc.size()==3)
											{
												%><td>  </td><%
											}
			%> 
			    <td><button type="Submit" value="<%=PrelucrariDB.returnCodDisc(disciplina) %>" id="pondere" name="pondere" style="font-size:20px;">Confirma</button></td>
			    </tr>
	<%}}%>
</table>
</fieldset>


<div id="adaugarePondere" style="display: none;">
	<table>
		<td>
			
		</td>
	</table>
</
</div>
</form>
<!-- <table> -->
<!-- <tr> -->
<!-- 			<td>Grupa</td> -->
<!-- 			<td>Disciplina</td> -->
<!-- 			<td>Tipul Disciplinei</td> -->
<!-- 			<td>Pondere</td> -->
<!-- 		</tr> -->
<%-- <%List<Pondere> listaPondere=PrelucrariDB.returnPondere(); %> --%>
<%-- <%List<AfisarePondere> ponderiStabilite=PrelucrariDB.afisarePondere(listaPondere); %> --%>
<%-- 	<%for(AfisarePondere pondere: ponderiStabilite){ %> --%>
<!-- 		<tr> -->
<%-- 			<td><%=pondere.getGrupa()%></td> --%>
<%-- 			<td><%=pondere.getNumeDisciplina()%></td> --%>
<%-- 			<td><%=pondere.getTipDisciplina()%></td> --%>
<%-- 			<td><input type="text" name="pondere" id="pondere" value="<%=pondere.getPondere()%>"/></td> --%>
<!-- 		</tr> -->
<%-- 	<%}%> --%>
<!-- </table> -->
<script>
function showFunctionAdd() {
    var x = document.getElementById("adaugarePondere");
    if (x.style.display === "none") {
        x.style.display = "block";
    } 
}
</script>	
</body>
</html>