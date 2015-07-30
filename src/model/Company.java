package model;

import java.util.Date;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import persistence.CourierDAO;
import persistence.OfficerDAO;
import persistence.SenderDAO;
import persistence.TaskDAO;
import system.Config;
import system.Key;
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
	private Set<Courier> couriers;
	
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

	/**
	 * @return the couriers
	 */
	public Set<Courier> getCouriers() {
		return couriers;
	}

	/**
	 * @param couriers the couriers to set
	 */
	public void setCouriers(Set<Courier> couriers) {
		this.couriers = couriers;
	}
	
	public JSONObject toJson(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.COMPANYID, this.companyId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.USERNAME, this.username);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		return returnJson;
	}

	public JSONObject toJsonStrong(){
		JSONObject returnJson = new JSONObject();
		
		returnJson.put(Key.COMPANYID, this.companyId);
		returnJson.put(Key.NAME, this.name);
		returnJson.put(Key.USERNAME, this.username);
		
		returnJson.put(Key.OBJSTATUS, this.objStatus);
		returnJson.put(Key.CREATEDATE, Config.SDF.format(this.createDate));
		returnJson.put(Key.REMARK, this.remark);
		
		JSONArray officerJArr = new JSONArray();
		for(Officer o : OfficerDAO.getOfficersByCompany(this)){
			officerJArr.add(o.toJson());
		}
		returnJson.put(Key.OFFICERS, officerJArr);
		
		JSONArray senderJArr = new JSONArray();
		for(Sender s : SenderDAO.getSendersByCompany(this)){
			senderJArr.add(s.toJson());
		}
		returnJson.put(Key.SENDERS, senderJArr);
		
		JSONArray courierJArr = new JSONArray();
		for(Courier c : CourierDAO.getCouriersByCompany(this)){
			courierJArr.add(c.toJson());
		}
		returnJson.put(Key.SENDERS, courierJArr);
		
		return returnJson;
	}

}
