<%-- 
    Document   : doSearchBookingView
    Created on : Dec 6, 2020, 10:14:23 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*,Model.*,DAO.*,Controller.User.*" %>

<!DOCTYPE html>
<%
    if (request.getParameter("post_content").equalsIgnoreCase("search")) {
        String name = request.getParameter("booking_customer");
        int booking_id = Integer.parseInt(request.getParameter("booking_id"));
        BookingDAO dao = new BookingDAO();
        if (name != "") {
            ArrayList<Booking> bookings = new ArrayList<>();
            bookings = dao.getBookingByCustomerName(name);
            session.setAttribute("bookings", bookings);
        } else {
            Booking booking = new Booking();
            booking = dao.getBooking(booking_id);
            session.setAttribute("booking", booking);
        }
        response.sendRedirect("SearchBookingView.jsp");
    } else if (request.getParameter("post_content").equalsIgnoreCase("choose")) {
        response.sendRedirect("BookingView.jsp");
    } else if (request.getParameter("post_content").equalsIgnoreCase("choose_list")) {
        int booking_id = Integer.parseInt(request.getParameter("booking_id"));
        ArrayList<Booking> bookings = (ArrayList<Booking>) session.getAttribute("bookings");
        for (Booking booking : bookings) {
            if (booking_id == booking.getId()) {
                session.setAttribute("booking", booking);
                response.sendRedirect("BookingView.jsp");
                return;
            }
        }
    }
%>