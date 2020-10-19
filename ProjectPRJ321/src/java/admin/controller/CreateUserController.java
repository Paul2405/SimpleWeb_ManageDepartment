/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import haudq.dao.ManageDAO;
import haudq.dtos.UserAppDTO;
import haudq.dtos.UserErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PauL
 */
public class CreateUserController extends HttpServlet {

    private static final String SUCCESS = "admin/manageUserRole.jsp";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "admin/createUser.jsp";

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
            HttpSession session = request.getSession();
            String username = request.getParameter("txtUsername");
            String fullname = request.getParameter("txtFullname");
            String password = request.getParameter("txtPassword");
            String confirmPass = request.getParameter("txtConfirmPass");
            String role = request.getParameter("txtRole");
            String userUpdate = (String) session.getAttribute("USERLOGIN");
            Date day = new Date();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String dateUpdate = format.format(day);
            boolean check = true;
            ManageDAO dao = new ManageDAO();
            UserErrorDTO errDTO = new UserErrorDTO();

           
            if (fullname.length() == 0 || password.length() == 0 || role.length() == 0) {
                errDTO.setFullnameError("Can't be Blank!");
                check = false;
            }
            if (username.length() == 0) {
                errDTO.setUsernameError("Can't be Blank!");
                check = false;
            } else if (dao.checkUsername(username)) {
                errDTO.setUsernameError("Username is existed!");
                check = false;
            }
            if (!confirmPass.equals(password)) {
                errDTO.setConfirmPassError("Confirm password must be same as password!");
                check = false;
            }

            if (check) {
                UserAppDTO dto = new UserAppDTO(username, password, fullname, role, "", userUpdate, dateUpdate);
                boolean insert = dao.insertUserIntoDB(dto);
                if (insert) {
                    request.setAttribute("SUCCESS", "Create User SucessFull");
                    String contentNotify = "Welcome " + fullname + ", your account is created at " + dateUpdate;
                    dao.insertNotification(username, contentNotify, true);
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Create User fail");
                    url = ERROR;
                }
            } else {
                UserAppDTO data = new UserAppDTO(username, password, fullname, role, "");
                request.setAttribute("REDATA", data);
                request.setAttribute("NO", errDTO);
                url = INVALID;
            }

        } catch (Exception e) {
            log("ERROR at CreateUserController: " + e.getMessage());
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
