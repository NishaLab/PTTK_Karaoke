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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
