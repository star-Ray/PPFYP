package fypproject.Entity;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Task {
    private int taskId, officerId, courierId, senderId;
    private String receiverName, receiverContact, startLocation, endLocation, signature, oneTimePass, taskStatus, remarks;
    private Date planStartTime, planEndTime, actualStartTime, actualEndTime, createDate;
    private ArrayList<Item> itemList;

    public Task(int taskId, int officerId, int courierId, int senderId, String receiverName, String receiverContact,
                String startLocation, String endLocation, String signature, String oneTimePass, String taskStatus,
                String remarks, Date planStartTime, Date planEndTime, Date actualStartTime, Date actualEndTime,
                Date createDate, ArrayList<Item> itemList) {
        this.taskId = taskId;
        this.officerId = officerId;
        this.courierId = courierId;
        this.senderId = senderId;
        this.receiverName = receiverName;
        this.receiverContact = receiverContact;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.signature = signature;
        this.oneTimePass = oneTimePass;
        this.taskStatus = taskStatus;
        this.remarks = remarks;
        this.planStartTime = planStartTime;
        this.planEndTime = planEndTime;
        this.actualStartTime = actualStartTime;
        this.actualEndTime = actualEndTime;
        this.createDate = createDate;
        this.itemList = itemList;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getOfficerId() {
        return officerId;
    }

    public void setOfficerId(int officerId) {
        this.officerId = officerId;
    }

    public int getCourierId() {
        return courierId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverContact() {
        return receiverContact;
    }

    public void setReceiverContact(String receiverContact) {
        this.receiverContact = receiverContact;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getOneTimePass() {
        return oneTimePass;
    }

    public void setOneTimePass(String oneTimePass) {
        this.oneTimePass = oneTimePass;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }
}
