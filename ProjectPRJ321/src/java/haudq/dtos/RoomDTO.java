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
public class RoomDTO implements Serializable{
    private String roomID, roomName;
    boolean status;

    public RoomDTO() {
    }

    public RoomDTO(String roomID, String roomName,  boolean status) {
        this.roomID = roomID;
        this.roomName = roomName;
        this.status = status;
    }

    public RoomDTO(String roomID, String roomName) {
        this.roomID = roomID;
        this.roomName = roomName;
    }
    
    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
