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
public class DeviceErrorDTO implements Serializable{
    private String deviceIDError,roomIDError, deviceNameError, deviceDescriptionError,deviceTyoeError,deviceImageError, deviceBuyError, deviceWarrantyError;

    public DeviceErrorDTO() {
    }

    public String getDeviceIDError() {
        return deviceIDError;
    }

    public void setDeviceIDError(String deviceIDError) {
        this.deviceIDError = deviceIDError;
    }

    public String getRoomIDError() {
        return roomIDError;
    }

    public void setRoomIDError(String roomIDError) {
        this.roomIDError = roomIDError;
    }

    public String getDeviceNameError() {
        return deviceNameError;
    }

    public void setDeviceNameError(String deviceNameError) {
        this.deviceNameError = deviceNameError;
    }

    public String getDeviceDescriptionError() {
        return deviceDescriptionError;
    }

    public void setDeviceDescriptionError(String deviceDescriptionError) {
        this.deviceDescriptionError = deviceDescriptionError;
    }

    public String getDeviceTyoeError() {
        return deviceTyoeError;
    }

    public void setDeviceTyoeError(String deviceTyoeError) {
        this.deviceTyoeError = deviceTyoeError;
    }

    public String getDeviceImageError() {
        return deviceImageError;
    }

    public void setDeviceImageError(String deviceImageError) {
        this.deviceImageError = deviceImageError;
    }

    public String getDeviceBuyError() {
        return deviceBuyError;
    }

    public void setDeviceBuyError(String deviceBuyError) {
        this.deviceBuyError = deviceBuyError;
    }

    public String getDeviceWarrantyError() {
        return deviceWarrantyError;
    }

    public void setDeviceWarrantyError(String deviceWarrantyError) {
        this.deviceWarrantyError = deviceWarrantyError;
    }
    
}
