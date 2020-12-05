<%-- 
    Document   : index
    Created on : Dec 2, 2020, 10:09:23 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,Model.*,DAO.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/Login.css">

        <title>Login Page</title>
    </head>
    <body>

        <%
            Item item = new Item();
            ItemDAO dao = new ItemDAO();
            item  = dao.getItemById(1);
            System.out.println(item);
        %>

        <a href="Login.jsp">login</a>|  
        <a href="../Register.jsp">Register</a>  
    </body>
</html>
