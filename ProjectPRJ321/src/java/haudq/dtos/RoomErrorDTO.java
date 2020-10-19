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
public class RoomErrorDTO implements Serializable{
    private String roomIDError, roomNameError;
    private boolean statusError;


    public RoomErrorDTO() {
    }
    

    public String getRoomIDError() {
        return roomIDError;
    }

    public void setRoomIDError(String roomIDError) {
        this.roomIDError = roomIDError;
    }

    public String getRoomNameError() {
        return roomNameError;
    }

    public void setRoomNameError(String roomNameError) {
        this.roomNameError = roomNameError;
    }

    public boolean isStatusError() {
        return statusError;
    }

    public void setStatusError(boolean statusError) {
        this.statusError = statusError;
    }

   
    
}
