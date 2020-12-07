<%-- 
    Document   : BookingView
    Created on : Dec 6, 2020, 11:21:08 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*,Model.*,DAO.*,Controller.User.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../CSS/Booking.css">
        <link rel="stylesheet" href="../CSS/Checkout.css">
    </head>
    <body>
        <div class = "container">
            <%
                if (session.getAttribute("booking") != null) {
                    Booking booking = (Booking) session.getAttribute("booking");
                    System.out.println(booking);
                    pageContext.setAttribute("booking", booking);
                    pageContext.setAttribute("selected_rooms", booking.getRooms());
                    float total = 0;
                    for (BookedRoom room : booking.getRooms()) {
                        int MILLI_TO_HOUR = 1000 * 60 * 60;
                        long hours = (room.getReturnDate().getTime() - room.getReceiveDate().getTime()) / MILLI_TO_HOUR;
                        total += room.getPrice() * hours;
                    }
                    System.out.println(total);
                    pageContext.setAttribute("total", total);
                    User client = booking.getClient();
                    pageContext.setAttribute("client", client);
                }
            %>
            <h1> Booking</h1>
            <div class = "infor_wrapper container">
                <div class = "client_wrapper">
                    <div class = "row">
                        <div class = "col col-6">
                            <label class = "client_label"> Name:  </label>
                            <p><c:out value="${client.getName()}"/></p>
                        </div>
                        <div class = "col col-6">
                            <label class = "client_label"> Address:  </label>
                            <p><c:out value="${client.getAddress()}"/></p>
                        </div>
                        <div class = "col col-6">
                            <label class = "client_label"> Phone  </label>
                            <p><c:out value="${client.getPhone()}"/></p>
                        </div>
                        <div class = "col col-6">
                            <label class = "client_label"> Email  </label>
                            <p><c:out value="${client.getEmail()}"/></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class = "table_wrapper container">
                <div>
                    <h3> Selected Room </h3>
                    <table width="600px">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Receive At</th>
                                <th>Return At</th>
                                <th>Desc</th>
                                <th>Price</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${selected_rooms}" var="current" varStatus = "loop">
                                <tr>
                                    <th><c:out value="${current.getRoom().id}" /></th>
                                    <th><c:out value="${current.getRoom().name}" /></th>
                                    <th><c:out value="${current.getRoom().type}" /></th>
                                    <th><c:out value="${current.getReceiveDate()}" /></th>
                                    <th><c:out value="${current.getReturnDate()}" /></th>
                                    <th><c:out value="${current.getRoom().desc}" /></th>
                                    <th><c:out value="${current.getRoom().price}" /></th>

                                    <th>
                                        <form name="select_room" action="doBooking.jsp" method="POST">
                                            <input type="hidden" id="search_room" name="post_content" value="delete">
                                            <input type="hidden" id="room_id" name="room_id" value="${loop.index}">
                                            <input type="submit" value="Delete" name="choose" class ="btn btn-danger" style ="width: 100%" />
                                        </form>                                    
                                    </th>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th colspan="6"> Total </th>
                                <th colspan="2"><c:out value="${total}"/> </th>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class ="item_service_wrapper container">
                <div class = "items">
                    <div class ="row">
                        <h3 class = "col col-6"> Booked Items </h3>
                        <div class= "col col-6">
                            <form name="confirm" action="AddItemView.jsp" method="POST" >
                                <input type="hidden" id="add_item" name="post_content" value="add_item">
                                <input type="submit" value="Add Item" name="confirm" class ="btn btn-primary" />
                            </form>
                        </div>
                    </div>
                    <table border="0" width="450px">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Room</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>

                </div>
                <div class = "services">
                    <div class ="row">
                        <h3 class = "col col-6"> Booked Items </h3>
                        <div class= "col col-6">
                            <form name="confirm" action="AddServiceView.jsp" method="POST" >
                                <input type="hidden" id="add_service" name="post_content" value="add_service">
                                <input type="submit" value="Add Service" name="confirm" class ="btn btn-primary" />
                            </form>
                        </div>
                    </div>                    
                    <table width="450px">
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
                        </tbody>
                    </table>
                </div>
            </div>
            <div class = "container">
                <form name="confirm" action="doBooking.jsp" method="POST" class = "confirm-btt">
                    <input type="hidden" id="search_room" name="post_content" value="confirm">
                    <input type="submit" value="Confirm" name="confirm" class ="btn btn-success" />
                </form>
            </div>
        </div>

    </body>
</html>