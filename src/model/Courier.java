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
	private String username;
	private String passwordSalt;
	private String passwordHash;
	private String contactNo;
	private double curLat;
	private double curLon;

	private long objStatus;
	private Date createDate;
	private String remark;
	
	private Company company;
	private Set<Task> tasks;
	
	public Courier(){}
	
	public Courier(Company company, String name, String username, String passwordSalt, String passwordHash, String contactNo) {
		super();
		this.setCompany(company);
		this.name = name;
		this.username = username;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.contactNo = contactNo;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}
	
	public Courier(Company company, String name, String username, String passwordSalt, String passwordHash, String contactNo, Double curLat, Double curLon) {
		super();
		this.setCompany(company);
		this.name = name;
		this.username = username;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.contactNo = contactNo;
		this.setCurLat(curLat);
		this.setCurLon(curLon);
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	/**
	 * @return the courierId
	 */
	public long getCourierId() {
		return courierId;
	}

	/**
	 * @param courierId the courierId to set
	 */
	public void setCourierId(long courierId) {
		this.courierId = courierId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the passwordSalt
	 */
	public String getPasswordSalt() {
		return passwordSalt;
	}

	/**
	 * @param passwordSalt the passwordSalt to set
	 */
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	/**
	 * @return the passwordHash
	 */
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * @param passwordHash the passwordHash to set
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the objStatus
	 */
	public long getObjStatus() {
		return objStatus;
	}

	/**
	 * @param objStatus the objStatus to set
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
	 * @param createDate the createDate to set
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
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the tasks
	 */
	public Set<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	/**
	 * @return the curLat
	 */
	public double getCurLat() {
		return curLat;
	}

	/**
	 * @param curLat the curLat to set
	 */
	public void setCurLat(double curLat) {
		this.curLat = curLat;
	}

	/**
	 * @return the curLon
	 */
	public double getCurLon() {
		return curLon;
	}

	/**
	 * @param curLon the curLon to set
	 */
	public void setCurLon(double curLon) {
		this.curLon = curLon;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.COURIERID, this.courierId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.USERNAME, this.username);
		returnJson.put(Key.CONTACTNO, this.contactNo);
		returnJson.put(Key.CURLAT, this.curLat);
		returnJson.put(Key.CURLON, this.curLon);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		returnJson.put(Key.COMPANY, this.company.toJson());
		
		return returnJson;
	}
	
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.COURIERID, this.courierId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.USERNAME, this.username);
		returnJson.put(Key.CONTACTNO, this.contactNo);
		returnJson.put(Key.CURLAT, this.curLat);
		returnJson.put(Key.CURLON, this.curLon);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		returnJson.put(Key.COMPANY, this.company.toJson());
		
		JSONArray taskJArr = new JSONArray();
		for(Task t : TaskDAO.getTasksByCourier(this)){
			taskJArr.add(t.toJson());
		}
		returnJson.put(Key.TASKS, taskJArr);
		
		return returnJson;
	}


}
