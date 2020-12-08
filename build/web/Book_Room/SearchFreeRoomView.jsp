<%-- 
    Document   : SearchFreeRoomView
    Created on : Dec 6, 2020, 10:08:04 AM
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
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="../CSS/SeachRoom.css">
    </head>
    <body>
        <div class ="container">
            <h1>Search Free Room View!</h1>
            <%
                if (session.getAttribute("free_rooms") != null) {
                    ArrayList<Room> rooms = (ArrayList<Room>) session.getAttribute("free_rooms");
                    pageContext.setAttribute("free_rooms", rooms);
                }
                if (session.getAttribute("selected_rooms") != null) {
                    ArrayList<BookedRoom> rooms = (ArrayList<BookedRoom>) session.getAttribute("selected_rooms");
                    pageContext.setAttribute("selected_rooms", rooms);
                }
                if (session.getAttribute("querry_receive") != null && session.getAttribute("querry_return") != null) {
                    pageContext.setAttribute("querry_receive", session.getAttribute("querry_receive"));
                    pageContext.setAttribute("querry_return", session.getAttribute("querry_return"));
                }
            %>
            <form name="search_room" action="doSearchFreeRoomView.jsp" method="POST">
                <input type="hidden" id="search_room" name="post_content" value="search_room">
                <div class = "search_room container">
                    <label style="display:block;"> Room Type </label>
                    <select name="type">
                        <option></option>
                        <option>Single</option>
                        <option>Double</option>
                        <option>VIP</option>
                    </select>
                </div>
                <div class = "search_room container">
                    <div class = "row">
                        <div class = "col col-6">
                            <label style="display:block;"> Receive Time </label>
                            <input type="datetime-local" id="receive-time"
                                   name="receive-time"><c:out value="${querry_receive}"></c:out>
                        </div>
                        <div class = "col col-6">
                            <label style="display:block;"> Return Time </label>
                            <input type="datetime-local" id="return-time"
                                   name="return-time"/>
                        </div>
                    </div>

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
                <form name="confirm" action="doSearchFreeRoomView.jsp" method="POST">
                    <input type="hidden" id="search_room" name="post_content" value="confirm">
                    <input type="submit" value="Confirm" name="confirm" class ="btn btn-success" />
                </form>
            </div>

            <div class ="room_wrapper container">
                <div class = "free_room">
                    <p> Free Room </p>
                    <table border="0" width="450px">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Price</th>
                                <th>Desc</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${free_rooms}" var="current" >
                                <tr>
                                    <th><c:out value="${current.id}" /></th>
                                    <th><c:out value="${current.name}" /></th>
                                    <th><c:out value="${current.type}" /></th>
                                    <th><c:out value="${current.price}" /></th>
                                    <th><c:out value="${current.desc}" /></th>
                                    <th>
                                        <form name="select_room" action="doSearchFreeRoomView.jsp" method="POST">
                                            <input type="hidden" id="search_room" name="post_content" value="select">
                                            <input type="hidden" id="room_id" name="room_id" value="${current.id}">
                                            <input type="hidden" id="room_name" name="room_name" value="${current.name}">
                                            <input type="hidden" id="room_type" name="room_type" value="${current.type}">
                                            <input type="hidden" id="room_price" name="room_price" value="${current.price}">
                                            <input type="hidden" id="room_desc" name="room_desc" value="${current.desc}">
                                            <input type="submit" value="Choose" name="choose" class ="btn btn-success" style ="width: 100%" />
                                        </form>                                    
                                    </th>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
                <div class = "selected_room">
                    <p> Selected Room </p>
                    <table width="450px">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Type</th>
                                <th>Price</th>
                                <th>Desc</th>
                                <th>Option</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${selected_rooms}" var="current" varStatus = "loop">
                                <tr>
                                    <th><c:out value="${current.getRoom().id}" /></th>
                                    <th><c:out value="${current.getRoom().name}" /></th>
                                    <th><c:out value="${current.getRoom().type}" /></th>
                                    <th><c:out value="${current.getRoom().price}" /></th>
                                    <th><c:out value="${current.getRoom().desc}" /></th>
                                    <th>
                                        <form name="select_room" action="doSearchFreeRoomView.jsp" method="POST">
                                            <input type="hidden" id="search_room" name="post_content" value="delete">
                                            <input type="hidden" id="room_id" name="room_id" value="${loop.index}">
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
