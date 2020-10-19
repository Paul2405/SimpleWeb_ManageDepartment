/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.dao;

import haudq.db.MyConnection;
import haudq.dtos.EmplDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PauL
 */
public class EmplDao implements Serializable {

    private Connection conn = null;
    private PreparedStatement pre = null;
    private ResultSet rs = null;

    public void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (pre != null) {
            pre.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean checkLogin(String username, String password) throws Exception {
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select EmpID from TBL_emp where EmpID = ?  and Password = ?");
            pre.setString(1, username);
            pre.setString(2, password);
            rs = pre.executeQuery();
            if (rs.next()) {
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public List<EmplDTO> LoadData() throws Exception {
        List<EmplDTO> dto = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select EmpID , EmpName, Address FROM TBL_emp");
            rs = pre.executeQuery();
            dto = new ArrayList<>();
            while (rs.next()) {
                String empId = rs.getString("EmpID");
                String empName = rs.getString("EmpName");
                String address = rs.getString("Address");
                EmplDTO empdto = new EmplDTO(empId, empName, address);
                dto.add(empdto);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public EmplDTO SearchForUpdate(String id) throws Exception {
        EmplDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select EmpName, Address FROM TBL_emp WHERE EmpID = ?");
            pre.setString(1, id);
            rs = pre.executeQuery();
            if (rs.next()) {
                String empId = id;
                String empName = rs.getString("EmpName");
                String address = rs.getString("Address");
                dto = new EmplDTO(empId, empName, address);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean Update(EmplDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update TBL_emp set EmpName = ?, Address = ? Where EmpID = ?");
            pre.setString(1, dto.getEmployeeName());
            pre.setString(2, dto.getAddress());
            pre.setString(3, dto.getEmployeeID());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
