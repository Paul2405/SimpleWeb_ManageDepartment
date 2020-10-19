/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package haudq.dao;

import haudq.db.MyConnection;
import haudq.dtos.DeviceDTO;
import haudq.dtos.HistoryDeviceDTO;
import haudq.dtos.LocationDTO;
import haudq.dtos.NotificationDTO;
import haudq.dtos.RoomDTO;
import haudq.dtos.UserAppDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author PauL
 */
public class ManageDAO implements Serializable {

    private Connection conn;
    private PreparedStatement pre;
    private ResultSet rs;

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

    public List<NotificationDTO> getNotifyByUserID(String userID, boolean seen) throws Exception {
        List<NotificationDTO> notify = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select NotifyID ,Conttent From Notification where NotifyToUserID = ? and Seen = ?");
            pre.setString(1, userID);
            pre.setBoolean(2, seen);
            notify = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String t = rs.getString("Conttent");
                int id = rs.getInt("NotifyID");
                NotificationDTO dto =  new NotificationDTO(t, id);
                notify.add(dto);
            }
        } finally {
            closeConnection();
        }
        return notify;
    }

    public boolean deleteNotification(String userID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Delete Notification where NotifyToUserID = ?");
            pre.setString(1, userID);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean setSeen(boolean seen, int id) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update Notification set Seen = ? Where NotifyID = ? ");
            pre.setBoolean(1, seen);
            pre.setInt(2, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertNotification(String userReceive, String content, boolean seen) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Insert into Notification(NotifyToUserID,Conttent,Seen) values(?,?,?)");
            pre.setString(1, userReceive);
            pre.setString(2, content);
            pre.setBoolean(3, seen);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getNumberRepairs(String deviceID) throws Exception {
        int number = 0;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select count(IsgetRequest) as count From Request where DeviceID = ?");
            pre.setString(1, deviceID);
            rs = pre.executeQuery();
            if (rs.next()) {
                number = rs.getInt("count");
            }
        } finally {
            closeConnection();
        }
        return number;
    }

    public boolean finishRepair(String username, String deviceID, String endDate, String contentRepair, boolean isget, boolean repair, boolean reRepair) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update Request set EndRepairDate = ?, RepairConttent = ? ,ResultRepair = ? Where UserRepair = ? and DeviceID = ? and IsgetRequest = ? and ResultRepair = ? ");
            pre.setString(1, endDate);
            pre.setString(2, contentRepair);
            pre.setBoolean(3, reRepair);
            pre.setString(4, username);
            pre.setString(5, deviceID);
            pre.setBoolean(6, isget);
            pre.setBoolean(7, repair);
            check = pre.executeUpdate() > 0;
        } catch (Exception e) {
        }
        return check;
    }

    public List<HistoryDeviceDTO> getMyJob(String username, boolean isget, boolean repair) throws Exception {
        List<HistoryDeviceDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select TimeRequest,FixedContent,UserRequest,DeviceID,StartRepairDate From Request where IsgetRequest = ? and UserRepair = ? and ResultRepair = ?");
            pre.setBoolean(1, isget);
            pre.setString(2, username);
            pre.setBoolean(3, repair);
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String timeRequest = rs.getString("TimeRequest");
                String fixContent = rs.getString("FixedContent");
                String userRequest = rs.getString("UserRequest");
                String userRepair = username;
                String deviceID = rs.getString("DeviceID");
                String startDate = rs.getString("StartRepairDate");
                HistoryDeviceDTO dto = new HistoryDeviceDTO(timeRequest, fixContent, userRequest, deviceID, userRepair, startDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean setIsgetRequest(boolean reIsget, String userRepair, String startDate, boolean repair, String deviceID, boolean isget) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update Request set IsgetRequest = ?, UserRepair = ?, StartRepairDate= ?,ResultRepair=? Where DeviceID = ? and IsgetRequest = ?");
            pre.setBoolean(1, reIsget);
            pre.setString(2, userRepair);
            pre.setString(3, startDate);
            pre.setBoolean(4, repair);
            pre.setString(5, deviceID);
            pre.setBoolean(6, isget);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<HistoryDeviceDTO> getRequestIsGetByStatusFalse(boolean bool) throws Exception {
        List<HistoryDeviceDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select TimeRequest,FixedContent,UserRequest,DeviceID From Request where IsgetRequest = ?");
            pre.setBoolean(1, bool);
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String timeRequest = rs.getString("TimeRequest");
                String fixContent = rs.getString("FixedContent");
                boolean isGet = bool;
                String userRequest = rs.getString("UserRequest");
                String deviceID = rs.getString("DeviceID");
                HistoryDeviceDTO dto = new HistoryDeviceDTO(timeRequest, fixContent, userRequest, deviceID, isGet);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertRequest(HistoryDeviceDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("INSERT into Request(TimeRequest, FixedContent, IsgetRequest, UserRequest, DeviceID) values(?,?,?,?,?)");
            pre.setString(1, dto.getTimeRequest());
            pre.setString(2, dto.getFixConttent());
            pre.setBoolean(3, dto.isIsGetRequest());
            pre.setString(4, dto.getUserRequest());
            pre.setString(5, dto.getDeviceID());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<HistoryDeviceDTO> getListHistoryDevice(String deviceID) throws Exception {
        List<HistoryDeviceDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select TimeRequest,FixedContent,IsgetRequest, UserRequest,UserRepair,StartRepairDate,EndRepairDate,ResultRepair ,RepairConttent From Request where DeviceID = ?");
            pre.setString(1, deviceID);
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String timeRequest = rs.getString("TimeRequest");
                String fixConttent = rs.getString("FixedContent");
                boolean isGetRequest = rs.getBoolean("IsgetRequest");
                String userRequest = rs.getString("UserRequest");
                String deID = deviceID;
                String userRepair = rs.getString("UserRepair");
                String startTime = rs.getString("StartRepairDate");
                String endTime = rs.getString("EndRepairDate");
                boolean resultRepair = rs.getBoolean("ResultRepair");
                String rePairconttent = rs.getString("RepairConttent");
                HistoryDeviceDTO his = new HistoryDeviceDTO(timeRequest, fixConttent, userRequest, deviceID, userRepair, startTime, endTime, rePairconttent, resultRepair, isGetRequest);
                result.add(his);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getRoomIDByDeviceID(String deviceID) throws Exception {
        String roomID = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select RoomID From Device Where DeviceID = ?");
            pre.setString(1, deviceID);
            rs = pre.executeQuery();
            if (rs.next()) {
                roomID = rs.getString("RoomID");
            }
        } finally {
            closeConnection();
        }
        return roomID;
    }

    public boolean updatePassByUsername(String password, String username) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update UserApp set Password = ? where Username = ?");
            pre.setString(1, password);
            pre.setString(2, username);
            check = pre.executeUpdate() > 0;
        } finally {

        }
        return check;
    }

    public boolean checkPassword(String password, String username) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("select Password from UserApp where Username = ?");
            pre.setString(1, username);
            rs = pre.executeQuery();
            if (rs.next()) {
                String pass = rs.getString("Password");
                if (password.equals(pass)) {
                    check = true;
                }
            }
        } finally {

        }
        return check;
    }

    public List<DeviceDTO> getListDeviceLikeNameAndRoomID(String roomID, String search, boolean bool) throws Exception {
        List<DeviceDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("SELECT DeviceID, Name, Description, Type,Img, BuyDate, WarrantyDate FROM Device WHERE Name like ? and Status = ? and RoomID = ?");
            pre.setString(1, "%" + search + "%");
            pre.setBoolean(2, bool);
            pre.setString(3, roomID);
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String deviceID = rs.getString("DeviceID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                String buyDate = rs.getString("BuyDate");
                String WarrDate = rs.getString("WarrantyDate");
                String img = rs.getString("Img");
                DeviceDTO dto = new DeviceDTO(deviceID, name, description, type, img, buyDate, WarrDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getRoomIDByUserName(String username) throws Exception {
        String roomID = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select RoomID From UserApp where Username = ?");
            pre.setString(1, username);
            rs = pre.executeQuery();
            if (rs.next()) {
                roomID = rs.getString("RoomID");
            }
        } finally {
            closeConnection();
        }
        return roomID;
    }

    public List<DeviceDTO> getListDeviceInRoom(String roomID) throws Exception {
        List<DeviceDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select DeviceID, Name, Description, Type, Img, BuyDate, WarrantyDate from Device where RoomID = ? and Status = ? ");
            pre.setString(1, roomID);
            pre.setBoolean(2, false);
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String deviceID = rs.getString("DeviceID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                String img = rs.getString("Img");
                String buydate = rs.getString("BuyDate");
                String warrDate = rs.getString("WarrantyDate");

                DeviceDTO dto = new DeviceDTO(deviceID, name, description, type, img, buydate, warrDate);
                result.add(dto);

            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getUserNameByRoomID(String RoomID) throws Exception {
        String userID = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select Username From UserApp where RoomID = ?");
            pre.setString(1, RoomID);
            rs = pre.executeQuery();
            if (rs.next()) {
                userID = rs.getString("Username");
            }
        } finally {
            closeConnection();
        }
        return userID;
    }

    public boolean realDeleteRoom(String roomID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Delete Room where RoomID = ?");
            pre.setString(1, roomID);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean setRoomIDFromUserApp(String roomID, String username) throws Exception {
        boolean check = true;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update UserApp set RoomID = ? Where Username=?");
            pre.setString(1, roomID);
            pre.setString(2, username);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<DeviceDTO> getListDeviceToAdd() throws Exception {
        List<DeviceDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("SELECT DeviceID, Name, Description, Type,Img, BuyDate, WarrantyDate FROM Device WHERE Status = ?");
            pre.setBoolean(1, true);
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String deviceID = rs.getString("DeviceID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                String buyDate = rs.getString("BuyDate");
                String WarrDate = rs.getString("WarrantyDate");
                String img = rs.getString("Img");
                DeviceDTO dto = new DeviceDTO(deviceID, name, description, type, img, buyDate, WarrDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<UserAppDTO> getListUserToAddIntoRoom(String roomID) throws Exception {
        List<UserAppDTO> dto = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select Username, Password, Fullname, Role  From UserApp WHERE RoomID is null ");
            dto = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String username = rs.getString("Username").trim();
                String password = rs.getString("Password").trim();
                String fullname = rs.getString("Fullname").trim();
                String role = rs.getString("Role").trim();
                String roomId = roomID;
                UserAppDTO data = new UserAppDTO(username, password, fullname, role, roomId);
                dto.add(data);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<RoomDTO> getListRoomToAddData() throws Exception {
        List<RoomDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select RoomID, RoomName,Status FROM Room ");
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("RoomID");
                String roomName = rs.getString("RoomName");
                boolean status = rs.getBoolean("Status");
                RoomDTO dto = new RoomDTO(roomID, roomName, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insertRoom(RoomDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("INSERT into Room(RoomID, RoomName,Status) values(?,?,?)");
            pre.setString(1, dto.getRoomID());
            pre.setString(2, dto.getRoomName());
            pre.setBoolean(3, dto.isStatus());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateRoomByID(String roomID, String name) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update Room set RoomName = ? Where RoomID = ?");
            pre.setString(1, name);
            pre.setString(2, roomID);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteRoomToUser(String roomID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update UserApp set RoomID = null where RoomID = ?");
            pre.setString(1, roomID);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkRoomID(String roomID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select RoomID from Room");
            rs = pre.executeQuery();
            while (rs.next()) {
                if (rs.getString("RoomID").trim().equals(roomID)) {
                    check = true;
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<RoomDTO> searchRoomByName(String nameRoom) throws Exception {
        List<RoomDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select RoomID, RoomName FROM Room  Where RoomName like ? and Status = ?");
            pre.setString(1, "%" + nameRoom + "%");
            pre.setBoolean(2, false);
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("RoomID");
                String roomName = rs.getString("RoomName");
                boolean status = false;
                RoomDTO dto = new RoomDTO(roomID, roomName, status);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateDeviceByID(DeviceDTO device) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("UPDATE Device set Name = ?, Description = ?, Type = ?, BuyDate = ?, WarrantyDate= ?  WHERE DeviceID = ?");
            pre.setString(1, device.getName());
            pre.setString(2, device.getDecription());
            pre.setString(3, device.getType());
            pre.setString(4, device.getBuyDate());
            pre.setString(5, device.getWarrantyDate());
            pre.setString(6, device.getDeviceID());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public DeviceDTO getDeviceByID(String deviceID) throws Exception {
        DeviceDTO result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("SELECT RoomID, Name, Description, Type, BuyDate, WarrantyDate FROM Device WHERE DeviceID = ? ");
            pre.setString(1, deviceID);
            rs = pre.executeQuery();
            while (rs.next()) {
                String id = deviceID;
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                String buyDate = rs.getString("BuyDate");
                String roomID = rs.getString("RoomID").trim();
                String WarrDate = rs.getString("WarrantyDate");
                result = new DeviceDTO(deviceID, roomID, name, description, type, buyDate, WarrDate, false);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean setRoomIDByDeviceID(String idRoomChange, String deviceID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update Device set RoomID = ? where DeviceID = ?");
            pre.setString(1, idRoomChange);
            pre.setString(2, deviceID);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertIntoLocation(LocationDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("INSERT INTO LocationHistory(Reason, CurentDate, ChangeDate, LocalRoomID, Username, DeviceID) values(?,?,?,?,?,?)");
            pre.setString(1, dto.getReason());
            pre.setString(2, dto.getChangeDate());
            pre.setString(3, null);
            pre.setString(4, dto.getRoomID());
            pre.setString(5, dto.getUsername());
            pre.setString(6, dto.getDeviceID());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteLocationOld(String idOld, String idDevice) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Delete LocationHistory where LocalRoomID=? and DeviceID = ?");
            pre.setString(1, idOld);
            pre.setString(2, idDevice);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean confirmChangeLocation(String idCur, String idDevice) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update LocationHistory set CurentDate = NULL where LocalRoomID=? and DeviceID = ?");
            pre.setString(1, idCur);
            pre.setString(2, idDevice);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean changeLocationCurentToOld(String curDate, String idCur, String idDevice) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update LocationHistory set ChangeDate = ? where LocalRoomID=? and DeviceID = ?");
            pre.setString(1, curDate);
            pre.setString(2, idCur);
            pre.setString(3, idDevice);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<String> getListRoomIDExcept(String id) throws Exception {
        List<String> list = new ArrayList<>();
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select RoomID From Room where RoomID != ?");
            pre.setString(1, id);
            rs = pre.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("RoomID");
                list.add(roomID);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public LocationDTO getOldLocation(String deviceID) throws Exception {
        LocationDTO result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select LocalRoomID, Username, ChangeDate, Reason FROM LocationHistory Where DeviceID = ? and CurentDate is Null");
            pre.setString(1, deviceID.trim());
            rs = pre.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("LocalRoomID");
                String username = rs.getString("Username");
                String changeDate = rs.getString("ChangeDate");
                String reason = rs.getString("Reason");
                result = new LocationDTO(roomID, username, deviceID, changeDate, reason);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public LocationDTO getCurrerntLocation(String deviceID) throws Exception {
        LocationDTO result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select LocalRoomID, Username, CurentDate, Reason FROM LocationHistory Where DeviceID = ? and ChangeDate is Null");
            pre.setString(1, deviceID.trim());
            rs = pre.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("LocalRoomID");
                String username = rs.getString("Username");
                String currentDate = rs.getString("CurentDate");
                String reason = rs.getString("Reason");
                result = new LocationDTO(roomID, username, deviceID, currentDate, reason);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateImageDevice(String img, String id) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update Device set Img = ? WHERE DeviceID = ?");
            pre.setString(1, img);
            pre.setString(2, id);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertDevice(DeviceDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Insert into Device(DeviceID, Name, Description, Type, BuyDate, WarrantyDate, Status) values(?,?,?,?,?,?,?,?)");
            pre.setString(1, dto.getDeviceID());
            pre.setString(2, dto.getName());
            pre.setString(3, dto.getDecription());
            pre.setString(4, dto.getType());
            pre.setString(5, dto.getBuyDate());
            pre.setString(6, dto.getWarrantyDate());
            pre.setBoolean(7, dto.isStatus());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkDeviceID(String deviceID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select DeviceID from Device");
            rs = pre.executeQuery();
            while (rs.next()) {
                if (rs.getString("DeviceID").trim().equals(deviceID)) {
                    check = true;
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkIputINt(String s) {
        boolean check = true;
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            check = false;
        }
        return check;
    }

    public boolean checkDate(String s) throws Exception {
        boolean check = true;
        StringTokenizer stk = new StringTokenizer(s, "-");
        int length = stk.countTokens();
        if (length != 3) {
            check = false;
        } else if (s.length() == 0) {
            check = false;
        } else {
            String year = stk.nextToken().toString();
            String month = stk.nextToken().toString();
            String date = stk.nextToken().toString();
            if (!checkIputINt(year) || !checkIputINt(month) || !checkIputINt(date)) {
                check = false;
            } else {
                int yyyy = Integer.parseInt(year);
                int mm = Integer.parseInt(month);
                int dd = Integer.parseInt(date);
                if (yyyy % 400 == 0 || yyyy % 4 == 0 && yyyy % 100 != 0) {
                    if (mm == 2) {
                        if (dd < 0 || dd > 29) {
                            check = false;
                        }
                    }
                } else {
                    if (mm < 0 || mm > 12) {
                        check = false;
                    } else {
                        if (mm == 2) {
                            if (dd < 0 || dd > 28) {
                                check = false;
                            }
                        } else {
                            switch (mm) {
                                case 1:
                                case 3:
                                case 5:
                                case 7:
                                case 8:
                                case 10:
                                case 12:
                                    if (dd < 0 || dd > 31) {
                                        check = false;
                                    }
                                    break;
                                default:
                                    if (dd < 0 || dd > 30) {
                                        check = false;
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return check;
    }

    public List<DeviceDTO> getListDevice(String search, boolean bool) throws Exception {
        List<DeviceDTO> result = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("SELECT DeviceID, Name, Description, Type,Img, BuyDate, WarrantyDate FROM Device WHERE Name like ? and Status = ?");
            pre.setString(1, "%" + search + "%");
            pre.setBoolean(2, bool);
            result = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String deviceID = rs.getString("DeviceID");
                String name = rs.getString("Name");
                String description = rs.getString("Description");
                String type = rs.getString("Type");
                String buyDate = rs.getString("BuyDate");
                String WarrDate = rs.getString("WarrantyDate");
                String img = rs.getString("Img");
                DeviceDTO dto = new DeviceDTO(deviceID, name, description, type, img, buyDate, WarrDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteDeviceByID(String deviceID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Delete Device where DeviceID= ?");
            pre.setString(1, deviceID);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean setStatusDeviceID(boolean bool, String deviceID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update Device set Status = ? where DeviceID = ?");
            pre.setBoolean(1, bool);
            pre.setString(2, deviceID);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean setStatusForRoomByRoomID(boolean bool, String roomID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update Room set Status = ? where RoomID = ?");
            pre.setBoolean(1, bool);
            pre.setString(2, roomID);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean getStatusByRoomID(String roomID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("select Status From Room where RoomID = ?");
            pre.setString(1, roomID);
            rs = pre.executeQuery();
            if (rs.next()) {
                check = rs.getBoolean("Status");
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public String checkLogin(String username, String password) throws Exception {
        String role = "false";
        conn = MyConnection.getConnection();
        String sql = "Select Role From UserApp WHERE Username= ? AND Password = ?";
        pre = conn.prepareStatement(sql);
        pre.setString(1, username);
        pre.setString(2, password);
        rs = pre.executeQuery();
        if (rs.next()) {
            role = rs.getString("Role").trim();
        }
        return role;
    }

    public List<UserAppDTO> getListUserByFullname(String search, String roleSearch) throws Exception {
        List<UserAppDTO> dto = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select Username, Password, Fullname, Role , RoomID From UserApp Where Fullname like ? and Role = ?");
            pre.setString(1, "%" + search + "%");
            pre.setString(2, roleSearch);
            dto = new ArrayList<>();
            rs = pre.executeQuery();
            while (rs.next()) {
                String username = rs.getString("Username").trim();
                String password = rs.getString("Password").trim();
                String fullname = rs.getString("Fullname").trim();
                String role = rs.getString("Role").trim();
                String roomId = rs.getString("RoomID");
                UserAppDTO data = new UserAppDTO(username, password, fullname, role, roomId);
                dto.add(data);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean deleteUserByUsername(String username) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Delete UserApp where Username = ?");
            pre.setString(1, username);
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean updateUserByUserID(UserAppDTO data) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Update UserApp set Fullname = ? , Role = ?, RoomID = ?,UserUpdate = ?,DateUpdate = ? where Username = ?");
            pre.setString(1, data.getFullname());
            pre.setString(2, data.getRole());
            pre.setString(3, data.getRoomID());
            pre.setString(4, data.getUserUpdate());
            pre.setString(5, data.getDateUpdate());
            pre.setString(6, data.getUsername());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertUserIntoDB(UserAppDTO dto) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("INSERT into UserApp(Username,Password,Role,Fullname,UserUpdate,DateUpdate) values(?,?,?,?,?,?)");
            pre.setString(1, dto.getUsername());
            pre.setString(2, dto.getPassword());
            pre.setString(3, dto.getRole());
            pre.setString(4, dto.getFullname());
            pre.setString(5, dto.getUserUpdate());
            pre.setString(6, dto.getDateUpdate());
            check = pre.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean checkUsername(String userID) throws Exception {
        boolean check = false;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select Username from UserApp");
            rs = pre.executeQuery();
            while (rs.next()) {
                if (rs.getString("Username").trim().equals(userID)) {
                    check = true;
                }
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public UserAppDTO getUserByUserID(String userID) throws Exception {
        UserAppDTO dto = null;
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select Password, Fullname, Role , RoomID From UserApp Where Username = ?");
            pre.setString(1, userID);
            rs = pre.executeQuery();
            if (rs.next()) {
                String username = userID;
                String password = rs.getString("Password");
                String Fullname = rs.getString("Fullname");
                String Role = rs.getString("Role");
                String RoomID = rs.getString("RoomID");
                dto = new UserAppDTO(username, password, Fullname, Role, RoomID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<String> getListRoomID(boolean status) throws Exception {
        List<String> list = new ArrayList<>();
        try {
            conn = MyConnection.getConnection();
            pre = conn.prepareStatement("Select RoomID From Room Where status = ?");
            pre.setBoolean(1, status);
            rs = pre.executeQuery();
            while (rs.next()) {
                String roomID = rs.getString("RoomID");
                list.add(roomID);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

}
