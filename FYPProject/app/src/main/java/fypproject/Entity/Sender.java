package fypproject.Entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Sender {
    private int senderId, objStatus, companyId;
    private String name, contactNo, username, passwordSALT, passwordHASH, remarks;
    private Date createDate;
    private ArrayList<Task> taskList;

    public Sender(int senderId, int objStatus, int companyId, String name,
                  String contactNo, String username, String passwordSALT, String passwordHASH,
                  String remarks, Date createDate, ArrayList<Task> taskList) {
        this.senderId = senderId;
        this.objStatus = objStatus;
        this.companyId = companyId;
        this.name = name;
        this.contactNo = contactNo;
        this.username = username;
        this.passwordSALT = passwordSALT;
        this.passwordHASH = passwordHASH;
        this.remarks = remarks;
        this.createDate = createDate;
        this.taskList = taskList;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getObjStatus() {
        return objStatus;
    }

    public void setObjStatus(int objStatus) {
        this.objStatus = objStatus;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
}
