package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONObject;

import system.Value;

public class Sender {
	private long senderId;
	private String name;
	private String contactNo;
	private String username;
	private String passwordSalt;
	private String passwordHash;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	private Company company;
	private Set<Task> tasks;
	
	public Sender(){}

	public Sender(Company company, String name, String contactNo, String username, String passwordSalt,String passwordHash) {
		super();
		this.setCompany(company);
		this.name = name;
		this.contactNo = contactNo;
		this.setUsername(username);
		this.setPasswordHash(passwordHash);
		this.setPasswordSalt(passwordSalt);
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
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

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		return returnJson;
	}

}
