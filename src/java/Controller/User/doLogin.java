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

/**
 *
 * @author LEGION
 */
@WebServlet("/doLogin")

public class doLogin extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            UserDAO dao = new UserDAO();

            if (username.isEmpty() || password.isEmpty()) {
                response.sendRedirect("Login.jsp");
            } else if (dao.checkLogin(user)) {
                System.out.println(user.getRole());
                if (user.getRole().equalsIgnoreCase("customer") ) {
                    response.sendRedirect("MainCustomerView.jsp");
                } else if (user.getRole().equalsIgnoreCase("receptionist")) {
                    response.sendRedirect("MainReceptionist.jsp");
                } else {
                    response.sendRedirect("index.jsp");
                }
            } else {
                response.sendRedirect("Login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
