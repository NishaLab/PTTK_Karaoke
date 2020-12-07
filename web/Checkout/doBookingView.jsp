<%-- 
    Document   : doBookingView
    Created on : Dec 6, 2020, 11:22:02 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*,java.text.*,Model.*,DAO.*,Controller.User.*" %>
<%
    if (request.getParameter("post_content").equalsIgnoreCase("add_item")) {
        response.sendRedirect("AddItemView.jsp");
    } else if (request.getParameter("post_content").equalsIgnoreCase("add_service")) {
        response.sendRedirect("AddServiceView.jsp");
    } else if (request.getParameter("post_content").equalsIgnoreCase("confirm")) {
        Booking booking = (Booking) session.getAttribute("booking");
        ArrayList<BookedItem> list_item = new ArrayList<BookedItem>();
        list_item = (ArrayList<BookedItem>) session.getAttribute("confirm_booked_items");
        ArrayList<BookedService> list_service = new ArrayList<BookedService>();
        list_service = (ArrayList<BookedService>) session.getAttribute("confirm_booked_services");
        ArrayList<BookedRoom> br = (ArrayList<BookedRoom>) session.getAttribute("selected_rooms");
        booking.setRooms(br);
        session.setAttribute("booking", booking);
        float item_total = (float) session.getAttribute("item_total");
        float service_total = (float) session.getAttribute("service_total");
        float total = (float) session.getAttribute("total");
        float final_total = item_total + service_total + total;
        session.setAttribute("final_total", final_total);
        User staff = (User) session.getAttribute("staff_id");
        booking.setStaff(staff);
        System.out.println(staff);
        response.sendRedirect("ConfirmationView.jsp");

    }
%>