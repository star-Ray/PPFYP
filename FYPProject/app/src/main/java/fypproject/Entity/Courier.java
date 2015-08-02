package fypproject.Entity;

import java.sql.Timestamp;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Courier {
    private int ID, objStatus, companyID;
    private String name, username, passwordSALT, passwordHASH, contactNo, realtimeLocation, remarks;
    private Timestamp createDate;





    public Courier(int ID, int objStatus, String name, String username, String passwordHASH, String passwordSALT,
                   String contactNo, String realtimeLocation, String remarks, Timestamp createDate, int companyID) {

        this.ID = ID;
        this.companyID = companyID;
        this.objStatus = objStatus;
        this.username = username;
        this.passwordHASH = passwordHASH;
        this.passwordSALT = passwordSALT;
        this.name = name;
        this.contactNo = contactNo;
        this.realtimeLocation = realtimeLocation;
        this.remarks = remarks;
        this.createDate = createDate;
    }

    public String getPasswordSALT() {
        return passwordSALT;
    }

    public void setPasswordSALT(String passwordSALT) {
        this.passwordSALT = passwordSALT;
    }

    public String getPasswordHASH() {
        return passwordHASH;
    }

    public void setPasswordHASH(String passwordHASH) {
        this.passwordHASH = passwordHASH;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }
}
