<%-- 
    Document   : SearchBookingView
    Created on : Dec 6, 2020, 10:14:11 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*,Model.*,DAO.*,Controller.User.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Booking View</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../CSS/SeachRoom.css">

    </head>
    <body>
        <div class ="container">
            <h1>Search Booking View!</h1>
            <form action="../User/MainReceptionistView.jsp">
                <input type="submit" value="Back" class="btn btn-primary"/>
            </form>            
            <%
                if (session.getAttribute("bookings") != null) {
                    ArrayList<Booking> bookings = (ArrayList<Booking>) session.getAttribute("bookings");
                    pageContext.setAttribute("bookings", bookings);
                }
                if (session.getAttribute("booking") != null) {
                    Booking booking = (Booking) session.getAttribute("booking");
                    pageContext.setAttribute("booking", booking);
                }
            %>
            <form name="search_room" action="doSearchBookingView.jsp" method="POST">
                <input type="hidden" id="search_room" name="post_content" value="search">
                <div class = "search_room container">
                    <label style="display:block;"> Booking Id </label>
                    <input type="text" name="booking_id" value="" />
                </div>
                <div class = "search_room container">
                    <label style="display:block;"> Customer name </label>
                    <input type="text" name="booking_customer" value="" />
                </div>
                <div class = "search_room container" style="padding-top: 24px">
                    <div class ="row">
                        <div class = "col col-6">
                            <input type="submit" value="Search" name="submit" class="btn btn-primary"/>
                        </div>
                        <div class = "col col-6">
                            <input type="reset" value="Clear" class="btn btn-danger" />
                        </div>
                    </div>
                </div>
            </form>
            <div class = "container" style="padding-top: 16px">
                <form name="confirm" action="doSearchBookingView.jsp" method="POST">
                    <input type="hidden" id="search_room" name="post_content" value="confirm">
                    <input type="submit" value="Confirm" name="confirm" class ="btn btn-success" />
                </form>
            </div>
            <div class = "table_wrapper container">
                <div class = "selected_room">
                    <h3> Selected Room </h3>
                    <table width="600px">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Created At</th>
                                <th>Customer Name</th>
                                <th>Detail</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${bookings}" var="current" varStatus="loop">
                                <tr>
                                    <th><c:out value="${current.getId()}" /></th>
                                    <th><c:out value="${current.getCreateDate()}" /></th>
                                    <th><c:out value="${current.getClient().getName()}"/></th>
                                    <th>
                                        <form name="select_room" action="doSearchBookingView.jsp" method="POST">
                                            <input type="hidden" id="search_room" name="post_content" value="detail_list">
                                            <input type="hidden" id="booking_id" name="booking_id" value="${current.getId()}">
                                            <input type="submit" value="Detail" name="Detail" class ="btn btn-info" style ="width: 100%" />
                                        </form>                                    
                                    </th>
                                    <th>
                                        <form name="select_room" action="doSearchBookingView.jsp" method="POST">
                                            <input type="hidden" id="search_room" name="post_content" value="choose_list">
                                            <input type="hidden" id="booking_id" name="booking_id" value="${current.getId()}">
                                            <input type="submit" value="Choose" name="Choose" class ="btn btn-primary" style ="width: 100%" />
                                        </form>                                    
                                    </th>
                                </tr>
                            </c:forEach>
                            <c:if test ="${booking!= null}">
                                <tr>
                                    <th><c:out value="${booking.getId()}" /></th>
                                    <th><c:out value="${booking.getCreateDate()}" /></th>
                                    <th><c:out value="${booking.getClient().getName()}"/></th>
                                    <th>
                                        <form name="select_room" action="doSearchBookingView.jsp" method="POST">
                                            <input type="hidden" id="search_room" name="post_content" value="detail">
                                            <input type="hidden" id="room_id" name="room_id" value="${booking.getId()}">
                                            <input type="submit" value="Detail" name="choose" class ="btn btn-info" style ="width: 100%" />
                                        </form>                                    
                                    </th>
                                    <th>
                                        <form name="select_room" action="doSearchBookingView.jsp" method="POST">
                                            <input type="hidden" id="search_room" name="post_content" value="choose">
                                            <input type="hidden" id="room_id" name="room_id" value="${booking.getId()}">
                                            <input type="submit" value="Choose" name="choose" class ="btn btn-primary" style ="width: 100%" />
                                        </form>                                    
                                    </th>
                                </tr>
                            </c:if>    
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
