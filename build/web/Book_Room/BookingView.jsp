<%-- 
    Document   : BookingView
    Created on : Dec 6, 2020, 4:23:49 PM
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
    </head>
    <body>
        <div class = "container">
            <%
                if (session.getAttribute("booking") != null) {
                    Booking booking = (Booking) session.getAttribute("booking");
                    pageContext.setAttribute("booking", booking);
                    pageContext.setAttribute("selected_rooms", booking.getRooms());
                    float total = 0;
                    for (BookedRoom room : booking.getRooms()) {
                        int MILLI_TO_HOUR = 1000 * 60 * 60;
                        long hours = (room.getReturnDate().getTime() - room.getReceiveDate().getTime()) / MILLI_TO_HOUR;
                        total += room.getPrice() * hours;
                    }
                    pageContext.setAttribute("total", total);
                    User client = booking.getClient();
                    pageContext.setAttribute("client", client);
                }
                if(session.getAttribute("save")!=null){
                    boolean save = (boolean) session.getAttribute("save");
                    pageContext.setAttribute("save", save);
                }
            %>
            <c:if test ="${save != null && save == false}">
                <div class="alert alert-danger alert-dismissible">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Danger!</strong> Can not save Booking
                </div>
                <%
                    session.setAttribute("save", null);
                %>
            </c:if>
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
                <div class = "selected_room">
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
            <div>
                <form name="confirm" action="doBooking.jsp" method="POST">
                    <input type="hidden" id="search_room" name="post_content" value="confirm">
                    <input type="submit" value="Confirm" name="confirm" class ="btn btn-success" />
                </form>
            </div>
        </div>

    </body>
</html>
