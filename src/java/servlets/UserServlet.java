package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Role;

import models.User;
import services.RoleService;
import services.UserService;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserService us = new UserService();
        String action = (String) request.getParameter("action");
        String email = request.getParameter("Email");

        if (action != null && action.equals("edit")) {
            try {
                User editUser = us.getUser(email);
                request.setAttribute("editUser", editUser);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            session.setAttribute("change", "edit");
        }

        if (action != null && action.equals("delete")) {
            try {
                us.deleteUser(email);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            List<User> user = us.getAll();
            session.setAttribute("user", user);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        UserService us = new UserService();
        RoleService rs = new RoleService();

        String email = request.getParameter("email");
        String firstname = request.getParameter("Fname");
        String lastname = request.getParameter("Lname");
        String password = request.getParameter("password");
        int roleID = Integer.parseInt(request.getParameter("role"));

        try {
            switch (action) {
                case "add": {
                    if (password.equals("") || email.equals("") || firstname.equals("") || lastname.equals("") || password == null || email == null
                            || firstname == null || lastname == null) {
                        request.setAttribute("message", "All fields are required");
                    } else {
                        String emailCheck = us.userExist(email);
                        if (emailCheck == null) {
                            Role role = rs.getRole(roleID);
                            us.addUser(email, firstname, lastname, password, role);
                        } else {
                            request.setAttribute("message", "This email is already in use");
                        }
                    }
                }
                case "Update": {
                    session.setAttribute("change", "update");
                    if (password.equals("") || email.equals("") || firstname.equals("") || lastname.equals("") || password == null || email == null
                            || firstname == null || lastname == null) {
                        request.setAttribute("message", "All fields are required");
                    } else {
                        Role role = rs.getRole(roleID);
                        us.updateUser(email, firstname, lastname, password, role);
                    }
                }
                case "Cancel": {
                    session.setAttribute("change", "canceled");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Error");
        }

        try {
            List<User> user = us.getAll();
            request.setAttribute("user", user);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "Error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

}
