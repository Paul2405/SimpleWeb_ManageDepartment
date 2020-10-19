/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.controller;

import haudq.dao.ManageDAO;
import haudq.dtos.HistoryDeviceDTO;
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
public class RequestController extends HttpServlet {

    private static final String SUCCESS = "GetDeviceInRoomUserController";
    private static final String INVALID = "user/request.jsp";
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
            String userID = (String)session.getAttribute("USERLOGIN");
            String deviceID = request.getParameter("deviceID");
            Date day = new Date();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            String date = dateFormat.format(day);
            String content = request.getParameter("txtContent");
            boolean isGet = false;
            
            ManageDAO dao =  new ManageDAO();
            HistoryDeviceDTO dto =  new HistoryDeviceDTO(date, content, userID, deviceID, isGet);
            boolean check = true;
            if (content.length() == 0) {
                check = false;
            }
            if (check) {
                boolean test = dao.insertRequest(dto);
                if(test){
                    request.setAttribute("SUCC", "Send request successfull.");
                    url = SUCCESS;
                }else{
                    request.setAttribute("ERROR", "Send request fail!");
                }
            } else {
                request.setAttribute("INVALID", "Can not blank.");
                url = INVALID;
            }

        } catch (Exception e) {
            log("ERROR at RequestController: " + e.getMessage());
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
