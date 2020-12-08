<%-- 
    Document   : doAddItemView
    Created on : Dec 7, 2020, 9:13:51 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*,Model.*,DAO.*,Controller.User.*" %>
<%
    if (request.getParameter("post_content").equalsIgnoreCase("add_item")) {
        ArrayList<BookedRoom> selects = (ArrayList) session.getAttribute("selected_rooms");
        ArrayList<Item> list = (ArrayList) session.getAttribute("all_items");
        int item_id = Integer.parseInt(request.getParameter("item"));
        int room_id = Integer.parseInt(request.getParameter("room"));
        Item selected = list.get(item_id);
        BookedItem bi = new BookedItem();
        bi.setItem(selected);
        bi.setPrice(selected.getPrice());
        bi.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        selects.get(room_id).getItems().add(bi);
        ArrayList<Room> rooms = new ArrayList<Room>();
        if (session.getAttribute("booked_items") == null) {
            ArrayList<BookedItem> booked_items = new ArrayList<>();
            booked_items.add(bi);
            rooms.add(selects.get(room_id).getRoom());
            session.setAttribute("booked_items", booked_items);

        } else {
            ArrayList<BookedItem> booked_items = (ArrayList<BookedItem>) session.getAttribute("booked_items");
            rooms = (ArrayList<Room>) session.getAttribute("item_rooms");
            booked_items.add(bi);
            rooms.add(selects.get(room_id).getRoom());
            session.setAttribute("booked_items", booked_items);
        }
        session.setAttribute("item_rooms", rooms);
        for (BookedRoom select : selects) {
            System.out.println(select);
        }
        session.setAttribute("selected_rooms", selects);
        response.sendRedirect("AddItemView.jsp");
    } else if (request.getParameter("post_content").equalsIgnoreCase("delete")) {
        ArrayList<BookedItem> booked_items = (ArrayList<BookedItem>) session.getAttribute("booked_items");
        ArrayList<Room> rooms = (ArrayList<Room>) session.getAttribute("item_rooms");
        int index = Integer.parseInt(request.getParameter("item_id"));
        ArrayList<BookedRoom> selects = (ArrayList) session.getAttribute("selected_rooms");
        BookedItem items = booked_items.get(index);
        for (BookedRoom select : selects) {
            ArrayList<BookedItem> item_list = select.getItems();

            for (BookedItem item : item_list) {
                if (items.getItem().getId() == item.getItem().getId()) {
                    item_list.remove(item);
                }
            }
        }
        booked_items.remove(index);
        rooms.remove(index);
        response.sendRedirect("AddItemView.jsp");
    } else if (request.getParameter("post_content").equalsIgnoreCase("confirm")) {
        ArrayList<BookedItem> booked_items = (ArrayList<BookedItem>) session.getAttribute("booked_items");
        ArrayList<Room> rooms = (ArrayList<Room>) session.getAttribute("item_rooms");
        session.setAttribute("confirm_booked_items", booked_items);
        session.setAttribute("confirm_item_rooms", rooms);
        response.sendRedirect("BookingView.jsp");

    }
%>