/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import haudq.dao.ManageDAO;
import haudq.dtos.LocationDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PauL
 */
public class FinishAddController extends HttpServlet {

    private static final String SUCCESS = "SelectRoomToAddController";
    private static final String FAIL = "error.jsp";

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
        String url = FAIL;
        try {
            String roomID = request.getParameter("roomID");
            String userID = request.getParameter("userID");
            String[] deviceID = request.getParameterValues("chkChoose");
            String status = request.getParameter("status");
            
            ManageDAO dao = new ManageDAO();
            Date day = new Date();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            String dateChange = dateFormat.format(day);
            boolean checkSetStatusDevice = false;
            boolean checkSetRoomIDDevice = false;
            boolean checkFinishAdd = false;
            List<LocationDTO> listLocation = new ArrayList<>();
            for (String str : deviceID) {
                checkSetStatusDevice = dao.setStatusDeviceID(false, str);
                checkSetRoomIDDevice = dao.setRoomIDByDeviceID(roomID, str);
                LocationDTO local = new LocationDTO(roomID, userID, str, dateChange, "Add new");
                listLocation.add(local);
            }

            boolean checkSetStatusRoom = dao.setStatusForRoomByRoomID(false, roomID);
            boolean checkSetRoomIDForUser = dao.setRoomIDFromUserApp(roomID, userID);
            for (LocationDTO lo : listLocation) {
                checkFinishAdd = dao.insertIntoLocation(lo);
            }
            if (checkSetStatusRoom && checkSetStatusDevice && checkSetRoomIDDevice && checkSetRoomIDForUser && checkFinishAdd) {
                url = SUCCESS;
            } else {
                request.setAttribute("ERROR", "Add data fail.");
            }
        } catch (Exception e) {
            log("ERROR at FinishAddController: " + e.getMessage());
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
