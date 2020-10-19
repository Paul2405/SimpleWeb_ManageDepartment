/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.controller;

import haudq.dao.ManageDAO;
import haudq.dtos.DeviceDTO;
import haudq.dtos.DeviceErrorDTO;
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
public class ConfirmUpdateDeviceController extends HttpServlet {
    private static final String SUCCESS = "admin/manageDevice.jsp";
    private static final String FAIL = "error.jsp";
    private static final String INVALID = "admin/updateDevice.jsp";
    
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
        String url =  FAIL;
        try {
            String deviceID = request.getParameter("txtDeviceID");
            String name = request.getParameter("txtName");
            String description = request.getParameter("txtDescription");
            String type = request.getParameter("txtType");
            String buyDate = request.getParameter("txtBuyDate");
            String warrantyDate = request.getParameter("txtWarranty");

            ManageDAO dao = new ManageDAO();
            DeviceErrorDTO errDTO = new DeviceErrorDTO();
            boolean check = true;
            if (name.length() == 0) {
                errDTO.setDeviceNameError("Name can't be blank.");
                check = false;
            }
            if (description.length() == 0) {
                errDTO.setDeviceDescriptionError("Description can not be blank.");
                check = false;
            }
            if (type.length() == 0) {
                errDTO.setDeviceTyoeError("Type can not be blank.");
                check = false;
            }
            if (buyDate.length() == 0) {
                errDTO.setDeviceBuyError("Can't be blank.");
                check = false;
            }else if (!dao.checkDate(buyDate)) {
                errDTO.setDeviceBuyError("Date is not valid!(YYYY-MM-DD)");
                check = false;
            }
            if (warrantyDate.length() == 0) {
                errDTO.setDeviceWarrantyError("Can't be blank.");
                check = false;
            }else if (!dao.checkDate(warrantyDate)) {
                errDTO.setDeviceWarrantyError("Date is not valid!(YYYY-MM-DD)");
                check = false;
            }
            DeviceDTO dto = new DeviceDTO(deviceID, name, description, type, buyDate, warrantyDate);
            if(check){
                boolean test = dao.updateDeviceByID(dto);
                if(test){
                    request.setAttribute("SUCC", "Udpate device successful");
                    url = SUCCESS;
                }else{
                    request.setAttribute("ERROR", "Update device Error!");
                }
            }else{
                request.setAttribute("INVALID", errDTO);
                request.setAttribute("UPDEViCE", dto);
                url = INVALID;
            }

        } catch (Exception e) {
            log("ERROR at ConfirmUpdateDeviceController: " + e.getMessage());
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
