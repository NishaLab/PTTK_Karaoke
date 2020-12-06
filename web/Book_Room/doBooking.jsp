<%-- 
    Document   : doBooking
    Created on : Dec 6, 2020, 4:56:30 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*,Model.*,DAO.*,Controller.User.*" %>

<!DOCTYPE html>
<%
    if (request.getParameter("post_content").equalsIgnoreCase("delete")) {
        ArrayList<BookedRoom> selects = (ArrayList) session.getAttribute("selected_rooms");
        int room_id = Integer.parseInt(request.getParameter("room_id"));
        selects.remove(room_id);
        response.sendRedirect("BookingView.jsp");
    }
    if (request.getParameter("post_content").equalsIgnoreCase("confirm")) {
        Booking booking = new Booking();
        booking = (Booking) session.getAttribute("booking");
        BookingDAO dao = new BookingDAO();
        if (dao.addBooking(booking)) {
            response.sendRedirect("../User/MainCustomerView.jsp");
            session.setAttribute("selected_rooms", null);
            session.setAttribute("booking", null);
            session.setAttribute("free_rooms", null);
            return;
        }
        response.sendRedirect("BookingView.jsp");

    }
%>