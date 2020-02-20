<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.ArrayList" import="javax.servlet.*" import="utility.Item"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<title>Farma App</title>
	<link rel="stylesheet" href="./css/bootstrap.min.css">
	<link rel="stylesheet" href="./css/style.css">
</head>

<body class="bg-warning text-white">
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark justify-content-center">
	  <h4 class="text-center text-warning">
			FARMA APP
			<footer class="p-footer text-white font-italic">Scegli la tua farmacia!</footer>
	  </h4>
	</nav>

	<div class="table-responsive-sm">
		<table class="table mtable">
		
		<% 
					ArrayList<Item> list = (ArrayList<Item>) request.getAttribute("ris");
					
					if(list == null || list.size() == 0)
					{
		%>
					<tr class="bg-dark">
					<td> Nessun risultato trovato! </td>
					</tr>
					<tr class="bg-dark">
					<td> Prova con un'altra città! </td>
					</tr>
		<%				
					}
					else{
					for(int i = 0; i < list.size() ; i++)
					{
		%>
				<tr class="bg-dark">
					<td> <%=list.get(i).getDenominazione()%> </td>
				</tr>
				<tr class="bg-dark">
					<td> <%=list.get(i).getIndirizzo()%> </td>
				</tr>
				<%
					if(list.get(i).getLatitudine() > 0.0 && list.get(i).getLongitudine() > 0.0)
					{
						
				%>
				<tr>
					<td>  
						<div class="form-group col-md-12 mbutton">
							<a href="./Mappa.jsp?lat=
								<%=list.get(i).getLatitudine() %>&lon=<%=list.get(i).getLongitudine()%>">
							<button type="submit" class="btn btn-primary btn-dark">
									VEDI POSIZIONE
							</button>
							</a>
						</div>
					</td>
				</tr>
				
				<%}%>
		<%}}%>
			
		</table>
		
		<div class="form-group col-md-12 mbutton">
			<a href="./Home.html">
				<button type="submit" class="btn btn-primary btn-dark btn-lg">HOME</button>
			</a>
		</div>
	</div>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
</body>

</html>