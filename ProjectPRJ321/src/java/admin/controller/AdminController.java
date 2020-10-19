/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PauL
 */
public class AdminController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SEARCH = "SearchUserController";
    private static final String DELETE = "DeleteUserController";
    private static final String UPDATE = "UpdateUserController";
    private static final String CONFUPDATE = "ConfirmUpdateUserController";
    private static final String CREATE = "CreateUserController";

    private static final String DELETEDEVICE = "DeleteDeviceController";
    private static final String CREATEDEVICE = "CreateDeviceController";
    private static final String SEARCHDEVICE = "SearchDeviceController";
    private static final String UPDATEIMAGEDEVICE = "UpdateImgDeviceController";
    private static final String DETAILIMG = "DetailsImgController";
    private static final String DETAILLOCATION = "DetailLocationController";
    private static final String CHANGELOCATION = "ChangeLocationController";
    private static final String UPDATEDEVICE = "UpdateDeviceController";
    private static final String CONFIRMUPDATEDEVICE = "ConfirmUpdateDeviceController";
    private static final String HISTORYDEVICE = "HistoryDeviceController";

    private static final String SEARCHROOM = "SearchRoomController";
    private static final String CREATEROOM = "CreateRoomController";
    private static final String UPDATEROOM = "UpdateRoomController";

    private static final String ADDUSERANDDEVICEINTOROOM = "SelectRoomToAddController";

    private static final String NOTIFICATION = "NotifyAdminController";

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
            String action = request.getParameter("action");
            if (action.equals("Search")) {
                url = SEARCH;
            } else if (action.equals("Delete")) {
                url = DELETE;
            } else if (action.equals("Update")) {
                url = UPDATE;
            } else if (action.equals("Update data")) {
                url = CONFUPDATE;
            } else if (action.equals("Create")) {
                url = CREATE;
            } else if (action.equals("DeleteDevice")) {
                url = DELETEDEVICE;
            } else if (action.equals("Create device")) {
                url = CREATEDEVICE;
            } else if (action.equals("Search Device")) {
                url = SEARCHDEVICE;
            } else if (action.equals("Update image")) {
                url = UPDATEIMAGEDEVICE;
            } else if (action.equals("DetailsImage")) {
                url = DETAILIMG;
            } else if (action.equals("DetailLocation")) {
                url = DETAILLOCATION;
            } else if (action.equals("Change")) {
                url = CHANGELOCATION;
            } else if (action.equals("Update device")) {
                url = UPDATEDEVICE;
            } else if (action.equals("Save update")) {
                url = CONFIRMUPDATEDEVICE;
            } else if (action.equals("Search Room")) {
                url = SEARCHROOM;
            } else if (action.equals("Create room")) {
                url = CREATEROOM;
            } else if (action.equals("Update room")) {
                url = UPDATEROOM;
            } else if (action.equals("addUserAndDeviceToRoom")) {
                url = ADDUSERANDDEVICEINTOROOM;
            } else if (action.equals("deviceHistory")) {
                url = HISTORYDEVICE;
            } else if (action.equals("notify")) {
                url = NOTIFICATION;
            } else {
                request.setAttribute("ERROR", "Your action is invalid!");
            }
        } catch (Exception e) {
            log("ERROR at AdminController: " + e.getMessage());
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
