<%-- 
    Document   : AddServiceView
    Created on : Dec 7, 2020, 7:00:10 PM
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
            ServiceDAO dao = new ServiceDAO();
            ArrayList<Service> all_services = new ArrayList<Service>();
            all_services = dao.getAllService();
            session.setAttribute("all_services", all_services);
            pageContext.setAttribute("all_services", all_services);
            if (session.getAttribute("booked_services") != null) {
                ArrayList<BookedService> list = new ArrayList<BookedService>();
                list = (ArrayList<BookedService>) session.getAttribute("booked_services");
                AbstractList<Room> rooms = (AbstractList<Room>) session.getAttribute("service_rooms");
                pageContext.setAttribute("service_rooms", rooms);
                pageContext.setAttribute("booked_services", list);
            }
            ArrayList<BookedRoom> br = new ArrayList<BookedRoom>();
            br = (ArrayList<BookedRoom>) session.getAttribute("selected_rooms");
            pageContext.setAttribute("selected_rooms", br);

        %>
        <div class="containter">
            <form action="BookingView.jsp">
                <input type="submit" value="Back" class="btn btn-primary"/>
            </form> 
            <form name="add_service" action="doAddServiceView.jsp" method="POST">
                <input type="hidden" id="search_room" name="post_content" value="add_service">
                <div class = "search_room container">
                    <label style="display:block;"> Service</label>
                    <select name="service">
                        <c:forEach items="${all_services}" var="current" varStatus="loop">
                            <option value = "${loop.index}"> <c:out value="${current.getName()}" /> </option>
                        </c:forEach>
                    </select>
                </div>
                <div class = "search_room container">
                    <label style="display:block;"> Room</label>
                    <select name="room">
                        <c:forEach items="${selected_rooms}" var="current" varStatus="loop">
                            <option value = "${loop.index}"> <c:out value="${current.getRoom().getName()}" /> </option>
                        </c:forEach>
                    </select>
                </div>
                <div class = "search_room container" style="padding-top: 24px">
                    <div class ="row">
                        <div class = "col col-6">
                            <input type="submit" value="Add" name="submit" class="btn btn-primary"/>
                        </div>
                        <div class = "col col-6">
                            <input type="reset" value="Clear" class="btn btn-danger" />
                        </div>
                    </div>
                </div>
            </form>
            <div class="container" style="padding-top: 32px">
                <form action="doAddServiceView.jsp" method="POST">
                    <input type="hidden" id="confirm_item" name="post_content" value="confirm">
                    <input type="submit" value="Confirm" name="submit" class="btn btn-success"/>
                </form> 
            </div>

            <div class ="container">
                <div class = "items">
                    <div class ="row">
                        <h3 class = "col col-6"> Booked Services </h3>
                    </div>
                    <table border="0" width="450px">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Room</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${booked_services}" var="current" varStatus = "loop">
                                <tr>
                                    <th><c:out value="${current.getService().id}" /></th>
                                    <th><c:out value="${current.getService().name}" /></th>
                                    <th><c:out value="${current.getPrice()}" /></th>
                                    <th><c:out value="${service_rooms.get(loop.index).getName()}"/></th>
                                    <th>
                                        <form name="select_room" action="doAddServiceView.jsp" method="POST">
                                            <input type="hidden" id="delete_item" name="post_content" value="delete">
                                            <input type="hidden" id="item_id" name="service_id" value="${loop.index}">
                                            <input type="submit" value="Delete" name="choose" class ="btn btn-danger" style ="width: 100%" />
                                        </form>                                    
                                    </th>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </body>
</html>
