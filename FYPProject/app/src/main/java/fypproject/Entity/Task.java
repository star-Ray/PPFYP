package fypproject.Entity;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Arnold on 7/9/2015.
 */
public class Task {
    private int ID, officerID, courierID, senderID;
    private String receiverName, receiverContact, startLocation, endLocation, signature, oneTimePass, taskStatus, remarks;
    private Timestamp planStartTime, planEndTime, actualStartTime, actualEndTime, createDate;
    private ArrayList<Item> itemList;

    public Task(int ID, int officerID, int courierID, int senderID, String receiverName,
                String receiverContact, String startLocation, String endLocation, String signature,
                String oneTimePass, String taskStatus, String remarks, Timestamp planStartTime,
                Timestamp planEndTime, Timestamp actualStartTime, Timestamp actualEndTime,
                Timestamp createDate, ArrayList<Item> itemList) {
        this.ID = ID;
        this.officerID = officerID;
        this.courierID = courierID;
        this.senderID = senderID;
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

    public Task(int ID, int officerID, int courierID, int senderID,
                String receiverName, String receiverContact, String startLocation,
                String endLocation, String signature, String oneTimePass, String taskStatus,
                String remarks, Timestamp planStartTime, Timestamp planEndTime, Timestamp actualStartTime,
                Timestamp actualEndTime, Timestamp createDate) {
        this.ID = ID;
        this.officerID = officerID;
        this.courierID = courierID;
        this.senderID = senderID;
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
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getOfficerID() {
        return officerID;
    }

    public void setOfficerID(int officerID) {
        this.officerID = officerID;
    }

    public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
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

    public Timestamp getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Timestamp planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Timestamp getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Timestamp planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Timestamp getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(Timestamp actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Timestamp getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(Timestamp actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

}
