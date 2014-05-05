<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.ArrayList" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	
</head>
<body>
	<div class = "container-fluid">
		<div class = "row-fluid">
			<div class = "span9">
			</div>
			<div class = "span3">
				<form action="${pageContext.request.contextPath}/LoginServlet" method="GET">
					<%if(session.getAttribute("fb-access-token")!=null){%>
						<button type="submit" id="btn-fb" class="btn btn-success btn-large">
							Logged in with Facebook
						</button>
					<%}else{%>
						<button type="submit" id="btn-fb" class="btn btn-primary btn-large">
							Log in with Facebook
						</button>
					<%}%>
				</form>
			</div>
		</div>
	
		<div class = "row-fluid">
			<div class = "span3">
			</div>
			
			<div class = "span6">
				<div class = "row-fluid">
					<form action="${pageContext.request.contextPath}/DataServlet" method="GET">
						<ul class = "pager">
							<li class="previous">
								<a href="${pageContext.request.contextPath}/DataServlet?name=prev">
									&larr; Older</a>
							</li>
	  						<li class="next">
	  							<a href="${pageContext.request.contextPath}/DataServlet?name=next">
	  								Newer &rarr;</a>
	  						</li>
						</ul>
					</form>
				</div>
				
				<div class = "row-fluid">
					<%
						if(session.getAttribute("messages") !=null) {
							ArrayList<String> messages = (ArrayList<String>)session.getAttribute("messages");
							for(Integer i=0; i< messages.size(); i++){
								%>
									<p> <%=messages.get(i)%></p><br>
								<%
							}	
						}
						
					%>
				</div>
				
				<div class = "row-fluid">
					
					<form action="${pageContext.request.contextPath}/DataServlet" method="POST">
						<textarea name="message" class="form-control field span10" rows="3"></textarea>
						<button type = "submit" class = "btn btn-primary btn-large">Enter</button>
					</form>
					
				</div>
			</div>
			
			<div class = "span3">
			</div>
		</div>
	</div>
</body>
</html>