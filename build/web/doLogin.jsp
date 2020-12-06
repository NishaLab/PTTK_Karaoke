<%-- 
    Document   : doLogin
    Created on : Dec 5, 2020, 11:36:26 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Controller.User.*,Model.*,DAO.*,Controller.User.*" %>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    UserDAO dao = new UserDAO();

    if (username.isEmpty() || password.isEmpty()) {
        response.sendRedirect("Login.jsp");
    } else if (dao.checkLogin(user)) {
        if (user.getRole().equalsIgnoreCase("customer")) {
            System.out.println(user);
            session.setAttribute("client_id", user);
            request.getRequestDispatcher("/MainCustomerView.jsp").forward(request, response);
        } else if (user.getRole().equalsIgnoreCase("receptionist")) {
            response.sendRedirect("MainReceptionist.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    } else {
        response.sendRedirect("Login.jsp");
    }
%>