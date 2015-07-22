package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.TaskDAO;
import system.Config;
import system.Key;
import system.Value;

public class Officer {
	private long officerId;
	private String name;
	private String username;
	private String passwordSalt;
	private String passwordHash;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	private Company company;
	private Set<Task> tasks;
	
	public Officer(){}

	public Officer(Company company, String name, String username, String passwordSalt, String passwordHash) {
		super();
		this.setCompany(company);
		this.name = name;
		this.username = username;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	public long getOfficerId() {
		return officerId;
	}

	public void setOfficerId(long officerId) {
		this.officerId = officerId;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.OFFICERID, this.officerId);
		returnJson.put(Key.NAME,this.name);
		returnJson.put(Key.USERNAME,this.username);
		returnJson.put(Key.NAME,this.name);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		returnJson.put(Key.COMPANY, this.company.toJson());
		
		return returnJson;
	}
	
	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.OFFICERID, this.officerId);
		returnJson.put(Key.NAME,this.name);
		returnJson.put(Key.USERNAME,this.username);
		returnJson.put(Key.NAME,this.name);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		returnJson.put(Key.COMPANY, this.company.toJson());
		
		JSONArray taskJArr = new JSONArray();
		for(Task t : TaskDAO.getTasksByOfficer(this)){
			taskJArr.add(t.toJson());
		}
		returnJson.put(Key.TASKS, taskJArr);
		
		return returnJson;
	}

}
