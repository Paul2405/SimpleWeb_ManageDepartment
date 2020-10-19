/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import haudq.dao.ManageDAO;
import haudq.dtos.LocationDTO;
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
public class ChangeLocationController extends HttpServlet {

    private static final String SUCCESS = "DetailLocationController";
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
            HttpSession session = request.getSession();
            String userChange = (String) session.getAttribute("USERLOGIN");
            String idCur = request.getParameter("txtCurRoomID").trim();
            String idOld = request.getParameter("txtOldRoomID").trim();
            String idDevice = request.getParameter("txtDeviceID").trim();
            String dateCur = request.getParameter("txtDateCur").trim();
            String idChange = request.getParameter("ListRoomID").trim();
            String reason = request.getParameter("txtReason");
            Date day = new Date();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            String dateChange = dateFormat.format(day);
            LocationDTO dto = new LocationDTO(idChange, userChange, idDevice, dateChange, reason);

            ManageDAO dao = new ManageDAO();
            boolean checkChangeCurToOld = dao.changeLocationCurentToOld(dateCur, idCur, idDevice);
            boolean checkConfirmChange = dao.confirmChangeLocation(idCur, idDevice);
            boolean checkDeleteOld = true;
            if (idOld.length() != 0) {
                dao.deleteLocationOld(idOld, idDevice);
            }
            boolean checkAdd = dao.insertIntoLocation(dto);
            boolean checkConfirmAdd = dao.setRoomIDByDeviceID(idChange,idDevice);
            if (checkAdd && checkChangeCurToOld && checkConfirmChange && checkDeleteOld && checkConfirmAdd) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("ERROR at ChangeLocationController: " + e.getMessage());
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
