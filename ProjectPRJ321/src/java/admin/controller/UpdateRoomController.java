/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import haudq.dao.ManageDAO;
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
public class UpdateRoomController extends HttpServlet {
    private static final String SUCCESS = "admin/manageRoom.jsp";
    private static final String ERROR = "error.jsp";
    private static final String INVALID = "admin/updateRoom.jsp";
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
        String url =  ERROR;
        try {
            String roomID = request.getParameter("txtRoomID").trim();
            String roomName = request.getParameter("txtRoomName");
            
            boolean check = true;
            RoomErrorDTO errDTO =  new RoomErrorDTO();
            if(roomName.length() == 0){
                errDTO.setRoomNameError("Room name can not blank.");
                check =false;
            }
            if(check){
                ManageDAO dao =  new ManageDAO();
                boolean test  = dao.updateRoomByID(roomID, roomName);
                if(test){
                request.setAttribute("SUCC", "Update successful");
                url = SUCCESS;
                }else{
                 request.setAttribute("ERROR", "Udpate room fail.");
                }
            }else{
                request.setAttribute("INVALID", errDTO);
                url = INVALID;
            }
        } catch (Exception e) {
            log("ERROR at UpdateRoomController: " + e.getMessage());
        }finally{
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
