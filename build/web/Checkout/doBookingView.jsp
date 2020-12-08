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

    } else if (request.getParameter("post_content").equalsIgnoreCase("add_item")) {
        if (session.getAttribute("confirm_item_rooms") == null) {
            ArrayList<BookedItem> booked_items = (ArrayList<BookedItem>) session.getAttribute("confirm_item_rooms");
            session.setAttribute("booked_items", booked_items);
        }
        response.sendRedirect("AddItemView.jsp");
    }else if (request.getParameter("post_content").equalsIgnoreCase("add_service")) {
        if (session.getAttribute("confirm_booked_services") == null) {
            ArrayList<BookedItem> confirm_booked_services = (ArrayList<BookedItem>) session.getAttribute("confirm_booked_services");
            session.setAttribute("booked_services", confirm_booked_services);
        }
        response.sendRedirect("AddServiceView.jsp");
    }
%>