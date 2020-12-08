<%-- 
    Document   : Logout
    Created on : Dec 8, 2020, 9:30:51 AM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page import="java.util.*,Model.*,DAO.*,Controller.User.*" %>
<%
    session.setMaxInactiveInterval(1);
    response.sendRedirect("Login.jsp");
%>