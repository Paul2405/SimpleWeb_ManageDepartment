/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.Controller;

import haudq.dao.ManageDAO;
import haudq.dtos.UserErrorDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PauL
 */
public class LoginController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String ADMIN = "/admin/begin.jsp";
    private static final String USER = "GetDeviceInRoomUserController";
    private static final String STAFF = "GetDeviceToStaffController";
    private static final String INVALID = "index.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            UserErrorDTO errUSER = new UserErrorDTO();
            boolean check = true;
            if (username.length() == 0) {
                errUSER.setUsernameError("Username can't be blank!");
                check = false;
            }
            if (password.length() == 0) {
                errUSER.setPasswordError("Password can't be blank!");
                check = false;
            }

            if (check) {
                ManageDAO dao = new ManageDAO();
                String role = dao.checkLogin(username, password);
                HttpSession session = request.getSession();
                session.setAttribute("ROLE", role);
                session.setAttribute("USERLOGIN", username);
                if (role.equals("false")) {
                    request.setAttribute("ERROR", "Username or password is invalid!");
                } else if (role.equals("admin")) {
                    url = ADMIN;
                } else if (role.equals("user")) {
                    url = USER;
                } else if (role.equals("staff")) {
                    url = STAFF;
                } else {
                    request.setAttribute("ERROR", "Your Role is invalid");
                }
            } else {
                request.setAttribute("INVALID", errUSER);
                url = INVALID;
            }

           
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
