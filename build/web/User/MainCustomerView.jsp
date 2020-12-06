<%-- 
    Document   : MainCustomerView
    Created on : Dec 5, 2020, 9:39:04 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controller.User.*,Model.*,DAO.*,Controller.User.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% User client = new User();
            client = (User) session.getAttribute("client_id");
        %>
        <h1>Hello Customer <%= client.getName()%>!</h1>
        <h2><a href="/PTTK_Karaoke/Book_Room/SearchFreeRoomView.jsp"> Book Room </a></h2>
        <h2><a href="/PTTK_Karaoke/index.jsp"> Logout </a></h2>

    </body>
</html>
