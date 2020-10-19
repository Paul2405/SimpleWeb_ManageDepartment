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
public class HistoryDeviceDTO implements Serializable {

    private String timeRequest, fixConttent, userRequest, deviceID, userRepair, startTime, endTime, repairConttent;
    private boolean resultRepair, isGetRequest;

    public HistoryDeviceDTO() {
    }

    public HistoryDeviceDTO(String timeRequest, String fixConttent, String userRequest, String deviceID, String userRepair, String startTime, String endTime, String repairConttent, boolean resultRepair, boolean isGetRequest) {
        this.timeRequest = timeRequest;
        this.fixConttent = fixConttent;
        this.userRequest = userRequest;
        this.deviceID = deviceID;
        this.userRepair = userRepair;
        this.startTime = startTime;
        this.endTime = endTime;
        this.repairConttent = repairConttent;
        this.resultRepair = resultRepair;
        this.isGetRequest = isGetRequest;
    }

    public HistoryDeviceDTO(String timeRequest, String fixConttent, String userRequest, String deviceID, boolean isGetRequest) {
        this.timeRequest = timeRequest;
        this.fixConttent = fixConttent;
        this.userRequest = userRequest;
        this.deviceID = deviceID;
        this.isGetRequest = isGetRequest;
    }

    public HistoryDeviceDTO(String timeRequest, String fixConttent, String userRequest, String deviceID, String userRepair, String startTime) {
        this.timeRequest = timeRequest;
        this.fixConttent = fixConttent;
        this.userRequest = userRequest;
        this.deviceID = deviceID;
        this.userRepair = userRepair;
        this.startTime = startTime;
    }
    
    
    public String getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(String timeRequest) {
        this.timeRequest = timeRequest;
    }

    public String getFixConttent() {
        return fixConttent;
    }

    public void setFixConttent(String fixConttent) {
        this.fixConttent = fixConttent;
    }

    public String getUserRequest() {
        return userRequest;
    }

    public void setUserRequest(String userRequest) {
        this.userRequest = userRequest;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUserRepair() {
        return userRepair;
    }

    public void setUserRepair(String userRepair) {
        this.userRepair = userRepair;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRepairConttent() {
        return repairConttent;
    }

    public void setRepairConttent(String repairConttent) {
        this.repairConttent = repairConttent;
    }

    public boolean isResultRepair() {
        return resultRepair;
    }

    public void setResultRepair(boolean resultRepair) {
        this.resultRepair = resultRepair;
    }

    public boolean isIsGetRequest() {
        return isGetRequest;
    }

    public void setIsGetRequest(boolean isGetRequest) {
        this.isGetRequest = isGetRequest;
    }
    

}
