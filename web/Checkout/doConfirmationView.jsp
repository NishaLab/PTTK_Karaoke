<%-- 
    Document   : doConfirmationView
    Created on : Dec 7, 2020, 10:53:33 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*,java.text.*,Model.*,DAO.*,Controller.User.*" %>
<%
    Booking booking = (Booking) session.getAttribute("booking");
    booking.setPaymentType(request.getParameter("paymentType"));
    booking.setPaymentDate(new Date());
    BookingDAO dao = new BookingDAO();
    if (dao.updateBooking(booking)) {
        response.sendRedirect("../User/MainReceptionistView.jsp");
    } else {
        response.sendRedirect("ConfirmationView.jsp");
    }
%>