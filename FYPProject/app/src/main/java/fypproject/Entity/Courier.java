package fypproject.Entity;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Courier {
    private int ID, objStatus, companyID;
    private String name, username, passwordSALT, passwordHASH, contactNo, realTimeLocation, remarks;
    private Date createDate;
    private ArrayList<Task> taskList;

    public Courier(int ID, int objStatus, String name, String username, String passwordHASH, String passwordSALT,
                   String contactNo, String realTimeLocation, String remarks, Date createDate, int companyID, ArrayList<Task> taskList) {

        this.ID = ID;
        this.companyID = companyID;
        this.objStatus = objStatus;
        this.username = username;
        this.passwordHASH = passwordHASH;
        this.passwordSALT = passwordSALT;
        this.name = name;
        this.contactNo = contactNo;
        this.realTimeLocation = realTimeLocation;
        this.remarks = remarks;
        this.createDate = createDate;
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
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

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
