<%-- 
    Document   : MainCustomerView
    Created on : Dec 5, 2020, 9:39:04 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controller.User.*,Model.*,DAO.*,Controller.User.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <% User client = new User();
            client = (User) session.getAttribute("client_id");
        %>
        <h1>Hello Customer <%= client.getName()%>!</h1>
        <c:if test ="${save != null && save == true}">
            <div class="alert alert-success alert-dismissible">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Success!</strong> You have successfully booked a room.
            </div>
            <%
                session.setAttribute("save", null);
            %>
        </c:if>
        <h2><a href="/PTTK_Karaoke/Book_Room/SearchFreeRoomView.jsp"> Book Room </a></h2>
        <form name="log_out" action="Logout.jsp">
            <input type="submit" value="Log Out"  class ="btn btn-primary"/> 
        </form>
    </body>
</html>
