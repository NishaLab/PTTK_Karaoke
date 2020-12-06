<%-- 
    Document   : MainReceptionistView
    Created on : Dec 5, 2020, 10:46:49 PM
    Author     : LEGION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Receptionist View</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    </head>
    <body>
        <div class = "container">
            <h1>Hello Recepionist!</h1>
            <form name="checkout" action="../Checkout/SearchBookingView.jsp">
                <input type="submit" value="Checkout" class ="btn btn-info" />                
            </form>
        </div>
    </body>
</html>
