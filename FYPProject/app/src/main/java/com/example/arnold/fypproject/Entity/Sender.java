package com.example.arnold.fypproject.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Sender {
    private int ID, objStatus, companyID;
    private String name, contactNo, username, passwordSALT, passwordHASH, remarks;
    private Timestamp createDate;
    private ArrayList<Task> taskList;

    public Sender(int ID, int objStatus, int companyID, String name, String contactNo,
                  String username, String passwordSALT, String passwordHASH, String remarks,
                  Timestamp createDate) {

        this.ID = ID;
        this.objStatus = objStatus;
        this.companyID = companyID;
        this.name = name;
        this.contactNo = contactNo;
        this.username = username;
        this.passwordSALT = passwordSALT;
        this.passwordHASH = passwordHASH;
        this.remarks = remarks;
        this.createDate = createDate;
    }

    public Sender(int ID, int objStatus, int companyID, String name, String contactNo,
                  String username, String passwordSALT, String passwordHASH, String remarks,
                  Timestamp createDate, ArrayList<Task> taskList) {

        this.ID = ID;
        this.objStatus = objStatus;
        this.companyID = companyID;
        this.name = name;
        this.contactNo = contactNo;
        this.username = username;
        this.passwordSALT = passwordSALT;
        this.passwordHASH = passwordHASH;
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

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
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
}
