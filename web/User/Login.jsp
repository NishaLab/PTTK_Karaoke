<%-- 
    Document   : Login
    Created on : Dec 1, 2020, 2:44:43 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Welcome to PTTK Karaoke </h1>
        <form action="doLogin.jsp" method="post">
            <table style="with: 80%">
                <tr>
                    <td>UserName</td>
                    <td><input type="text" name="username" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" /></td>
                </tr>
            </table>
            <input type="submit" value="Submit" />
        </form>
    </body>
</html>
