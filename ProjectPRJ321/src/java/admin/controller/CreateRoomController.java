/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import haudq.dao.ManageDAO;
import haudq.dtos.RoomDTO;
import haudq.dtos.RoomErrorDTO;
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
public class CreateRoomController extends HttpServlet {

    private static final String SUCCESS = "admin/manageRoom.jsp";
    private static final String FAIL = "error.jsp";
    private static final String INVALID = "admin/createRoom.jsp";

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
            String roomID = request.getParameter("txtRoomID");
            String roomName = request.getParameter("txtRoomName");

            boolean check = true;
            ManageDAO dao = new ManageDAO();
            RoomErrorDTO errDTO = new RoomErrorDTO();
            if (roomID.length() == 0) {
                errDTO.setRoomIDError("RoomID can not be blank.");
                check = false;
            } else if (dao.checkRoomID(roomID)) {
                errDTO.setRoomIDError("RoomID is existed.");
                check = false;
            }
            if (roomName.length() == 0) {
                errDTO.setRoomNameError("Room name can not be blank.");
                check = false;
            }
            RoomDTO dto = new RoomDTO(roomID, roomName, true);
            if (check) {
                boolean test = dao.insertRoom(dto);
                if (test) {
                    request.setAttribute("SUCC", "Create room successfull.");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Create room fail.");
                }
            } else {
                request.setAttribute("INVALID", errDTO);
                url = INVALID;
            }
        } catch (Exception e) {
            log("ERROR at CreateRoomController: " + e.getMessage());
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
