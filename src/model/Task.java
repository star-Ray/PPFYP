package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.ItemDAO;
import system.Key;
import system.Value;

public class Task {
	private long taskId;
	private String receiverName;
	private String receiverContact;
	private Date planStartTime;
	private Date planEndTime;
	private Date actualStartTime;
	private Date actualEndTime;
	private String startLocation;
	private String endLocation;
	
	private String signature;
	private String verifyPass;
	
	private String taskStatus;
	private long objStatus;
	private Date createDate;
	private String remark;
	
	private Officer officer;
	private Courier courier;
	private Sender sender;
	
	private Set<Item> items;
	
	public Task(){}

	public Task(Officer officer, Courier courier, Sender sender, String receiverName, String receiverContact,Date planStartTime, Date planEndTime,
				Date actualStartTime, Date actualEndTime, String startLocation, String endLocation, String verifyPass, String taskStatus) {
		super();
		this.setReceiverName(receiverName);
		this.setReceiverContact(receiverContact);
		this.setplanStartTime(planStartTime);
		this.setplanEndTime(planEndTime);
		this.setactualEndTime(actualEndTime);
		this.setactualStartTime(actualStartTime);
		this.setStartLocation(startLocation);
		this.setEndLocation(endLocation);
		
		this.setOfficer(officer);
		this.setCourier(courier);
		this.setSender(sender);
		
		this.setSignature("");
		this.setVerifyPass(verifyPass);
		this.setTaskStatus(taskStatus);
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
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

	public Date getplanStartTime() {
		return planStartTime;
	}

	public void setplanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}

	public Date getplanEndTime() {
		return planEndTime;
	}

	public void setplanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}

	public Date getactualStartTime() {
		return actualStartTime;
	}

	public void setactualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	public Date getactualEndTime() {
		return actualEndTime;
	}

	public void setactualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
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

	public String getVerifyPass() {
		return verifyPass;
	}

	public void setVerifyPass(String verifyPass) {
		this.verifyPass = verifyPass;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	public Courier getCourier() {
		return courier;
	}

	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	public long getObjStatus() {
		return objStatus;
	}

	public void setObjStatus(long objStatus) {
		this.objStatus = objStatus;
	}
	
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		
		
		JSONArray itemArr = new JSONArray();
		for(Item k : ItemDAO.getItemsByTask(this)){
			itemArr.add(k.toJson());
		}
		returnJson.put(Key.ITEMS, itemArr);
		
		return returnJson;
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		//all have
		returnJson.put(Key.SENDER, this.sender.toJson());
		returnJson.put(Key.COURIER, this.courier.toJson());
		returnJson.put(Key.OFFICER, this.officer.toJson());
		
		return returnJson;
	}
	
	public JSONObject toJsonSender(){
		JSONObject returnJson = new JSONObject();
		//only have sender
		returnJson.put(Key.SENDER, this.sender.toJson());
		
		return returnJson;
	}
	
	public JSONObject toJsonOfficer(){
		JSONObject returnJson = new JSONObject();
		//sender and officer
		returnJson.put(Key.SENDER, this.sender.toJson());
		returnJson.put(Key.OFFICER, this.officer.toJson());
		
		return returnJson;
	}
	
	//TODO possibly add in the function that courier can also create task.??? then the business flow would change.
	
}
