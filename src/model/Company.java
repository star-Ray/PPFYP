package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONObject;

import system.Value;

public class Company {
	private long companyId;
	private String name;
	private String username;
	private String passwordSalt;
	private String passwordHash;
	
	private long objStatus;
	private Date createDate;
	private String remark;
	
	private SuperAdmin superAdmin;
	private Set<Officer> officers;
	private Set<Sender>	senders;
	
	public Company(){}

	public Company(String name, String username, String passwordSalt, String passwordHash) {
		super();
		this.name = name;
		this.username = username;
		this.passwordSalt = passwordSalt;
		this.passwordHash = passwordHash;
		this.setObjStatus(Value.ACTIVED);
		this.setCreateDate(new Date());
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
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

	public Set<Officer> getOfficers() {
		return officers;
	}

	public void setOfficers(Set<Officer> officers) {
		this.officers = officers;
	}

	public Set<Sender> getSenders() {
		return senders;
	}

	public SuperAdmin getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(SuperAdmin superAdmin) {
		this.superAdmin = superAdmin;
	}
	
	public void setSenders(Set<Sender> senders) {
		this.senders = senders;
	}
	
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		return returnJson;
	}


}
