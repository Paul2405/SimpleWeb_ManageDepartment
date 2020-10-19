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
public class UserAppDTO implements Serializable{
    private String username, password, fullname, role, roomID,userUpdate;
    private String dateUpdate;

    public String getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public UserAppDTO() {
    }

    public UserAppDTO(String username, String password, String fullname, String role, String roomID) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.roomID = roomID;
    }

    public UserAppDTO(String username, String password, String fullname, String role, String roomID, String userUpdate, String dateUpdate) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.role = role;
        this.roomID = roomID;
        this.userUpdate = userUpdate;
        this.dateUpdate = dateUpdate;
    }
    
    
    public UserAppDTO(String username, String fullname, String role, String roomID, String userUpdate, String dateUpdate) {
        this.username = username;
        this.fullname = fullname;
        this.role = role;
        this.roomID = roomID;
        this.userUpdate = userUpdate;
        this.dateUpdate = dateUpdate;
    }

   
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
    
}
