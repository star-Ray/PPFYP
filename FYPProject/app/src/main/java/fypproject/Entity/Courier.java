package fypproject.Entity;

import java.sql.Date;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Courier {
    private int courierId, objStatus;
    private String name, username, passwordSALT, passwordHASH, contactNo, realTimeLocation, remark, company;
    private Date createDate;
//    private ArrayList<Task> taskList;

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRealTimeLocation() {
        return realTimeLocation;
    }

    public void setRealTimeLocation(String realTimeLocation) {
        this.realTimeLocation = realTimeLocation;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Courier(int courierId, int objStatus, String name, String username, String passwordSALT, String passwordHASH, String contactNo, String realTimeLocation, String remark, String company, Date createDate) {

        this.courierId = courierId;
        this.objStatus = objStatus;
        this.name = name;
        this.username = username;
        this.passwordSALT = passwordSALT;
        this.passwordHASH = passwordHASH;
        this.contactNo = contactNo;
        this.realTimeLocation = realTimeLocation;
        this.remark = remark;
        this.company = company;
        this.createDate = createDate;
    }
}