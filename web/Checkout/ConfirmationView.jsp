<%-- 
    Document   : ConfirmationView
    Created on : Dec 7, 2020, 10:44:59 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*,Model.*,DAO.*,Controller.User.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../CSS/Booking.css">
        <link rel="stylesheet" href="../CSS/Checkout.css">
    </head>
    <body>
        <%
            float final_total = (float) session.getAttribute("final_total");
            pageContext.setAttribute("final_total", final_total);
            Booking booking = (Booking) session.getAttribute("booking");
            pageContext.setAttribute("staff", booking.getStaff().getName());
            pageContext.setAttribute("client", booking.getClient().getName());
            if (session.getAttribute("save") != null) {
                boolean save = (boolean) session.getAttribute("save");
                pageContext.setAttribute("save", save);
            }
        %>
        <div class = "container">
            <c:if test ="${save != null && save == false}">
                <div class="alert alert-danger alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Danger!</strong> Can not Update Booking
                </div>
                <%
                    session.setAttribute("save", null);
                %>
            </c:if>
            <form name="finish" action="doConfirmationView.jsp" method="POST">
                <h1>Confirmation View</h1>
                <input type="hidden" id="confirm_view" name="post_content" value="confirm">
                <div class = "finish container">
                    <label> Payment Type: </label>
                    <select name="paymentType">
                        <option>Cash</option>
                        <option>Credit</option>
                    </select>
                </div>
                <div class = "finish container">
                    <label> Customer: <c:out value="${client}"/></label>
                </div>
                <div class = "finish container">
                    <label> Staff: <c:out value="${staff}"/></label>
                </div>
                <h2>Total: <c:out value="${final_total}"/></h2>
                <input type="submit" value="Confirm" class ="btn btn-success" />                
            </form>            
        </div>
    </body>
</html>
