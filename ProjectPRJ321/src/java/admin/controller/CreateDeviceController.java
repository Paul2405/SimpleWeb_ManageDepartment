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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PauL
 */
public class CreateDeviceController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "admin/manageDevice.jsp";
    private static final String INVALID = "admin/createDevice.jsp";

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
            String deviceID = request.getParameter("txtDeviceID");
            String name = request.getParameter("txtName");
            String description = request.getParameter("txtDescription");
            String type = request.getParameter("txtType");
            String buyDate = request.getParameter("txtBuyDate");
            String warrdate = request.getParameter("txtWarrantyDate");
            boolean status = true;
            boolean check = true;

            ManageDAO dao = new ManageDAO();
            DeviceErrorDTO errDTO = new DeviceErrorDTO();
            if (deviceID.length() == 0) {
                errDTO.setDeviceIDError("Can't be blank.");
                check = false;
            }
            if (dao.checkDeviceID(deviceID)) {
                errDTO.setDeviceIDError("DeviceID ia existed.");
                check = false;
            }
            if (name.length() == 0) {
                errDTO.setDeviceNameError("Can't be blank.");
                check = false;
            }
            if (description.length() == 0) {
                errDTO.setDeviceDescriptionError("Can't be blank.");
                check = false;
            }
            if (type.length() == 0) {
                errDTO.setDeviceTyoeError("Can't be blank.");
                check = false;
            }
           
            if (buyDate.length() == 0) {
                errDTO.setDeviceBuyError("Can't be blank.");
                check = false;
            }else if (!dao.checkDate(buyDate)) {
                errDTO.setDeviceBuyError("Date is not valid!(YYYY-MM-DD)");
                check = false;
            }
            if (warrdate.length() == 0) {
                errDTO.setDeviceWarrantyError("Can't be blank.");
                check = false;
            }else if (!dao.checkDate(warrdate)) {
                errDTO.setDeviceWarrantyError("Date is not valid!(YYYY-MM-DD)");
                check = false;
            }
           
            DeviceDTO device = new DeviceDTO(deviceID, "", name, description, type, buyDate, warrdate, status);
            if (check) {
                boolean add = dao.insertDevice(device);
                if (add) {
                    request.setAttribute("SUCC", "Insert new device successful.");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Insert fails");
                }
            } else {
                request.setAttribute("INVALID", errDTO);
                request.setAttribute("REDATA", device);
                url = INVALID;
            }
        } catch (Exception e) {
            log("Error at CreateDeviceController: " + e.getMessage());
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
