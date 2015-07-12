package com.example.arnold.fypproject.Entity;

import java.sql.Timestamp;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Courier {
    private int ID, objStatus;
    private String name, contactNo, realtimeLocation, remarks;
    private Timestamp createDate;

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Courier(int ID, int objStatus, String name, String contactNo, String realtimeLocation, String remarks, Timestamp createDate) {

        this.ID = ID;
        this.objStatus = objStatus;
        this.name = name;
        this.contactNo = contactNo;
        this.realtimeLocation = realtimeLocation;
        this.remarks = remarks;
        this.createDate = createDate;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getObjStatus() {
        return objStatus;
    }

    public void setObjStatus(int objStatus) {
        this.objStatus = objStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRealtimeLocation() {
        return realtimeLocation;
    }

    public void setRealtimeLocation(String realtimeLocation) {
        this.realtimeLocation = realtimeLocation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
