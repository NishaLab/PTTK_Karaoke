/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.User;

import Model.User;
import DAO.*;
import Model.Booking;
import Model.Client;
/**
 *
 * @author LEGION
 */
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.*;

@WebServlet("/doRegister")
public class doRegister extends HttpServlet{

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String name = request.getParameter("name");
            String adrress = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            Client client = new Client("normal", 0, username, password, email, adrress, phone, name,"customer");
            UserDAO dao = new UserDAO();
            Booking booking = new Booking();
            BookingDAO book = new BookingDAO();
            booking = book.getBooking(1);
            System.out.println(booking);
            if (username.isEmpty() || password.isEmpty()) {
                response.sendRedirect("Register.jsp");
            }
            else if (dao.saveClient(client)) {
                request.setAttribute("client_id", client);
                request.getRequestDispatcher("/MainCustomerView.jsp").forward(request, response);
            }
            else{
                response.sendRedirect(request.getPathInfo() + "Register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
