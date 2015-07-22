package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.OfficerDAO;
import persistence.TaskDAO;
import system.Config;
import system.Key;
import system.Value;

public class Courier {
	private long courierId;
	private String name;
	private String contactNo;
	private String realTimeLocation;

	private long objStatus;
	private Date createDate;
	private String remark;
	
	private Set<Task> tasks;
	
	public Courier(){}

	public Courier(String name, String contactNo, String realTimeLocation) {
		super();
		this.name = name;
		this.contactNo = contactNo;
		this.setRealTimeLocation(realTimeLocation);
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	public long getCourierId() {
		return courierId;
	}

	public void setCourierId(long courierId) {
		this.courierId = courierId;
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
	
	public String getRealTimeLocation() {
		return realTimeLocation;
	}

	public void setRealTimeLocation(String realTimeLocation) {
		this.realTimeLocation = realTimeLocation;
	}

	public long getObjStatus() {
		return objStatus;
	}

	public void setObjStatus(long objStatus) {
		this.objStatus = objStatus;
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

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.COURIERID, this.courierId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.CONTACTNO, this.contactNo);
		returnJson.put(Key.REALTIMELOCATION, this.realTimeLocation);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}
	
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.COURIERID, this.courierId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.CONTACTNO, this.contactNo);
		returnJson.put(Key.REALTIMELOCATION, this.realTimeLocation);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray taskJArr = new JSONArray();
		for(Task t : TaskDAO.getTasksByCourier(this)){
			taskJArr.add(t.toJson());
		}
		returnJson.put(Key.TASKS, taskJArr);
		
		return returnJson;
	}

}
