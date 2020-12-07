<%-- 
    Document   : doAddService
    Created on : Dec 7, 2020, 10:05:04 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*,Model.*,DAO.*,Controller.User.*" %>
<%
    if (request.getParameter("post_content").equalsIgnoreCase("add_service")) {
        ArrayList<BookedRoom> selects = (ArrayList) session.getAttribute("selected_rooms");
        ArrayList<Service> list = (ArrayList) session.getAttribute("all_services");
        int item_id = Integer.parseInt(request.getParameter("service"));
        int room_id = Integer.parseInt(request.getParameter("room"));
        Service selected = list.get(item_id);
        BookedService bi = new BookedService();
        bi.setService(selected);
        bi.setPrice(selected.getPrice());
        selects.get(room_id).getServices().add(bi);
        ArrayList<Room> rooms = new ArrayList<Room>();
        if (session.getAttribute("booked_services") == null) {
            ArrayList<BookedService> booked_services = new ArrayList<>();
            booked_services.add(bi);
            rooms.add(selects.get(room_id).getRoom());
            session.setAttribute("booked_services", booked_services);

        } else {
            ArrayList<BookedService> booked_services = (ArrayList<BookedService>) session.getAttribute("booked_services");
            rooms = (ArrayList<Room>) session.getAttribute("service_rooms");
            booked_services.add(bi);
            rooms.add(selects.get(room_id).getRoom());
            session.setAttribute("booked_services", booked_services);
        }
        session.setAttribute("service_rooms", rooms);
        session.setAttribute("selected_rooms", selects);
        response.sendRedirect("AddServiceView.jsp");
    } else if (request.getParameter("post_content").equalsIgnoreCase("delete")) {
        ArrayList<BookedService> booked_services = (ArrayList<BookedService>) session.getAttribute("booked_services");
        ArrayList<Room> rooms = (ArrayList<Room>) session.getAttribute("service_rooms");
        int index = Integer.parseInt(request.getParameter("service_id"));
        booked_services.remove(index);
        rooms.remove(index);
        response.sendRedirect("AddServiceView.jsp");
    } else if (request.getParameter("post_content").equalsIgnoreCase("confirm")) {
        ArrayList<BookedService> booked_services = (ArrayList<BookedService>) session.getAttribute("booked_services");
        ArrayList<Room> rooms = (ArrayList<Room>) session.getAttribute("service_rooms");
        session.setAttribute("confirm_booked_services", booked_services);
        session.setAttribute("confirm_service_rooms", rooms);
        response.sendRedirect("BookingView.jsp");

    }
%>