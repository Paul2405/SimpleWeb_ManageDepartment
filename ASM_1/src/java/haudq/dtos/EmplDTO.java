/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.dtos;

import java.io.Serializable;

/**
 *
 * @author PauL
 */
public class EmplDTO implements Serializable{
    private String EmployeeID, Password, EmployeeName, Address;

    public EmplDTO(String EmployeeID, String EmployeeName, String Address) {
        this.EmployeeID = EmployeeID;
        this.EmployeeName = EmployeeName;
        this.Address = Address;
    }

    public EmplDTO() {
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public String getAddress() {
        return Address;
    }

    public void setEmployeeID(String EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setEmployeeName(String EmployeeName) {
        this.EmployeeName = EmployeeName;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    
}
