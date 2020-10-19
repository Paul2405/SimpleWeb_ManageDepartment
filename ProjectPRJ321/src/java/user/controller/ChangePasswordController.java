/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import haudq.dao.ManageDAO;
import haudq.dtos.UserErrorDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PauL
 */
public class ChangePasswordController extends HttpServlet {

    private static final String SUCCESS = "GetDeviceInRoomUserController";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "user/changePassword.jsp";

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
            String username = (String) session.getAttribute("USERLOGIN");
            String oldPass = request.getParameter("txtOldPass");
            String newPass = request.getParameter("txtNewPass");
            String confPass = request.getParameter("txtConfPass");
            UserErrorDTO errDTO = new UserErrorDTO();
            ManageDAO dao = new ManageDAO();
            boolean checkPass = dao.checkPassword(oldPass, username);
            boolean check = true;

            if (oldPass.length() == 0) {
                errDTO.setPasswordError("Can not blank.");
                check = false;
            } else if (checkPass) {
                errDTO.setPasswordError("Old password is not valid.");
                check = false;
            }
            if (newPass.length() == 0) {
                errDTO.setRoomIDError("Can not blank.");
                check = false;
            }
            if (confPass.length() == 0) {
                errDTO.setConfirmPassError("Can not blank.");
                check = false;
            } else if (!confPass.equals(newPass)) {
                errDTO.setConfirmPassError("Confirm password must be same as new password.");
                check = false;
            }
            if (check) {
                boolean test = dao.updatePassByUsername(newPass, username);
                if(test){
                    request.setAttribute("SUCC", "Change password successful!");
                    url = SUCCESS;
                }else{
                    request.setAttribute("ERROR", "Change password fails");
                }
            }else{
                request.setAttribute("INVALID", errDTO);
                url = INVALID;
            }
        } catch (Exception e) {
            log("ERROR at ChangePasswordController: " + e.getMessage());
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
