<%-- 
    Document   : doSearchFreeRoomView
    Created on : Dec 6, 2020, 10:22:43 AM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,Model.*,DAO.*,Controller.User.*" %>

<!DOCTYPE html>
<%
    if (request.getParameter("post_content").equalsIgnoreCase("search_room")) {
        session.setAttribute("querry_receive", request.getParameter("receive-time"));
        session.setAttribute("querry_return", request.getParameter("return-time"));

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        Date receive_date = (Date) formatter.parse(request.getParameter("receive-time"));
        Date return_date = (Date) formatter.parse(request.getParameter("return-time"));
        String type = request.getParameter("type");
        RoomDAO dao = new RoomDAO();
        ArrayList<Room> rooms = dao.searchFreeRoom(receive_date, return_date, type);
        session.setAttribute("receive-time", receive_date);
        session.setAttribute("return-time", return_date);
        session.setAttribute("free_rooms", rooms);
        response.sendRedirect("SearchFreeRoomView.jsp");
    }
    if (request.getParameter("post_content").equalsIgnoreCase("select")) {
        Room room = new Room();
        room.setId(Integer.parseInt(request.getParameter("room_id")));
        room.setName(request.getParameter("room_name"));
        room.setType(request.getParameter("room_type"));
        room.setDesc(request.getParameter("room_desc"));
        room.setPrice(Float.parseFloat(request.getParameter("room_price")));
        BookedRoom bk = new BookedRoom();
        bk.setRoom(room);
        bk.setReceiveDate((Date) session.getAttribute("receive-time"));
        bk.setReturnDate((Date) session.getAttribute("return-time"));
        bk.setPrice(room.getPrice());
        if (session.getAttribute("selected_rooms") == null) {
            ArrayList<BookedRoom> selects = new ArrayList<BookedRoom>();
            selects.add(bk);
            session.setAttribute("selected_rooms", selects);
        } else {
            ArrayList<BookedRoom> selects = (ArrayList) session.getAttribute("selected_rooms");
            for (BookedRoom select : selects) {
                if (select.getRoom().getId() == Integer.parseInt(request.getParameter("room_id"))) {
                    response.sendRedirect("SearchFreeRoomView.jsp");
                    return;
                }
            }
            selects.add(bk);
            session.setAttribute("selected_rooms", selects);
        }
        response.sendRedirect("SearchFreeRoomView.jsp");
    }
    if (request.getParameter("post_content").equalsIgnoreCase("delete")) {
        ArrayList<BookedRoom> selects = (ArrayList) session.getAttribute("selected_rooms");
        int room_id = Integer.parseInt(request.getParameter("room_id"));
        selects.remove(room_id);
        response.sendRedirect("SearchFreeRoomView.jsp");
    }
    if (request.getParameter("post_content").equalsIgnoreCase("confirm")) {
        Booking booking = new Booking();
        User user = (User) session.getAttribute("client_id");
        booking.setCreateDate(new Date());
        booking.setClient(user);
        ArrayList<BookedRoom> selects = (ArrayList) session.getAttribute("selected_rooms");
        booking.setRooms(selects);
        session.setAttribute("booking", booking);
        session.setAttribute("save", null);
        response.sendRedirect("BookingView.jsp");
    }
%>

