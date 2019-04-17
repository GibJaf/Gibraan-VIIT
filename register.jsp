<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>JSP to register</title>
</head>
<body>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.* "%>
<%
	String name = "default";
	String uname = request.getParameter("username");
	String pwd   = request.getParameter("password");
	
	out.print(" Username : "+uname);
	out.print("<br>");
	out.print(" Password : "+pwd);
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo","root","abc12345");
	PreparedStatement ps = con.prepareStatement("INSERT INTO people values(?,?,?)");
	ps.setString(1,name);
	ps.setString(2,uname);
	ps.setString(3,pwd);
	int i = ps.executeUpdate();
	
	if(i>0)
		out.write("<br> Nice going mate . Now continue to <a href='login.html'>LOGIN</a>");
	else
		out.write(" Something fuckedup !");
		
%>

</body>
</html>