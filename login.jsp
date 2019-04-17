<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>

<%
	String uname = request.getParameter("username");
	String pwd   = request.getParameter("password");
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/userinfo","root","abc12345");
	PreparedStatement smt = con.prepareStatement("Select * from people where email = ? and password = ?");
	smt.setString(1,uname);
	smt.setString(2,pwd);
	ResultSet rs = smt.executeQuery();
	
	if(rs.next()){
		out.print(" Welcome "+rs.getString(1)+" Have a good time !");
	}else{
		out.print(" Invalid username or password :( ");
	}
	
	//out.print("Members <br>" +rs.getString(1));
	
%>
</body>
</html>