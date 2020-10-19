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
public class ConfirmUpdateUserController extends HttpServlet {

    private static final String SUCCESS = "SearchUserController";
    private static final String INVALID = "admin/updateUser.jsp";
    private static final String ERROR = "error.jsp";

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
            Date day = new Date();
            String username = request.getParameter("txtUsername");
            String fullname = request.getParameter("txtFullname");
            String role = request.getParameter("listRole");
            System.out.println("Role: " + role);
            String roomID = request.getParameter("listRoomID");
            String userUpdate = (String) session.getAttribute("USERLOGIN");
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String date = format.format(day);
            String oldRoom = request.getParameter("oldRoom");
            boolean check = true;
            UserErrorDTO errUser = new UserErrorDTO();

            if (fullname.length() == 0) {
                errUser.setFullnameError("Fullname can't be blank!");
                check = false;
            }

            UserAppDTO dto = new UserAppDTO(username, fullname, role, roomID, userUpdate, date);
            if (check) {
                ManageDAO dao = new ManageDAO();
                boolean update = dao.updateUserByUserID(dto);
                if (update) {
                    if (roomID.equals(oldRoom)) {
                        url = SUCCESS;
                        String contentNotify = "Your account has been update by user: " + userUpdate + " at " + date;
                        dao.insertNotification(username, contentNotify, true);
                    } else {
                        dao.setStatusForRoomByRoomID(false, roomID);
                        dao.setStatusForRoomByRoomID(true, oldRoom);
                        String contentNotify = "Your account has been update by user: " + userUpdate + " at " + date;
                        dao.insertNotification(username, contentNotify, true);
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("ERROR", "Can't not update");
                }
            } else {
                request.setAttribute("ERROR", errUser);
                request.setAttribute("DATA", dto);
                url = INVALID;
            }

        } catch (Exception e) {
            log("ERROR at confirmUpdateUserController: " + e.getMessage());
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
