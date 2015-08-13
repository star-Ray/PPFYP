package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.ItemDAO;
import system.Config;
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
	private String startAddress;
	private double startLon;
	private double startLat;
	private long startPostalCode;
	private String endAddress;
	private double endLon;
	private double endLat;
	private long endPostalCode;

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

	public Task() {}

	public Task(Officer officer, Courier courier, Sender sender,
			String receiverName, String receiverContact, Date planStartTime,
			Date planEndTime, Date actualStartTime, Date actualEndTime,
			String startAddress, double startLon, double startLat,long startPostalCode,
			String endAddress, double endLon, double endLat, long endPostalCode,
			String verifyPass, String taskStatus) {
		super();
		this.setReceiverName(receiverName);
		this.setReceiverContact(receiverContact);
		this.setPlanStartTime(planStartTime);
		this.setPlanEndTime(planEndTime);
		this.setActualEndTime(actualEndTime);
		this.setActualStartTime(actualStartTime);
		this.setStartAddress(startAddress);
		this.setStartPostalCode(startPostalCode);
		this.setEndAddress(endAddress);
		this.setEndPostalCode(endPostalCode);

		this.setOfficer(officer);
		this.setCourier(courier);
		this.setSender(sender);

		this.setSignature("");
		this.setVerifyPass(verifyPass);
		this.setTaskStatus(taskStatus);
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the taskId
	 */
	public long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the receiverName
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * @param receiverName
	 *            the receiverName to set
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * @return the receiverContact
	 */
	public String getReceiverContact() {
		return receiverContact;
	}

	/**
	 * @param receiverContact
	 *            the receiverContact to set
	 */
	public void setReceiverContact(String receiverContact) {
		this.receiverContact = receiverContact;
	}

	/**
	 * @return the planStartTime
	 */
	public Date getPlanStartTime() {
		return planStartTime;
	}

	/**
	 * @param planStartTime
	 *            the planStartTime to set
	 */
	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}

	/**
	 * @return the planEndTime
	 */
	public Date getPlanEndTime() {
		return planEndTime;
	}

	/**
	 * @param planEndTime
	 *            the planEndTime to set
	 */
	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}

	/**
	 * @return the actualStartTime
	 */
	public Date getActualStartTime() {
		return actualStartTime;
	}

	/**
	 * @param actualStartTime
	 *            the actualStartTime to set
	 */
	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	/**
	 * @return the actualEndTime
	 */
	public Date getActualEndTime() {
		return actualEndTime;
	}

	/**
	 * @param actualEndTime
	 *            the actualEndTime to set
	 */
	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}

	/**
	 * @return the startAddress
	 */
	public String getStartAddress() {
		return startAddress;
	}

	/**
	 * @param startAddress
	 *            the startAddress to set
	 */
	public void setStartAddress(String startAddress) {
		this.startAddress = startAddress;
	}

	/**
	 * @return the startLon
	 */
	public double getStartLon() {
		return startLon;
	}

	/**
	 * @param startLon
	 *            the startLon to set
	 */
	public void setStartLon(double startLon) {
		this.startLon = startLon;
	}

	/**
	 * @return the startLat
	 */
	public double getStartLat() {
		return startLat;
	}

	/**
	 * @param startLat
	 *            the startLat to set
	 */
	public void setStartLat(double startLat) {
		this.startLat = startLat;
	}

	/**
	 * @return the startPostalCode
	 */
	public long getStartPostalCode() {
		return startPostalCode;
	}

	/**
	 * @param startPostalCode
	 *            the startPostalCode to set
	 */
	public void setStartPostalCode(long startPostalCode) {
		this.startPostalCode = startPostalCode;
	}

	/**
	 * @return the endAddress
	 */
	public String getEndAddress() {
		return endAddress;
	}

	/**
	 * @param endAddress
	 *            the endAddress to set
	 */
	public void setEndAddress(String endAddress) {
		this.endAddress = endAddress;
	}

	/**
	 * @return the endLon
	 */
	public double getEndLon() {
		return endLon;
	}

	/**
	 * @param endLon
	 *            the endLon to set
	 */
	public void setEndLon(double endLon) {
		this.endLon = endLon;
	}

	/**
	 * @return the endLat
	 */
	public double getEndLat() {
		return endLat;
	}

	/**
	 * @param endLat
	 *            the endLat to set
	 */
	public void setEndLat(double endLat) {
		this.endLat = endLat;
	}

	/**
	 * @return the endPostalCode
	 */
	public long getEndPostalCode() {
		return endPostalCode;
	}

	/**
	 * @param endPostalCode
	 *            the endPostalCode to set
	 */
	public void setEndPostalCode(long endPostalCode) {
		this.endPostalCode = endPostalCode;
	}

	/**
	 * @return the signature
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * @param signature
	 *            the signature to set
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}

	/**
	 * @return the verifyPass
	 */
	public String getVerifyPass() {
		return verifyPass;
	}

	/**
	 * @param verifyPass
	 *            the verifyPass to set
	 */
	public void setVerifyPass(String verifyPass) {
		this.verifyPass = verifyPass;
	}

	/**
	 * @return the taskStatus
	 */
	public String getTaskStatus() {
		return taskStatus;
	}

	/**
	 * @param taskStatus
	 *            the taskStatus to set
	 */
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	/**
	 * @return the objStatus
	 */
	public long getObjStatus() {
		return objStatus;
	}

	/**
	 * @param objStatus
	 *            the objStatus to set
	 */
	public void setObjStatus(long objStatus) {
		this.objStatus = objStatus;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the officer
	 */
	public Officer getOfficer() {
		return officer;
	}

	/**
	 * @param officer
	 *            the officer to set
	 */
	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	/**
	 * @return the courier
	 */
	public Courier getCourier() {
		return courier;
	}

	/**
	 * @param courier
	 *            the courier to set
	 */
	public void setCourier(Courier courier) {
		this.courier = courier;
	}

	/**
	 * @return the sender
	 */
	public Sender getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(Sender sender) {
		this.sender = sender;
	}

	/**
	 * @return the items
	 */
	public Set<Item> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(Set<Item> items) {
		this.items = items;
	}

	// all have
	public JSONObject toJson() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TASKID, this.taskId);
		returnJson.put(Key.RECEIVERNAME, this.receiverName);
		returnJson.put(Key.RECEIVERCONTACT, this.receiverContact);
		returnJson.put(Key.PLANSTARTTIME, Config.SDF.format(this.planStartTime));
		returnJson.put(Key.PLANENDTIME, Config.SDF.format(this.planEndTime));
		returnJson.put(Key.ACTUALSTARTTIME,Config.SDF.format(this.actualStartTime));
		returnJson.put(Key.ACTUALENDTIME, Config.SDF.format(this.actualEndTime));
		returnJson.put(Key.STARTADDRESS, this.startAddress);
		returnJson.put(Key.STARTLON, this.startLon);
		returnJson.put(Key.STARTLAT, this.startLat);
		returnJson.put(Key.STARTPOSTALCODE, this.startPostalCode);
		returnJson.put(Key.ENDADDRESS, this.endAddress);
		returnJson.put(Key.ENDLON, this.endLon);
		returnJson.put(Key.ENDLAT, this.endLat);
		returnJson.put(Key.ENDPOSTALCODE, this.endPostalCode);

		returnJson.put(Key.SIGNATURE, this.signature);
		returnJson.put(Key.VERIFYPASS, this.planStartTime);
		returnJson.put(Key.TASKSTATUS, this.taskStatus);
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		returnJson.put(Key.SENDER, this.sender.toJson());
		returnJson.put(Key.COURIER, this.courier.toJson());
		returnJson.put(Key.OFFICER, this.officer.toJson());

		return returnJson;
	}

	/*
	 * //only have sender public JSONObject toJsonSender(){ JSONObject
	 * returnJson = new JSONObject();
	 * 
	 * returnJson.put(Key.SENDER, this.sender.toJson());
	 * 
	 * return returnJson; }
	 * 
	 * //sender and officer public JSONObject toJsonOfficer(){ JSONObject
	 * returnJson = new JSONObject();
	 * 
	 * returnJson.put(Key.SENDER, this.sender.toJson());
	 * returnJson.put(Key.OFFICER, this.officer.toJson());
	 * 
	 * return returnJson; }
	 */

	public JSONObject toJsonStrong() {
		JSONObject returnJson = new JSONObject();

		returnJson.put(Key.TASKID, this.taskId);
		returnJson.put(Key.RECEIVERNAME, this.receiverName);
		returnJson.put(Key.RECEIVERCONTACT, this.receiverContact);
		returnJson.put(Key.PLANSTARTTIME, Config.SDF.format(this.planStartTime));
		returnJson.put(Key.PLANENDTIME, Config.SDF.format(this.planEndTime));
		returnJson.put(Key.ACTUALSTARTTIME,Config.SDF.format(this.actualStartTime));
		returnJson.put(Key.ACTUALENDTIME, Config.SDF.format(this.actualEndTime));
		returnJson.put(Key.STARTADDRESS, this.startAddress);
		returnJson.put(Key.STARTLON, this.startLon);
		returnJson.put(Key.STARTLAT, this.startLat);
		returnJson.put(Key.STARTPOSTALCODE, this.startPostalCode);
		returnJson.put(Key.ENDADDRESS, this.endAddress);
		returnJson.put(Key.ENDLON, this.endLon);
		returnJson.put(Key.ENDLAT, this.endLat);
		returnJson.put(Key.ENDPOSTALCODE, this.endPostalCode);

		returnJson.put(Key.SIGNATURE, this.signature);
		returnJson.put(Key.VERIFYPASS, this.verifyPass);
		returnJson.put(Key.TASKSTATUS, this.taskStatus);
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);

		returnJson.put(Key.SENDER, this.sender.toJson());
		returnJson.put(Key.COURIER, this.courier.toJson());
		returnJson.put(Key.OFFICER, this.officer.toJson());

		JSONArray itemArr = new JSONArray();
		for (Item k : ItemDAO.getItemsByTask(this)) {
			itemArr.add(k.toJson());
		}
		returnJson.put(Key.ITEMS, itemArr);

		return returnJson;
	}
	// TODO possibly add in the function that courier can also create task.???
	// then the business flow would change.

}
