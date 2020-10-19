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
public class NotificationDTO implements Serializable{
    private String content;
    private int notifyID;

    public NotificationDTO(String content, int notifyID) {
        this.content = content;
        this.notifyID = notifyID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNotifyID() {
        return notifyID;
    }

    public void setNotifyID(int notifyID) {
        this.notifyID = notifyID;
    }
    
}
