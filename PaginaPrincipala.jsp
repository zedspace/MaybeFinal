<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css\firstPageStyle.css">
<title>Catalogul virtual al studentului</title>
</head>
<body>    
	<img src="logo.gif" height="100" width="500" alt="logo" class="img"/>
	
	
	<%if(request.getAttribute("invalid")!=null){ %>
	    <div class="alert" align="center">
			<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		    <strong>Atentie!</strong><%=request.getAttribute("invalid")%>
		</div>
	<%}%>
	
	<div class="wrap">  
	    <form method="post" name="from" onsubmit="return validare();" action="ConnectionServlet">
	    	<table align="center">
		    	<tr>
		    		<td><p>Acceseaza platforma:</p></td>
		    	</tr>
		    	<tr>
					<td><input type="text" name="nume" id="nume" placeholder="Nume de utilizator" required/></td>
				</tr>
				<tr>
					<td><input type="password" name="parola" id="parola" placeholder="Parola" required/></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button type="submit">Conectare</button></td>
				</tr>		
			</table>
		</form>
	</div>
</body>
<script>
function validare(){
         var nume = document.getElementById("nume").value;
         var parola = document.getElementById("parola").value;
         var valid = true;
         if(nume=="" || parola=="" || nume==null ||parola==null)
             {
                 alert("Campurile Nume Utilizator si Parola sunt obligatorii!");
                 valid = false;
             }
         return valid;
     }
</script>
</html>