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
public class DeviceDTO implements Serializable{
    private String deviceID, roomID, name,decription,type,image,buyDate,warrantyDate;
    private boolean status;

    public DeviceDTO() {
    }

    public DeviceDTO(String deviceID, String name, String decription, String type, String buyDate, String warrantyDate) {
        this.deviceID = deviceID;
        this.name = name;
        this.decription = decription;
        this.type = type;
        this.buyDate = buyDate;
        this.warrantyDate = warrantyDate;
    }

    public DeviceDTO(String deviceID, String name, String decription, String type, String image, String buyDate, String warrantyDate) {
        this.deviceID = deviceID;
        this.name = name;
        this.decription = decription;
        this.type = type;
        this.image = image;
        this.buyDate = buyDate;
        this.warrantyDate = warrantyDate;
    }

    public DeviceDTO(String deviceID, String roomID, String name, String decription, String type, String buyDate, String warrantyDate, boolean status) {
        this.deviceID = deviceID;
        this.roomID = roomID;
        this.name = name;
        this.decription = decription;
        this.type = type;
        this.buyDate = buyDate;
        this.warrantyDate = warrantyDate;
        this.status = status;
    }
     

    public DeviceDTO(String deviceID, String roomID, String name, String decription, String type, String image, String buyDate, String warrantyDate, boolean status) {
        this.deviceID = deviceID;
        this.roomID = roomID;
        this.name = name;
        this.decription = decription;
        this.type = type;
        this.image = image;
        this.buyDate = buyDate;
        this.warrantyDate = warrantyDate;
        this.status = status;
    }
    

    public DeviceDTO(String deviceID, String name, String image) {
        this.deviceID = deviceID;
        this.name = name;
        this.image = image;
    }
    
    
    
    
    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(String warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
